package jerarquicas;

import lineales.Cola;
import lineales.ColaDinamica;
import lineales.Lista;
import lineales.ListaDinamica;

/**
 * Un árbol binariorio es aquel árbol donde cada nodo tiene, como máximo, dos hijos.
 *
 * @param <T> Tipo de dato que almacena el árbol
 */
public class ArbolBinario<T> implements Arbol<T> {
    public static final int HIJO_IZQUIERDO = 0;
    public static final int HIJO_DERECHO = 1;

    private Nodo<T> raiz;

    /**
     * Crea un árbol binario vacio.
     */
    public ArbolBinario() {
        this.raiz = null;
    }

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
     * Dado un elemento elemNuevo y un elemento elemPadre, inserta elemNuevo desde el hijo izquierdo al hijo derecho.
     * Para insertar solo el hijo derecho y no el izquierdo, se debe insertar primero el izquierdo como nulo y luego
     * el derecho.
     *
     * @param elemNuevo elemento a insertar
     * @param elemPadre elmento padre del nodo
     * @return si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso
     */
    @Override
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
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        int result = -1, altIzq, altDer;

        if (nodo != null) {
            altIzq = altura(nodo.getIzq());
            altDer = altura(nodo.getDer());
            result = Math.max(altIzq, altDer) + 1;
        }

        return result;
    }

    @Override
    public int nivel(T elemento) {
        return nivel(elemento, raiz);
    }

    private int nivel(T elemento, Nodo<T> nodo) {
        int nivel = -1;

        if (elemento != null && nodo != null) {
            if (elemento.equals(nodo.getElem())) {
                nivel = 0;
            } else {
                nivel = nivel(elemento, nodo.getIzq());
                if (nivel == -1) {
                    nivel = nivel(elemento, nodo.getDer());
                }
                if (nivel != -1) {
                    nivel++;
                }
            }
        }

        return nivel;
    }

    @Override
    public T padre(T elemento) {
        return padre(elemento, raiz, null);
    }

    private T padre(T elemento, Nodo<T> nodoActual, Nodo<T> nodoPadre) {
        T result = null;

        if (elemento != null && nodoActual != null) {
            if (elemento.equals(nodoActual.getElem())) {
                if (nodoPadre != null) {
                    result = nodoPadre.getElem();
                }
            } else {
                result = padre(elemento, nodoActual.getIzq(), nodoActual);
                if (result == null) {
                    result = padre(elemento, nodoActual.getDer(), nodoActual);
                }
            }
        }

        return result;
    }

    @Override
    public Lista<T> listarPreorden() {
        Lista<T> lista = new ListaDinamica<>();
        listarPreorden(lista, raiz);
        return lista;
    }

    private void listarPreorden(Lista<T> lista, Nodo<T> nodo) {
        if (nodo != null) {
            lista.insertar(nodo.getElem());
            listarPreorden(lista, nodo.getIzq());
            listarPreorden(lista, nodo.getDer());
        }
    }

    @Override
    public Lista<T> listarInorden() {
        Lista<T> lista = new ListaDinamica<>();
        listarInorden(lista, raiz);
        return lista;
    }

    private void listarInorden(Lista<T> lista, Nodo<T> nodo) {
        if (nodo != null) {
            listarInorden(lista, nodo.getIzq());
            lista.insertar(nodo.getElem());
            listarInorden(lista, nodo.getDer());
        }
    }

    @Override
    public Lista<T> listarPosorden() {
        Lista<T> lista = new ListaDinamica<>();
        listarPosorden(lista, raiz);
        return lista;
    }

    private void listarPosorden(Lista<T> lista, Nodo<T> nodo) {
        if (nodo != null) {
            listarPosorden(lista, nodo.getIzq());
            listarPosorden(lista, nodo.getDer());
            lista.insertar(nodo.getElem());
        }
    }

    @Override
    public Lista<T> listarNiveles() {
        Lista<T> lista = new ListaDinamica<>();
        Cola<Nodo<T>> cola;
        Nodo<T> nodoActual;

        if (raiz != null) {
            cola = new ColaDinamica<>();
            cola.poner(raiz);
            while (!cola.esVacia()) {
                nodoActual = cola.obtenerFrente();
                cola.sacar();
                lista.insertar(nodoActual.getElem());
                if (nodoActual.tieneIzq()) {
                    cola.poner(nodoActual.getIzq());
                }
                if (nodoActual.tieneDer()) {
                    cola.poner(nodoActual.getDer());
                }
            }
        }

        return lista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArbolBinario<T> that = (ArbolBinario<T>) o;
        return equals(this.raiz, that.raiz);
    }

    private boolean equals(Nodo<T> nodothis, Nodo<T> nodoThat) {
        boolean equals = false;

        if (nodothis == null && nodoThat == null) {
            equals = true;
        } else if (nodothis != null && nodoThat != null) {
            if (nodothis.getElem().equals(nodoThat.getElem())) {
                equals = equals(nodothis.getIzq(), nodoThat.getIzq()) &&
                        equals(nodothis.getDer(), nodoThat.getDer());
            }
        }

        return equals;
    }

    @Override
    public Object clone() {
        ArbolBinario<T> clon = new ArbolBinario<>();
        clon.raiz = clone(this.raiz);
        return clon;
    }

    private Nodo<T> clone(Nodo<T> nodo) {
        Nodo<T> nodoNuevo = null;
        if (nodo != null) {
            nodoNuevo = new Nodo<>(nodo.getElem(), clone(nodo.getIzq()), clone(nodo.getDer()));
        }
        return nodoNuevo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ArbolBinario{");
        toString(sb, raiz);
        sb.append("}");
        return sb.toString();
    }

    private void toString(StringBuilder sb, Nodo<T> nodo) {
        if (nodo != null) {
            sb.append(nodo.getElem()).append(", ");
            toString(sb, nodo.getIzq());
            toString(sb, nodo.getDer());
        }
    }

    @Override
    public void vaciar() {
        raiz = null;
    }

    private static class Nodo<T> {
        private T elem;
        private Nodo<T> izq, der;

        public Nodo(T elem, Nodo<T> izq, Nodo<T> der) {
            this.elem = elem;
            this.izq = izq;
            this.der = der;
        }

        public Nodo(T elem) {
            this.elem = elem;
            this.izq = this.der = null;
        }

        public T getElem() {
            return elem;
        }

        public void setElem(T elem) {
            this.elem = elem;
        }

        public Nodo<T> getIzq() {
            return izq;
        }

        public void setIzq(Nodo<T> izq) {
            this.izq = izq;
        }

        public Nodo<T> getDer() {
            return der;
        }

        public void setDer(Nodo<T> der) {
            this.der = der;
        }

        public boolean tieneIzq() {
            return izq != null;
        }

        public boolean tieneDer() {
            return der != null;
        }

        @Override
        public String toString() {
            return "Nodo{" +
                    "elem=" + elem +
                    '}';
        }
    }
}
