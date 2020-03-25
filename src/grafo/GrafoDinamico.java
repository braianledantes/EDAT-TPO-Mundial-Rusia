package grafo;

import lineales.Cola;
import lineales.ColaDinamica;
import lineales.Lista;
import lineales.ListaDinamica;

public class GrafoDinamico<E> implements Grafo<E> {
    private NodoVert<E> inicio;

    public GrafoDinamico() {
        this.inicio = null;
    }

    @Override
    public boolean insertarVertice(E elem) {
        boolean existeVert = exiteVertice(elem);
        if (!existeVert) inicio = new NodoVert<>(elem, inicio);
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
                    } else {
                        vertAnt = vert;
                        vert = vert.getSigVertice();
                    }
                }
            }
            if (elimino) eliminarAdyacentes(vert);
        }

        return elimino;
    }

    private void eliminarAdyacentes(NodoVert<E> vertAEliminar) {
        NodoVert<E> vertice = inicio;
        while (vertice != null) {
            eliminarArco(vertice, vertAEliminar.getElem());
            vertice = vertice.getSigVertice();
        }
    }

    @Override
    public boolean insertarArco(E origen, E destino) {
        return insertarArco(origen, destino, 1);
    }

    @Override
    public boolean insertarArco(E origen, E destino, int etiqueta) {
        boolean inserto = false;
        NodoVert<E> nodoOrigen = buscarVertice(origen);
        NodoVert<E> nodoDestino = buscarVertice(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            if (!existeArco(nodoOrigen, nodoDestino)) {
                nodoOrigen.setPrimerAdy(new NodoAdy<>(nodoDestino, nodoOrigen.getPrimerAdy(), etiqueta));
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
        NodoVert<E> vertOrigen = buscarVertice(origen);
        NodoVert<E> vertDestino = buscarVertice(destino);

        if (vertOrigen != null && vertDestino != null) {
            Lista<E> visitados = new ListaDinamica<>();
            exito = existeCamino(vertOrigen, vertDestino, visitados);
        }

        return exito;
    }

    private boolean existeCamino(NodoVert<E> vert, NodoVert<E> destino, Lista<E> visitados) {
        boolean exito = false;

        if (vert != null) {
            if (vert == destino) {
                exito = true;
            } else {
                visitados.insertar(vert.getElem());
                NodoAdy<E> ady = vert.getPrimerAdy();
                while (!exito && ady != null) {
                    if (!visitados.existe(ady.getVertice().getElem())) {
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
        NodoVert<E> vertOrigen = buscarVertice(origen);
        NodoVert<E> vertDestino = buscarVertice(destino);
        ListaDinamica<E> visitados = new ListaDinamica<>();
        ListaDinamica<E> camino = new ListaDinamica<>();
        int[] distanciaMinima = {Integer.MAX_VALUE}; // Se usa un arreglo para mantener la referencia y poder modificarlo.

        if (vertOrigen != null & vertDestino != null) {
            camino = caminoMasCorto(vertOrigen, destino, visitados, 0, camino, distanciaMinima);
        }
        return camino;
    }

    private ListaDinamica<E> caminoMasCorto(NodoVert<E> vertice,
                                            E destino,
                                            ListaDinamica<E> visitados,
                                            int distActual,
                                            ListaDinamica<E> camino,
                                            int[] distMin) {
        visitados.insertar(vertice.getElem());
        if (vertice.getElem().equals(destino)) {
            camino = visitados.clone();
            distMin[0] = distActual;
        } else {
            NodoAdy<E> ady = vertice.getPrimerAdy();
            while (ady != null) {
                distActual += ady.getEtiqueta();
                if (distActual < distMin[0]) {
                    if (!visitados.existe(ady.getVertice().getElem())) {
                        camino = caminoMasCorto(ady.getVertice(), destino, visitados, distActual, camino, distMin);
                    }
                }
                distActual -= ady.getEtiqueta();
                ady = ady.getSigAdy();
            }
        }
        visitados.eliminar(visitados.longitud());

        return camino;
    }

    @Override
    public Lista<E> caminoMasLargo(E origen, E destino) {
        NodoVert<E> vertOrigen = buscarVertice(origen);
        NodoVert<E> vertDestino = buscarVertice(destino);
        ListaDinamica<E> visitados = new ListaDinamica<>();
        ListaDinamica<E> camino = new ListaDinamica<>();
        int[] distanciaMaxima = {Integer.MIN_VALUE};

        if (vertOrigen != null & vertDestino != null) {
            camino = caminoMasLargo(vertOrigen, destino, visitados, 0, camino, distanciaMaxima);
        }
        return camino;
    }

    private ListaDinamica<E> caminoMasLargo(NodoVert<E> vertice,
                                            E destino,
                                            ListaDinamica<E> visitados,
                                            int distActual,
                                            ListaDinamica<E> camino,
                                            int[] distMax) {
        visitados.insertar(vertice.getElem());
        if (vertice.getElem().equals(destino)) {
            if (distActual > distMax[0]) {
                camino = visitados.clone();
                distMax[0] = distActual;
            }
        } else {
            NodoAdy<E> ady = vertice.getPrimerAdy();
            while (ady != null) {
                distActual += ady.getEtiqueta();
                if (!visitados.existe(ady.getVertice().getElem())) {
                    camino = caminoMasLargo(ady.getVertice(), destino, visitados, distActual, camino, distMax);
                }
                distActual -= ady.getEtiqueta();
                ady = ady.getSigAdy();
            }
        }
        visitados.eliminar(visitados.longitud());

        return camino;
    }

    @Override
    public Lista<E> listarEnProfundidad() {
        Lista<E> visitados = new ListaDinamica<>();
        NodoVert<E> aux = this.inicio;
        while (aux != null) {
            if (!visitados.existe(aux.getElem())) {
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
                if (!visitados.existe(ady.getVertice().getElem())) {
                    listarEnProfundidad(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdy();
            }
        }
    }

    @Override
    public Lista<E> listarEnAnchura() {
        Lista<E> visitados = new ListaDinamica<>();
        NodoVert<E> vert = inicio;
        while (vert != null) {
            if (!visitados.existe(vert.getElem())) {
                listarEnAnchura(vert, visitados);
            }
            vert = vert.getSigVertice();
        }
        return visitados;
    }

    private void listarEnAnchura(NodoVert<E> vertIni, Lista<E> visitados) {
        Cola<NodoVert<E>> cola = new ColaDinamica<>();
        NodoVert<E> vert;
        NodoAdy<E> ady;

        cola.poner(vertIni);
        while (!cola.esVacia()) {
            vert = cola.obtenerFrente();
            cola.sacar();
            visitados.insertar(vert.getElem());
            ady = vert.getPrimerAdy();
            while (ady != null) {
                if (!visitados.existe(ady.getVertice().getElem())) {
                    cola.poner(ady.getVertice());
                }
                ady = ady.getSigAdy();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GrafoDinamico{inicio=");
        if (inicio != null) sb.append(inicio.getElem());
        else sb.append("null");
        sb.append(",\n");
        NodoVert<E> vertice = inicio;
        NodoAdy<E> ady;

        while (vertice != null) {
            sb.append(vertice.getElem()).append(" -> ");
            ady = vertice.getPrimerAdy();
            while (ady != null) {
                sb.append(ady.getVertice().getElem()).append(" (").append(ady.getEtiqueta()).append("), ");
                ady = ady.getSigAdy();
            }
            vertice = vertice.getSigVertice();
            sb.append("\n");
        }

        return sb.append("}").toString();
    }

    @Override
    public boolean esVacio() {
        return inicio == null;
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

        @Override
        public String toString() {
            return "NodoVert{" +
                    "elem=" + elem +
                    '}';
        }
    }

    private static class NodoAdy<E> {
        private NodoVert<E> vertice;
        private NodoAdy<E> sigAdy;
        private int etiqueta;

        public NodoAdy(NodoVert<E> vertice, NodoAdy<E> sigAdy, int etiqueta) {
            this.vertice = vertice;
            this.sigAdy = sigAdy;
            this.etiqueta = etiqueta;
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

        public int getEtiqueta() {
            return etiqueta;
        }

        public void setEtiqueta(int etiqueta) {
            this.etiqueta = etiqueta;
        }

        @Override
        public String toString() {
            return "NodoAdy{" +
                    "vertice=" + vertice.getElem() +
                    ", etiqueta=" + etiqueta +
                    '}';
        }
    }
}
