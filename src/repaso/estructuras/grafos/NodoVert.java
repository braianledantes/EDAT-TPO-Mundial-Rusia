package repaso.estructuras.grafos;

public class NodoVert<E> {

    private E elemento;

    private NodoVert<E> sigVertice;

    private NodoAdy<E> primerAdy;

    public NodoVert(E elemento, NodoVert<E> sigVertice, NodoAdy<E> primerAdy) {
        this.elemento = elemento;
        this.sigVertice = sigVertice;
        this.primerAdy = primerAdy;
    }

    public NodoVert(E elemento, NodoVert<E> sigVertice) {
        this(elemento, sigVertice, null);
    }

    public NodoVert(E elemento) {
        this(elemento, null, null);
    }

    public void eliminarAdyacente(NodoVert<E> vertice) {
        NodoAdy<E> nodoAdyAnt = null;
        NodoAdy<E> nodoAdy = this.primerAdy;
        while (nodoAdy != null && nodoAdy.getVertice() != vertice) {
            nodoAdyAnt = nodoAdy;
            nodoAdy = nodoAdy.getSigAdyacente();
        }

        if (nodoAdy != null) {
            if (nodoAdyAnt == null) {
                this.primerAdy = nodoAdy.getSigAdyacente();
            } else {
                nodoAdyAnt.setSigAdyacente(nodoAdy.getSigAdyacente());
            }
        }
    }

    public E getElem() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public NodoVert<E> getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(NodoVert<E> sigVertice) {
        this.sigVertice = sigVertice;
    }

    public NodoAdy<E> getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdy<E> primerAdy) {
        this.primerAdy = primerAdy;
    }

    @Override
    public String toString() {
        return "NodoVert{" +
                "elemento=" + elemento +
                '}';
    }
}
