package repaso.estructuras.grafos;

public class NodoAdy<E> {

    private NodoVert<E> vertice;
    private NodoAdy<E> sigAdyacente;

    public NodoAdy(NodoVert<E> vertice, NodoAdy<E> sigAdyacente) {
        this.vertice = vertice;
        this.sigAdyacente = sigAdyacente;
    }

    public NodoAdy(NodoVert<E> vertice) {
        this(vertice, null);
    }

    public NodoVert<E> getVertice() {
        return vertice;
    }

    public void setVertice(NodoVert<E> vertice) {
        this.vertice = vertice;
    }

    public NodoAdy<E> getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy<E> sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    @Override
    public String toString() {
        return "NodoAdy{" +
                "vertice=" + vertice.getElem() +
                '}';
    }
}
