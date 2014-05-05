/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.dao.DaoFactory;
import biblioteca.domain.Cd;
import biblioteca.domain.Ejemplar;
import biblioteca.domain.Libro;
import java.util.List;

/**
 *
 * @author guillermo
 */
public class BibliotecaTester {

    public static void showCds() {
        List<Cd> cds = DaoFactory.getCdDao().retrieveAll();
        
        System.out.println(String.format("*** CDS EXISTENTES (%d) ***", cds.size()));
        for (Cd cd : cds) {
            System.out.println("--");
            System.out.println("Título    : " + cd.getTitulo());
            System.out.println("Editorial : " + cd.getEditorial());
            System.out.println("Ejemplares: " + cd.getEjemplares().size());
        }
        System.out.println("--");
    }
    
    public static void showLibros() {
        List<Libro> libros = DaoFactory.getLibroDao().retrieveAll();
        
        System.out.println(String.format("*** LIBROS EXISTENTES (%d) ***", libros.size()));
        for (Libro libro : libros) {
            System.out.println("--");
            System.out.println("Título    : " + libro.getTitulo());
            System.out.println("Autor     : " + libro.getAutor());
            System.out.println("Editorial : " + libro.getEditorial());
            System.out.println("ISBN      : " + libro.getIsbn());
            System.out.println("Ejemplares: " + libro.getEjemplares().size());
            
            if (libro.hasCd()) {
                System.out.println("--");
                System.out.println("\tCD ASOCIADO");
                System.out.println("\tTítulo   : " + libro.getCd().getTitulo());
                System.out.println("\tEditorial: " + libro.getCd().getEditorial());
            }
        }
        System.out.println("--");
    }
    
    public static void showRevistas() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
    public static void showExistencias() {
        //showCds();
        showLibros();
    }
    
    public static void main(String[] args) {
        
        // Se muestran todos los materiales existentes.
        showExistencias();
        
        // Se agrega un libro.
        Libro book = new Libro("978-84-204-1233-7", "La ciudad y los perros", "Mario Vargas Llosa", "Alfaguara");
        
        // Se agregan ejemplares del libro.
        for (int i = 1; i < 5; i++) {
            book.addEjemplar(new Ejemplar(i));
        }
        
        // Se agrega un CD asociado al libro.
        book.setCd(new Cd("Imágenes de La ciudad y de los perros", "Alfaguara Digital"));
        
        // Se guarda el libro.
        book.persist();
        
        // Se muestran nuevamente los materiales existentes.
        showExistencias();
        
        // Se elimina el libro recién agregado.
        book.delete();
        
        // Se muestra nuevamente los materiales existentes.
        showExistencias();
    }
}
