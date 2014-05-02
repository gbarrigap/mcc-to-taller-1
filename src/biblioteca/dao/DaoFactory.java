/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteca.dao;

import biblioteca.dao.sqlite.LibroDaoSqlite;
import biblioteca.domain.Libro;
import java.sql.*;

/**
 *
 * @author guillermo
 */
public class DaoFactory {
    
    //public static 
    
    public static LibroDAO createLibroDao() {
        
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception err) {
            System.out.println(err.toString());
        }

        Connection connection = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
        } catch (Exception err) {
            System.err.println(err.toString());
        }
        
        return new LibroDaoSqlite(connection);
    }
}
