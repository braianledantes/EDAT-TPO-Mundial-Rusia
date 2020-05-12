package structures.propositoEspecifico;

import structures.lineales.Lista;

public interface Diccionario<K, V> {

    /**
     * Recibe la clave que es única y el dato (información asociada a ella). Si no existe en la estructura un
     * elemento con igual clave, agrega el par (clave, dato) a la estructura.
     *
     * @param key   clave
     * @param value valor
     * @return si la operación termina con éxito devuelve verdadero y falso en caso contrario.
     */
    boolean insertar(K key, V value);

    /**
     * Si la operación termina con éxito devuelve verdadero y falso en caso contrario.
     *
     * @param key clave del dato
     * @return si lo encuentra y la operación de eliminación termina con éxito devuelve verdadero y falso en caso contrario
     */
    boolean eliminar(K key);

    /**
     * Verifica si el elemento con la clave enviada por parametro existe en la estructurra.
     *
     * @param key clave del dato
     * @return devuelve verdadero si en la estructura se encuentra almacenado un elemento con la clave recibido por parámetro, caso contrario devuelve falso.
     */
    boolean existeClave(K key);

    /**
     * si en la estructura se encuentra almacenado un elemento con la clave recibida por parámetro, devuelve
     * la información asociada a ella. Precondición: si no existe un elemento con esa clave no se puede asegurar
     * el funcionamiento de la operación
     *
     * @param key clave del dato
     * @return dato con la clave key
     */
    V obtenerDato(K key);

    /**
     * Recorre la estructura completa y devuelve una lista ordenada con las claves de los elementos que se
     * encuentran almacenados en ella.
     *
     * @return lista con las claves
     */
    Lista<K> listarClaves();

    /**
     * Recorre la estructura completa y devuelve una lista ordenada con la información asociada de los
     * elementos que se encuentran almacenados en ella.
     *
     * @return lista con los datos almacenados
     */
    Lista<V> listarDatosOrdenados();

    /**
     * Devuelve falso si hay al menos un elemento cargado en el árbol y verdadero en caso contrario.
     *
     * @return si esta vacio
     */
    boolean esVacio();

    /**
     * Quita todos los elementos de la estructura.
     */
    void vaciar();
}
