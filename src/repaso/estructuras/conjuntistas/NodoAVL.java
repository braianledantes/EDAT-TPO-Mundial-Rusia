package repaso.estructuras.conjuntistas;

public class NodoAVL<E> {
    private E elemento;
    private int altura;
    private NodoAVL<E> izquierdo, derecho;

    public NodoAVL(E elemento) {
        this.elemento = elemento;
        this.altura = 0;
        this.izquierdo = null;
        this.derecho = null;
    }

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void recalcularAltura() {
        int altIzq = -1, altDer = -1;
        if (izquierdo != null) {
            altIzq = izquierdo.getAltura();
        }
        if (derecho != null) {
            altDer = derecho.getAltura();
        }
        altura = Math.max(altIzq, altDer) + 1;
    }

    public int calcularBalance() {
        int altIzq = -1, altDer = -1;
        if (izquierdo != null) {
            altIzq = izquierdo.getAltura();
        }
        if (derecho != null) {
            altDer = derecho.getAltura();
        }
        return altIzq - altDer;
    }

    public NodoAVL<E> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL<E> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL<E> derecho) {
        this.derecho = derecho;
    }
}
