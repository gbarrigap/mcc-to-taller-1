package biblioteca.dao;

import biblioteca.domain.Libro;

public interface LibroDao extends Dao<Libro> {

    public abstract Libro getLibroByIsbn(String isbn);
}
