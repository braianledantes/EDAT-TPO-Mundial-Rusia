package lineales;

/**
 * La lista es una secuencia de elementos, que constituye una estructura flexible, que puede crecer o acortarse
 * según sea necesario. Los elementos de una lista se pueden insertar, acceder, borrar y consultar por posiciones,
 * siendo 1 la primer posición.
 *
 * @param <T>
 */
public interface Lista<T> {

    /**
     * Agrega un elemento pasado por parámetro a la última posición de la lista.
     *
     * @param elem elelemto a ingresar
     * @return verdadero si se puede insertar correctamente y falso en caso contrario
     */
    boolean insertar(T elem);

    /**
     * Agrega el elemento pasado por parámetro en la posición pos, de manera que la cantidad de elementos
     * de la lista se incrementa en 1. Para una inserción exitosa, la posición recibida debe ser 1 ≤ pos ≤
     * longitud(lista) + 1.
     *
     * @param elem elelemto a ingresar
     * @param pos  posición donde se va a insertar el elemento, debe ser entre 1 y n
     * @return verdadero si se puede insertar correctamente y falso en caso contrario
     */
    boolean insertar(T elem, int pos);

    /**
     * Borra el elemento de la posición pos, por lo que la cantidad de elementos de la lista disminuye
     * en uno. Para una eliminación exitosa, la lista no debe estar vacía y la posición recibida debe ser
     * 1 ≤ pos ≤ longitud(lista). Devuelve verdadero si se pudo eliminar correctamente y falso en caso contrario.
     *
     * @param pos elemento a eliminar
     * @return true si pudo eliminar el elemento o false de lo contrario
     */
    boolean eliminar(int pos);

    /**
     * Devuelve el elemento de la posición pos. La precondición es que la posición sea válida.
     *
     * @param pos pos del elemento
     * @return elemento en pos, null si no existe
     */
    T recuperar(int pos);

    /**
     * Devuelve la posición en la que se encuentra la primera ocurrencia de elem dentro de la lista. En caso
     * de no encontrarlo devuelve -1.
     *
     * @param elem elemento a localizar
     * @return pos del elem, -1 si no existe
     */
    int localizar(T elem);

    /**
     * Devuelve verdadero si la lista no tiene elementos y falso en caso contrario.
     *
     * @return verdadero si la lista no tiene elementos y falso en caso contrario.
     */
    boolean estaVacia();

    /**
     * Quita todos los elementos de la lista.
     */
    void vaciar();

    /**
     * Devuelve la cantidad de elementos de la lista.
     *
     * @return cant de elementos.
     */
    int longitud();
}
