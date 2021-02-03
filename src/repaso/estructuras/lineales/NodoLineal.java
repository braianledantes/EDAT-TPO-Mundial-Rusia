package repaso.estructuras.lineales;

public class NodoLineal<E> {
    private E elemento;
    private NodoLineal<E> enlace;

    public NodoLineal(E elemento, NodoLineal<E> enlace) {
        this.elemento = elemento;
        this.enlace = enlace;
    }

    public NodoLineal(E elemento) {
        this.elemento = elemento;
    }

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public NodoLineal<E> getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoLineal<E> enlace) {
        this.enlace = enlace;
    }
}
