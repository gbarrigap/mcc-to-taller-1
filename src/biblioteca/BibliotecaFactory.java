/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteca;

import biblioteca.dao.DaoFactory;
import biblioteca.dao.LibroDAO;
import biblioteca.domain.Libro;

/**
 *
 * @author guillermo
 */
public class BibliotecaFactory {
    
    public static void crearLibro(Libro book) {
        LibroDAO dao = DaoFactory.getLibroDao();
        dao.create(book);
    }
    
    public static Libro crearLibro(long isbn, String titulo, String autor, String editorial) {
        Libro book = new Libro(isbn, titulo, autor, editorial);
        BibliotecaFactory.crearLibro(book);
        return book;
    }
}
