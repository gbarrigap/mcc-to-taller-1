package biblioteca;

import biblioteca.dao.DaoFactory;
import biblioteca.domain.Cd;
import biblioteca.domain.Ejemplar;
import biblioteca.domain.Libro;
import biblioteca.domain.Revista;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase implementa métodos para demostrar la funcionalidad del programa.
 * 
 * @see BibliotecaTester.runTests
 */
public final class BibliotecaTester {

    /**
     * Muestra los CDs existentes y un conteo del total.
     */
    private static void showCds() {
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

    /**
     * Muestra los libros existentes y un conteo del total.
     * <p>
     * Por cada libro muestra los datos relevantes; en caso de tener asociado
     * un CD muestra los datos correspondientes con sangría apropropiada.
     */
    private static void showLibros() {
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

    /**
     * Muestra las revistas existentes y un conteo del total.
     * <p>
     * Por cada revista muestra los datos relevantes; en caso de tener asociado
     * un CD muestra los datos correspondientes con sangría apropropiada.
     */
    private static void showRevistas() {
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

    /**
     * Muestra los materiales existentes y un conteo parcial del total.
     * 
     * @see biblioteca.BibliotecaTester.showCds
     * @see biblioteca.BibliotecaTester.showLibros
     * @see biblioteca.BibliotecaTester.showRevistas
     */
    private static void showExistencias() {
        showCds();
        showLibros();
        showRevistas();
    }

    /**
     * Agrega, asocia y elimina materiales, demostrando la funcionalidad del
     * programa.
     * <p>
     * Realiza operaciones para demostrar la funcionalidad del programa,
     * agregando, asociando y eliminando materiales, de acuerdo a las
     * restricciones propuestas, de la siguiente forma:
     * 
     * <ol>
     * <li>Muestra el estado actual de las existencias, contabilizando
     * cada tipo de material junto al encabezado.
     * 
     * <li>Agrega CDs y muestra nuevamente las existencias para ver los
     * cambios; es decir, la presencia de dos nuevos CD.
     * 
     * <li>Agrega un libro y le asocia un CD, previamente agregado, y muestra
     * las existencias para ver los cambios; un nuevo libro con un CD asociado.
     * Lo anterior sólo modifica el total de libros, no el de CDs.
     * 
     * <li>Agrega una revista y le asocia un CD, previamente agregado, y muestra
     * las existencias para ver los cambios; una nueva revista con un CD
     * asociado. Lo anterior sólo modifica el total de revistas, no el de CDs.
     * 
     * <li>Elimina uno a uno los materiales agregados, y muestra los cambios
     * por cada operación. Primero se elimina la revista y se muestran las
     * existencias; luego se elimina el libro y se muestran las existencias;
     * finalmente se elimina el CD y se muestran las existencias.
     * </ol>
     * 
     * <p>
     * Una vez ejecutado este método, las existencias se encuentran como antes
     * de su ejecución.
     */
    public static void runTests() {
        try {
            // Se muestran todos los materiales existentes.
            showExistencias();
            
            // Se agregan CDs.
            System.out.println("AGREGANDO CDS");
            
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
            
            // Se agrega un libro.
            System.out.println("AGREGANDO LIBRO");
            
            Libro book = new Libro("978-84-204-1233-7", "La ciudad y los perros", "Mario Vargas Llosa", "Alfaguara");
            
            // Se agregan ejemplares del libro.
            for (int i = 1; i < 5; i++) {
                book.addEjemplar(new Ejemplar(i));
            }
            
            // Se asocia un CD asociado al libro.
            book.setCd(cdImagenes);
            
            // Se guarda el libro.
            book.persist();
            
            // Se muestran nuevamente los materiales existentes.
            showExistencias();
            
            // Se agrega una revista.
            System.out.println("AGREGANDO REVISTA");
            
            Revista magazine = new Revista("Conozca Más", "Televisa S.A.", "Mensual");
            
            // Se agregan ejemplares de la revista.
            for (int i = 1; i < 3; i++) {
                magazine.addEjemplar(new Ejemplar(i));
            }
            
            // Se asocia un CD a la revista.
            magazine.setCd(cdOvnis);
            
            // Se guarda la revista.
            magazine.persist();
            
            // Se muestran los materiales existentes.
            showExistencias();
            
            // Se elimina el libro recién agregado.
            System.out.println("BORRANDO LIBRO");
            
            book.delete();
            
            // Se muestran los materiales existentes.
            showExistencias();
            
            // Se elimina la revista recién agregada.
            System.out.println("BORRANDO REVISTA");
            
            magazine.delete();
            
            // Se muestran los materiales existentes.
            showExistencias();
            
            // Se eliminan los CDs recién agregados.
            System.out.println("BORRANDO CDS");
            
            cdImagenes.delete();
            cdOvnis.delete();
            
            // Se muestran los materiales existentes.
            showExistencias();
        } catch (Revista.PeriodicidadInvalidaException ex) {
            //Logger.getLogger(BibliotecaTester.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
}
