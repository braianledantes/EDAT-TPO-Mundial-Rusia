package structures.grafo;

/**
 * Clase utilizada por otras clases ajenas a Grafo
 *
 * @param <T> Tipo de elemento que guarda el grafo
 * @param <E> Etiqueta del arco
 */
public class Arco<T, E> {
    private T verticeA, verticeB;
    private E etiqueta;

    public Arco(T verticeA, T verticeB, E etiqueta) {
        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.etiqueta = etiqueta;
    }

    public T getVerticeA() {
        return verticeA;
    }

    public T getVerticeB() {
        return verticeB;
    }

    public E getEtiqueta() {
        return etiqueta;
    }
}
