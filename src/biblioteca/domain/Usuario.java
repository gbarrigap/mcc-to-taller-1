package biblioteca.domain;

/**
 * Representa un usuario de la biblioteca que puede pedir materiales.
 */
public class Usuario {
    
    private int uid;
    private int rut;
    private String nombre;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int uid) {
        this.rut = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
