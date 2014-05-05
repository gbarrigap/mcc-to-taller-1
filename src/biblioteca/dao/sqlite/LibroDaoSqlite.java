/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.sqlite;

import biblioteca.dao.LibroDao;
import biblioteca.domain.Cd;
import biblioteca.domain.Ejemplar;
import biblioteca.domain.Libro;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author guillermo
 */
public class LibroDaoSqlite extends LibroDao {

    private Connection connection;

    public LibroDaoSqlite(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Libro getLibroByIsbn(String isbn) {
        Libro book = null;
        
        try {
            Statement statement = this.connection.createStatement();

            // Primero se cargan los datos del libro.
            String query = String.format("SELECT lid FROM libros WHERE isbn = '%s'", isbn);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                book = this.retrieve(Integer.parseInt(rs.getString("lid")));
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return book;
    }

    @Override
    public void create(Libro b) {
        String bookMid = null;
        String cdMid = null;
        String cdCid = null;

        try {
            Statement statement = this.connection.createStatement();

            // Insert material.
            String insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", b.getTitulo(), b.getEditorial());
            statement.executeUpdate(insertCmd);

            // Obtiene el identificador del material insertado.
            String getLastKeyQuery = "SELECT max(mid) AS mid FROM materiales";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                bookMid = rs.getString("mid");
                b.setMid(Integer.parseInt(bookMid));
            }

            // Insert CD?
            if (b.hasCd()) {
                insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", b.getCd().getTitulo(), b.getCd().getEditorial());
                statement.executeUpdate(insertCmd);

                // Obtiene el identificador del material insertado.
                getLastKeyQuery = "SELECT max(mid) FROM materiales";
                rs = statement.executeQuery(getLastKeyQuery);
                while (rs.next()) {
                    //cdMid = rs.getString("mid");
                    cdMid = Integer.toString(rs.getInt(1));
                }

                insertCmd = String.format("INSERT INTO cds (mid) VALUES (%s)", cdMid);
                statement.executeUpdate(insertCmd);

                // Obtiene el identificador del CD insertado.
                getLastKeyQuery = "SELECT max(cid) FROM cds";
                rs = statement.executeQuery(getLastKeyQuery);
                while (rs.next()) {
                    //cdCid = rs.getString("cid");
                    cdCid = Integer.toString(rs.getInt(1));
                }
            }

            // Insert libro.
            String cid = b.hasCd() ? cdCid : "NULL";
            insertCmd = String.format("INSERT INTO libros (mid, cid, isbn, autor) VALUES (%s, %s, '%s', '%s')", bookMid, cid, b.getIsbn(), b.getAutor());
            statement.executeUpdate(insertCmd);
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
    }

    @Override
    public Libro retrieve(Libro book) {
        try {
            Statement statement = this.connection.createStatement();

            // Primero se cargan los datos del libro.
            String query = String.format("SELECT titulo, autor, editorial FROM libros JOIN materiales USING (mid) WHERE isbn = '%s'", book.getIsbn());
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");

                book.setTitulo(titulo);
                book.setAutor(autor);
                book.setEditorial(editorial);
            }

            // Una vez cargado el libro,
            // se cargan sus ejemplares.
            book.setEjemplares(new ArrayList<Ejemplar>());
            query = String.format("SELECT numero FROM libros JOIN ejemplares USING (mid) WHERE isbn = '%s'", book.getIsbn());
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int numero = Integer.parseInt(rs.getString("numero"));
                book.addEjemplar(new Ejemplar(numero));
            }

        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return book;
    }

    @Override
    public Libro retrieve(int lid) {
        Libro book = new Libro();
        Cd cd = new Cd();

        book.setLid(lid);
        try {
            Statement statement = this.connection.createStatement();

            // Primero se cargan los datos del libro.
            String query = String.format("SELECT mid, titulo, autor, editorial, isbn FROM libros JOIN materiales USING (mid) WHERE lid = %d", lid);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                book.setMid(Integer.parseInt(rs.getString("mid")));
                book.setTitulo(rs.getString("titulo"));
                book.setAutor(rs.getString("autor"));
                book.setEditorial(rs.getString("editorial"));
            }

            // Una vez cargado el libro,
            // se cargan sus ejemplares.
            book.setEjemplares(new ArrayList<Ejemplar>());
            query = String.format("SELECT numero FROM libros JOIN ejemplares USING (mid) WHERE lid = %d", lid);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int numero = Integer.parseInt(rs.getString("numero"));
                book.addEjemplar(new Ejemplar(numero));
            }
            
            // Se carga el CD asociado al libro, si existe.
            // @todo Usar método "retrieve" del CD para cargar datos y ejemplares.
            //query = String.format("SELECT cid, c.mid AS mid, titulo, editorial FROM materiales m JOIN (libros l JOIN cds c USING (cid)) ON (m.mid = c.mid) WHERE isbn = '%s'", isbn);
            query = String.format("SELECT cid, mid, titulo, editorial FROM materiales JOIN (SELECT cds.* FROM libros JOIN cds USING (cid) WHERE lid = %d) USING (mid)", lid);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                cd.setCid(Integer.parseInt(rs.getString("cid")));
                cd.setMid(Integer.parseInt(rs.getString("mid")));
                cd.setTitulo(rs.getString("titulo"));
                cd.setEditorial(rs.getString("editorial"));

                book.setCd(cd);
            }

        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return book;
    }

    @Override
    public Libro update(Libro book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Libro book) {
        try {
            // Se fuerza a la base de datos a reconocer las claves foráneas,
            // para eliminar las dependencias en cascada.
            String query = "PRAGMA foreign_keys = ON;";
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
            
            // Luego se elimina el libro.
            query = String.format("DELETE FROM materiales WHERE mid = %d", book.getMid());
            statement.executeUpdate(query);
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
    }

    @Override
    public List<Libro> retrieveAll() {
        List<Libro> books = new ArrayList<>();

        try {
            String query = "SELECT lid FROM libros";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Libro book = getLibroByIsbn(rs.getString("isbn"));
                Libro book = this.retrieve(Integer.parseInt(rs.getString("lid")));
                books.add(book);
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return books;
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}