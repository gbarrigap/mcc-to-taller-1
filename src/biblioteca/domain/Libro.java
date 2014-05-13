package biblioteca.domain;

import biblioteca.dao.DaoFactory;
import java.util.List;

/**
 * Representa un libro de la biblioteca.
 */
public class Libro extends Material {

    /**
     * Identificador de este libro.
     * <p>
     * Se usa un tipo de dato <code>Integer</code> porque puede no estar
     * definido, es decir, ser <code>null</code>.
     * @deprecated 
     */
    private Integer lid;
    private String isbn;
    private String autor;
    private Cd cd;

    public Libro() {
    }

    public Libro(String isbn, String titulo, String autor, String editorial) {
        super(titulo, editorial);

        this.isbn = isbn;
        this.autor = autor;
    }

    public Libro(String isbn, String titulo, String autor, String editorial, List<Copia> copias) {
        super(titulo, editorial, copias);

        this.isbn = isbn;
        this.autor = autor;
    }

    public Libro(String isbn, String titulo, String autor, String editorial, List<Copia> copias, Cd cd) {
        super(titulo, editorial, copias);

        this.isbn = isbn;
        this.autor = autor;
        this.cd = cd;
    }

    /**
     * @deprecated 
     * @param lid
     * @param isbn
     * @param titulo
     * @param autor
     * @param editorial
     * @param copias
     * @param cd 
     */
    public Libro(Integer lid, String isbn, String titulo, String autor, String editorial, List<Copia> copias, Cd cd) {
        super(titulo, editorial, copias);

        this.lid = lid;
        this.isbn = isbn;
        this.autor = autor;
        this.cd = cd;
    }

    /**
     * @deprecated 
     * @return 
     */
    public Integer getLid() {
        return lid;
    }

    /**
     * @deprecated 
     * @param lid 
     */
    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Cd getCd() {
        return cd;
    }

    public void setCd(Cd cd) {
        this.cd = cd;
    }

    /**
     * @return boolean <code>true</code> si este libro tiene un CD asociado,
     * de lo contrario, <code>false</code>.
     */
    public boolean hasCd() {
        return this.cd != null;
    }

    @Override
    public void persist() {
        DaoFactory.getLibroDao().create(this);
    }

    @Override
    public void delete() {
        DaoFactory.getLibroDao().delete(this);
    }

    @Override
    public void load() {
        Libro libro = DaoFactory.getLibroDao().retrieve(this);

        this.setTitulo(libro.getTitulo());
        this.setEditorial(libro.getEditorial());
        this.setAutor(libro.getAutor());
        this.setIsbn(libro.getIsbn());
        this.setCd(libro.getCd());
        this.setCopias(libro.getCopias());
        this.setIsbn(libro.getIsbn());
    }

}
