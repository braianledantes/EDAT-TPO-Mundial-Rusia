package estructuras.jerarquicas;

import estructuras.lineales.Lista;

public interface Arbol<T> {

    /**
     * Devuelve falso si hay al menos un elemento cargado en el árbol y verdadero en caso contrario.
     *
     * @return si esta vacio
     */
    boolean esVacio();

    /**
     * Devuelve la altura del árbol, es decir la longitud del camino más largo desde la raíz hasta una hoja (Nota:
     * un árbol vacío tiene altura -1 y una hoja tiene altura 0).
     *
     * @return altura del arbol
     */
    int altura();

    /**
     * Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en el árbol devuelve -1.
     *
     * @param elemento elemento a buscar nivel
     * @return nivel del elemento
     */
    int nivel(T elemento);

    /**
     * Dado un elemento devuelve el valor almacenado en su nodo padre (busca la primera aparición de elemento).
     *
     * @param elemento elemento hijo
     * @return elemento padre
     */
    T padre(T elemento);

    /**
     * En un recorrido en preorden de un árbol A, la raíz de A es visitada primero y luego
     * los subárboles izquierdo y derecho son recorridos recursivamente con el mismo criterio: se lista la raíz y luego
     * sus subárboles, hasta llegar a un subárbol vacío.
     *
     * @return lista con los elementos
     */
    Lista<T> listarPreorden();

    /**
     * En un recorrido en inorden de un árbol A, primero se visitan los elementos del subárbol
     * izquierdo de la raíz, luego se visita la raíz de A y por último los elementos del subárbol derecho.
     *
     * @return lista con los elementos
     */
    Lista<T> listarInorden();

    /**
     * En un recorrido en posorden de un árbol A, primero se visitan los elementos del
     * subárbol izquierdo y derecho de la raíz y luego la raíz de A.
     *
     * @return lista con los elementos
     */
    Lista<T> listarPosorden();

    /**
     * En el recorrido por niveles el orden de visita de los nodos es de acuerdo al nivel del
     * mismo, comenzando con los nodos del nivel cero, incrementando el nivel en uno, hasta llegar al nivel máximo
     * del árbol. Los nodos del mismo nivel se listan de izquierda a derecha.
     *
     * @return lista con los elementos
     */
    Lista<T> listarNiveles();

    /**
     * Quita todos los elementos de la estructura. El manejo de memoria es similar al explicado anteriormente
     * para estructuras estructuras.lineales dinámicas.
     */
    void vaciar();

    /**
     * Determina si dicha lista coincide exactamente con al menos un camino del árbol que comience
     * en la raíz y termine en una hoja.
     *
     * @param lista lista del camino
     * @return si verifica el camino
     */
    boolean verificarPatron(Lista<T> lista);

    /**
     * Devuelve una lista con la frontera del árbol. Se dene frontera de un
     * árbol, la secuencia formada por los elementos almacenados en las hojas del árbol, tomadas de izquierda
     * a derecha.
     *
     * @return lista con la frontera
     */
    Lista<T> frontera();
}
