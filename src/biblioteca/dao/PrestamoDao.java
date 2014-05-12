package biblioteca.dao;

import biblioteca.domain.Material;
import biblioteca.domain.Prestamo;
import biblioteca.domain.Usuario;
import java.util.List;

public abstract class PrestamoDao implements Dao<Prestamo> {

    public abstract List<Prestamo> retrieveAllByUsuario(Usuario u);

    public abstract List<Prestamo> retrieveAllVigenteByUsuario(Usuario u);

    public abstract List<Prestamo> retrieveAllByMaterial(Material m);

    public abstract List<Prestamo> retrieveAllVigenteByMaterial(Material m);
}
