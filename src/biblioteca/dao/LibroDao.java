/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao;

import biblioteca.domain.Libro;

/**
 *
 * @author guillermo
 */
public abstract class LibroDao implements Dao<Libro> {

    public abstract Libro getLibroByIsbn(String isbn);
}
