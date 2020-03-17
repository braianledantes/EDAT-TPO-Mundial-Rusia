package Conjuntistas;

import jerarquicas.ArbolBinarioDinamico;
import lineales.Lista;

public class ABB<T extends Comparable<T>> extends ArbolBinarioDinamico<T> implements ArbolBinarioBusqueda<T> {

    @Override
    public boolean insertar(T elem) {
        boolean exito = true;

        if (this.raiz == null)
            this.raiz = new Nodo<>(elem);
        else
            exito = insertar(elem, this.raiz);

        return exito;
    }

    private boolean insertar(T elem, Nodo<T> nodo) {
        boolean exito = true;

        if (elem.compareTo(nodo.getElem()) == 0) {
            exito = false;
        } else {
            if (elem.compareTo(nodo.getElem()) < 0) {
                if (nodo.getIzq() != null)
                    exito = insertar(elem, nodo.getIzq());
                else
                    nodo.setIzq(new Nodo<>(elem));
            } else {
                if (nodo.getDer() != null)
                    exito = insertar(elem, nodo.getDer());
                else
                    nodo.setDer(new Nodo<>(elem));
            }
        }
        return exito;
    }

    @Override
    public boolean eliminar(T elem) {
        return false;
    }

    @Override
    public boolean pertenece(T elem) {
        return false;
    }

    @Override
    public Lista<T> listar() {
        return listarInorden();
    }

    @Override
    public Lista<T> listarRango(T elemMin, T elemMax) {
        return null;
    }

    @Override
    public T minimoElemento() {
        return null;
    }

    @Override
    public T maximoElemento() {
        return null;
    }
}
