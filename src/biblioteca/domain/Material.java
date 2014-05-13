package biblioteca.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un material de la biblioteca.
 * <p>
 * Define atributos generales que los materiales de la biblioteca poseen, y un
 * listado de ejemplares asociado a este material.
 * <p>
 * Dado que esta es una clase abstracta, sólo presenta funcionalidad común de
 * los materiales; la funcionalidad propia de cada material se debe implementar
 * en la clase respectiva.
 */
public abstract class Material {

    /**
     * Identificador de este material.
     * <p>
     * Se usa un tipo de dato <code>Integer</code> porque puede no estar
     * definido, es decir, ser <code>null</code>.
     */
    private Integer mid;
    private String titulo;
    private String editorial;
    private List<Ejemplar> ejemplares;

    /**
     * Constructor vacío.
     * <p>
     * El constructor permite que se cree una instancia sin parámetro alguno,
     * para luego inicializar los atributos cuando fuere necesario.
     */
    public Material() {
    }

    public Material(String titulo, String editorial) {
        this.titulo = titulo;
        this.editorial = editorial;
        this.ejemplares = new ArrayList<>();
    }

    public Material(String titulo, String editorial, List<Ejemplar> ejemplares) {
        this(titulo, editorial);

        this.ejemplares = ejemplares;
    }

    public Material(Integer mid, String titulo, String editorial, List<Ejemplar> ejemplares) {
        this(titulo, editorial, ejemplares);

        this.mid = mid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    /**
     * Indica si este material tiene ejemplares asociados.
     * 
     * @return true si este ejemplar tiene materiales
     */
    public boolean hasEjemplares() {
        return this.ejemplares != null && !this.ejemplares.isEmpty();
    }

    /**
     * Agrega un ejemplar a este material.
     * 
     * @param ejemplar El ejemplar que se agregará
     */
    public void addEjemplar(Ejemplar ejemplar) {
        // Si no existe una lista de ejemplares,
        // se inicializa una.
        if (this.ejemplares == null) {
            this.ejemplares = new ArrayList<>();
        }

        // Se agrega el ejemplar a la lista de ejemplares,
        // siempre y cuando no haya sido agregado antes.
        if (!this.ejemplares.contains(ejemplar)) {
            this.ejemplares.add(ejemplar);
        }
    }
    
    /**
     * Agrega una lista de ejemplares a este material.
     * 
     * @param ejemplares Lista de ejemplares
     */
    public void addEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares.addAll(ejemplares);
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
