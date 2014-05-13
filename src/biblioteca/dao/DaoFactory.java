package biblioteca.dao;

import biblioteca.dao.sqlite.CdDaoSqlite;
import biblioteca.dao.sqlite.LibroDaoSqlite;
import biblioteca.dao.sqlite.RevistaDaoSqlite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Fábrica para generar objetos de acceso a datos.
 * 
 * @see biblioteca.dao.Dao
 */
public class DaoFactory {

    private static Connection getConnectionDao() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }

    public static CdDao getCdDao() {
        return new CdDaoSqlite(DaoFactory.getConnectionDao());
    }
    public static LibroDao getLibroDao() {
        return new LibroDaoSqlite(DaoFactory.getConnectionDao());
    }
    
    public static RevistaDao getRevistaDao() {
        return new RevistaDaoSqlite(DaoFactory.getConnectionDao());
    }
}
