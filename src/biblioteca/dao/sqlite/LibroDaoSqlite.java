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
        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return libro;
    }

    @Override
    public void create(Libro libro) {
        try {
            Statement statement = this.connection.createStatement();

            // Inserta el material.
            String insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", libro.getTitulo(), libro.getEditorial());
            statement.executeUpdate(insertCmd);

            /*// Obtiene el identificador del material insertado.
            String getLastKeyQuery = "SELECT max(mid) AS mid FROM materiales";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                b.setMid(rs.getInt("mid"));
            }*/

            // Inserta el libro.
            //String cid = b.hasCd() ? b.getCd().getCid().toString() : "NULL";
            String cid = libro.hasCd() ? libro.getCd().getId().toString() : "NULL";
            //insertCmd = String.format("INSERT INTO libros (mid, cid, isbn, autor) VALUES (%d, %s, '%s', '%s')", b.getMid(), cid, b.getIsbn(), b.getAutor());
            insertCmd = String.format("INSERT INTO libros (mid, cid, isbn, autor) VALUES ((SELECT max(mid) AS mid FROM materiales), %s, '%s', '%s')", cid, libro.getIsbn(), libro.getAutor());
            statement.executeUpdate(insertCmd);
            
            // Se obtiene el identificador del libro insertado.
            String getLastKeyQuery = "SELECT max(lid) AS lid FROM libros";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                libro.setId(rs.getInt("lid"));
            }

            // Guarda las copias del libro, si tiene.
            if (libro.hasCopias()) {
                for (Copia ejemplar : libro.getCopias()) {
                    //insertCmd = String.format("INSERT INTO ejemplares (mid, numero) VALUES (%d, %d)", b.getMid(), ejemplar.getNumero());
                    insertCmd = String.format("INSERT INTO ejemplares (mid, numero) VALUES ((SELECT mid FROM libros WHERE lid = %d), %d)", libro.getId(), ejemplar.getNumero());
                    statement.executeUpdate(insertCmd);

                    // Obtiene el identificador de la copia almacenada.
                    getLastKeyQuery = "SELECT max(eid) AS eid FROM ejemplares";
                    rs = statement.executeQuery(getLastKeyQuery);
                    while (rs.next()) {
                        ejemplar.setEid(rs.getInt("eid"));
                    }
                }
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
    }

    /**
     * @todo Utilizar método retrieve(int lid)
     * @param libro
     * @return
     */
    @Override
    public Libro retrieve(Libro libro) {
        try {
            Statement statement = this.connection.createStatement();

            // Primero se cargan los datos del libro.
            String query = String.format("SELECT titulo, autor, editorial FROM libros JOIN materiales USING (mid) WHERE isbn = '%s'", libro.getIsbn());
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");

                libro.setTitulo(titulo);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
            }

            // Una vez cargado el libro,
            // se cargan sus ejemplares.
            libro.setCopias(new ArrayList<Copia>());
            query = String.format("SELECT numero FROM libros JOIN ejemplares USING (mid) WHERE isbn = '%s'", libro.getIsbn());
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int numero = Integer.parseInt(rs.getString("numero"));
                libro.addCopia(new Copia(numero));
            }

        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return libro;
    }

    @Override
    public Libro retrieve(int lid) {
        Libro libro = new Libro();
        //Cd cd = new Cd();
        //book.setLid(lid);
        libro.setId(lid);

        try {
            Statement statement = this.connection.createStatement();

            // Primero se cargan los datos del libro.
            String query = String.format("SELECT mid, titulo, autor, editorial, isbn FROM libros JOIN materiales USING (mid) WHERE lid = %d", lid);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //book.setMid(Integer.parseInt(rs.getString("mid")));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setIsbn(rs.getString("isbn"));
            }

            // Se cargan las copias del libro.
            //book.setCopias(new ArrayList<Copia>());
            query = String.format("SELECT numero FROM libros JOIN ejemplares USING (mid) WHERE lid = %d", lid);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int numero = Integer.parseInt(rs.getString("numero"));
                libro.addCopia(new Copia(numero));
            }

            // Se carga el CD asociado al libro, si existe.
            // @todo Usar método "retrieve" del CD para cargar datos y ejemplares.
            // @todo Limpiar SELECT.
            query = String.format("SELECT cid, mid, titulo, editorial FROM materiales JOIN (SELECT cds.* FROM libros JOIN cds USING (cid) WHERE lid = %d) USING (mid)", lid);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                //book.setCd(new Cd());
                /*cd.setCid(rs.getInt("cid"));
                cd.setMid(rs.getInt("mid"));
                cd.setTitulo(rs.getString("titulo"));
                cd.setEditorial(rs.getString("editorial"));*/
                /*book.getCd().setCid(rs.getInt("cid"));
                book.getCd().setMid(rs.getInt("mid"));
                book.getCd().setTitulo(rs.getString("titulo"));
                book.getCd().setEditorial(rs.getString("editorial"));*/

                //book.setCd(cd);
                libro.setCd(DaoFactory.getCdDao().retrieve(rs.getInt("cid")));
            }

        } catch (SQLException err) {
            System.err.println(err.toString());
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
            //query = String.format("DELETE FROM materiales WHERE mid = %d", book.getMid());
            query = String.format("DELETE FROM materiales WHERE mid = (SELECT mid FROM libros WHERE lid = %d)", libro.getId());
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
                //Libro libro = this.retrieve(Integer.parseInt(rs.getString("lid")));
                books.add(this.retrieve(rs.getInt("lid")));
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return books;
    }
}
