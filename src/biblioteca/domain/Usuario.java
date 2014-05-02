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
public class Usuario {
    
    private int rut;
    private String nombre;

    /**
     * @return the uid
     */
    public int getRut() {
        return rut;
    }

    /**
     * @param uid the uid to set
     */
    public void setRut(int uid) {
        this.rut = uid;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
