package biblioteca.domain;

import biblioteca.dao.DaoFactory;

/**
 * Representa un libro de la biblioteca.
 */
public class Libro extends Material {

    private String isbn;
    private String autor;
    private Cd cd;

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
     * @return boolean <code>true</code> si este libro tiene un CD asociado, de
     * lo contrario, <code>false</code>.
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
        Libro libro = DaoFactory.getLibroDao().retrieve(this.getId());

        this.setTitulo(libro.getTitulo());
        this.setEditorial(libro.getEditorial());
        this.setAutor(libro.getAutor());
        this.setIsbn(libro.getIsbn());
        this.setCd(libro.getCd());
        this.setCopias(libro.getCopias());
        this.setIsbn(libro.getIsbn());
    }

}
