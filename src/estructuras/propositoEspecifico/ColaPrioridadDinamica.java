package estructuras.propositoEspecifico;

import estructuras.lineales.Cola;
import estructuras.lineales.ColaDinamica;

import java.io.Serializable;

public class ColaPrioridadDinamica<E> implements ColaPrioridad<E>, Serializable {
    private Nodo<E> inicio;

    public ColaPrioridadDinamica() {
        inicio = null;
    }

    @Override
    public boolean insertar(E elemento, int prioridad) throws IllegalArgumentException {
        if (prioridad >= 0)
            return insertar(elemento, prioridad, null, inicio);
        else
            throw new IllegalArgumentException("prioridad debe ser un numero positivo: " + prioridad);
    }

    private boolean insertar(E elemento, int prioridad, Nodo<E> nodoAnt, Nodo<E> nodo) {
        boolean exito = false;
        if (nodo == null) {
            if (nodoAnt == null) {
                inicio = new Nodo<>(prioridad);
                inicio.getItems().poner(elemento);
            } else {
                Nodo<E> nuevoNodo = new Nodo<>(prioridad);
                nuevoNodo.getItems().poner(elemento);
                nodoAnt.setEnlace(nuevoNodo);
            }
        } else {
            if (nodo.getPrioridad() == prioridad) {
                nodo.getItems().poner(elemento);
                exito = true;
            } else if (prioridad > nodo.getPrioridad()) {
                exito = insertar(elemento, prioridad, nodo, nodo.getEnlace());
            } else if (prioridad < nodo.getPrioridad()) {
                if (nodoAnt == null) {
                    inicio = new Nodo<>(prioridad, elemento, nodo);
                } else {
                    nodoAnt.setEnlace(new Nodo<>(prioridad, elemento, nodo));
                }
            }
        }
        return exito;
    }

    @Override
    public boolean eliminarFrente() {
        boolean exito = false;

        if (inicio != null) {
            exito = inicio.getItems().sacar();
            if (inicio.getItems().esVacia()) {
                inicio = inicio.getEnlace();
            }
        }

        return exito;
    }

    @Override
    public E obtenerFrente() {
        E elem = null;

        if (inicio != null) {
            elem = inicio.getItems().obtenerFrente();
        }

        return elem;
    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
    }

    @Override
    public void vaciar() {
        inicio = null;
    }

    private static class Nodo<E> implements Serializable {
        private int prioridad;
        private Cola<E> items;
        private Nodo<E> enlace;

        public Nodo(int prioridad) {
            this.prioridad = prioridad;
            this.items = new ColaDinamica<>();
            enlace = null;
        }

        public Nodo(int prioridad, E elem, Nodo<E> enlace) {
            this.prioridad = prioridad;
            this.enlace = enlace;
            this.items = new ColaDinamica<>();
            this.items.poner(elem);
        }

        public Nodo<E> getEnlace() {
            return enlace;
        }

        public void setEnlace(Nodo<E> enlace) {
            this.enlace = enlace;
        }

        public int getPrioridad() {
            return prioridad;
        }

        public Cola<E> getItems() {
            return items;
        }
    }
}
