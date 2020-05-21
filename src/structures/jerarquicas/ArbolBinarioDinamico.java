package structures.jerarquicas;

import structures.lineales.Cola;
import structures.lineales.ColaDinamica;
import structures.lineales.Lista;
import structures.lineales.ListaDinamica;

import java.io.Serializable;

/**
 * Un árbol binariorio es aquel árbol donde cada nodo tiene, como máximo, dos hijos.
 *
 * @param <T> Tipo de dato que almacena el árbol
 */
public abstract class ArbolBinarioDinamico<T> implements Arbol<T>, Serializable {
    public static final int HIJO_IZQUIERDO = 0;
    public static final int HIJO_DERECHO = 1;

    protected Nodo<T> raiz;

    /**
     * Crea un árbol binario vacio.
     */
    public ArbolBinarioDinamico() {
        this.raiz = null;
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public int altura() {
        return altura(raiz);
    }

    protected int altura(Nodo<T> nodo) {
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
    public boolean verificarPatron(Lista<T> lista) {
        return verificarPatron(lista, this.raiz, 1);
    }

    private boolean verificarPatron(Lista<T> lista, Nodo<T> nodo, int pos) {
        boolean verifica = false;

        if (nodo != null && pos <= lista.longitud() && lista.recuperar(pos).equals(nodo.getElem())) {
            if (!nodo.tieneIzq() && !nodo.tieneIzq() && pos == lista.longitud()) {
                verifica = true;
            } else {
                pos++;
                verifica = verificarPatron(lista, nodo.getIzq(), pos);
                if (!verifica) {
                    verifica = verificarPatron(lista, nodo.getDer(), pos);
                }
            }
        }

        return verifica;
    }

    @Override
    public Lista<T> frontera() {
        Lista<T> lista = new ListaDinamica<>();
        frontera(lista, this.raiz);
        return lista;
    }

    private void frontera(Lista<T> lista, Nodo<T> nodo) {
        if (nodo != null) {
            if (!nodo.tieneIzq() && !nodo.tieneDer())
                lista.insertar(nodo.getElem());
            frontera(lista, nodo.getIzq());
            frontera(lista, nodo.getDer());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ArbolBinarioDinamico)) return false;
        ArbolBinarioDinamico<T> that = (ArbolBinarioDinamico<T>) o;
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

    protected Nodo<T> clone(Nodo<T> nodo) {
        Nodo<T> nodoNuevo = null;
        if (nodo != null) {
            nodoNuevo = new Nodo<>(nodo.getElem(), clone(nodo.getIzq()), clone(nodo.getDer()));
        }
        return nodoNuevo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getName() + "{ raiz=");
        if (raiz != null) {
            sb.append(raiz.getElem());
        } else {
            sb.append("null");
        }
        sb.append("\n");
        toString(sb, raiz);
        sb.append("}");
        return sb.toString();
    }

    private void toString(StringBuilder sb, Nodo<T> nodo) {
        if (nodo != null) {
            toString(sb, nodo.getIzq());

            sb.append(nodo.getElem()).append(" (").append(nodo.getAltura()).append(')').append(" -> ");
            if (nodo.tieneIzq()) {
                sb.append(nodo.getIzq().getElem()).append("; ");
            } else {
                sb.append("null").append("; ");
            }
            if (nodo.tieneDer()) {
                sb.append(nodo.getDer().getElem());
            } else {
                sb.append("null");
            }
            sb.append("\n");

            toString(sb, nodo.getDer());
        }
    }

    @Override
    public void vaciar() {
        raiz = null;
    }

    protected static class Nodo<T> implements Serializable {
        private T elem;
        private Nodo<T> izq, der;
        private int altura;

        public Nodo(T elem, Nodo<T> izq, Nodo<T> der) {
            this.elem = elem;
            this.izq = izq;
            this.der = der;
            this.altura = 0;
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

        public int getAltura() {
            return altura;
        }

        public void setAltura(int altura) {
            this.altura = altura;
        }

        public void recalcularAltura() {
            int altIzq = -1, altDer = -1;
            if (izq != null) {
                //izq.recalcularAltura();
                altIzq = izq.getAltura();
            }
            if (der != null) {
                //der.recalcularAltura();
                altDer = der.getAltura();
            }
            altura = Math.max(altIzq, altDer) + 1;
        }

        @Override
        public String toString() {
            return "Nodo{" +
                    "elem=" + elem +
                    ", altura=" + altura +
                    '}';
        }
    }
}
