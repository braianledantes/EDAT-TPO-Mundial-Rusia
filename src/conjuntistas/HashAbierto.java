package conjuntistas;

import lineales.Lista;
import lineales.ListaDinamica;

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
        int pos = elem.hashCode() % TAM;
        boolean encontrado = pertenece(elem);

        if (!encontrado) {
            tabla[pos] = new Nodo<>(elem, tabla[pos]);
            cant++;
        }
        return !encontrado;
    }

    @Override
    public boolean eliminar(T elem) {
        boolean elimino = false;
        int pos = elem.hashCode() % TAM;
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

        return elimino;
    }

    @Override
    public boolean pertenece(T elem) {
        boolean pertenece = false;
        int pos = elem.hashCode() % TAM;
        Nodo<T> nodo = tabla[pos];

        while (!pertenece && nodo != null) {
            pertenece = elem.equals(nodo.getElem());
            nodo = nodo.getEnlace();
        }

        return false;
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
