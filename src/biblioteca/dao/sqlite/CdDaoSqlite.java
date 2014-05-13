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
import java.util.logging.Level;
import java.util.logging.Logger;

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

            // Se inserta el material.
            String insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", cd.getTitulo(), cd.getEditorial());
            statement.executeUpdate(insertCmd);

            // Se inserta el CD.
            insertCmd = "INSERT INTO cds (mid) VALUES ((SELECT max(mid) AS mid FROM materiales))";
            statement.executeUpdate(insertCmd);

            // Se obtiene el identificador del CD.
            String getLastKeyQuery = "SELECT max(cid) AS cid FROM cds";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                cd.setId(rs.getInt("cid"));
            }

            // Se insertan las copias del CD.
            if (cd.hasCopias()) {
                for (Copia copia : cd.getCopias()) {
                    insertCmd = String.format("INSERT INTO ejemplares (mid, numero) VALUES ((SELECT mid FROM cds WHERE cid = %d), %d)", cd.getId(), copia.getNumero());
                    statement.executeUpdate(insertCmd);

                    // Se obtiene el identificador de la copia insertada.
                    getLastKeyQuery = "SELECT max(eid) AS eid FROM ejemplares";
                    rs = statement.executeQuery(getLastKeyQuery);
                    while (rs.next()) {
                        copia.setEid(rs.getInt("eid"));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Cd retrieve(int id) {
        Cd cd = new Cd();
        cd.setId(id);

        try {
            Statement statement = this.connection.createStatement();

            // Se cargan los datos del CD.
            String query = String.format("SELECT titulo, editorial FROM cds JOIN materiales USING (mid) WHERE cid = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                cd.setTitulo(rs.getString("titulo"));
                cd.setEditorial(rs.getString("editorial"));
            }

            // Se cargan las copias del CD.
            query = String.format("SELECT numero FROM cds JOIN ejemplares USING (mid) WHERE cid = %d", id);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                cd.addCopia(new Copia(rs.getInt("numero")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
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
            query = String.format("DELETE FROM materiales WHERE mid = (SELECT mid FROM cds WHERE cid = %d)", cd.getId());
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cds;
    }

}
