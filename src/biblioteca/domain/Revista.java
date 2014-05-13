package biblioteca.domain;

import biblioteca.dao.DaoFactory;

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
    public enum Periodicidad {

        MENSUAL,
        QUINCENAL,
        SEMANAL;

        /**
         * @return Una representación estandarizada de esta periodicidad.
         */
        @Override
        public String toString() {
            String value = null;
            switch (this) {
                case MENSUAL:
                    value = "Mensual";
                    break;
                case QUINCENAL:
                    value = "Quincenal";
                    break;
                case SEMANAL:
                    value = "Semanal";
                    break;
            }
            return value;
        }
    }

    private Periodicidad periodicidad;
    private Cd cd;

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
        this.periodicidad = Periodicidad.valueOf(periodicidad.toUpperCase());
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
