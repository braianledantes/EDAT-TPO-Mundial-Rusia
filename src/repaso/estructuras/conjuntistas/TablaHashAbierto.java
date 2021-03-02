package repaso.estructuras.conjuntistas;

import repaso.estructuras.lineales.Lista;
import repaso.estructuras.lineales.ListaDinamica;
import repaso.estructuras.lineales.NodoLineal;

public class TablaHashAbierto<E> implements TablaHash<E> {
    private NodoLineal<E> hash[];
    private static final int tam = 1000;
    private int cant;

    public TablaHashAbierto() {
        this.hash = new NodoLineal[tam];
        this.cant = 0;
    }

    public TablaHashAbierto(int cant) {
        this.hash = new NodoLineal[cant];
        this.cant = 0;
    }

    private int hash(E elemento) {
        int hash = -1;
        if (elemento != null) {
            hash = elemento.hashCode();
            if (hash < 0) {
                hash = -hash;
            }
        }
        return hash % this.hash.length;
    }

    @Override
    public boolean insertar(E elemento) {
        int pos = hash(elemento);

        boolean encontrado = pertenece(elemento);

        if (!encontrado) {
            this.hash[pos] = new NodoLineal<>(elemento, this.hash[pos]);
            this.cant++;
        }
        return !encontrado;
    }

    @Override
    public boolean eliminar(E elemento) {
        boolean elimino = false;

        int pos = hash(elemento);
        if (pos != -1) {
            NodoLineal<E> nodo = this.hash[pos];
            NodoLineal<E> nodoAnt = null;

            while (!elimino && nodo != null) {
                if (nodo.getElemento().equals(elemento)) {
                    if (nodoAnt != null) {
                        nodoAnt.setEnlace(nodo.getEnlace());
                    } else {
                        this.hash[pos] = nodo.getEnlace();
                    }
                    elimino = true;
                    this.cant--;
                } else {
                    nodoAnt = nodo;
                    nodo = nodo.getEnlace();
                }
            }
        }

        return elimino;
    }

    @Override
    public boolean pertenece(E elemento) {
        boolean pertenece = false;

        int pos = hash(elemento) ;
        if (pos != -1) {
            NodoLineal<E> nodo = this.hash[pos];
            while (!pertenece && nodo != null) {
                pertenece = nodo.getElemento().equals(elemento);
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
    public Lista<E> listar() {
        Lista<E> listaElementos = new ListaDinamica<>();
        int pos = 0;
        int cantidad = 0;

        while (cantidad < this.cant && pos < this.hash.length) {
            if (this.hash[pos] != null) {
                cantidad++;
                listaElementos.insertar(this.hash[pos].getElemento(), cantidad);
            }
            pos++;
        }

        return listaElementos;
    }

    @Override
    public void vaciar() {
        int pos = 0;
        while (this.cant > 0 && pos < this.hash.length) {
            if (this.hash[pos] != null) {
                this.hash[pos] = null;
                this.cant--;
            }
            pos++;
        }
    }
}
