package repaso.estructuras.conjuntistas;

import repaso.estructuras.jerarquicas.NodoArbolBin;
import repaso.estructuras.utliles.Resultado;
import repaso.estructuras.lineales.Lista;
import repaso.estructuras.lineales.ListaDinamica;

public class ArbolAVL<E extends Comparable<E>> implements AAVL<E> {
    protected NodoAVL<E> raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    @Override
    public boolean insertar(E elemento) {
        Resultado<Boolean> exito = new Resultado<>(false);
        if (elemento != null) {
            this.raiz = insertarAux(elemento, this.raiz, exito);
        }
        return exito.getValor();
    }

    private NodoAVL<E> insertarAux(E elemento, NodoAVL<E> nodo, Resultado<Boolean> exito) {
        NodoAVL<E> salida = nodo;

        if (nodo == null) {
            salida = new NodoAVL<>(elemento);
            exito.setValor(true);
        } else if (elemento.compareTo(nodo.getElemento()) < 0) {
            nodo.setIzquierdo(insertarAux(elemento, nodo.getIzquierdo(), exito));
        } else if (elemento.compareTo(nodo.getElemento()) > 0) {
            nodo.setDerecho(insertarAux(elemento, nodo.getDerecho(), exito));
        }
        if (exito.getValor()) {
            salida.recalcularAltura();
            salida = balancear(salida);
        }

        return salida;
    }

    private NodoAVL<E> balancear(NodoAVL<E> nodo) {
        NodoAVL<E> subRaiz = nodo;
        int balance = nodo.calcularBalance();
        if (balance == 2) {
            int balanceHijo = nodo.getIzquierdo().calcularBalance();
            if (balanceHijo == -1) {
                nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            }
            subRaiz = rotarDerecha(nodo);
        } else if (balance == -2) {
            int balanceHijo = nodo.getDerecho().calcularBalance();
            if (balanceHijo == 1) {
                nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            }
            subRaiz = rotarIzquierda(nodo);
        }
        return subRaiz;
    }

    private NodoAVL<E> rotarIzquierda(NodoAVL<E> r) {
        NodoAVL<E> h = r.getDerecho();
        NodoAVL<E> temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);

        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL<E> rotarDerecha(NodoAVL<E> r) {
        NodoAVL<E> h = r.getIzquierdo();
        NodoAVL<E> temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);

        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    @Override
    public boolean eliminar(E elemento) {
        Resultado<Boolean> exito = new Resultado<>(false);
        if (elemento != null) {
            this.raiz = eliminarAux(this.raiz, elemento, exito);
        }
        return exito.getValor();
    }

    private NodoAVL<E> eliminarAux(NodoAVL<E> nodo, E elemento, Resultado<Boolean> exito) {
        NodoAVL<E> salida = nodo;

        if (nodo != null) {
            if (elemento.compareTo(nodo.getElemento()) == 0) {
                exito.setValor(true);
                // el nodo es el elemento buscado
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    // caso 1: el nodo es una hoja
                    salida = null;
                } else if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                    // caso 2: el nodo tiene ambos hijos
                    NodoAVL<E> nodoCandidato = buscarCandidatoMenor(nodo);
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
        if (exito.getValor() && salida != null) {
            salida.recalcularAltura();
            salida = balancear(salida);
        }

        return salida;
    }

    private NodoAVL<E> buscarCandidatoMenor(NodoAVL<E> nodo) {
        return buscarMenor(nodo.getDerecho());
    }

    private NodoAVL<E> buscarMenor(NodoAVL<E> nodo) {
        NodoAVL<E> nodoCandidato = nodo;
        if (nodo.getIzquierdo() != null) {
            nodoCandidato = buscarMenor(nodo.getIzquierdo());
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

    private boolean perteneceAux(E elemento, NodoAVL<E> nodo) {
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

    private void listarAux(NodoAVL<E> nodo, Lista<E> listaElementos) {
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

    private void listarRangoAux(E elementoMinimo, E elementoMaximo, NodoAVL<E> nodo, Lista<E> listaRango) {
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

    private E minimoElementoAux(NodoAVL<E> nodo) {
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

    private E maximoElementoAux(NodoAVL<E> nodo) {
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
        ArbolAVL<E> that = (ArbolAVL<E>) o;
        return equals(this.raiz, that.raiz);
    }

    private boolean equals(NodoAVL<E> nodothis, NodoAVL<E> nodoThat) {
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

    private void toString(StringBuilder sb, NodoAVL<E> nodo) {
        if (nodo != null) {
            sb.append(nodo.getElemento()).append(" (").append(nodo.getAltura()).append(')').append(" -> ");
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
