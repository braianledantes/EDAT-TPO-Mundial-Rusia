package estructuras.conjuntistas;

import estructuras.jerarquicas.ArbolBinarioDinamico;
import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;

import java.io.Serializable;

public class ABB<T extends Comparable<T>> extends ArbolBinarioDinamico<T> implements ArbolBinarioBusqueda<T>, Serializable {

    @Override
    public boolean insertar(T elem) {
        boolean exito = true;

        if (elem != null) {
            if (this.raiz == null)
                this.raiz = new Nodo<>(elem);
            else
                exito = insertar(elem, this.raiz);
        }
        return exito;
    }

    protected boolean insertar(T elem, Nodo<T> nodo) {
        boolean exito = true;

        if (elem.compareTo(nodo.getElem()) == 0) {
            exito = false;
        } else {
            if (elem.compareTo(nodo.getElem()) < 0) {
                if (nodo.tieneIzq())
                    exito = insertar(elem, nodo.getIzq());
                else
                    nodo.setIzq(new Nodo<>(elem));
            } else {
                if (nodo.tieneDer())
                    exito = insertar(elem, nodo.getDer());
                else
                    nodo.setDer(new Nodo<>(elem));
            }
        }
        return exito;
    }

    @Override
    public boolean eliminar(T elem) {
        boolean exito = false;

        if (elem != null && this.raiz != null)
            exito = eliminar(elem, this.raiz, null);

        return exito;
    }

    protected boolean eliminar(T elem, Nodo<T> nodo, Nodo<T> nodoPadre) {
        boolean exito = false;

        if (nodo != null) {
            if (elem.equals(nodo.getElem())) {
                if (nodo.tieneIzq() && nodo.tieneDer()) {
                    eliminarNodoConAmbosHijos(nodo, nodoPadre);
                } else if (nodo.tieneIzq() || nodo.tieneDer()) {
                    eliminarNodoConUnHijo(nodo, nodoPadre);
                } else {
                    eliminarNodoHoja(nodo, nodoPadre);
                }
                exito = true;
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    exito = eliminar(elem, nodo.getIzq(), nodo);
                } else {
                    exito = eliminar(elem, nodo.getDer(), nodo);
                }
            }
        }

        return exito;
    }

    private void eliminarNodoHoja(Nodo<T> nodo, Nodo<T> nodoPadre) {
        if (nodoPadre == null) { // si es la raíz
            this.raiz = null;
        } else { // si es un nodo interno
            if (nodoPadre.getIzq() == nodo)
                nodoPadre.setIzq(null);
            else
                nodoPadre.setDer(null);
        }
    }

    private void eliminarNodoConUnHijo(Nodo<T> nodo, Nodo<T> nodoPadre) {
        Nodo<T> enlace;
        if (nodo.tieneIzq())
            enlace = nodo.getIzq();
        else
            enlace = nodo.getDer();

        if (nodoPadre == null) { // si es raíz
            raiz = enlace;
        } else { // si es un nodo interno
            if (nodo == nodoPadre.getIzq())
                nodoPadre.setIzq(enlace);
            else
                nodoPadre.setDer(enlace);
        }
    }

    private void eliminarNodoConAmbosHijos(Nodo<T> nodo, Nodo<T> nodoPadre) {
        Nodo<T> nodoCandidatoDer = buscarCandidatoMenor(nodo.getDer(), nodo);

        if (nodoPadre == null) { // si es raíz
            this.raiz.setElem(nodoCandidatoDer.getElem());
        } else { // si es un nodo interno
            nodo.setElem(nodoCandidatoDer.getElem());
        }
    }

    private Nodo<T> buscarCandidatoMenor(Nodo<T> nodo, Nodo<T> nodoPadre) {
        Nodo<T> nodoCandidato;

        if (!nodo.tieneIzq()) {
            nodoCandidato = nodo;
            if (nodoCandidato == nodoPadre.getDer()) { // si el candidato es el hijo inmediato al nodo a eliminar
                nodoPadre.setDer(nodoCandidato.getDer());
            } else {
                nodoPadre.setIzq(nodoCandidato.getDer());
            }
        } else {
            nodoCandidato = buscarCandidatoMenor(nodo.getIzq(), nodo);
        }

        return nodoCandidato;
    }

    @Override
    public boolean pertenece(T elem) {
        return pertenece(elem, this.raiz);
    }

    private boolean pertenece(T elem, Nodo<T> nodo) {
        boolean pertenece = false;

        if (elem != null && nodo != null) {
            if (elem.equals(nodo.getElem())) {
                pertenece = true;
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    pertenece = pertenece(elem, nodo.getIzq());
                } else {
                    pertenece = pertenece(elem, nodo.getDer());
                }
            }
        }

        return pertenece;
    }

    @Override
    public Lista<T> listar() {
        return listarInorden();
    }

    @Override
    public Lista<T> listarRango(T elemMin, T elemMax) {
        Lista<T> lista = new ListaDinamica<>();
        if (elemMin != null && elemMax != null)
            listarRango(lista, elemMin, elemMax, raiz);
        return lista;
    }

    private void listarRango(Lista<T> lista, T elemMin, T elemMax, Nodo<T> nodo) {
        if (nodo != null) {
            if (elemMin.compareTo(nodo.getElem()) < 0)
                listarRango(lista, elemMin, elemMax, nodo.getIzq());
            if (elemMin.compareTo(nodo.getElem()) <= 0 && elemMax.compareTo(nodo.getElem()) >= 0)
                lista.insertar(nodo.getElem());
            if (elemMax.compareTo(nodo.getElem()) > 0)
                listarRango(lista, elemMin, elemMax, nodo.getDer());
        }
    }

    @Override
    public T minimoElemento() {
        return minimoElemento(this.raiz);
    }

    private T minimoElemento(Nodo<T> nodo) {
        T elem = null;

        if (nodo != null) {
            if (!nodo.tieneIzq()) {
                elem = nodo.getElem();
            } else {
                elem = minimoElemento(nodo.getIzq());
            }
        }

        return elem;
    }

    @Override
    public T maximoElemento() {
        return maximoElemento(this.raiz);
    }

    private T maximoElemento(Nodo<T> nodo) {
        T elem = null;

        if (nodo != null) {
            if (!nodo.tieneDer()) {
                elem = nodo.getElem();
            } else {
                elem = minimoElemento(nodo.getDer());
            }
        }

        return elem;
    }
}
