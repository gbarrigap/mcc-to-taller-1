/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteca.dao.sqlite;

import biblioteca.dao.CdDao;
import biblioteca.domain.Cd;
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
public class CdDaoSqlite implements CdDao {

    private Connection connection;

    public CdDaoSqlite(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void create(Cd cd) {
        //String mid = null;
        try {
            Statement statement = this.connection.createStatement();
            
            // Insert material.
            String insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", cd.getTitulo(), cd.getEditorial());
            statement.executeUpdate(insertCmd);
            
            // Obtiene el identificador del material insertado.
            String getLastKeyQuery = "SELECT max(mid) AS mid FROM materiales";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                //mid = rs.getString("mid");
                cd.setMid(rs.getInt("mid"));
            }
            
            // Insert CD.
            insertCmd = String.format("INSERT INTO cds (mid) VALUES (%s)", cd.getMid());
            statement.executeUpdate(insertCmd);
            
            // Se obtiene el identificador del CD.
            getLastKeyQuery = "SELECT max(cid) AS cid FROM cds";
            rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                cd.setCid(rs.getInt("cid"));
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
    }

    @Override
    public Cd retrieve(int cid) {
        Cd cd = new Cd();
        cd.setCid(cid);
        
        try {
            Statement statement = this.connection.createStatement();
            
            // Primero se cargan los datos del CD.
            String query = String.format("SELECT mid, titulo, editorial FROM cds JOIN materiales USING (mid) WHERE cid = %d", cid);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                cd.setMid(rs.getInt("mid"));
                cd.setTitulo(rs.getString("titulo"));
                cd.setEditorial(rs.getString("editorial"));
            }
            
            // Se cargan los ejemplares del CD.
            cd.setEjemplares(new ArrayList<Ejemplar>());
            query = String.format("SELECT numero FROM cds JOIN ejemplares USING (mid) WHERE cid = %d", cid);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                cd.addEjemplar(new Ejemplar(rs.getInt("numero")));
            }
            
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
        
        return cd;
    }

    @Override
    public Cd retrieve(Cd t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cd update(Cd cd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Cd cd) {
        try {
            // Se fuerza a la base de datos a reconocer las claves foráneas,
            // para eliminar las dependencias en cascada.
            String query = "PRAGMA foreign_keys = ON;";
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);

            // Luego se elimina el CD.
            query = String.format("DELETE FROM materiales WHERE mid = %d", cd.getMid());
            statement.executeUpdate(query);
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
    }

    @Override
    public List<Cd> retrieveAll() {
        List<Cd> cds = new ArrayList<>();

        try {
            String query = "SELECT cid FROM cds";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                cds.add(retrieve(rs.getInt("cid")));
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return cds;
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
