package repaso.estructuras.conjuntistas;

public interface ArbolHeap<E extends Comparable<E>> {

    boolean insertar(E elemento);

    boolean eliminarCima();

    E recuperarCima();

    boolean esVacio();

    void vaciar();

}
