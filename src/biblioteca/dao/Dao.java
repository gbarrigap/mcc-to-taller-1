package biblioteca.dao;

import java.util.List;

/**
 * Define el comportamiento base de los objetos con persistencia del programa.
 * <p>
 * Dao (DataAccessObject), define el comportamiento mínimo de todos los objetos
 * del programa que deban ser almacenados en algún medio de memoria secundaria.
 * <p>
 * La idea es que el programa utilice esta interfaz para desacoplar el código de
 * acceso a datos, y permitir el cambio de, por ejemplo, un DBMS a otro, sin que
 * exista gran repercusión en el código.
 * <p>
 * Por cada materiale de la bibiblioteca existirá una interfaz que extienda
 * esta, permitiendo agregar reglas de comportamiento propios de dicha interfaz.
 * Por ejemplo, la interfaz <code>LibroDao</code>, definirá el método abstracto
 * <code>getByIsbn(String isbn)</code>, propio de los libros. Luego, la
 * implementación de estas interfaces se encargará de acceder a los datos,
 * siendo este acceso transparente al usuario de las interfaces, mediante una
 * fábrica. Por ejemplo, para persistir un libro en una base de datos Sqlite, la
 * fábrica implementará el método <code>getCdDao()</code>, que retornará una
 * instancia de la clase <code>CdDaoSqlite</code> que implementará la interfaz
 * <code>CdDao</code>, por lo que el usuario sólo tendrá que crear una instancia
 * del tipo <code>CdDao</code> para acceder a sus métodos. Lo anterior se puede
 * resumir en las líneas de código siguiente:
 * <p>
 * En biblioteca.dao.DaoFactory.java
 * <pre>
 *  public class DaoFactory {
 *      ...
 *      public CdDao getCdDao() {
 *          return new CdDaoSqlite(DaoFactory.getConnectionDao());
 *      }
 *      ...
 *  }
 * </pre>
 * <p>
 * En biblioteca.domainCd.java
 * <pre>
 *  public class Cd {
 *      ...
 *      public void persist() {
 *          CdDao dao = DaoFactory.getCdDao();
 *          dao.persist(this);
 *      }
 *      ...
 *  }
 * </pre>
 * <p>
 * en biblioteca.domain.Cd
 * <pre>
 *  Cd cd = new Cd();
 *  ...
 *  cd.persist();
 * </pre>
 *
 * @param <T>
 */
public interface Dao<T> {

    /**
     * Crea un material en el repositorio.
     *
     * @param t El material que se creará
     */
    public void create(T t);

    /**
     * Obtiene un material del repositorio usando un identificador.
     *
     * @param id El identificador del material
     * @return El material con el identificador recibido
     */
    public T retrieve(int id);

    /**
     * Actualiza en el repositorio los datos del material recibido.
     *
     * @param t El material recibido
     */
    public void update(T t);

    /**
     * Elimina del repositorio el material recibido.
     *
     * @param t El material recibido
     */
    public void delete(T t);

    /**
     * Obtiene una lista con todos los materiales del repositorio.
     *
     * @return una lista con materiales.
     */
    public List<T> retrieveAll();

}
