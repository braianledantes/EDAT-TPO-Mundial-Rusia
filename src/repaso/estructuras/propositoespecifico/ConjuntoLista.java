package repaso.estructuras.propositoespecifico;

import repaso.estructuras.lineales.NodoLineal;

public class ConjuntoLista<E> implements Conjunto<E> {
    private NodoLineal<E> cabecera;

    public ConjuntoLista() {
        this.cabecera = null;
    }

    @Override
    public boolean agregar(E valor) {
        boolean exito = false;
        if (valor != null && !this.pertenece(valor)) {
            this.cabecera = new NodoLineal<>(valor, this.cabecera);
            exito = true;
        }
        return exito;
    }


    @Override
    public boolean quitar(E valor) {
        boolean exito = false;
        if (valor != null) {
            NodoLineal<E> nodoAnt = null;
            NodoLineal<E> nodo = this.cabecera;
            while (nodo != null && !nodo.getElemento().equals(valor)) {
                nodoAnt = nodo;
                nodo = nodo.getEnlace();
            }
            if (nodo != null) {
                exito = true;
                if (nodoAnt == null)
                    this.cabecera = nodo.getEnlace();
                else
                    nodoAnt.setEnlace(nodo.getEnlace());
            }
        }
        return exito;
    }

    @Override
    public boolean pertenece(E valor) {
        NodoLineal<E> nodo = null;
        if (valor != null) {
            nodo = this.cabecera;
            while (nodo != null && !nodo.getElemento().equals(valor)) {
                nodo = nodo.getEnlace();
            }
        }
        return nodo != null;
    }

    @Override
    public boolean esVacio() {
        return this.cabecera == null;
    }
}
