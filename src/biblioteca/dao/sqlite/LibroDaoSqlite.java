package biblioteca.dao.sqlite;

import biblioteca.dao.DaoFactory;
import biblioteca.dao.LibroDao;
import biblioteca.domain.Copia;
import biblioteca.domain.Libro;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementa el acceso a libros almacenados en una base de datos Sqlite.
 */
public class LibroDaoSqlite implements LibroDao {

    private final Connection connection;

    public LibroDaoSqlite(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Libro getLibroByIsbn(String isbn) {
        Libro libro = null;

        try {
            Statement statement = this.connection.createStatement();

            // Se obtiene el identificador del libro con el isbn recibido.
            String query = String.format("SELECT lid FROM libros WHERE isbn = '%s'", isbn);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                // Se carga el libro con el método tradicional de carga,
                // utilizando el identificador obtenido.
                libro = this.retrieve(Integer.parseInt(rs.getString("lid")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return libro;
    }

    @Override
    public void create(Libro libro) {
        try {
            Statement statement = this.connection.createStatement();

            // Se inserta el material.
            String insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", libro.getTitulo(), libro.getEditorial());
            statement.executeUpdate(insertCmd);

            // Se inserta el libro.
            String cid = libro.hasCd() ? libro.getCd().getId().toString() : "NULL";
            insertCmd = String.format("INSERT INTO libros (mid, cid, isbn, autor) VALUES ((SELECT max(mid) FROM materiales), %s, '%s', '%s')", cid, libro.getIsbn(), libro.getAutor());
            statement.executeUpdate(insertCmd);

            // Se obtiene el identificador del libro insertado.
            String getLastKeyQuery = "SELECT max(lid) AS lid FROM libros";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                libro.setId(rs.getInt("lid"));
            }

            // Se guardan las copias del libro, si tiene.
            if (libro.hasCopias()) {
                for (Copia ejemplar : libro.getCopias()) {
                    insertCmd = String.format("INSERT INTO copias (mid, numero) VALUES ((SELECT mid FROM libros WHERE lid = %d), %d)", libro.getId(), ejemplar.getNumero());
                    statement.executeUpdate(insertCmd);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Libro retrieve(int id) {
        Libro libro = new Libro();
        libro.setId(id);

        try {
            Statement statement = this.connection.createStatement();

            // Primero se cargan los datos del libro.
            String query = String.format("SELECT titulo, autor, editorial, isbn FROM libros JOIN materiales USING (mid) WHERE lid = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setIsbn(rs.getString("isbn"));
            }

            // Se cargan las copias del libro.
            query = String.format("SELECT numero FROM libros JOIN copias USING (mid) WHERE lid = %d", id);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                libro.addCopia(new Copia(rs.getInt("numero")));
            }

            // Se carga el CD asociado al libro, si existe.
            // @todo Revisar consulta SQL; ¿es optimizable?
            //query = String.format("SELECT cid FROM materiales JOIN cds USING (mid) WHERE cid = (SELECT cid FROM libros WHERE lid = %d)), id);
            //query = String.format("SELECT cid FROM materiales JOIN (cds JOIN libros USING (cid)) USING (mid) WHERE lid = %d", id);
            query = String.format("SELECT cid FROM materiales JOIN (SELECT cds.* FROM libros JOIN cds USING (cid) WHERE lid = %d) USING (mid)", id);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                libro.setCd(DaoFactory.getCdDao().retrieve(rs.getInt("cid")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return libro;
    }

    @Override
    public void update(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Libro libro) {
        try {
            // Se fuerza a la base de datos a reconocer las claves foráneas,
            // para eliminar las dependencias en cascada.
            String query = "PRAGMA foreign_keys = ON;";
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);

            // Luego se elimina el libro.
            query = String.format("DELETE FROM materiales WHERE mid = (SELECT mid FROM libros WHERE lid = %d)", libro.getId());
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
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
                books.add(this.retrieve(rs.getInt("lid")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return books;
    }
}
