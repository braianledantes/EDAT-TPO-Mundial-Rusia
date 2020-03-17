package jerarquicas;

public class ArbolBinario<T> extends ArbolBinarioDinamico<T> {

    /**
     * Dado un elemento elemNuevo y un elemento elemPadre, inserta elemNuevo como hijo izquierdo o derecho
     * de la primer aparición de elemPadre, según lo indique el parámetro posicion. Para que la operación termine
     * con éxito debe existir un nodo en el árbol con elemento = elemPadre y ese nodo debe tener libre su hijo
     * posicion.
     *
     * @param elemNuevo elemento a insertar
     * @param elemPadre elmento padre del nodo
     * @param posicion  HIJO_IZQUIERDO o HIJO_DERECHO
     * @return si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso
     * @throws IllegalArgumentException si la posicion es invalida
     */
    public boolean insertar(T elemNuevo, T elemPadre, int posicion) throws IllegalArgumentException {
        boolean exito = true;
        Nodo<T> nodoPadre;

        if (posicion != HIJO_IZQUIERDO && posicion != HIJO_DERECHO) {
            throw new IllegalArgumentException();
        }
        if (elemNuevo != null) {
            if (raiz == null) {
                raiz = new Nodo<>(elemNuevo);
            } else {
                nodoPadre = obtenerNodo(elemPadre, raiz);
                if (nodoPadre != null) {
                    if (posicion == HIJO_IZQUIERDO && !nodoPadre.tieneIzq()) {
                        nodoPadre.setIzq(new Nodo<>(elemNuevo));
                    } else if (posicion == HIJO_DERECHO && !nodoPadre.tieneDer()) {
                        nodoPadre.setDer(new Nodo<>(elemNuevo));
                    } else {
                        exito = false;
                    }
                }
            }
        } else {
            exito = false;
        }

        return exito;
    }

    /**
     * Dado un elemento elemNuevo y un elemento elemPadre, agrega elemNuevo como hijo de la primer aparición
     * de elemPadre. Para que la operación termine con éxito debe existir un nodo en el árbol con elemento
     * = elemPadre. No se establece ninguna preferencia respecto a la posición del hijo respecto a sus posibles
     * hermanos. Esta operación devuelve
     *
     * @param elemNuevo elemento a insertar
     * @param elemPadre elmento padre del nodo
     * @return verdadero cuando se pudo agregar elemNuevo a la estructura y falso en caso contrario
     */
    public boolean insertar(T elemNuevo, T elemPadre) {
        boolean exito = true;
        Nodo<T> nodoPadre;

        if (elemNuevo != null) {
            if (raiz == null) {
                raiz = new Nodo<>(elemNuevo);
            } else {
                nodoPadre = obtenerNodo(elemPadre, raiz);
                if (nodoPadre != null) {
                    if (!nodoPadre.tieneIzq()) {
                        nodoPadre.setIzq(new Nodo<>(elemNuevo));
                    } else if (!nodoPadre.tieneDer()) {
                        nodoPadre.setDer(new Nodo<>(elemNuevo));
                    } else {
                        exito = false;
                    }
                }
            }
        } else {
            exito = false;
        }

        return exito;
    }

    private Nodo<T> obtenerNodo(T elem, Nodo<T> nodo) {
        Nodo<T> result = null;

        if (elem != null && nodo != null) {
            if (nodo.getElem().equals(elem)) {
                result = nodo;
            } else {
                result = obtenerNodo(elem, nodo.getIzq());
                if (result == null) {
                    result = obtenerNodo(elem, nodo.getDer());
                }
            }
        }

        return result;
    }

    @Override
    public ArbolBinario clone() {
        ArbolBinario<T> clon = new ArbolBinario<>();
        clon.raiz = clone(this.raiz);
        return clon;
    }
}
