package biblioteca.domain;

import biblioteca.dao.DaoFactory;

/**
 * Representa un CD de la biblioteca.
 */
public class Cd extends Material {

    @Override
    public void persist() {
        DaoFactory.getCdDao().create(this);
    }

    @Override
    public void delete() {
        DaoFactory.getCdDao().delete(this);
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
