/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ui.cli;

import biblioteca.dao.CdDao;
import biblioteca.dao.DaoFactory;
import biblioteca.dao.LibroDAO;
import biblioteca.domain.CD;
import biblioteca.domain.Libro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author guillermo
 */
public class BibliotecaCli {

    //private static DataInputStream in = new DataInputStream(System.in);
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static void agregarCD() {
        boolean error = false;
        CD cd = new CD();

        do {
            error = false;
            System.out.print("Título: ");
            try {
                cd.setTitulo(in.readLine());
            } catch (IOException err) {
                error = true;
                System.err.println(err.toString());
            }
        } while (error);

        do {
            error = false;
            System.out.print("Editorial: ");
            try {
                cd.setEditorial(in.readLine());
            } catch (IOException err) {
                error = true;
                System.err.println(err.toString());
            }
        } while (error);

        cd.persist();
    }

    private static void agregarLibro() {
        boolean error;
        Libro book = new Libro();

        do {
            error = false;
            System.out.print("ISBN: ");
            try {
                book.setIsbn(Integer.parseInt(in.readLine()));
            } catch (Exception err) {
                error = true;
                System.err.println(err.toString());
            }
        } while (error);

        do {
            error = false;
            System.out.print("Título: ");
            try {
                book.setTitulo(in.readLine());
            } catch (Exception err) {
                error = true;
                System.err.println(err.toString());
            }
        } while (error);

        do {
            error = false;
            System.out.print("Autor: ");
            try {
                book.setAutor(in.readLine());
            } catch (Exception err) {
                error = true;
                System.err.println(err.toString());
            }
        } while (error);

        do {
            error = false;
            System.out.print("Editorial: ");
            try {
                book.setEditorial(in.readLine());
            } catch (Exception err) {
                error = true;
                System.err.println(err.toString());
            }
        } while (error);

        do {
            error = false;
            System.out.print("Agregar CD? (s/n): ");
            try {
                if ("s".equals(in.readLine().toLowerCase())) {
                    System.out.print("CD ID: ");
                    
                    CdDao dao = DaoFactory.getCdDao();
                    book.setCd(dao.retrieve(Integer.parseInt(in.readLine())));
                }
            } catch (IOException err) {
                System.err.println(err.toString());
            }
        } while (error);

        book.persist();
    }

    private static void agregarRevista() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuMaterialesAdministrarAgregar() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("* MATERIALES >> ADMINISTRAR >> AGREGAR *");
                System.out.println("*                                      *");
                System.out.println("* 0. Volver                            *");
                System.out.println("* 1. Libro                             *");
                System.out.println("* 2. Revista                           *");
                System.out.println("* 3. CD                                *");
                System.out.println("*                                      *");
                System.out.println("****************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Libro
                    agregarLibro();
                    break;

                case 2: // Revista
                    agregarRevista();
                    break;

                case 3: // CD
                    agregarCD();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void editarLibro() {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    private static void editarRevista() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void editarCD() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuMaterialesAdministrarEditar() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("* MATERIALES >> ADMINISTRAR >> EDITAR  *");
                System.out.println("*                                      *");
                System.out.println("* 0. Volver                            *");
                System.out.println("* 1. Libro                             *");
                System.out.println("* 2. Revista                           *");
                System.out.println("* 3. CD                                *");
                System.out.println("*                                      *");
                System.out.println("****************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Libro
                    editarLibro();
                    break;

                case 2: // Revista
                    editarRevista();
                    break;

                case 3: // CD
                    editarCD();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void eliminarLibro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void eliminarRevista() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void eliminarCD() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuMaterialesAdministrarEliminar() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("* MATERIALES >> ADMINISTRAR >> ELIMINAR *");
                System.out.println("*                                       *");
                System.out.println("* 0. Volver                             *");
                System.out.println("* 1. Libro                              *");
                System.out.println("* 2. Revista                            *");
                System.out.println("* 3. CD                                 *");
                System.out.println("*                                       *");
                System.out.println("*****************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Libro
                    eliminarLibro();
                    break;

                case 2: // Revista
                    eliminarRevista();
                    break;

                case 3: // CD
                    eliminarCD();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void showMenuMaterialesAdministrar() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("*** MATERIALES >> ADMINISTRAR  ********");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Agregar                          *");
                System.out.println("* 2. Editar                           *");
                System.out.println("* 3. Eliminar                         *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Agregar
                    showMenuMaterialesAdministrarAgregar();
                    break;

                case 2: // Editar
                    showMenuMaterialesAdministrarEditar();
                    break;

                case 3: // Eliminar
                    showMenuMaterialesAdministrarEliminar();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void listarLibros() {
        LibroDAO dao = DaoFactory.getLibroDao();
        List<Libro> books = dao.retrieveAll();

        System.out.println("**** LIBROS *****");
        for (Libro book : books) {
            System.out.println("--");
            System.out.println("Título     : " + book.getTitulo());
            System.out.println("Autor      : " + book.getAutor());
            System.out.println("Editorial  : " + book.getEditorial());
            System.out.println("ISBN       : " + book.getIsbn());
            System.out.println("Ejemplares : " + book.getEjemplares().size());
        }
        System.out.println("--");
    }

    private static void listarLibrosDisponibles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void listarLibrosNoDisponibles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuMaterialesConsultasListarLibros() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("* MATERIALES >> CONSULTAS >> LISTAR >> LIBROS *");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Todos                            *");
                System.out.println("* 2. Disponibles                      *");
                System.out.println("* 3. No disponibles                   *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Libros
                    listarLibros();
                    break;

                case 2: // Revistas
                    listarLibrosDisponibles();
                    break;

                case 3: // CDs
                    listarLibrosNoDisponibles();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void listarRevistas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void listarRevistasDisponibles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void listarRevistasNoDisponibles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuMaterialesConsultasListarRevistas() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("* MATERIALES >> CONSULTAS >> LISTAR >> REVISTAS *");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Todas                            *");
                System.out.println("* 2. Disponibles                      *");
                System.out.println("* 3. No disponibles                   *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Todas
                    listarRevistas();
                    break;

                case 2: // Disponibles
                    listarRevistasDisponibles();
                    break;

                case 3: // No disponibles
                    listarRevistasNoDisponibles();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void listarCDs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void listarCDsDisponibles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void listarCDsNoDisponibles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuMaterialesConsultasListarCDs() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("* MATERIALES >> CONSULTAS >> LISTAR >> CDS *");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Todos                            *");
                System.out.println("* 2. Disponibles                      *");
                System.out.println("* 3. No disponibles                   *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Todos
                    listarCDs();
                    break;

                case 2: // Disponibles
                    listarCDsDisponibles();
                    break;

                case 3: // No disponibles
                    listarCDsNoDisponibles();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void showMenuMaterialesConsultasListar() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("********* AGREGAR MATERIAL ************");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Libros                           *");
                System.out.println("* 2. Revistas                         *");
                System.out.println("* 3. CDs                              *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Libros
                    showMenuMaterialesConsultasListarLibros();
                    break;

                case 2: // Revistas
                    showMenuMaterialesConsultasListarRevistas();
                    break;

                case 3: // CDs
                    showMenuMaterialesConsultasListarCDs();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void contarLibros() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void contarRevistas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void contarCDs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void contarMateriales() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuMaterialesConsultasContar() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("* MATERIALES >> CONSULTAS >> CONTAR   *");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Libros                           *");
                System.out.println("* 2. Revistas                         *");
                System.out.println("* 3. CDs                              *");
                System.out.println("* 4. Todos                            *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 4);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Libros
                    contarLibros();
                    break;

                case 2: // Revistas
                    contarRevistas();
                    break;

                case 3: // CDs
                    contarCDs();
                    break;

                case 4: // Todos
                    contarMateriales();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void buscarLibros() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void buscarRevistas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void buscarCDs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void buscarMateriales() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuMaterialesConsultasBuscar() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("* MATERIALES >> CONSULTAS >> BUSCAR   *");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Libros                           *");
                System.out.println("* 2. Revistas                         *");
                System.out.println("* 3. CDs                              *");
                System.out.println("* 4. Todo                             *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 4);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Libros
                    buscarLibros();
                    break;

                case 2: // Revistas
                    buscarRevistas();
                    break;

                case 3: // CDs
                    buscarCDs();
                    break;

                case 4: // Todos
                    buscarMateriales();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void showMenuMaterialesConsultas() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("********* AGREGAR MATERIAL ************");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Listar                           *");
                System.out.println("* 2. Contar                           *");
                System.out.println("* 3. Buscar                           *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Listar
                    showMenuMaterialesConsultasListar();
                    break;

                case 2: // Contar
                    showMenuMaterialesConsultasContar();
                    break;

                case 3: // Buscar
                    showMenuMaterialesConsultasBuscar();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void showMenuMateriales() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("********* AGREGAR MATERIAL ************");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Administrar                      *");
                System.out.println("* 2. Consultas                        *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 2);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Administrar
                    showMenuMaterialesAdministrar();
                    break;

                case 2: // Consultas
                    showMenuMaterialesConsultas();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void agregarUsuario() {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    private static void editarUsuario() {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    private static void eliminarUsuario() {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    private static void showMenuUsuariosAdministrar() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("***   USUARIOS >> ADMINISTRAR  ********");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Agregar                          *");
                System.out.println("* 2. Editar                           *");
                System.out.println("* 3. Eliminar                         *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Agregar
                    agregarUsuario();
                    break;

                case 2: // Editar
                    editarUsuario();
                    break;

                case 3: // Eliminar
                    eliminarUsuario();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void showMenuUsuariosConsultas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuUsuarios() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("*********    USUARIOS      ************");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Administrar                      *");
                System.out.println("* 2. Consultas                        *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 2);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Administrar
                    break;

                case 2: // Consultas
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    private static void prestarMateriales() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuPrestamosConsultas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void showMenuPrestamos() {
        boolean volver = false;
        int opt = 0;

        do {
            do {
                System.out.println("*********    PRESTAMOS     ************");
                System.out.println("*                                     *");
                System.out.println("* 0. Volver                           *");
                System.out.println("* 1. Prestar materiales               *");
                System.out.println("* 2. Consultas                        *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 2);

            switch (opt) {
                case 0: // Volver
                    volver = true;
                    break;

                case 1: // Prestar materiales
                    prestarMateriales();
                    break;

                case 2: // Consultas
                    showMenuPrestamosConsultas();
                    break;

                default: // Error!
                    System.err.println("Opción inválida!");
                    break;
            }

        } while (!volver);
    }

    public static void showMenuPrincipal() {
        boolean salir = false;
        int opt = 0;

        do {
            do {
                System.out.println("*********** BIBLIOTECA ****************");
                System.out.println("*                                     *");
                System.out.println("* 0. Salir                            *");
                System.out.println("* 1. Materiales                       *");
                System.out.println("* 2. Usuarios                         *");
                System.out.println("* 3. Préstamos                        *");
                System.out.println("*                                     *");
                System.out.println("***************************************");

                try {
                    opt = Integer.parseInt(in.readLine());
                } catch (Exception err) {
                    System.err.println(err.toString());
                }

            } while (opt < 0 || opt > 3);

            switch (opt) {
                case 0: // Salir
                    salir = true;
                    break;

                case 1: // Materiales
                    showMenuMateriales();
                    break;

                case 2: // Usuarios
                    showMenuUsuarios();
                    break;

                case 3: // Préstamos
                    showMenuPrestamos();
                    break;

                default: // Error!
                    System.err.println("Error: opción no válida!");
                    break;
            }

        } while (!salir);
    }
}
