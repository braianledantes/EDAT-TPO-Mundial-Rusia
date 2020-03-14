package lineales;

/**
 * Una pila es una estructura de datos en la que el último elemento en entrar es el primero en salir. Se denominan
 * estructuras LIFO (Last In, First Out). En esta estructura sólo se tiene acceso al tope o cima de la pila y no
 * existe el concepto de posiciones. Todas las operaciones se realizan en el extremo de la estructura llamado tope.
 * El tope de la pila corresponde al elemento que entró en último lugar, es el único elemento visible en cada
 * momento y el que saldrá en caso de eliminarse un elemento.
 *
 * @param <T> tipo de elemento que almacena la pila
 */
public interface Pila<T> {
    /**
     * Pone el elemento nuevoElem en el tope de la pila. Devuelve verdadero si el elemento se pudo apilar y
     * falso en caso contrario.
     *
     * @param elem elemento a apilar
     * @return true si hubo exito al apilar y false de lo contrario
     */
    boolean apilar(T elem);

    /**
     * Saca el elemento del tope de la pila. Devuelve verdadero si la pila no estaba vacía al momento de
     * desapilar(es decir que se pudo desapilar) y falso en caso contrario.
     *
     * @return true si hubo exito al desapilar y false de lo contrario
     */
    boolean desapilar();

    /**
     * Devuelve el elemento en el tope de la pila. Precondición: la pila no está vacía.
     *
     * @return el tope de la pila
     */
    T obtenerTope();

    /*
     * Saca todos los elementos de la pila.
     */
    void vaciar();

    /**
     * Devuelve verdadero si la pila no tiene elementos y falso en caso contrario.
     *
     * @return true si esta vacia y false de lo contrario
     */
    boolean esVacia();
}
