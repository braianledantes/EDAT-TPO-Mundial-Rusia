package repaso.estructuras.jerarquicas;

import repaso.estructuras.lineales.Cola;
import repaso.estructuras.lineales.ColaDinamica;
import repaso.estructuras.lineales.Lista;
import repaso.estructuras.lineales.ListaDinamica;

public class ArbolGen<E> implements ArbolGenerico<E> {
    private NodoArbolGen<E> raiz = null;

    @Override
    public boolean insertar(E elementoNuevo, E elementoPadre) {
        boolean inserto = false;

        if (elementoNuevo != null && elementoPadre != null) {
            NodoArbolGen<E> nodoPadre = obtenerNodo(elementoPadre, this.raiz);
            if (nodoPadre != null) {
                NodoArbolGen<E> primerHijoPadre = nodoPadre.getHijoIzquierdo();
                NodoArbolGen<E> nodoHijoNuevo = new NodoArbolGen<>(elementoNuevo, null, primerHijoPadre);
                nodoPadre.setHijoIzquierdo(nodoHijoNuevo);
                inserto = true;
            }
        }

        return inserto;
    }

    private NodoArbolGen<E> obtenerNodo(E elemento, NodoArbolGen<E> nodoRecorre) {
        NodoArbolGen<E> nodo = null;

        if (nodoRecorre != null) {
            if (nodoRecorre.getElemento().equals(elemento)) {
                nodo = nodoRecorre;
            } else {
                nodo = obtenerNodo(elemento, nodoRecorre.getHijoIzquierdo());
                if (nodo == null) {
                    nodo = obtenerNodo(elemento, nodoRecorre.getHermanoDerecho());
                }
            }
        }

        return nodo;
    }

    @Override
    public boolean pertenece(E elemento) {
        return obtenerNodo(elemento, this.raiz) != null;
    }

    @Override
    public Lista<E> ancestros(E elemento) {
        Lista<E> listaAncestros = new ListaDinamica<>();
        ancestrosAux(elemento, this.raiz, listaAncestros);
        return listaAncestros;
    }

    private void ancestrosAux(E elemento, NodoArbolGen<E> nodo, Lista<E> listaAncestros) {
        if (elemento != null && nodo != null) {
            if (nodo.getElemento().equals(elemento)) {
                listaAncestros.insertar(nodo.getElemento(), 1);
            } else {
                ancestrosAux(elemento, nodo.getHijoIzquierdo(), listaAncestros);
                if (!listaAncestros.esVacia()) {
                    listaAncestros.insertar(nodo.getElemento(), 1);
                } else {
                    ancestrosAux(elemento, nodo.getHermanoDerecho(), listaAncestros);
                }
            }
        }
    }

    @Override
    public boolean esVacio() {
        return this.raiz == null;
    }

    @Override
    public int altura() {
        return alturaAux(this.raiz);
    }

    private int alturaAux(NodoArbolGen<E> nodo) {
        int altura = -1;

        if (nodo != null) {
            int alturaConHijo = alturaAux(nodo.getHijoIzquierdo()) + 1;
            int alturaHermano = alturaAux(nodo.getHermanoDerecho());
            altura = Math.max(alturaConHijo, alturaHermano);
        }

        return altura;
    }

    @Override
    public int nivel(E elemento) {
        return nivelAux(elemento, this.raiz);
    }

    private int nivelAux(E elemento, NodoArbolGen<E> nodoElemento) {
        int nivel = -1;

        if (elemento != null && nodoElemento != null) {
            if (nodoElemento.getElemento().equals(elemento)) {
                nivel = 0;
            } else {
                nivel = nivelAux(elemento, nodoElemento.getHijoIzquierdo());
                if (nivel != -1) {
                    nivel++;
                } else {
                    nivel = nivelAux(elemento, nodoElemento.getHermanoDerecho());
                }
            }
        }

        return nivel;
    }

    @Override
    public E padre(E elemento) {
        return padreAux(elemento, this.raiz, null);
    }

    private E padreAux(E elemento, NodoArbolGen<E> nodoRecorre, NodoArbolGen<E> nodoPadre) {
        E elementoPadre = null;

        if (elemento != null && nodoRecorre != null) {
            if (nodoRecorre.getElemento().equals(elemento) && nodoPadre != null) {
                elementoPadre = nodoPadre.getElemento();
            } else {
                elementoPadre = padreAux(elemento, nodoRecorre.getHermanoDerecho(), nodoPadre);
                if (elementoPadre == null) {
                    elementoPadre = padreAux(elemento, nodoRecorre.getHijoIzquierdo(), nodoRecorre);
                }
            }
        }

        return elementoPadre;
    }

    @Override
    public Lista<E> listarPreorden() {
        Lista<E> listaElementos = new ListaDinamica<>();
        listarPreordenAux(this.raiz, listaElementos);
        return listaElementos;
    }

    private void listarPreordenAux(NodoArbolGen<E> nodo, Lista<E> listaElementos) {
        if (nodo != null) {
            listaElementos.insertar(nodo.getElemento(), listaElementos.longitud() + 1);
            NodoArbolGen<E> nodoHijo = nodo.getHijoIzquierdo();
            while (nodoHijo != null) {
                listarPreordenAux(nodoHijo, listaElementos);
                nodoHijo = nodoHijo.getHermanoDerecho();
            }
        }
    }

    @Override
    public Lista<E> listarInorden() {
        Lista<E> listaElementos = new ListaDinamica<>();
        listarInordenAux(this.raiz, listaElementos);
        return listaElementos;
    }

    private void listarInordenAux(NodoArbolGen<E> nodo, Lista<E> listaElementos) {
        if (nodo != null) {
            NodoArbolGen<E> nodoHijo = nodo.getHijoIzquierdo();
            listarPreordenAux(nodoHijo, listaElementos);
            listaElementos.insertar(nodo.getElemento(), listaElementos.longitud() + 1);
            while (nodoHijo != null) {
                nodoHijo = nodoHijo.getHermanoDerecho();
                listarPreordenAux(nodoHijo, listaElementos);
            }
        }
    }

    @Override
    public Lista<E> listarPosorden() {
        Lista<E> listaElementos = new ListaDinamica<>();
        listarPosordenAux(this.raiz, listaElementos);
        return listaElementos;
    }

    private void listarPosordenAux(NodoArbolGen<E> nodo, Lista<E> listaElementos) {
        if (nodo != null) {
            NodoArbolGen<E> nodoHijo = nodo.getHijoIzquierdo();
            while (nodoHijo != null) {
                listarPreordenAux(nodoHijo, listaElementos);
                nodoHijo = nodoHijo.getHermanoDerecho();
            }
            listaElementos.insertar(nodo.getElemento(), listaElementos.longitud() + 1);
        }
    }

    @Override
    public Lista<E> listarNiveles() {
        Lista<E> listaElementos = new ListaDinamica<>();
        if (this.raiz != null) {
            Cola<NodoArbolGen<E>> cola = new ColaDinamica<>();
            cola.poner(this.raiz);

            while (!cola.esVacia()) {
                NodoArbolGen<E> nodo = cola.obtenerFrente();
                cola.sacar();
                listaElementos.insertar(nodo.getElemento(), listaElementos.longitud() + 1);

                NodoArbolGen<E> nodoHijo = nodo.getHijoIzquierdo();
                while (nodoHijo != null) {
                    cola.poner(nodoHijo);
                    nodoHijo = nodoHijo.getHermanoDerecho();
                }
            }
        }
        return listaElementos;
    }

    @Override
    public void vaciar() {
        this.raiz = null;
    }

    @Override
    public boolean verificarPatron(Lista<E> listaPatron) {
        return verificarPatronAux(this.raiz, listaPatron, 1);
    }

    private boolean verificarPatronAux(NodoArbolGen<E> nodo, Lista<E> listaPatron, int posicion) {
        boolean verifica = false;

        if (nodo == null && posicion == listaPatron.longitud() + 1) {
            verifica = true;
        } else if (nodo != null) {
            if (nodo.getElemento().equals(listaPatron.recuperar(posicion))) {
                verifica = verificarPatronAux(nodo.getHijoIzquierdo(), listaPatron, posicion + 1);
            } else {
                verifica = verificarPatronAux(nodo.getHermanoDerecho(), listaPatron, posicion);
            }
        }

        return verifica;
    }

    @Override
    public Lista<E> frontera() {
        Lista<E> listaFrontera = new ListaDinamica<>();
        fronteraAux(this.raiz, listaFrontera);
        return listaFrontera;
    }

    private void fronteraAux(NodoArbolGen<E> nodo, Lista<E> listaFrontera) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                listaFrontera.insertar(nodo.getElemento(), listaFrontera.longitud() + 1);
            }
            fronteraAux(nodo.getHijoIzquierdo(), listaFrontera);
            fronteraAux(nodo.getHermanoDerecho(), listaFrontera);
        }
    }
}
