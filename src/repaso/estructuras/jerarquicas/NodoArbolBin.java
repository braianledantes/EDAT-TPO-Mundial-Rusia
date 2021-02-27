package repaso.estructuras.jerarquicas;

public class NodoArbolBin<E> {
    private E elemento;
    private NodoArbolBin<E> izquierdo, derecho;

    public NodoArbolBin(E elemento, NodoArbolBin<E> izquierdo, NodoArbolBin<E> derecho) {
        this.elemento = elemento;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public NodoArbolBin(E elemento) {
        this.elemento = elemento;
    }

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public NodoArbolBin<E> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbolBin<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbolBin<E> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbolBin<E> derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return "NodoArbolBin{" +
                "elemento=" + elemento + '}';
    }
}
