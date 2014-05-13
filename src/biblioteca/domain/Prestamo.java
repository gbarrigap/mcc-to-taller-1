package biblioteca.domain;

import java.util.List;

public class Prestamo {
    private Usuario usuario;
    private List<Material> materiales;
    private boolean vigente;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }
    
    public void addMaterial(Material material) {
        this.materiales.add(material);
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
