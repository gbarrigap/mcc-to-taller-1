/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.domain;

/**
 *
 * @author guillermo
 */
public class Ejemplar {

    private Integer eid;
    private int numero;

    public Ejemplar(int numero) {
        this.numero = numero;
    }

    public Ejemplar() {
    }

    /**
     * @return the eid
     */
    public Integer getEid() {
        return eid;
    }

    /**
     * @param eid the eid to set
     */
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
}
