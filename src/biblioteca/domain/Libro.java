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

    public Libro(String isbn, String titulo, String autor, String editorial, List<Ejemplar> ejemplares) {
        super(titulo, editorial, ejemplares);

        this.isbn = isbn;
        this.autor = autor;
    }

    public Libro(String isbn, String titulo, String autor, String editorial, List<Ejemplar> ejemplares, Cd cd) {
        super(titulo, editorial, ejemplares);

        this.isbn = isbn;
        this.autor = autor;
        this.cd = cd;
    }

    public Libro(Integer lid, String isbn, String titulo, String autor, String editorial, List<Ejemplar> ejemplares, Cd cd) {
        super(titulo, editorial, ejemplares);

        this.lid = lid;
        this.isbn = isbn;
        this.autor = autor;
        this.cd = cd;
    }

    public Integer getLid() {
        return lid;
    }

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
        Libro b = DaoFactory.getLibroDao().retrieve(this);

        this.setTitulo(b.getTitulo());
        this.setEditorial(b.getEditorial());
        this.setAutor(b.getAutor());
        this.setIsbn(b.getIsbn());
        this.setCd(b.getCd());
        this.setEjemplares(b.getEjemplares());
        this.setIsbn(b.getIsbn());
    }

}
