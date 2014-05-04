/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteca.dal;

/**
 *
 * @author guillermo
 */
public class BibliotecaDal {
    
    public static void createLibro(Libro book) {
        String statement = String.format("INSERT INTO libros (isbn, titulo, autor, editorial) VALUES (%d, '%s', '%s', '%s')",
                book.getIsbn(), book.getTitulo(), book.getAutor(), book.getEditorial());
    }
}
