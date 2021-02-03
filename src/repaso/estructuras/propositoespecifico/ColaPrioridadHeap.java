package repaso.estructuras.propositoespecifico;

import repaso.estructuras.conjuntistas.ArbolHeap;
import repaso.estructuras.conjuntistas.ArbolHeapMinimo;

public class ColaPrioridadHeap<E> implements ColaPrioridad<E> {

    private final ArbolHeap<CeldaColaPrioridad<E>> heap;
    private int ordenLlegada;

    public ColaPrioridadHeap(int cantidad) {
        this.heap = new ArbolHeapMinimo<>(cantidad);
        this.ordenLlegada = 0;
    }

    @Override
    public boolean insertar(E elemento, int prioridad) {
        boolean exito = false;

        if (elemento != null) {
            int orden = this.ordenLlegada + 1;
            exito = this.heap.insertar(new CeldaColaPrioridad<>(elemento, prioridad, orden));
            if (exito)
                this.ordenLlegada = orden;
        }

        return exito;
    }

    @Override
    public boolean eliminarFrente() {
        return this.heap.eliminarCima();
    }

    @Override
    public E obtenerFrente() {
        return this.heap.recuperarCima().getElemento();
    }

    @Override
    public boolean esVacia() {
        return this.heap.esVacio();
    }
}
