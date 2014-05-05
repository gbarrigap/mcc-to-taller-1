/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.domain;

import biblioteca.dao.CdDao;
import biblioteca.dao.DaoFactory;

/**
 *
 * @author guillermo
 */
public class Cd extends Material {

    private int cid;

    public Cd(String titulo, String editorial) {
        super(titulo, editorial);
    }

    public Cd() {
    }

    /**
     * @return the cid
     */
    public int getCid() {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(int cid) {
        this.cid = cid;
    }

    public void persist() {
        DaoFactory.getCdDao().create(this);
    }

}
