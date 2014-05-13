package biblioteca.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un material de la biblioteca.
 * <p>
 * Define atributos generales que los materiales de la biblioteca poseen, y un
 * listado de copias asociado a este material.
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
    private Integer id;
    private String titulo;
    private String editorial;
    private List<Copia> copias;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.copias = new ArrayList<>();
    }

    public Material(String titulo, String editorial, List<Copia> copias) {
        this(titulo, editorial);

        this.copias = copias;
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

    public List<Copia> getCopias() {
        return this.copias;
    }

    public void setCopias(List<Copia> copias) {
        this.copias = copias;
    }

    /**
     * Indica si este material tiene copias asociados.
     *
     * @return true si este material tiene copias
     */
    public boolean hasCopias() {
        return this.copias != null && !this.copias.isEmpty();
    }

    /**
     * Agrega una copia a este material.
     *
     * @param copia La copia que se agregará
     */
    public void addCopia(Copia copia) {
        // Si no existe una lista de copias,
        // se inicializa una.
        if (this.copias == null) {
            this.copias = new ArrayList<>();
        }

        // Se agrega la copia a la lista de copias,
        // siempre y cuando no haya sido agregada antes.
        if (!this.copias.contains(copia)) {
            this.copias.add(copia);
        }
    }

    /**
     * Agrega una lista de copias a este material.
     *
     * @param copias Lista de copias
     */
    public void addCopias(List<Copia> copias) {
        // Si no está inicializada la lista de copias de este material,
        // se crea una nueva.
        if (this.copias == null) {
            this.copias = new ArrayList<>();
        }

        // Se agrega la lista de nuevas copias de este material.
        this.copias.addAll(copias);
    }

    public void getCopia(Copia copia) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public void getCopia(int eid) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public void removeCopia(Copia copia) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public void removeCopia(int eid) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public abstract void persist();

    public abstract void delete();

    public abstract void load();
}
