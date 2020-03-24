package grafo;

import lineales.Lista;

/**
 * Un grafo es un conjunto de objetos llamados vértices o nodos unidos por enlaces llamados aristas o arcos,
 * que permiten representar relaciones arbitrarias entre elementos de un conjunto. Visualmente, un grafo se
 * representa como un conjunto de puntos (los vértices) unidos por líneas (aristas).
 *
 * @param <E> tipo de elemento qe almacena el grafo.
 */
public interface Grafo<E> {

    /**
     * Dado un elemento de tipoVertice se lo agrega a la estructura controlando que no se inserten vértices
     * repetidos.
     *
     * @param elem elemento a insertar
     * @return si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso
     */
    boolean insertarVertice(E elem);

    /**
     * Dado un elemento de tipoVertice se lo quita de la estructura. Si se encuentra el vértice, también
     * deben eliminarse todos los arcos que lo tengan como origen o destino.
     *
     * @param elem elemento a eliminar
     * @return Si se puede realizar la eliminación con éxito devuelve verdadero, en caso contrario devuelve falso
     */
    boolean eliminarVertice(E elem);

    /**
     * Dados dos elementos de tipoVertice (origen y destino) se agrega a la estructura el arco que une
     * ambos vértices.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return si el arco existe y se puede realizar la inserción con éxito devuelve verdadero, en caso contrario devuelve falso
     */
    boolean insertarArco(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino) se quita de la estructura el arco que une
     * ambos vértices.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return si el arco existe y se puede realizar la eliminación con éxito devuelve verdadero, en caso contrario devuelve falso
     */
    boolean eliminarArco(E origen, E destino);

    /**
     * Verifica si existe el elemento vertice en el grafo.
     *
     * @param elem elemento a verificar
     * @return true si existe y false de lo contrario
     */
    boolean exiteVertice(E elem);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), verifca si existe un arco entre ellos.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return true si el arco existe y en caso contrario devuelve falso
     */
    boolean existeArco(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), verifca si existe un camino entre ellos.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return true si el arco existe y en caso contrario devuelve falso
     */
    boolean existeCamino(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), devuelve un camino (lista de vértices) que
     * indique el camino que pasa por menos vértices que permite llegar del vértice origen al vértice destino.
     * Si hay más de un camino con igual cantidad de vértices, devuelve cualquiera de ellos. Si alguno de los
     * vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return lista con el camino
     */
    Lista<E> caminoMasCorto(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), devuelve un camino (lista de vértices) que
     * indique el camino que pasa por más vértices (sin ciclos) que permite llegar del vértice origen al vértice
     * destino. Si hay más de un camino con igual cantidad de vértices, devuelve cualquiera de ellos. Si alguno
     * de los vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return lista con el camino
     */
    Lista<E> caminoMasLargo(E origen, E destino);

    /**
     * Devuelve una lista con los vértices del grafo visitados según el recorrido en profundidad explicado
     * en la sección anterior.
     *
     * @return una lista con los elementos
     */
    Lista<E> listarEnProfundidad();

    /**
     * Devuelve una lista con los vértices del grafo visitados según el recorrido en anchura explicado en la
     * sección anterior.
     *
     * @return una lista con los elementos
     */
    Lista<E> listarEnAnchura();

    /**
     * Verifica si el grafo tiene o no elementos.
     *
     * @return devuelve falso si hay al menos un vértice cargado en el grafo y verdadero en caso contrario
     */
    boolean esVacio();
}
