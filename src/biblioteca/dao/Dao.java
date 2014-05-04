/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao;

import java.util.List;

/**
 *
 * @author guillermo
 */
public interface Dao<T> {

    public void create(T t);

    public T retrieve(int id);
    
    public T retrieve(T t);

    public T update();

    public void delete();

    public List<T> retrieveAll();

    public int getCount();
}
