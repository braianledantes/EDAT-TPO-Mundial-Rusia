package structures.jerarquicas;

import structures.lineales.Cola;
import structures.lineales.ColaDinamica;
import structures.lineales.Lista;
import structures.lineales.ListaDinamica;

import java.io.Serializable;

/**
 * Árbol normal, donde cada vértice puede tener varios hijos.
 *
 * @param <T> tipo de dato que almacena el árbol
 */
public class ArbolGenerico<T> implements Arbol<T>, Serializable {

    /**
     * Vértice raiz del árbol.
     */
    private Nodo<T> raiz;

    /**
     * Crea un árbol genérico vacio.
     */
    public ArbolGenerico() {
        this.raiz = null;
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
        Nodo<T> nodoPadre, nodoHijo;

        if (elemNuevo != null) {
            if (raiz == null) {
                raiz = new Nodo<>(elemNuevo);
            } else {
                nodoPadre = obtenerNodo(elemPadre, this.raiz);
                if (nodoPadre != null) {
                    if (!nodoPadre.tieneHijoIzq()) {
                        nodoPadre.setHijoIzq(new Nodo<>(elemNuevo));
                    } else {
                        nodoHijo = nodoPadre.getHijoIzq();
                        while (nodoHijo.tieneHerDer()) {
                            nodoHijo = nodoHijo.getHerDer();
                        }
                        nodoHijo.setHerDer(new Nodo<>(elemNuevo));
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
                result = obtenerNodo(elem, nodo.getHijoIzq());
                if (result == null) {
                    result = obtenerNodo(elem, nodo.getHerDer());
                }
            }
        }

        return result;
    }

    /**
     * Verifica si el elemento enviado por parámetro existe en el árbol.
     *
     * @param elem elemento a buscar
     * @return verdadero si lo encontró y false de lo contrario
     */
    public boolean pertenece(T elem) {
        return obtenerNodo(elem, this.raiz) != null;
    }

    /**
     * Si el elemento se encuentra en el árbol, devuelve una lista con el camino desde la raíz hasta dicho elemento
     * (es decir, con los ancestros del elemento). Si el elemento no está en el árbol devuelve la lista vacía.
     *
     * @param elem elemento a obtener ancestros
     * @return una lista con todos los ancestros desde la raíz del árbol
     */
    public Lista<T> ancestros(T elem) {
        Lista<T> lista = new ListaDinamica<>();
        ancestros(elem, this.raiz, lista);
        return lista;
    }

    private void ancestros(T elem, Nodo<T> nodo, Lista<T> lista) {
        if (elem != null && nodo != null) {
            if (nodo.getElem().equals(elem)) {
                lista.insertar(elem, 1);
            } else {
                ancestros(elem, nodo.getHijoIzq(), lista);
                if (!lista.estaVacia()) {
                    lista.insertar(nodo.getElem(), 1);
                } else {
                    ancestros(elem, nodo.getHerDer(), lista);
                }
            }
        }
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    public int altura(Nodo<T> nodo) {
        int altura = -1;

        if (nodo != null) {
            altura = altura(nodo.getHijoIzq()) + 1;
            altura = Math.max(altura, altura(nodo.getHerDer()));
        }

        return altura;
    }

    @Override
    public int nivel(T elemento) {
        return nivel(elemento, this.raiz);
    }

    private int nivel(T elemento, Nodo<T> nodo) {
        int nivel = -1;

        if (elemento != null && nodo != null) {
            if (elemento.equals(nodo.getElem())) {
                nivel = 0;
            } else {
                nivel = nivel(elemento, nodo.getHijoIzq());
                if (nivel == -1) {
                    nivel = nivel(elemento, nodo.getHerDer());
                } else {
                    nivel++;
                }
            }
        }

        return nivel;
    }

    @Override
    public T padre(T elemento) {
        return padre(elemento, this.raiz, null);
    }

    private T padre(T elemento, Nodo<T> nodo, Nodo<T> nodoPadre) {
        T padre = null;

        if (elemento != null && nodo != null) {
            if (nodo.getElem().equals(elemento) && nodoPadre != null) {
                padre = nodoPadre.getElem();
            } else {
                padre = padre(elemento, nodo.getHijoIzq(), nodo);
                if (padre == null) {
                    padre = padre(elemento, nodo.getHerDer(), nodoPadre);
                }
            }
        }

        return padre;
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
            listarPreorden(lista, nodo.getHijoIzq());
            listarPreorden(lista, nodo.getHerDer());
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
            listarInorden(lista, nodo.getHijoIzq());
            lista.insertar(nodo.getElem());
            nodo = nodo.getHijoIzq();
            while (nodo != null) {
                listarInorden(lista, nodo.getHerDer());
                nodo = nodo.getHerDer();
            }
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
            listarPosorden(lista, nodo.getHijoIzq());
            lista.insertar(nodo.getElem());
            listarPosorden(lista, nodo.getHerDer());
        }
    }

    @Override
    public Lista<T> listarNiveles() {
        Lista<T> visitados = new ListaDinamica<>();
        Cola<Nodo<T>> porVisitar = new ColaDinamica<>();
        Nodo<T> nodo;

        if (raiz != null) {
            porVisitar.poner(raiz);
            while (!porVisitar.esVacia()) {
                nodo = porVisitar.obtenerFrente();
                porVisitar.sacar();
                visitados.insertar(nodo.getElem());
                nodo = nodo.getHijoIzq();
                while (nodo != null) {
                    porVisitar.poner(nodo);
                    nodo = nodo.getHerDer();
                }
            }
        }

        return visitados;
    }

    @Override
    public void vaciar() {
        this.raiz = null;
    }

    @Override
    public boolean verificarPatron(Lista<T> lista) {
        return verificarPatron(lista, this.raiz, 1);
    }

    private boolean verificarPatron(Lista<T> lista, Nodo<T> nodo, int pos) {
        boolean verifica = false;

        if (nodo != null && pos <= lista.longitud()) {
            if (lista.recuperar(pos).equals(nodo.getElem())) {
                if (!nodo.tieneHijoIzq() && pos == lista.longitud())
                    verifica = true;
                else
                    verifica = verificarPatron(lista, nodo.getHijoIzq(), pos + 1);
            } else {
                verifica = verificarPatron(lista, nodo.getHerDer(), pos);
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
            if (!nodo.tieneHijoIzq())
                lista.insertar(nodo.getElem());
            frontera(lista, nodo.getHijoIzq());
            frontera(lista, nodo.getHerDer());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArbolGenerico<T> that = (ArbolGenerico<T>) o;
        return equals(this.raiz, that.raiz);
    }

    private boolean equals(Nodo<T> nodothis, Nodo<T> nodoThat) {
        boolean equals = false;

        if (nodothis == null && nodoThat == null) {
            equals = true;
        } else if (nodothis != null && nodoThat != null) {
            if (nodothis.getElem().equals(nodoThat.getElem())) {
                equals = equals(nodothis.getHijoIzq(), nodoThat.getHijoIzq()) &&
                        equals(nodothis.getHerDer(), nodoThat.getHerDer());
            }
        }

        return equals;
    }

    @Override
    public ArbolGenerico<T> clone() {
        ArbolGenerico<T> clon = new ArbolGenerico<>();
        clon.raiz = clone(this.raiz);
        return clon;
    }

    private Nodo<T> clone(Nodo<T> nodo) {
        Nodo<T> nodoClon = null;
        if (nodo != null) {
            nodoClon = new Nodo<>(nodo.getElem());
            nodoClon.setHerDer(clone(nodo.getHerDer()));
            nodoClon.setHijoIzq(clone(nodo.getHijoIzq()));
        }
        return nodoClon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ArbolGenerico{ raiz=");
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
        Nodo<T> hijo;
        if (nodo != null) {
            sb.append(nodo.getElem()).append(" -> ");
            hijo = nodo.getHijoIzq();
            while (hijo != null) {
                sb.append(hijo.getElem()).append("; ");
                hijo = hijo.getHerDer();
            }
            sb.append("\n");

            toString(sb, nodo.getHerDer());
            toString(sb, nodo.getHijoIzq());
        }
    }

    private static class Nodo<T> implements Serializable {
        private T elem;
        private Nodo<T> hijoIzq, herDer;

        public Nodo(T elem, Nodo<T> hijoIzq, Nodo<T> herDer) {
            this.elem = elem;
            this.hijoIzq = hijoIzq;
            this.herDer = herDer;
        }

        public Nodo(T elem) {
            this(elem, null, null);
        }

        public T getElem() {
            return elem;
        }

        public void setElem(T elem) {
            this.elem = elem;
        }

        public Nodo<T> getHijoIzq() {
            return hijoIzq;
        }

        public void setHijoIzq(Nodo<T> hijoIzq) {
            this.hijoIzq = hijoIzq;
        }

        public Nodo<T> getHerDer() {
            return herDer;
        }

        public void setHerDer(Nodo<T> herDer) {
            this.herDer = herDer;
        }

        public boolean tieneHijoIzq() {
            return hijoIzq != null;
        }

        public boolean tieneHerDer() {
            return herDer != null;
        }

        @Override
        public String toString() {
            return "Nodo{" +
                    "elem=" + elem +
                    '}';
        }
    }
}
