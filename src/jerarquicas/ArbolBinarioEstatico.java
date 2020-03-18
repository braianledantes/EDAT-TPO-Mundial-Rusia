package jerarquicas;

import lineales.EstructuraLlenaException;
import lineales.Lista;

import java.util.ArrayList;

public class ArbolBinarioEstatico<T> implements Arbol<T> {
    public static final int HIJO_IZQUIERDO = 0;
    public static final int HIJO_DERECHO = 1;

    public static final int TAM = 20;
    private Celda<T>[] celdas;
    private int raiz;

    /**
     * Crea un Arbol binario vacio.
     */
    public ArbolBinarioEstatico() {
        celdas = (Celda<T>[]) new ArrayList<Celda<T>>(TAM).toArray();
        for (int i = 0; i < TAM; i++) {
            celdas[i] = new Celda<>(i);
        }
        raiz = -1;
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
    public boolean insertar(T elemNuevo, T elemPadre, int posicion) throws IllegalArgumentException, EstructuraLlenaException {
        boolean exito = true;
        Celda<T> padre = null;
        int posHijo = -1;

        if (posicion != HIJO_IZQUIERDO && posicion != HIJO_DERECHO) {
            throw new IllegalArgumentException();
        }

        if (elemNuevo != null) {
            if (raiz == -1) {
                raiz = 0;
                celdas[0].setElem(elemNuevo);
            } else {

                // busca una posicion para el hijo y tambien obtiene la celda del padre
                int i = 0;
                while ((posHijo != -1 && padre == null) || i < TAM) {
                    if (celdas[i].tieneElem() && celdas[i].getElem().equals(elemPadre)) {
                        padre = celdas[i];
                    }
                    if (!celdas[i].tieneElem()) {
                        posHijo = i;
                    }
                    i++;
                }
                if (posHijo != -1) { // si encontro un lugar para el hijo
                    if (padre != null) { // si encontro al padre
                        celdas[posHijo].setElem(elemNuevo);
                        if (posicion == HIJO_IZQUIERDO && !padre.tieneIzq()) {
                            padre.setIzq(posHijo);
                        } else if (posicion == HIJO_DERECHO && !padre.tieneDer()) {
                            padre.setDer(posHijo);
                        } else {
                            exito = false;
                        }
                    } else {
                        exito = false;
                    }
                } else {
                    throw new EstructuraLlenaException();
                }
            }
        }

        return exito;
    }

    private Celda<T> obtenelCelda(T elem) {
        Celda<T> celda = null;
        int i = 0;

        while (celda == null || i < TAM) {
            if (celdas[i].tieneElem() && celdas[i].getElem().equals(elem)) {
                celda = celdas[i];
            } else {
                i++;
            }
        }

        return celda;
    }

    public boolean insertar(T elemNuevo, T elemPadre) throws EstructuraLlenaException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean esVacio() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int altura() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int nivel(T elemento) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public T padre(T elemento) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Lista<T> listarPreorden() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Lista<T> listarInorden() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Lista<T> listarPosorden() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Lista<T> listarNiveles() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void vaciar() {
        for (Celda<T> celda : celdas) {
            celda.vaciar();
        }
        raiz = -1;
    }

    @Override
    public boolean verificalPatron(Lista<T> lista) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Lista<T> frontera() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    private static class Celda<T> {
        private T elem;
        private int izq, der, pos;

        public Celda(int pos) {
            this.pos = pos;
            this.izq = this.der = -1;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public T getElem() {
            return elem;
        }

        public void setElem(T elem) {
            this.elem = elem;
        }

        public int getIzq() {
            return izq;
        }

        public void setIzq(int izq) {
            this.izq = izq;
        }

        public int getDer() {
            return der;
        }

        public void setDer(int der) {
            this.der = der;
        }

        public boolean tieneElem() {
            return elem != null;
        }

        public boolean tieneIzq() {
            return izq != -1;
        }

        public boolean tieneDer() {
            return der != -1;
        }

        public void vaciar() {
            this.elem = null;
            this.izq = this.der = -1;
        }

        @Override
        public String toString() {
            return "Celda{" +
                    "elem=" + elem +
                    ", izq=" + izq +
                    ", der=" + der +
                    '}';
        }
    }
}
