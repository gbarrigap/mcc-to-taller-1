package biblioteca.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guillermo
 */
public abstract class Material {

    private Integer mid;
    private String titulo;
    private String editorial;
    private List<Ejemplar> ejemplares;

    public Material() {
    }

    public Material(String titulo, String editorial) {
        this.titulo = titulo;
        this.editorial = editorial;
        this.ejemplares = new ArrayList<Ejemplar>();
    }

    public Material(String titulo, String editorial, List<Ejemplar> ejemplares) {
        this(titulo, editorial);

        this.ejemplares = ejemplares;
    }

    public Material(Integer mid, String titulo, String editorial, List<Ejemplar> ejemplares) {
        this(titulo, editorial, ejemplares);

        this.mid = mid;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /*
     private void setPrestado(boolean prestado) {
     this.prestado = prestado;
     }

     public boolean isPrestado() {
     return this.prestado;
     }
     */
    /*
     public void prestar() {
     this.prestado = true;
     }

     public void devolver() {
     this.prestado = false;
     }
     */
    /**
     * @return the mid
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * @param mid the mid to set
     */
    public void setMid(Integer mid) {
        this.mid = mid;
    }

    /**
     * @return the numeroCopia
     */
    /*
     public int getNumeroCopia() {
     return numeroCopia;
     }
     */
    /**
     * @param numeroCopia the numeroCopia to set
     */
    /*
     public void setNumeroCopia(int numeroCopia) {
     this.numeroCopia = numeroCopia;
     }
     */
    /**
     * @return the ejemplares
     */
    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    /**
     * @param ejemplares the ejemplares to set
     */
    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }
    
    public boolean hasEjemplares() {
        return this.ejemplares != null && !this.ejemplares.isEmpty();
    }

    public void addEjemplar(Ejemplar ejemplar) {
        // Si no existe una lista de ejemplares,
        // se inicializa una.
        if (this.ejemplares == null) {
            this.ejemplares = new ArrayList<>();
        }

        this.ejemplares.add(ejemplar);
    }

    public void getEjemplar(Ejemplar ejemplar) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public void getEjemplar(int eid) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public void removeEjemplar(Ejemplar ejemplar) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public void removeEjemplar(int eid) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public abstract void persist();

    public abstract void delete();

    public abstract void load();
}
