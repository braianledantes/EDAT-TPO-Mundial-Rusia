package grafo;

import lineales.Lista;
import lineales.ListaDinamica;

public class GrafoDinamico<E> implements Grafo<E> {
    private NodoVert<E> inicio;

    @Override
    public boolean insertarVertice(E elem) {
        boolean existeVert = !exiteVertice(elem);
        if (existeVert) inicio = new NodoVert<>(elem, inicio);
        return !existeVert;
    }

    /**
     * Busca un nodo vertice con el elemento enviado por parámetro.
     *
     * @param elem el elemento a buscar
     * @return el nodo que contiene el elemento
     */
    private NodoVert<E> buscarVertice(E elem) {
        NodoVert<E> nodoVert = inicio;
        if (elem != null) {
            while (nodoVert != null && !nodoVert.getElem().equals(elem))
                nodoVert = nodoVert.getSigVertice();
        }
        return nodoVert;
    }

    @Override
    public boolean eliminarVertice(E elem) {
        boolean elimino = false;
        NodoVert<E> vert, vertAnt;

        if (elem != null && inicio != null) {
            if (inicio.getElem().equals(elem)) {
                vert = inicio; // para que elimine los adyacentes
                inicio = inicio.getSigVertice();
                elimino = true;
            } else {
                vert = inicio.getSigVertice();
                vertAnt = inicio;
                while (!elimino && vert != null) {
                    if (vert.getElem().equals(elem)) {
                        elimino = true;
                        vertAnt.setSigVertice(vert.getSigVertice());
                    }
                    vertAnt = vert;
                    vert = vert.getSigVertice();
                }
            }
            if (elimino) eliminarAdyacentes(vert);
        }

        return elimino;
    }

    private void eliminarAdyacentes(NodoVert<E> vertAEliminar) {
        NodoVert<E> vertice = inicio;
        while (vertice != null && vertice.getPrimerAdy() != null) {
            eliminarArco(vertice, vertAEliminar.getElem());
            vertice = vertice.getSigVertice();
        }
    }

    @Override
    public boolean insertarArco(E origen, E destino) {
        boolean inserto = false;
        NodoVert<E> nodoOrigen = buscarVertice(origen);
        NodoVert<E> nodoDestino = buscarVertice(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            if (!existeArco(nodoOrigen, nodoDestino)) {
                nodoOrigen.setPrimerAdy(new NodoAdy<>(nodoDestino, nodoOrigen.getPrimerAdy()));
                inserto = true;
            }
        }

        return inserto;
    }

    @Override
    public boolean eliminarArco(E origen, E destino) {
        boolean elimino = false;
        if (origen != null && destino != null)
            elimino = eliminarArco(buscarVertice(origen), destino);
        return elimino;
    }

    private boolean eliminarArco(NodoVert<E> vertOrigen, E destino) {
        boolean elimino = false;
        NodoAdy<E> ady, adyAnt;

        if (vertOrigen != null && vertOrigen.getPrimerAdy() != null) {
            if (vertOrigen.getPrimerAdy().getVertice().getElem().equals(destino)) {
                vertOrigen.setPrimerAdy(vertOrigen.getPrimerAdy().getSigAdy());
                elimino = true;
            } else {
                adyAnt = vertOrigen.getPrimerAdy();
                ady = vertOrigen.getPrimerAdy().getSigAdy();
                while (!elimino && ady != null) {
                    if (ady.getVertice().getElem().equals(destino)) {
                        adyAnt.setSigAdy(ady.getSigAdy());
                        elimino = true;
                    }
                    adyAnt = ady;
                    ady = ady.getSigAdy();
                }
            }
        }
        return elimino;
    }

    @Override
    public boolean exiteVertice(E elem) {
        return buscarVertice(elem) != null;
    }

    @Override
    public boolean existeArco(E origen, E destino) {
        NodoVert<E> nodoOrigen = buscarVertice(origen);
        NodoVert<E> nodoDestino = buscarVertice(destino);
        return existeArco(nodoOrigen, nodoDestino);
    }

    private boolean existeArco(NodoVert<E> origen, NodoVert<E> destino) {
        boolean existe = false;

        if (origen != null && destino != null) {
            NodoAdy<E> ady = origen.getPrimerAdy();
            while (!existe && ady != null) {
                existe = ady.getVertice().getElem().equals(destino.getElem());
                ady = ady.getSigAdy();
            }
        }

        return existe;
    }

    @Override
    public boolean existeCamino(E origen, E destino) {
        boolean exito = false;
        NodoVert<E> nodoOrigen = buscarVertice(origen);
        NodoVert<E> nodoDestino = buscarVertice(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            Lista<E> visitados = new ListaDinamica<>();
            exito = existeCamino(nodoOrigen, destino, visitados);
        }

        return exito;
    }

    private boolean existeCamino(NodoVert<E> nodo, E destino, Lista<E> visitados) {
        boolean exito = false;

        if (nodo != null) {
            if (nodo.getElem().equals(destino)) {
                exito = true;
            } else {
                visitados.insertar(nodo.getElem());
                NodoAdy<E> ady = nodo.getPrimerAdy();
                while (!exito && ady != null) {
                    if (visitados.existe(ady.getVertice().getElem())) {
                        exito = existeCamino(ady.getVertice(), destino, visitados);
                    }
                    ady = ady.getSigAdy();
                }
            }
        }

        return exito;
    }

    @Override
    public Lista<E> caminoMasCorto(E origen, E destino) {
        // TODO
        return null;
    }

    @Override
    public Lista<E> caminoMasLargo(E origen, E destino) {
        // TODO
        return null;
    }

    @Override
    public Lista<E> listarEnProfundidad() {
        Lista<E> visitados = new ListaDinamica<>();
        NodoVert<E> aux = this.inicio;
        while (aux != null) {
            if (visitados.existe(aux.getElem())) {
                listarEnProfundidad(aux, visitados);
            }
            aux = aux.getSigVertice();
        }

        return visitados;
    }

    private void listarEnProfundidad(NodoVert<E> nodo, Lista<E> visitados) {
        if (nodo != null) {
            visitados.insertar(nodo.getElem());
            NodoAdy<E> ady = nodo.getPrimerAdy();
            while (ady != null) {
                if (visitados.existe(ady.getVertice().getElem())) {
                    listarEnProfundidad(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdy();
            }
        }
    }

    @Override
    public Lista<E> listarEnAnchura() {
        // TODO
        return null;
    }

    @Override
    public boolean esVacio() {
        return inicio != null;
    }

    private static class NodoVert<E> {
        private E elem;
        private NodoVert<E> sigVertice;
        private NodoAdy<E> primerAdy;

        public NodoVert(E elem) {
            this(elem, null);
        }

        public NodoVert(E elem, NodoVert<E> sigVertice) {
            this.elem = elem;
            this.sigVertice = sigVertice;
            this.primerAdy = null;
        }

        public E getElem() {
            return elem;
        }

        public void setElem(E elem) {
            this.elem = elem;
        }

        public NodoVert<E> getSigVertice() {
            return sigVertice;
        }

        public void setSigVertice(NodoVert<E> sigVertice) {
            this.sigVertice = sigVertice;
        }

        public NodoAdy<E> getPrimerAdy() {
            return primerAdy;
        }

        public void setPrimerAdy(NodoAdy<E> primerAdy) {
            this.primerAdy = primerAdy;
        }

        public boolean tieneSigVertice() {
            return sigVertice != null;
        }

        public boolean tienePrimerAdyacente() {
            return primerAdy != null;
        }
    }

    private static class NodoAdy<E> {
        private NodoVert<E> vertice;
        private NodoAdy<E> sigAdy;

        public NodoAdy(NodoVert<E> vertice, NodoAdy<E> sigAdy) {
            this.vertice = vertice;
            this.sigAdy = sigAdy;
        }

        public NodoAdy(NodoVert<E> vertice) {
            this(vertice, null);
        }

        public NodoVert<E> getVertice() {
            return vertice;
        }

        public void setVertice(NodoVert<E> vertice) {
            this.vertice = vertice;
        }

        public NodoAdy<E> getSigAdy() {
            return sigAdy;
        }

        public void setSigAdy(NodoAdy<E> sigAdy) {
            this.sigAdy = sigAdy;
        }

        public boolean tieneSigAdyacente() {
            return sigAdy != null;
        }
    }
}
