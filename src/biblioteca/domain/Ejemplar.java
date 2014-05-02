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

    private int numero;

    public Ejemplar(int numero) {
        this.numero = numero;
    }

    public Ejemplar() {
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
