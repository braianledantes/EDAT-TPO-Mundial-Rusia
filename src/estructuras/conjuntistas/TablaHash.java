package estructuras.conjuntistas;

import estructuras.lineales.Lista;

public interface TablaHash<T> {

    /**
     * Recibe un elemento e intenta insertarlo en la tabla
     *
     * @param elem elemento a insertar
     * @return true si lo pudo insertar o false de lo contrario
     */
    boolean insertar(T elem);

    /**
     * Recibe el elemento que se desea eliminar y se procede a quitarlo de la tabla.
     *
     * @param elem elemento a eliminar
     * @return true si lo pudo eliminar o false de lo contrario
     */
    boolean eliminar(T elem);

    /**
     * Verifica que el elemento enviado por parámetro pertenece a la tabla.
     *
     * @param elem elemento a verificar
     * @return true si pertenece o false de lo contrario
     */
    boolean pertenece(T elem);

    /**
     * Verifica si la tabla está vacia.
     *
     * @return true si está vacia o false de lo contrario
     */
    boolean esVacia();

    /**
     * Recorre la tabla completa y devuelve una lista con los elementos que se encuentran almacenados en ella.
     *
     * @return lista con los elementos
     */
    Lista<T> listar();
}
