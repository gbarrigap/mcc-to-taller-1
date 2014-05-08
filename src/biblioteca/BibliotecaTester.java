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
import biblioteca.domain.Revista;
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
        List<Revista> revistas = DaoFactory.getRevistaDao().retrieveAll();

        System.out.println(String.format("*** REVISTAS EXISTENTES (%d) ***", revistas.size()));
        for (Revista revista : revistas) {
            System.out.println("--");
            System.out.println("Título       : " + revista.getTitulo());
            System.out.println("Editorial    : " + revista.getEditorial());
            System.out.println("Periodicidad : " + revista.getPeriodicidad());
            System.out.println("Ejemplares   : " + (revista.hasEjemplares() ? revista.getEjemplares().size() : "0"));

            if (revista.hasCd()) {
                System.out.println("--");
                System.out.println("\tCD ASOCIADO");
                System.out.println("\tTítulo   : " + revista.getCd().getTitulo());
                System.out.println("\tEditorial: " + revista.getCd().getEditorial());
            }
        }
        System.out.println("--");
    }

    public static void showExistencias() {
        showCds();
        showLibros();
        showRevistas();
    }

    public static void main(String[] args) {

        runTests();
    }

    private static void runTests() {
        // Se muestran todos los materiales existentes.
        showExistencias();

        System.out.println("AGREGANDO CDS");

        // Se agregan CDs.
        Cd cdImagenes = new Cd("Imágenes de La ciudad y de los perros", "Alfaguara Digital");

        Cd cdOvnis = new Cd("Imágenes de avistamientos de ovnis", "Televisa S.A.");

        // Se agregan ejemplares de los CDs.
        for (int i = 0; i < 4; i++) {
            cdImagenes.addEjemplar(new Ejemplar(i));
        }

        for (int i = 0; i < 4; i++) {
            cdOvnis.addEjemplar(new Ejemplar(i));
        }

        // Se guardan los CDs.
        cdImagenes.persist();
        cdOvnis.persist();

        // Se muestran los materiales existentes.
        showExistencias();

        System.out.println("AGREGANDO LIBRO");

        // Se agrega un libro.
        Libro book = new Libro("978-84-204-1233-7", "La ciudad y los perros", "Mario Vargas Llosa", "Alfaguara");

        // Se agregan ejemplares del libro.
        for (int i = 1; i < 5; i++) {
            book.addEjemplar(new Ejemplar(i));
        }

        // Se agrega un CD asociado al libro.
        book.setCd(cdImagenes);

        // Se guarda el libro.
        book.persist();

        // Se muestran nuevamente los materiales existentes.
        showExistencias();

        System.out.println("AGREGANDO REVISTA");

        // Se agrega una revista.
        Revista magazine = new Revista("Conozca Más", "Televisa S.A.", "Mensual");

        // Se agregan ejemplares de la revista.
        for (int i = 1; i < 3; i++) {
            magazine.addEjemplar(new Ejemplar(i));
        }

        // Se agrega un CD a la revista.
        magazine.setCd(cdOvnis);

        // Se guarda la revista.
        magazine.persist();

        // Se muestran los materiales existentes.
        showExistencias();

        System.out.println("BORRANDO LIBRO");

        // Se elimina el libro recién agregado.
        book.delete();

        // Se muestran los materiales existentes.
        showExistencias();

        System.out.println("BORRANDO REVISTA");

        // Se elimina la revista recién agregada.
        magazine.delete();

        // Se muestran los materiales existentes.
        showExistencias();

        System.out.println("BORRANDO CDS");

        // Se eliminan los CDs recién agregados.
        cdImagenes.delete();
        cdOvnis.delete();

        // Se muestran los materiales existentes.
        showExistencias();
    }
}
