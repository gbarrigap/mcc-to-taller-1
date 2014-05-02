/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao.sqlite;

import biblioteca.dao.LibroDAO;
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
        Libro b = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT titulo, autor, editorial FROM libros");

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");

                b = new Libro(isbn, titulo, autor, editorial);
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return b;
    }

    @Override
    public void create(Libro b) {
        try {
            String insertCmd = "INSERT INTO libros (isbn, titulo, autor, editorial) VALUES (%d, '%s', '%s', '%s')";
            insertCmd = String.format(insertCmd, b.getIsbn(), b.getTitulo(), b.getAutor(), b.getEditorial());
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(insertCmd);
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
    }

    @Override
    public Libro retrieve(Libro book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT isbn, titulo, autor, editorial FROM libros");
            while (rs.next()) {
                long isbn = Long.parseLong(rs.getString("isbn"));
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");
                Libro book = new Libro(isbn, titulo, autor, editorial);
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
