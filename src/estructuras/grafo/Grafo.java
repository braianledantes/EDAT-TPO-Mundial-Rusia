package estructuras.grafo;

import estructuras.lineales.Lista;

/**
 * Un estructuras.grafo es un conjunto de objetos llamados vértices o nodos unidos por enlaces llamados aristas o arcos,
 * que permiten representar relaciones arbitrarias entre elementos de un conjunto. Visualmente, un estructuras.grafo se
 * representa como un conjunto de puntos (los vértices) unidos por líneas (aristas).
 *
 * @param <E> tipo de elemento qe almacena el estructuras.grafo.
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
     * ambos vértices con la etiqueta igual a 1.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return si el arco existe y se puede realizar la inserción con éxito devuelve verdadero, en caso contrario devuelve falso
     */
    boolean insertarArco(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino) se agrega a la estructura el arco que une
     * ambos vértices, la una etiqueda que es la distancia entre vertices.
     *
     * @param origen   vertice origen
     * @param destino  vertice destino
     * @param etiqueta distancia entre origen y destino
     * @return si el arco existe y se puede realizar la inserción con éxito devuelve verdadero, en caso contrario devuelve falso
     */
    boolean insertarArco(E origen, E destino, int etiqueta);

    /**
     * Dados dos elementos de tipoVertice (origen y destino) se quita de la estructura el arco que une
     * ambos vértices desde origen a destino.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return si el arco existe y se puede realizar la eliminación con éxito devuelve verdadero, en caso contrario devuelve falso
     */
    boolean eliminarArco(E origen, E destino);

    /**
     * Verifica si existe el elemento vertice en el estructuras.grafo.
     *
     * @param elem elemento a verificar
     * @return true si existe y false de lo contrario
     */
    boolean exiteVertice(E elem);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), verifca si existe un arco desde origen a destino.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return true si el arco existe y en caso contrario devuelve falso
     */
    boolean existeArco(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), verifca si existe un camino desde origen a destino.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return true si el arco existe y en caso contrario devuelve falso
     */
    boolean existeCamino(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), devuelve un camino (lista de vértices) que
     * indique el camino que pasa por menor distancía que permite llegar desde el vértice origen al vértice destino.
     * Si hay más de un camino con igual distancía, devuelve cualquiera de ellos. Si alguno de los
     * vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return lista con el camino
     */
    Lista<E> caminoMasCorto(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), devuelve un camino (lista de vértices) que
     * indique el camino que pasa por menos vértices que permite llegar desde el vértice origen al vértice destino.
     * Si hay más de un camino con igual cantidad de vértices, devuelve cualquiera de ellos. Si alguno de los
     * vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return lista con el camino
     */
    Lista<E> caminoConMenosVertices(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), devuelve un camino (lista de vértices) que
     * indique el camino que pasa por menor distancía que permite llegar desde el vértice origen al vértice destino,
     * pasando por uno intermedio.
     * Si hay más de un camino con igual distancía, devuelve cualquiera de ellos. Si alguno de los
     * vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.
     *
     * @param origen   vertice origen
     * @param destino1 vertice intermedio
     * @param destino2 vertice destino final
     * @return lista con el camino
     */
    Lista<E> caminoMasCorto(E origen, E destino1, E destino2);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), devuelve un camino (lista de vértices) que
     * indique el camino que pasa por más vértices (sin ciclos) que permite llegar desde el vértice origen al vértice
     * destino. Si hay más de un camino con igual cantidad de vértices, devuelve cualquiera de ellos. Si alguno
     * de los vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return lista con el camino
     */
    Lista<E> caminoMasLargo(E origen, E destino);

    /**
     * Dados dos elementos de tipoVertice (origen y destino), devuelve una lista con los posibles caminos (listas) desde
     * el vértice origen al vértice destino.
     * Si hay más de un camino con igual distancía, devuelve cualquiera de ellos. Si alguno de los
     * vértices no existe o no hay camino posible entre ellos devuelve la lista vacía.
     *
     * @param origen  vertice origen
     * @param destino vertice destino
     * @return lista con los caminos
     */
    Lista<Lista<E>> caminosPosibles(E origen, E destino);

    /**
     * Devuelve una lista con los vértices del estructuras.grafo visitados según el recorrido en profundidad explicado
     * en la sección anterior.
     *
     * @return una lista con los elementos
     */
    Lista<E> listarEnProfundidad();

    /**
     * Devuelve una lista con los vértices del estructuras.grafo visitados según el recorrido en anchura explicado en la
     * sección anterior.
     *
     * @return una lista con los elementos
     */
    Lista<E> listarEnAnchura();

    /**
     * Verifica si el estructuras.grafo tiene o no elementos.
     *
     * @return devuelve falso si hay al menos un vértice cargado en el estructuras.grafo y verdadero en caso contrario
     */
    boolean esVacio();

    /**
     * Quita todos los elementos del grafo.
     */
    void vaciar();
}
