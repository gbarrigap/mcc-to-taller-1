package biblioteca.domain;

import biblioteca.dao.DaoFactory;
import java.util.List;

/**
 * Representa una revista de la biblioteca.
 */
public class Revista extends Material {
    
    /**
     * La frecuencia de publicación de esta revista.
     * <p>
     * Permite controlar la creación de revistas con una frecuencia válida.
     * 
     * @see biblioteca.domain.Revista.PeriodicidadInvalidaException
     */
    public enum Periodicidad { Mensual, Quincenal, Semanal };

    /**
     * Identificador de esta revista.
     * <p>
     * Se usa un tipo de dato <code>Integer</code> porque puede no estar
     * definido, es decir, ser <code>null</code>.
     */
    private Integer rid;
    private Periodicidad periodicidad;
    private Cd cd;

    public Revista() {

    }

    public Revista(String titulo, String editorial) {
        super(titulo, editorial);
    }

    public Revista(String titulo, String editorial, String periodicidad) throws PeriodicidadInvalidaException {
        super(titulo, editorial);

        this.setPeriodicidad(periodicidad);
    }

    public Revista(String titulo, String editorial, Cd cd) {
        super(titulo, editorial);

        this.cd = cd;
    }

    public Revista(String titulo, String editorial, List<Copia> ejemplares, Cd cd) {
        super(titulo, editorial, ejemplares);

        this.cd = cd;
    }

    public Revista(Integer rid, String titulo, String editorial, List<Copia> ejemplares, Cd cd) {
        super(titulo, editorial, ejemplares);

        this.rid = rid;
        this.cd = cd;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * @return Periodicidad la frecuencia de publicación de esta revista
     * @see this.setPeriodicidad
     */
    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad La frecuencia de publicación de esta revista. La
     * periodicidad puede ser mensual, quincenal, semanal, etc.
     * @throws biblioteca.domain.Revista.PeriodicidadInvalidaException
     */
    public final void setPeriodicidad(String periodicidad) throws PeriodicidadInvalidaException {
        try {
            this.periodicidad = Periodicidad.valueOf(periodicidad);
        } catch (Exception err) {
            throw new PeriodicidadInvalidaException(err.toString());
        }
    }
    
    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Cd getCd() {
        return cd;
    }

    public void setCd(Cd cd) {
        this.cd = cd;
    }

    public boolean hasCd() {
        return this.cd != null;
    }

    @Override
    public void persist() {
        DaoFactory.getRevistaDao().create(this);
    }

    @Override
    public void delete() {
        DaoFactory.getRevistaDao().delete(this);
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Exception para capturar errores en la periodicidad de una revista.
     */
    public class PeriodicidadInvalidaException extends Exception {
        public PeriodicidadInvalidaException(String message) {
            super(message);
        }
    }
}
