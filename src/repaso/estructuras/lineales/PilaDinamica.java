package repaso.estructuras.lineales;

public class PilaDinamica<E> implements Pila<E> {
    private NodoLineal<E> tope = null;

    @Override
    public boolean apilar(E elemento) {
        if (elemento != null) {
            tope = new NodoLineal<>(elemento, tope);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean desapilar() {
        if (tope != null) {
            tope = tope.getEnlace();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public E obtenerTope() {
        return (tope != null) ? tope.getElemento() : null;
    }

    @Override
    public void vaciar() {
        tope = null;
    }

    @Override
    public boolean esVacia() {
        return tope == null;
    }
}
