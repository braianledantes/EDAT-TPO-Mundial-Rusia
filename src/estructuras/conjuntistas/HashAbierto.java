package estructuras.conjuntistas;

import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;

public class HashAbierto<T> implements TablaHash<T> {
    public static final int TAM = 10000;
    private Nodo<T>[] tabla;
    private int cant;

    public HashAbierto() {
        cant = 0;
        tabla = new Nodo[TAM];
    }

    @Override
    public boolean insertar(T elem) {
        boolean encontrado = true;
        if (elem != null) {
            int pos = elem.hashCode() % TAM;
            pos = pos < 0 ? -pos : pos;
            encontrado = pertenece(elem);

            if (!encontrado) {
                tabla[pos] = new Nodo<>(elem, tabla[pos]);
                cant++;
            }
        }
        return !encontrado;
    }

    @Override
    public boolean eliminar(T elem) {
        boolean elimino = false;
        if (elem != null) {
            int pos = elem.hashCode() % TAM;
            pos = pos < 0 ? -pos : pos;
            Nodo<T> nodoAnt = null, nodo = tabla[pos];

            while (!elimino && nodo != null) {
                if (elem.equals(nodo.getElem())) {
                    if (nodoAnt != null) {
                        nodoAnt.setEnlace(nodo.getEnlace());
                    } else {
                        tabla[pos] = nodo.getEnlace();
                    }
                    elimino = true;
                    cant--;
                } else {
                    nodoAnt = nodo;
                    nodo = nodo.getEnlace();
                }
            }
        }
        return elimino;
    }

    @Override
    public boolean pertenece(T elem) {
        boolean pertenece = false;
        if (elem != null) {
            int pos = elem.hashCode() % TAM;
            pos = pos < 0 ? -pos : pos;
            Nodo<T> nodo = tabla[pos];

            while (!pertenece && nodo != null) {
                pertenece = elem.equals(nodo.getElem());
                nodo = nodo.getEnlace();
            }
        }
        return pertenece;
    }

    @Override
    public boolean esVacia() {
        return cant == 0;
    }

    @Override
    public Lista<T> listar() {
        Lista<T> lista = new ListaDinamica<>();
        Nodo<T> nodo;

        for (int i = 0; i < TAM; i++) {
            nodo = tabla[i];
            while (nodo != null) {
                lista.insertar(nodo.getElem());
                nodo = nodo.getEnlace();
            }
        }

        return lista;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HashAbierto{cant=" + cant);
        Nodo<T> nodo;

        for (int i = 0; i < TAM; i++) {
            nodo = tabla[i];
            while (nodo != null) {
                sb.append(", ").append(nodo.getElem());
                nodo = nodo.getEnlace();
            }
        }

        return sb.append('}').toString();
    }

    private static class Nodo<T> {
        private T elem;
        private Nodo<T> enlace;

        public Nodo(T elem, Nodo<T> enlace) {
            this.elem = elem;
            this.enlace = enlace;
        }

        public T getElem() {
            return elem;
        }

        public void setElem(T elem) {
            this.elem = elem;
        }

        public Nodo<T> getEnlace() {
            return enlace;
        }

        public void setEnlace(Nodo<T> enlace) {
            this.enlace = enlace;
        }
    }
}
