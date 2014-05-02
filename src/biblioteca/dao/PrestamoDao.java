/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao;

import biblioteca.domain.Material;
import biblioteca.domain.Prestamo;
import biblioteca.domain.Usuario;
import java.util.List;

/**
 *
 * @author guillermo
 */
public abstract class PrestamoDao implements Dao<Prestamo> {

    public abstract List<Prestamo> retrieveAllByUsuario(Usuario u);

    public abstract List<Prestamo> retrieveAllVigenteByUsuario(Usuario u);

    public abstract List<Prestamo> retrieveAllByMaterial(Material m);

    public abstract List<Prestamo> retrieveAllVigenteByMaterial(Material m);
}
