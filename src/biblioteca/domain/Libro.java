/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.domain;

import biblioteca.dao.DaoFactory;
import biblioteca.dao.LibroDAO;

/**
 *
 * @author guillermo
 */
public class Libro extends Material {

    private long isbn;
    private String autor;
    private CD cd;

    public Libro(long isbn, String titulo, String autor, String editorial) {
        super(titulo, editorial);

        this.isbn = isbn;
        this.autor = autor;
    }

    /**
     *
     * @param isbn
     */
    public Libro(long isbn) {
        this.isbn = isbn;
    }

    public Libro() {
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
     * @return the isbn
     */
    public long getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the cd
     */
    public CD getCd() {
        return cd;
    }

    /**
     * @param cd the cd to set
     */
    public void setCd(CD cd) {
        this.cd = cd;
    }

    public boolean hasCd() {
        return this.cd != null;
    }

    public void persist() {
        LibroDAO dao = DaoFactory.getLibroDao();
        dao.create(this);
    }
    
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
