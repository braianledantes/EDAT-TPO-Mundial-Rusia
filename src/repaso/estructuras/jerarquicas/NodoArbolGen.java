package repaso.estructuras.jerarquicas;

public class NodoArbolGen<E> {
    private E elemento;
    private NodoArbolGen<E> hijoIzquierdo, hermanoDerecho;

    public NodoArbolGen(E elemento) {
        this.elemento = elemento;
    }

    public NodoArbolGen(E elemento, NodoArbolGen<E> hijoIzquierdo, NodoArbolGen<E> hermanoDerecho) {
        this.elemento = elemento;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hermanoDerecho = hermanoDerecho;
    }

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public NodoArbolGen<E> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoArbolGen<E> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoArbolGen<E> getHermanoDerecho() {
        return hermanoDerecho;
    }

    public void setHermanoDerecho(NodoArbolGen<E> hermanoDerecho) {
        this.hermanoDerecho = hermanoDerecho;
    }
}
