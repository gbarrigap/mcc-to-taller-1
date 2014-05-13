# Taller 1 - Tecnología de Objetos

## ToDo List

* Comentar código donde corresponda
* Eliminar código comentado (commented out)
* Estandarizar nombres de variables
* En biblioteca.dao.Dao, ¿es correcto el método retrieve(T t)?, ¿cambiar por
un método load(T t)?, ¿delegar la carga al objeto del dominio?
* ¿Será necesario una clase biblioteca.dao.MaterialDao para acceder a todos los
materiales del repositorio en una llamada?
* ¿Es necesario tener tantos constructores? ¿Sera mejor un constructor vacio
dejando la inicializacion de valores a metodos setter?
* ¿Es necesario el identificador "eid" para las copias?
* ¿biblioteca.domain.Material.addCopia y biblioteca.domain.Material.addCopias
deberían ser los único métodos para acceder a las copias? ¿setCopias, etc?

## Descripción de la problemática

El señor B. trabaja en una biblioteca. En ella, se debe registrar la existencia
de material bibliográfico. Existen tres tipos de material bibliográfico:
libros, revistas y CDs. Los libros tienen un Título, Autor, Editorial y número
ISBN (que identifica un libro de manera única). Las revistas tienen un Título,
una Editorial, y una periodicidad (quincenal, mensual, trimestral, etc.).
Los CDs Tienen un Título, una Editorial, y en algunos casos, acompañan a un
libro o una revista. Es necesario mantener un registro de cuantos libros,
revistas y CDs se mantienen en la biblioteca, así como el registro del total de
material.

Se pide que diseñe e implemente un programa orientado a objetos en Java, que
permita **mantener este registro** y **calcular las existencias**.

Observaciones: Se asume que por cada libro, revista o CD en inventario, existe
una correspondiente instancia de la clase respectiva en el sistema. Por lo
tanto, cada vez que se crea una nueva instancia, se deben actualizar los valores
de existencia.
