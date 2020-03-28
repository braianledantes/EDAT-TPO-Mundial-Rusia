package estructuras.conjuntistas;

import estructuras.jerarquicas.Arbol;
import estructuras.lineales.Lista;

public interface ArbolBinarioBusqueda<T extends Comparable<T>> extends Arbol<T> {

    /**
     * Recibe un elemento y lo agrega en el árbol de manera ordenada. Si el elemento ya se encuentra en
     * el árbol no se realiza la inserción.
     *
     * @param elem elemento a insertar
     * @return Devuelve verdadero si el elemento se agrega a la estructura y falso en caso contrario.
     */
    boolean insertar(T elem);

    /**
     * Recibe el elemento que se desea eliminar y se procede a removerlo del árbol. Si no se encuentra
     * el elemento no se puede realizar la eliminación.
     *
     * @param elem elemento a eliminar
     * @return Devuelve verdadero si el elemento se elimina de la estructura y falso en caso contrario
     */
    boolean eliminar(T elem);

    /**
     * Verifica si el elemento enviado por parámetro pertenece al árbol.
     *
     * @param elem elemento a buscar
     * @return Devuelve verdadero si el elemento recibido por parámetro está en el árbol y falso en caso contrario
     */
    boolean pertenece(T elem);

    /**
     * Recorre el árbol completo y devuelve una lista ordenada con los elementos que se encuentran
     * almacenados en él.
     *
     * @return lista de elementos
     */
    Lista<T> listar();

    /**
     * Recorre parte del árbol (sólo lo necesario) y devuelve una lista ordenada con los elementos que se
     * encuentran en el intervalo [elemMinimo, elemMaximo].
     *
     * @param elemMin elemento mínimo
     * @param elemMax elemento máximo
     * @return lista de elementos
     */
    Lista<T> listarRango(T elemMin, T elemMax);

    /**
     * Recorre la rama correspondiente y devuelve el elemento más pequeño almacenado en el árbol.
     *
     * @return elemento mínimo
     */
    T minimoElemento();

    /**
     * Recorre la rama correspondiente y devuelve el elemento más grande almacenado en el árbol.
     *
     * @return elemento máximo
     */
    T maximoElemento();
}
