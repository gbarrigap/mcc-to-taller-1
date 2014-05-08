/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.domain;

import biblioteca.dao.DaoFactory;
import biblioteca.dao.RevistaDao;
import java.util.List;

/**
 *
 * @author guillermo
 */
public class Revista extends Material {

    private Integer rid;
    private String periodicidad;
    private Cd cd;

    public Revista() {

    }

    public Revista(String titulo, String editorial) {
        super(titulo, editorial);
    }
    
    public Revista(String titulo, String editorial, String periodicidad) {
        super(titulo, editorial);
        
        this.periodicidad = periodicidad;
    }

    public Revista(String titulo, String editorial, Cd cd) {
        super(titulo, editorial);

        this.cd = cd;
    }

    public Revista(String titulo, String editorial, List<Ejemplar> ejemplares, Cd cd) {
        super(titulo, editorial, ejemplares);

        this.cd = cd;
    }

    public Revista(Integer rid, String titulo, String editorial, List<Ejemplar> ejemplares, Cd cd) {
        super(titulo, editorial, ejemplares);

        this.rid = rid;
        this.cd = cd;
    }

    /**
     * @return the rid
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * @return the periodicidad
     */
    public String getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad the periodicidad to set
     */
    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    /**
     * @return the cd
     */
    public Cd getCd() {
        return cd;
    }

    /**
     * @param cd the cd to set
     */
    public void setCd(Cd cd) {
        this.cd = cd;
    }

    public boolean hasCd() {
        return this.cd != null;
    }

    @Override
    public void persist() {
        RevistaDao dao = DaoFactory.getRevistaDao();
        dao.create(this);
    }

    @Override
    public void delete() {
        RevistaDao dao = DaoFactory.getRevistaDao();
        dao.delete(this);
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
