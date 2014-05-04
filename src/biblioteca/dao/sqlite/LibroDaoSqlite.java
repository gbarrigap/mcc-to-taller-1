/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.sqlite;

import biblioteca.dao.LibroDAO;
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
public class LibroDaoSqlite extends LibroDAO {

    private Connection connection;

    public LibroDaoSqlite(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Libro getLibroByIsbn(long isbn) {
        Libro book = new Libro();
        
        try {
            Statement statement = this.connection.createStatement();
            
            // Primero se cargan los datos del libro.
            String query = String.format("SELECT titulo, autor, editorial FROM libros JOIN materiales USING (mid) WHERE isbn = %d", isbn);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");
                
                System.out.println(titulo + ", " + autor + ", " + editorial);
                
                book.setTitulo(titulo);
                book.setAutor(autor);
                book.setEditorial(editorial);
            }
            
            // Una vez cargado el libro,
            // se cargan sus ejemplares.
            book.setEjemplares(new ArrayList<Ejemplar>());
            query = String.format("SELECT numero FROM libros JOIN ejemplares USING (mid) WHERE isbn = %d", isbn);
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
            String cid = b.hasCd() ? cdMid : "NULL";
            insertCmd = String.format("INSERT INTO libros (mid, cid, isbn, autor) VALUES (%s, %s, %d, '%s')", bookMid, cdCid, b.getIsbn(), b.getAutor());
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
            String query = String.format("SELECT titulo, autor, editorial FROM libros JOIN materiales USING (mid) WHERE isbn = %d", book.getIsbn());
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
            query = String.format("SELECT numero FROM libros JOIN ejemplares USING (mid) WHERE isbn = %d", book.getIsbn());
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
        
        try {
            Statement statement = this.connection.createStatement();
            
            // Primero se cargan los datos del libro.
            String query = String.format("SELECT titulo, autor, editorial, isbn FROM libros JOIN materiales USING (mid) WHERE lid = %d", lid);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
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
            
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
        
        return book;
    }

    @Override
    public Libro update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Libro> retrieveAll() {
        List<Libro> books = new ArrayList<>();

        try {
            String query = "SELECT isbn FROM libros";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                long isbn = Long.parseLong(rs.getString("isbn"));
                Libro book = getLibroByIsbn(isbn);
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
