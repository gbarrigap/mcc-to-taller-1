/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.domain;

/**
 *
 * @author guillermo
 */
public class Libro extends Material {

    private long isbn;
    private String autor;

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
}
