package estructuras.grafo;

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

    public void setVerticeA(T verticeA) {
        this.verticeA = verticeA;
    }

    public T getVerticeB() {
        return verticeB;
    }

    public void setVerticeB(T verticeB) {
        this.verticeB = verticeB;
    }

    public E getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(E etiqueta) {
        this.etiqueta = etiqueta;
    }
}
