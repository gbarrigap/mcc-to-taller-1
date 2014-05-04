/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.domain;

import biblioteca.dao.CdDao;
import biblioteca.dao.DaoFactory;

/**
 *
 * @author guillermo
 */
public class CD extends Material {

    public CD(String titulo, String editorial) {
        super(titulo, editorial);
    }

    public CD() {
    }

    public void persist() {
        DaoFactory.getCdDao().create(this);
    }

}
