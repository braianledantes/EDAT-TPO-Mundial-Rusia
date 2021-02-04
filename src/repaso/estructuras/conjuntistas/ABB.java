package repaso.estructuras.conjuntistas;

import repaso.estructuras.Resultado;
import repaso.estructuras.jerarquicas.NodoArbolBin;
import repaso.estructuras.lineales.Lista;
import repaso.estructuras.lineales.ListaDinamica;

public class ABB<E extends Comparable<E>> implements ArbolBinarioBusqueda<E> {
    protected NodoArbolBin<E> raiz;

    public ABB() {
        this.raiz = null;
    }

    @Override
    public boolean insertar(E elemento) {
        boolean exito = false;
        if (elemento != null) {
            if (this.raiz == null) {
                this.raiz = new NodoArbolBin<>(elemento);
                exito = true;
            } else {
                exito = insertarAux(elemento, this.raiz);
            }
        }
        return exito;
    }

    private boolean insertarAux(E elemento, NodoArbolBin<E> nodo) {
        boolean exito = false;
        if (!nodo.getElemento().equals(elemento)) {
            if (elemento.compareTo(nodo.getElemento()) < 0) {
                if (nodo.getIzquierdo() == null) {
                    nodo.setIzquierdo(new NodoArbolBin<>(elemento));
                    exito = true;
                } else {
                    exito = insertarAux(elemento, nodo.getIzquierdo());
                }
            } else {
                if (nodo.getDerecho() == null) {
                    nodo.setDerecho(new NodoArbolBin<>(elemento));
                    exito = true;
                } else {
                    exito = insertarAux(elemento, nodo.getDerecho());
                }
            }
        }
        return exito;
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

        if (nodo != null)  {
            if (elemento.compareTo(nodo.getElemento()) == 0) {
                exito.setValor(true);
                // el nodo es el elemento buscado
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    // caso 1: el nodo es una hoja
                    salida = null;
                } else if (nodo.getIzquierdo() != null && nodo.getDerecho() != null){
                    // caso 2: el nodo tiene ambos hijos
                    NodoArbolBin<E> nodoCandidato = buscarCandidatoMenor(nodo.getDerecho());
                    if (nodoCandidato == nodo.getDerecho()) {
                        nodoCandidato.setIzquierdo(nodo.getIzquierdo());
                    } else {
                        nodo.getDerecho().setIzquierdo(nodoCandidato.getDerecho());
                        nodoCandidato.setIzquierdo(nodo.getIzquierdo());
                        nodoCandidato.setDerecho(nodo.getDerecho());
                    }
                    salida = nodoCandidato;
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
        NodoArbolBin<E> nodoCandidato = nodo;
        if (nodo.getIzquierdo() != null) {
            nodoCandidato = buscarCandidatoMenor(nodo.getIzquierdo());
        }
        return nodoCandidato;
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
        return null;
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
