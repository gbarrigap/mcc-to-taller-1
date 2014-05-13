package biblioteca.dao.sqlite;

import biblioteca.dao.DaoFactory;
import biblioteca.dao.RevistaDao;
import biblioteca.domain.Copia;
import biblioteca.domain.Revista;
import biblioteca.domain.Revista.PeriodicidadInvalidaException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementa el acceso a revistas almacenadas en una base de datos Sqlite.
 */
public class RevistaDaoSqlite implements RevistaDao {

    private final Connection connection;

    public RevistaDaoSqlite(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Revista revista) {
        try {
            Statement statement = this.connection.createStatement();

            // Inserta el material.
            String insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", revista.getTitulo(), revista.getEditorial());
            statement.executeUpdate(insertCmd);

            // Se inserta la revista.
            String cid = revista.hasCd() ? revista.getCd().getId().toString() : "NULL";
            insertCmd = String.format("INSERT INTO revistas (mid, periodicidad, cid) VALUES ((SELECT max(mid) FROM materiales), '%s', %s)", revista.getPeriodicidad(), cid);
            statement.executeUpdate(insertCmd);

            // Se obtiene el identificador de la revista.
            String getLastKeyQuery = "SELECT max(rid) AS rid FROM revistas";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                revista.setId(rs.getInt("rid"));
            }

            // Si esta revista tiene copias, se guardan.
            if (revista.hasCopias()) {
                for (Copia copia : revista.getCopias()) {
                    insertCmd = String.format("INSERT INTO ejemplares (mid, numero) VALUES ((SELECT mid FROM revistas WHERE rid = %d), %d)", revista.getId(), copia.getNumero());
                    statement.executeUpdate(insertCmd);

                    // Se obtiene el identificador de la copia almacenada.
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
    public Revista retrieve(int id) {
        Revista revista = new Revista();
        revista.setId(id);

        try {
            Statement statement = this.connection.createStatement();

            // Se cargan los datos de la revista.
            String query = String.format("SELECT titulo, editorial, periodicidad FROM revistas JOIN materiales USING (mid) WHERE rid = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                revista.setTitulo(rs.getString("titulo"));
                revista.setEditorial(rs.getString("editorial"));
                revista.setPeriodicidad(rs.getString("periodicidad"));
            }

            // Se cargan los ejemplares de la revista, si tiene.
            query = String.format("SELECT numero FROM revistas JOIN ejemplares USING (mid) WHERE rid = %d", id);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                revista.addCopia(new Copia(rs.getInt("numero")));
            }

            // Se carga el CD asociado a la revista, si existe.
            query = String.format("SELECT cid FROM materiales JOIN (SELECT cds.* FROM revistas JOIN cds USING (cid) WHERE rid = %d) USING (mid)", id);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                revista.setCd(DaoFactory.getCdDao().retrieve(rs.getInt("cid")));
            }
        } catch (SQLException | PeriodicidadInvalidaException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return revista;
    }

    @Override
    public void update(Revista t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Revista revista) {
        try {
            // Se fuerza a la base de datos a reconocer las claves foráneas,
            // para eliminar las dependencias en cascada.
            String query = "PRAGMA foreign_keys = ON;";
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);

            // Luego se elimina la revista.
            query = String.format("DELETE FROM materiales WHERE mid = (SELECT mid FROM revistas WHERE rid = %d)", revista.getId());
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Revista> retrieveAll() {
        List<Revista> revistas = new ArrayList<>();

        try {
            String query = "SELECT rid FROM revistas";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                revistas.add(retrieve(rs.getInt("rid")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RevistaDaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return revistas;
    }

}
