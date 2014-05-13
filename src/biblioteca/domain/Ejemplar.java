package biblioteca.domain;

public class Ejemplar {

    private Integer eid;
    private int numero;

    public Ejemplar(int numero) {
        this.numero = numero;
    }

    public Ejemplar() {
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
