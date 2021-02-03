package repaso.estructuras.conjuntistas;

import java.util.Arrays;

public class ArbolHeapMaximo<E extends Comparable<E>> implements ArbolHeap<E> {
    private final E[] arr;
    private int ultimo;

    public ArbolHeapMaximo(int cant) {
        arr = (E[]) new Comparable[cant];
        ultimo = -1;
    }

    @Override
    public boolean insertar(E elemento) {
        boolean exito = false;

        if (elemento != null && this.ultimo < this.arr.length - 1) {
            exito = true;
            this.ultimo++;
            this.arr[this.ultimo] = elemento;

            hacerSubir(this.ultimo);
        }

        return exito;
    }

    private void hacerSubir(int posicion) {
        if (posicion > 0) {
            int posPadre = (posicion - 1) / 2;

            if (this.arr[posPadre].compareTo(this.arr[posicion]) < 0) {
                E aux = this.arr[posPadre];
                this.arr[posPadre] = this.arr[posicion];
                this.arr[posicion] = aux;

                hacerSubir(posPadre);
            }
        }
    }

    @Override
    public boolean eliminarCima() {
        boolean exito = false;

        if (this.ultimo != -1) {
            this.arr[0] = this.arr[this.ultimo];
            this.ultimo--;
            exito = true;

            hacerBajar(0);
        }

        return exito;
    }

    private void hacerBajar(int posicion) {
        int posCandidato = (posicion * 2) + 1;
        if (posCandidato <= this.ultimo) {

            if (posCandidato < this.ultimo) {
                if (this.arr[posCandidato].compareTo(this.arr[posCandidato + 1]) < 0) {
                    posCandidato++;
                }
            }

            if (this.arr[posCandidato].compareTo(this.arr[posicion]) > 0) {
                E aux = this.arr[posCandidato];
                this.arr[posCandidato] = this.arr[posicion];
                this.arr[posicion] = aux;

                hacerBajar(posCandidato);
            }
        }
    }

    @Override
    public E recuperarCima() {
        return (this.ultimo != -1) ? this.arr[0] : null;
    }

    @Override
    public boolean esVacio() {
        return this.ultimo != -1;
    }

    @Override
    public void vaciar() {
        this.ultimo = -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ArbolHeap{");
        for (int i = 0; i <= this.ultimo; i++) {
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
