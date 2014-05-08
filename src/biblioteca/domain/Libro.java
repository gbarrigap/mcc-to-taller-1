/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.domain;

import biblioteca.dao.DaoFactory;
import biblioteca.dao.LibroDao;
import java.util.List;

/**
 *
 * @author guillermo
 */
public class Libro extends Material {

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

    /**
     * @return the lid
     */
    public Integer getLid() {
        return lid;
    }

    /**
     * @param lid the lid to set
     */
    public void setLid(Integer lid) {
        this.lid = lid;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the cd
     */
    public Cd getCd() {
        return cd;
    }

    /**
     * @param cd the cd to set
     */
    public void setCd(Cd cd) {
        this.cd = cd;
    }

    public boolean hasCd() {
        return this.cd != null;
    }

    @Override
    public void persist() {
        LibroDao dao = DaoFactory.getLibroDao();
        dao.create(this);
    }

    @Override
    public void delete() {
        LibroDao dao = DaoFactory.getLibroDao();
        dao.delete(this);
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
