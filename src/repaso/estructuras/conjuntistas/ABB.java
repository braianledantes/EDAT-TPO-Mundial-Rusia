package repaso.estructuras.conjuntistas;

import repaso.estructuras.jerarquicas.NodoArbolBin;
import repaso.estructuras.lineales.Lista;
import repaso.estructuras.lineales.ListaDinamica;
import repaso.estructuras.utliles.Resultado;

public class ABB<E extends Comparable<E>> implements ArbolBinarioBusqueda<E> {
    protected NodoArbolBin<E> raiz;

    public ABB() {
        this.raiz = null;
    }

    @Override
    public boolean insertar(E elem) {
        Resultado<Boolean> exito = new Resultado<>(false);
        if (elem != null) {
            this.raiz = insertartAux(elem, this.raiz, exito);
        }
        return exito.getValor();
    }

    private NodoArbolBin<E> insertartAux(E elem, NodoArbolBin<E> nodo, Resultado<Boolean> exito) {
        NodoArbolBin<E> nodoAux;
        if (nodo == null) {
            nodo = new NodoArbolBin<>(elem);
            exito.setValor(true);
        } else if (elem.compareTo(nodo.getElemento()) < 0) {
            nodoAux = insertartAux(elem, nodo.getIzquierdo(), exito);
            nodo.setIzquierdo(nodoAux);
        } else if (elem.compareTo(nodo.getElemento()) > 0) {
            nodoAux = insertartAux(elem, nodo.getDerecho(), exito);
            nodo.setDerecho(nodoAux);
        }
        return nodo;
    }

    @Override
    public boolean eliminar(E elemento) {
        Resultado<Boolean> exito = new Resultado<>(false);
        if (elemento != null) {
            this.raiz = eliminarAux(this.raiz, elemento, exito);
        }
        return exito.getValor();
    }

    private NodoArbolBin<E> eliminarAux(NodoArbolBin<E> nodo, E elemento, Resultado<Boolean> exito) {
        NodoArbolBin<E> salida = nodo;

        if (nodo != null) {
            if (elemento.compareTo(nodo.getElemento()) == 0) {
                exito.setValor(true);
                // el nodo es el elemento buscado
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    // caso 1: el nodo es una hoja
                    salida = null;
                } else if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                    // caso 2: el nodo tiene ambos hijos
                    salida = buscarCandidatoMenor(nodo);
                    salida.setIzquierdo(nodo.getIzquierdo());
                    salida.setDerecho(nodo.getDerecho());
                } else {
                    // caso 3: el nodo tiene un solo hijo
                    salida = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();
                }
            } else if (elemento.compareTo(nodo.getElemento()) < 0) {
                nodo.setIzquierdo(eliminarAux(nodo.getIzquierdo(), elemento, exito));
            } else {
                nodo.setDerecho(eliminarAux(nodo.getDerecho(), elemento, exito));
            }
        }

        return salida;
    }

    private NodoArbolBin<E> buscarCandidatoMenor(NodoArbolBin<E> nodo) {
        // precondicion: el nodo tiene ambos hijos
        Resultado<NodoArbolBin<E>> nodoCandidato = new Resultado<>(null);
        nodo.setDerecho(obtenerCandidatoMenor(nodo.getDerecho(), nodoCandidato));
        return nodoCandidato.getValor();
    }

    private NodoArbolBin<E> obtenerCandidatoMenor(NodoArbolBin<E> nodo, Resultado<NodoArbolBin<E>> nodoCandidato) {
        if (nodo.getIzquierdo() == null) {
            nodoCandidato.setValor(nodo);
            nodo = nodo.getDerecho(); // elimina el nodo candidato
        } else {
            nodo.setIzquierdo(obtenerCandidatoMenor(nodo.getIzquierdo(), nodoCandidato));
        }
        return nodo;
    }

    @Override
    public boolean pertenece(E elemento) {
        boolean exito = false;
        if (elemento != null) {
            exito = perteneceAux(elemento, this.raiz);
        }
        return exito;
    }

    private boolean perteneceAux(E elemento, NodoArbolBin<E> nodo) {
        boolean exito = false;

        if (nodo != null) {
            if (elemento.compareTo(nodo.getElemento()) == 0) {
                exito = true;
            } else if (elemento.compareTo(nodo.getElemento()) < 0) {
                exito = perteneceAux(elemento, nodo.getIzquierdo());
            } else {
                exito = perteneceAux(elemento, nodo.getDerecho());
            }
        }

        return exito;
    }

    @Override
    public boolean esVacio() {
        return this.raiz == null;
    }

    @Override
    public Lista<E> listar() {
        Lista<E> listaElementos = new ListaDinamica<>();
        listarAux(this.raiz, listaElementos);
        return listaElementos;
    }

    private void listarAux(NodoArbolBin<E> nodo, Lista<E> listaElementos) {
        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), listaElementos);
            listaElementos.insertar(nodo.getElemento(), listaElementos.longitud() + 1);
            listarAux(nodo.getDerecho(), listaElementos);
        }
    }

    @Override
    public Lista<E> lisarRango(E elementoMinimo, E elementoMaximo) {
        Lista<E> listaRango = new ListaDinamica<>();
        listarRangoAux(elementoMinimo, elementoMaximo, this.raiz, listaRango);
        return listaRango;
    }

    private void listarRangoAux(E elementoMinimo, E elementoMaximo, NodoArbolBin<E> nodo, Lista<E> listaRango) {
        if (nodo != null) {
            if (nodo.getElemento().compareTo(elementoMinimo) > 0) {
                listarRangoAux(elementoMinimo, elementoMaximo, nodo.getIzquierdo(), listaRango);
            }
            if (nodo.getElemento().compareTo(elementoMinimo) >= 0 && nodo.getElemento().compareTo(elementoMaximo) <= 0) {
                listaRango.insertar(nodo.getElemento(), listaRango.longitud() + 1);
            }
            if (nodo.getElemento().compareTo(elementoMaximo) < 0) {
                listarRangoAux(elementoMinimo, elementoMaximo, nodo.getDerecho(), listaRango);
            }
        }
    }

    @Override
    public E minimoElemento() {
        E elemento = null;
        if (this.raiz != null) {
            elemento = minimoElementoAux(this.raiz);
        }
        return elemento;
    }

    private E minimoElementoAux(NodoArbolBin<E> nodo) {
        E elemento = nodo.getElemento();
        if (nodo.getIzquierdo() != null) {
            elemento = minimoElementoAux(nodo.getIzquierdo());
        }
        return elemento;
    }

    @Override
    public E maximoElemento() {
        E elemento = null;
        if (this.raiz != null) {
            elemento = maximoElementoAux(this.raiz);
        }
        return elemento;
    }

    private E maximoElementoAux(NodoArbolBin<E> nodo) {
        E elemento = nodo.getElemento();
        if (nodo.getDerecho() != null) {
            elemento = maximoElementoAux(nodo.getDerecho());
        }
        return elemento;
    }

    @Override
    public void vaciar() {
        this.raiz = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ABB)) return false;
        ABB<E> that = (ABB<E>) o;
        return equals(this.raiz, that.raiz);
    }

    private boolean equals(NodoArbolBin<E> nodothis, NodoArbolBin<E> nodoThat) {
        boolean equals = false;

        if (nodothis == null && nodoThat == null) {
            equals = true;
        } else if (nodothis != null && nodoThat != null) {
            if (nodothis.getElemento().equals(nodoThat.getElemento())) {
                equals = equals(nodothis.getIzquierdo(), nodoThat.getIzquierdo()) &&
                        equals(nodothis.getDerecho(), nodoThat.getDerecho());
            }
        }

        return equals;
    }

    protected NodoArbolBin<E> clone(NodoArbolBin<E> nodo) {
        NodoArbolBin<E> nodoNuevo = null;
        if (nodo != null) {
            nodoNuevo = new NodoArbolBin<>(nodo.getElemento(), clone(nodo.getIzquierdo()), clone(nodo.getDerecho()));
        }
        return nodoNuevo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getName() + "{ raiz=");
        if (raiz != null) {
            sb.append(raiz.getElemento());
        } else {
            sb.append("null");
        }
        sb.append("\n");
        toString(sb, raiz);
        sb.append("}");
        return sb.toString();
    }

    private void toString(StringBuilder sb, NodoArbolBin<E> nodo) {
        if (nodo != null) {
            sb.append(nodo.getElemento()).append(" -> ");
            if (nodo.getIzquierdo() != null) {
                sb.append(nodo.getIzquierdo().getElemento()).append("; ");
            } else {
                sb.append("null").append("; ");
            }
            if (nodo.getDerecho() != null) {
                sb.append(nodo.getDerecho().getElemento());
            } else {
                sb.append("null");
            }
            sb.append("\n");

            toString(sb, nodo.getIzquierdo());
            toString(sb, nodo.getDerecho());
        }
    }
}
