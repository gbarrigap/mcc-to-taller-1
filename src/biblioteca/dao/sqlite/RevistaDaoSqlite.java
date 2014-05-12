package biblioteca.dao.sqlite;

import biblioteca.dao.RevistaDao;
import biblioteca.domain.Cd;
import biblioteca.domain.Ejemplar;
import biblioteca.domain.Revista;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa el acceso a revistas almacenadas en una base de datos Sqlite.
 */
public class RevistaDaoSqlite implements RevistaDao {

    private Connection connection;

    public RevistaDaoSqlite(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Revista magazine) {
        try {
            Statement statement = this.connection.createStatement();

            // Inserta el material.
            String insertCmd = String.format("INSERT INTO materiales (titulo, editorial) VALUES ('%s', '%s')", magazine.getTitulo(), magazine.getEditorial());
            statement.executeUpdate(insertCmd);

            // Obtiene el identificador del material insertado.
            String getLastKeyQuery = "SELECT max(mid) AS mid FROM materiales";
            ResultSet rs = statement.executeQuery(getLastKeyQuery);
            while (rs.next()) {
                magazine.setMid(Integer.parseInt(rs.getString("mid")));
            }

            // Inserta la revista.
            String cid = magazine.hasCd() ? magazine.getCd().getCid().toString() : "NULL";
            insertCmd = String.format("INSERT INTO revistas (mid, periodicidad, cid) VALUES (%d, '%s', %s)", magazine.getMid(), magazine.getPeriodicidad(), cid);
            statement.executeUpdate(insertCmd);

            // Se guardan los ejemplares de la revista.
            // Guarda los ejemplares del libro, si tiene.
            if (magazine.hasEjemplares()) {
                for (Ejemplar ejemplar : magazine.getEjemplares()) {
                    insertCmd = String.format("INSERT INTO ejemplares (mid, numero) VALUES (%d, %d)", magazine.getMid(), ejemplar.getNumero());
                    statement.executeUpdate(insertCmd);

                    // Obtiene el identificador del ejemplar almacenado.
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

    @Override
    public Revista retrieve(int rid) {
        Revista magazine = new Revista();
        magazine.setRid(rid);

        try {
            Statement statement = this.connection.createStatement();

            // Primero se cargan los datos de la revista.
            String query = String.format("SELECT mid, titulo, editorial, periodicidad FROM revistas JOIN materiales USING (mid) WHERE rid = %d", rid);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                magazine.setMid(rs.getInt("mid"));
                magazine.setTitulo(rs.getString("titulo"));
                magazine.setEditorial(rs.getString("editorial"));
                magazine.setPeriodicidad(rs.getString("periodicidad"));
            }
            
            // Se cargan los ejemplares de la revista, si tiene.
            query = String.format("SELECT numero FROM revistas JOIN ejemplares USING (mid) WHERE rid = %d", rid);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                magazine.addEjemplar(new Ejemplar(rs.getInt("numero")));
            }
            
            // Se carga el CD asociado a la revista, si existe.
            // @todo Usar método "retrieve" del CD para cargar datos y ejemplares.
            query = String.format("SELECT cid, mid, titulo, editorial FROM materiales JOIN (SELECT cds.* FROM revistas JOIN cds USING (cid) WHERE rid = %d) USING (mid)", rid);
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Cd cd = new Cd();
                cd.setCid(rs.getInt("cid"));
                cd.setMid(rs.getInt("mid"));
                cd.setTitulo(rs.getString("titulo"));
                cd.setEditorial(rs.getString("editorial"));

                magazine.setCd(cd);
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return magazine;
    }

    @Override
    public Revista retrieve(Revista t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Revista t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Revista magazine) {
        try {
            // Se fuerza a la base de datos a reconocer las claves foráneas,
            // para eliminar las dependencias en cascada.
            String query = "PRAGMA foreign_keys = ON;";
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);

            // Luego se elimina la revista.
            query = String.format("DELETE FROM materiales WHERE mid = %d", magazine.getMid());
            statement.executeUpdate(query);
        } catch (SQLException err) {
            System.err.println(err.toString());
        }
    }

    @Override
    public List<Revista> retrieveAll() {
        List<Revista> books = new ArrayList<>();

        try {
            String query = "SELECT rid FROM revistas";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                books.add(retrieve(rs.getInt("rid")));
            }
        } catch (SQLException err) {
            System.err.println(err.toString());
        }

        return books;
    }

}
