package biblioteca;

/**
 * Clase principal del programa Biblioteca.
 * <p>
 * El programa Biblioteca entrega una solución a la siguiente problemática:
 * <p>
 * El señor B. trabaja en una biblioteca. En ella, se debe registrar la
 * existencia de material bibliográfico. Existen tres tipos de material
 * bibliográfico: libros, revistas y CDs. Los libros tienen un Título, Autor,
 * Editorial y número ISBN (que identifica un libro de manera única). Las
 * revistas tienen un Título, una Editorial, y una periodicidad (quincenal,
 * mensual, trimestral, etc.). Los CDs Tienen un Título, una Editorial, y en
 * algunos casos, acompañan a un libro o una revista. Es necesario mantener un
 * registro de cuantos libros, revistas y CDs se mantienen en la biblioteca,
 * así como el registro del total de material.
 * <p>
 * Se pide que diseñe e implemente un programa orientado a objetos en Java,
 * que permita mantener este registro y calcular las existencias.
 * <p>
 * Observaciones: Se asume que por cada libro, revista o CD en inventario,
 * existe una correspondiente instancia de la clase respectiva en el sistema.
 * Por lo tanto, cada vez que se crea una nueva instancia, se deben actualizar
 * los valores de existencia.
 */
public class Biblioteca {

    public static void main(String[] args) {
        BibliotecaTester.runTests();
    }
    
}
