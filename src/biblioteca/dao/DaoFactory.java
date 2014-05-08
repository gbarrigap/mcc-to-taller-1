/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao;

import biblioteca.dao.sqlite.CdDaoSqlite;
import biblioteca.dao.sqlite.LibroDaoSqlite;
import biblioteca.dao.sqlite.RevistaDaoSqlite;
import java.sql.*;

/**
 *
 * @author guillermo
 */
public class DaoFactory {

    private static Connection getConnectionDao() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException err) {
            System.err.println(err.toString());
        }

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
        } catch (SQLException err) {
            System.err.println(err.toString());
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
