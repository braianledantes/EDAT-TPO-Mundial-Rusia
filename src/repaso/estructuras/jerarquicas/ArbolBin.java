package repaso.estructuras.jerarquicas;

import repaso.estructuras.lineales.Cola;
import repaso.estructuras.lineales.ColaDinamica;
import repaso.estructuras.lineales.Lista;
import repaso.estructuras.lineales.ListaDinamica;

public class ArbolBin<E> implements Arbol<E> {

    private static final int posicionIzq = 0;
    private static final int posicionDer = 1;

    private NodoArbolBin<E> raiz = null;

    @Override
    public boolean insertar(E elementoNuevo, E elementoPadre, int posicion) {
        boolean inserto = false;
        if (elementoNuevo != null && elementoPadre != null && (posicion == posicionIzq || posicion == posicionDer)) {
            if (this.raiz == null) {
                this.raiz = new NodoArbolBin<>(elementoNuevo);
                inserto = true;
            } else {
                NodoArbolBin<E> nodoPadre = obtenerNodo(elementoPadre, this.raiz);
                if (posicion == posicionIzq && nodoPadre.getIzquierdo() == null) {
                    nodoPadre.setIzquierdo(new NodoArbolBin<>(elementoNuevo));
                    inserto = true;
                } else if (posicion == posicionDer && nodoPadre.getDerecho() == null) {
                    nodoPadre.setDerecho(new NodoArbolBin<>(elementoNuevo));
                    inserto = true;
                }
            }
        }
        return inserto;
    }

    private NodoArbolBin<E> obtenerNodo(E elemento, NodoArbolBin<E> nodoRecorre) {
        NodoArbolBin<E> nodoPadre = null;

        if (elemento != null && nodoRecorre != null) {
            if (nodoRecorre.getElemento().equals(elemento)) {
                nodoPadre = nodoRecorre;
            } else {
                nodoPadre = obtenerNodo(elemento, nodoRecorre.getIzquierdo());
                if (nodoPadre == null) {
                    nodoPadre = obtenerNodo(elemento, nodoRecorre.getDerecho());
                }
            }
        }

        return nodoPadre;
    }

    @Override
    public boolean esVacio() {
        return this.raiz == null;
    }

    @Override
    public int altura() {
        return alturaAux(this.raiz);
    }

    private int alturaAux(NodoArbolBin<E> nodo) {
        int altura = -1;

        if (nodo != null) {
            int alturaIzq = alturaAux(nodo.getIzquierdo());
            int alturaDer = alturaAux(nodo.getDerecho());
            altura = Math.max(alturaIzq, alturaDer) + 1;
        }

        return altura;
    }

    @Override
    public int nivel(E elemento) {
        return nivelAux(elemento, this.raiz);
    }

    private int nivelAux(E elemento, NodoArbolBin<E> nodo) {
        int nivel = -1;

        if (nodo != null && elemento != null) {
            if (nodo.getElemento().equals(elemento)) {
                nivel = 0;
            } else {
                nivel = nivelAux(elemento, nodo.getIzquierdo());
                if (nivel == -1) {
                    nivel = nivelAux(elemento, nodo.getDerecho());
                }
                if (nivel != -1) {
                    nivel++;
                }
            }
        }

        return nivel;
    }

    @Override
    public E padre(E elemento) {
        return padreAux(elemento, this.raiz, null);
    }

    private E padreAux(E elemento, NodoArbolBin<E> nodoElemento, NodoArbolBin<E> nodoPadre) {
        E elementoPadre = null;

        if (elemento != null && nodoElemento != null) {
            if (nodoPadre != null && nodoElemento.getElemento().equals(elemento)) {
                elementoPadre = nodoPadre.getElemento();
            } else {
                elementoPadre = padreAux(elemento, nodoElemento.getIzquierdo(), nodoElemento);
                if (elementoPadre == null) {
                    elementoPadre = padreAux(elemento, nodoElemento.getDerecho(), nodoElemento);
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

    private void listarPreordenAux(NodoArbolBin<E> nodo, Lista<E> listaElementos) {
        if (nodo != null) {
            listaElementos.insertar(nodo.getElemento(), listaElementos.longitud() + 1);
            listarPreordenAux(nodo.getIzquierdo(), listaElementos);
            listarPreordenAux(nodo.getDerecho(), listaElementos);
        }
    }

    @Override
    public Lista<E> listarInorden() {
        Lista<E> listaElementos = new ListaDinamica<>();
        listarInordenAux(this.raiz, listaElementos);
        return listaElementos;
    }

    private void listarInordenAux(NodoArbolBin<E> nodo, Lista<E> listaElementos) {
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), listaElementos);
            listaElementos.insertar(nodo.getElemento(), listaElementos.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), listaElementos);
        }
    }

    @Override
    public Lista<E> listarPosorden() {
        Lista<E> listaElementos = new ListaDinamica<>();
        listarPosordenAux(this.raiz, listaElementos);
        return listaElementos;
    }

    private void listarPosordenAux(NodoArbolBin<E> nodo, Lista<E> listaElementos) {
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), listaElementos);
            listarPosordenAux(nodo.getDerecho(), listaElementos);
            listaElementos.insertar(nodo.getElemento(), listaElementos.longitud() + 1);
        }
    }

    @Override
    public Lista<E> listarNiveles() {
        Lista<E> listaElementos = new ListaDinamica<>();
        if (this.raiz != null) {
            Cola<NodoArbolBin<E>> cola = new ColaDinamica<>();
            cola.poner(this.raiz);

            while (cola.obtenerFrente() != null) {
                NodoArbolBin<E> nodo = cola.obtenerFrente();
                cola.sacar();
                listaElementos.insertar(nodo.getElemento(), listaElementos.longitud() + 1);

                if (nodo.getIzquierdo() != null) {
                    cola.poner(nodo.getIzquierdo());
                }
                if (nodo.getDerecho() != null) {
                    cola.poner(nodo.getDerecho());
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
        if (this.raiz == null && listaPatron.esVacia()){
            return true;
        }
        return verificarPatronAux(this.raiz, listaPatron, 1);
    }

    private boolean verificarPatronAux(NodoArbolBin<E> nodo, Lista<E> listaPatron, int posicion) {
        boolean verifica = false;
        if (nodo != null && posicion <= listaPatron.longitud() && listaPatron.recuperar(posicion).equals(nodo.getElemento())) {
            if (posicion == listaPatron.longitud() && nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                verifica = true;
            } else {
                verifica = verificarPatronAux(nodo.getIzquierdo(), listaPatron, posicion + 1);
                if (!verifica) {
                    verifica = verificarPatronAux(nodo.getDerecho(), listaPatron, posicion + 1);
                }
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

    private void fronteraAux(NodoArbolBin<E> nodo, Lista<E> listaFrontera) {
        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                listaFrontera.insertar(nodo.getElemento(), listaFrontera.longitud() + 1);
            } else {
                fronteraAux(nodo.getIzquierdo(), listaFrontera);
                fronteraAux(nodo.getDerecho(), listaFrontera);
            }
        }
    }
}
