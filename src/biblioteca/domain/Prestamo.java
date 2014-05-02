/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteca.domain;

import java.util.List;

/**
 *
 * @author guillermo
 */
public class Prestamo {
    private Usuario usuario;
    private List<Material> materiales;
    private boolean vigente;

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the materiales
     */
    public List<Material> getMateriales() {
        return materiales;
    }

    /**
     * @param materiales the materiales to set
     */
    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }
    
    public void addMaterial(Material material) {
        this.materiales.add(material);
    }

    /**
     * @return the vigente
     */
    public boolean isVigente() {
        return vigente;
    }

    /**
     * @param vigente the vigente to set
     */
    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
