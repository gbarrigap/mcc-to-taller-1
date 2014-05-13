package biblioteca.domain;

public class Copia {

    private Integer eid;
    private int numero;

    public Copia(int numero) {
        this.numero = numero;
    }

    public Copia() {
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
