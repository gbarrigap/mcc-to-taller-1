package biblioteca.domain;

/**
 *
 * @author guillermo
 */
public abstract class Material {

    private int mid;
    private int numeroCopia;
    private String titulo;
    private String editorial;
    private boolean prestado;

    public Material(String titulo, String editorial) {
        this.titulo = titulo;
        this.editorial = editorial;
    }

    public Material() {
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
    public void prestar() {
        this.prestado = true;
    }

    public void devolver() {
        this.prestado = false;
    }

    /**
     * @return the mid
     */
    public int getMid() {
        return mid;
    }

    /**
     * @param mid the mid to set
     */
    public void setMid(int mid) {
        this.mid = mid;
    }

    /**
     * @return the numeroCopia
     */
    public int getNumeroCopia() {
        return numeroCopia;
    }

    /**
     * @param numeroCopia the numeroCopia to set
     */
    public void setNumeroCopia(int numeroCopia) {
        this.numeroCopia = numeroCopia;
    }
}
