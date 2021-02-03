package estructuras.conjuntistas;

import estructuras.jerarquicas.Arbol;
import estructuras.lineales.Lista;

import java.io.Serializable;

public abstract class ArbolHeap<T extends Comparable<T>> implements Arbol<T>, Serializable {
    protected T[] arr;
    protected int ultimo;

    public ArbolHeap(int cant) {
        arr = (T[]) new Comparable[cant];
        ultimo = 0;
    }

    /**
     * Inserta un elemento en el árbol de manera ordenada.
     *
     * @param elemento elemento a insertar
     * @return devuelve verdadero y falso en caso contrario
     */
    public boolean insertar(T elemento) {
        boolean exito = false;
        if (elemento != null) {
            this.ultimo++;
            this.arr[this.ultimo] = elemento;
            if (this.ultimo < this.arr.length - 1) { // si hay logar
                hacerSubir(this.ultimo);
                exito = true;
            } else { // sino aumenta el tamaño del arreglo y lo llama denuevo
                T aux[] = (T[]) new Comparable[this.arr.length + 20];
                System.arraycopy(this.arr, 0, aux, 0, this.ultimo + 1);
                this.arr = aux;
                exito = insertar(elemento);
            }
        }
        return exito;
    }

    protected void hacerSubir(int posicion) {
        int posPadre = posicion / 2;
        T aux;
        while (posPadre > 0 && funcionHeap(arr[posPadre], arr[posicion])) {
            aux = (T) arr[posicion];
            arr[posicion] = arr[posPadre];
            arr[posPadre] = aux;
            posicion = posPadre;
            posPadre = posicion / 2;
        }
    }

    /**
     * Elimina el elemento de la raíz (o cima del montículo).
     *
     * @return verdadero si se eliminó correctamente y falso de lo contrario
     */
    public boolean eliminarCima() {
        boolean exito = false;

        if (this.ultimo != 0) {
            this.arr[1] = this.arr[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }

        return exito;
    }

    private void hacerBajar(int posicion) {
        int posHijo;
        Comparable<T> temp = this.arr[posicion];
        boolean salir = false;
        while (!salir) {
            posHijo = posicion * 2;
            if (posHijo <= this.ultimo) {
                // temp tiene hijos (al menos uno)
                if (posHijo < this.ultimo) {
                    // hijoMenor tiene hermano derecho
                    if (funcionHeap(this.arr[posHijo + 1], this.arr[posHijo])) {
                        posHijo++;
                    }
                }
                if (funcionHeap(this.arr[posHijo], temp)) {
                    this.arr[posicion] = this.arr[posHijo];
                    posicion = posHijo;
                } else {
                    // el padre es menor que sus hijos
                    salir = true;
                }
            } else {
                // hijoMenor es hoja
                salir = true;
            }
        }
    }

    abstract protected boolean funcionHeap(Comparable<T> c1, Comparable<T> c2);

    /**
     * Devuelve la cima del árbol (o cima del montículo).
     *
     * @return la cima del árbol
     */
    public T recuperarCima() {
        return this.ultimo == 0 ? null : (T) this.arr[1];
    }

    /**
     * Verifica si el árbol esta vacio.
     *
     * @return falso si hay al menos un elemento cargado en la tabla y verdadero en caso contrario
     */
    public boolean esVacio() {
        return this.ultimo == 0;
    }

    /**
     * Vacia el contenido del árbol.
     */
    public void vaciar() {
        this.ultimo = 0;
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
    public boolean verificarPatron(Lista<T> lista) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Lista<T> frontera() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ArbolHeap{");
        for (int i = 1; i <= this.ultimo; i++) {
            if (i != this.ultimo) {
                sb.append(this.arr[i]).append("; ");
            } else {
                sb.append(this.arr[i]);
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
