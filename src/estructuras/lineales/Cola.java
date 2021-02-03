package estructuras.lineales;

/**
 * Una cola es una estructura de datos en la que el primer elemento en entrar es el primero en salir. Se
 * denominan estructuras FIFO (First In, First Out). Se tiene acceso al frente de la cola para ver el primer
 * elemento o eliminarlo, y al final para agregar nuevos elementos. El frente de la cola corresponde al elemento que
 * entró primero, es decir el que saldrá en la próxima eliminación. El final de la cola corresponde al elemento que
 * entró en último lugar.
 *
 * @param <T>
 */
public interface Cola<T> {
    /**
     * Pone el elemento al final de la cola. Devuelve verdadero si el elemento se pudo agregar en la estructura
     * y falso en caso contrario.
     *
     * @param elem elemento a insertar
     * @return true si hubo exito al insertar y false de lo contrario
     */
    boolean poner(T elem);

    /**
     * Saca el elemento que está en el frente de la cola. Devuelve verdadero si el elemento se pudo sacar (la
     * estructura no estaba vacía) y falso en caso contrario.
     *
     * @return true si hubo exito al quitar y false de lo contrario
     */
    boolean sacar();

    /**
     * Devuelve el elemento que está en el frente. Precondición: la cola no está vacía.
     *
     * @return el elemento del frente
     */
    T obtenerFrente();

    /**
     * Devuelve verdadero si la cola no tiene elementos y falso en caso contrario.
     *
     * @return Devuelve verdadero si la cola no tiene elementos y falso en caso contrario
     */
    boolean esVacia();

    /**
     * Saca todos los elementos de la cola.
     */
    void vaciar();
}
