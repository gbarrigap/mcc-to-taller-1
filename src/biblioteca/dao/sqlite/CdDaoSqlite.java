/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteca.dao.sqlite;

import biblioteca.dao.CdDao;
import biblioteca.domain.CD;
import biblioteca.domain.Ejemplar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guillermo
 */
public class CdDaoSqlite extends CdDao {

    private Connection connection;

    public CdDaoSqlite(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void create(CD cd) {
        String mid = null;
        
        try {
            Statement statement = this.connection.createStatement();
            
            // Insert material.
            String insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", cd.getTitulo(), cd.getEditorial());
            statement.executeUpdate(insertCmd);
            
            // Obtiene el identificador del material insertado.
            String getLastKeyQuery = "SELECT max(mid) AS mid FROM materiales";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                mid = rs.getString("mid");
            }
            
            // Insert CD.
            insertCmd = String.format("INSERT INTO cds (mid) VALUES (%s)", mid);
            statement.executeUpdate(insertCmd);
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
    }

    @Override
    public CD retrieve(int cid) {
        CD cd = new CD();
        
        try {
            Statement statement = this.connection.createStatement();
            
            // Primero se cargan los datos del CD.
            String query = String.format("SELECT titulo, editorial FROM cds JOIN materiales USING (mid) WHERE cid = %d", cid);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                cd.setTitulo(rs.getString("titulo"));
                cd.setEditorial(rs.getString("editorial"));
            }
            
            // Una vez cargado el CD,
            // se cargan sus ejemplares.
            cd.setEjemplares(new ArrayList<Ejemplar>());
            query = String.format("SELECT numero FROM cds JOIN ejemplares USING (mid) WHERE cid = %d", cid);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int numero = Integer.parseInt(rs.getString("numero"));
                cd.addEjemplar(new Ejemplar(numero));
            }
            
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
        
        return cd;
    }

    @Override
    public CD retrieve(CD t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CD update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CD> retrieveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
