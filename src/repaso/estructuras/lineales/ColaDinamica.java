package repaso.estructuras.lineales;

public class ColaDinamica<E> implements Cola<E> {
    private NodoLineal<E> inicio = null;
    private NodoLineal<E> fin = null;

    @Override
    public boolean poner(E elemento) {
        if (elemento != null) {
            if (fin == null) {
                inicio = fin = new NodoLineal<>(elemento);
            } else {
                fin.setEnlace(new NodoLineal<>(elemento));
                fin = fin.getEnlace();
            }
            return true;
        }
        return false;
    }

    @Override
    public E obtenerFrente() {
        if (inicio == null) return null;
        return inicio.getElemento();
    }

    @Override
    public boolean sacar() {
        if (inicio != null) {
            inicio = inicio.getEnlace();
            if (inicio == null) fin = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean esVacia() {
        return inicio == null && fin == null;
    }

    @Override
    public void vaciar() {
        inicio = fin = null;
    }
}
