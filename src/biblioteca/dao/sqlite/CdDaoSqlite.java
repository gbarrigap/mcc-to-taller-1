package biblioteca.dao.sqlite;

import biblioteca.dao.CdDao;
import biblioteca.domain.Cd;
import biblioteca.domain.Copia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a CDs almacenados en una base de datos Sqlite.
 */
public class CdDaoSqlite implements CdDao {

    private final Connection connection;

    public CdDaoSqlite(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void create(Cd cd) {
        try {
            Statement statement = this.connection.createStatement();
            
            // Inserta material.
            String insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", cd.getTitulo(), cd.getEditorial());
            statement.executeUpdate(insertCmd);
            
            // Obtiene el identificador del material insertado.
            /*String getLastKeyQuery = "SELECT max(mid) AS mid FROM materiales";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                cd.setMid(rs.getInt("mid"));
            }*/
            
            // Inserta CD.
            //insertCmd = String.format("INSERT INTO cds (mid) VALUES (%s)", cd.getMid());
            insertCmd = "INSERT INTO cds (mid) VALUES ((SELECT max(mid) AS mid FROM materiales))";
            statement.executeUpdate(insertCmd);
            
            // Se obtiene el identificador del CD.
            String getLastKeyQuery = "SELECT max(cid) AS cid FROM cds";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                //cd.setCid(rs.getInt("cid"));
                cd.setId(rs.getInt("cid"));
            }
            
            // Se insertan las copias del CD.
            if (cd.hasCopias()) {
                for (Copia copia : cd.getCopias()) {
                    //insertCmd = String.format("INSERT INTO ejemplares (mid, numero) VALUES (%d, %d)", cd.getMid(), copia.getNumero());
                    insertCmd = String.format("INSERT INTO ejemplares (mid, numero) VALUES ((SELECT mid FROM cds WHERE cid = %d), %d)", cd.getId(), copia.getNumero());
                    statement.executeUpdate(insertCmd);

                    // Obtiene el identificador del ejemplar almacenado.
                    getLastKeyQuery = "SELECT max(eid) AS eid FROM ejemplares";
                    rs = statement.executeQuery(getLastKeyQuery);
                    while (rs.next()) {
                        copia.setEid(rs.getInt("eid"));
                    }
                }
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
    }

    @Override
    public Cd retrieve(int cid) {
        Cd cd = new Cd();
        //cd.setCid(cid);
        cd.setId(cid);
        
        try {
            Statement statement = this.connection.createStatement();
            
            // Se cargan los datos del CD.
            String query = String.format("SELECT mid, titulo, editorial FROM cds JOIN materiales USING (mid) WHERE cid = %d", cid);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //cd.setMid(rs.getInt("mid"));
                cd.setTitulo(rs.getString("titulo"));
                cd.setEditorial(rs.getString("editorial"));
            }
            
            // Se cargan las copias del CD.
            cd.setCopias(new ArrayList<Copia>());
            query = String.format("SELECT numero FROM cds JOIN ejemplares USING (mid) WHERE cid = %d", cid);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                cd.addCopia(new Copia(rs.getInt("numero")));
            }
            
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
        
        return cd;
    }

    @Override
    public Cd retrieve(Cd cd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cd cd) {
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
            //query = String.format("DELETE FROM materiales WHERE mid = %d", cd.getMid());
            query = String.format("DELETE FROM materiales WHERE mid = (SELECT mid FROM cds WHERE cid = %d)", cd.getId());
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

}
