package repaso.estructuras.grafos;

import repaso.estructuras.lineales.Cola;
import repaso.estructuras.lineales.ColaDinamica;
import repaso.estructuras.lineales.Lista;
import repaso.estructuras.lineales.ListaDinamica;
import repaso.estructuras.utliles.Valor;

public class GrafoSimple<E> implements Grafo<E> {

    private NodoVert<E> inicio;

    @Override
    public boolean insertar(E vertice) {
        boolean exito = false;

        if (vertice != null) {
            NodoVert<E> nodo = ubicarVertice(vertice);
            if (nodo == null) {
                this.inicio = new NodoVert<>(vertice, this.inicio);
                exito = true;
            }
        }

        return exito;
    }

    private NodoVert<E> ubicarVertice(E vertice) {
        NodoVert<E> nodoVert = inicio;
        while (nodoVert != null && !nodoVert.getElem().equals(vertice)) {
            nodoVert = nodoVert.getSigVertice();
        }
        return nodoVert;
    }

    private NodoVert<E> ubicarPrimerVertice(E vertice1, E vertice2) {
        NodoVert<E> nodoVert = inicio;
        while (nodoVert != null && !nodoVert.getElem().equals(vertice1) && !nodoVert.getElem().equals(vertice2)) {
            nodoVert = nodoVert.getSigVertice();
        }
        return nodoVert;
    }

    private NodoVert<E>[] ubicarVertices(E vertice1, E vertice2) {
        NodoVert<E>[] vertices = new NodoVert[2];
        if (vertice1 != null && vertice2 != null) {
            NodoVert<E> nodoVert = inicio;
            while (nodoVert != null && (vertices[0] == null || vertices[1] == null)) {
                if (nodoVert.getElem().equals(vertice1))
                    vertices[0] = nodoVert;
                if (nodoVert.getElem().equals(vertice2))
                    vertices[1] = nodoVert;

                nodoVert = nodoVert.getSigVertice();
            }
        }
        return vertices;
    }

    @Override
    public boolean eliminar(E vertice) {
        boolean exito = false;

        if (vertice != null) {
            NodoVert<E> nodoVertAnt = null;
            NodoVert<E> nodoVert = inicio;
            while (nodoVert != null && !nodoVert.getElem().equals(vertice)) {
                nodoVertAnt = nodoVert;
                nodoVert = nodoVert.getSigVertice();
            }
            if (nodoVert != null) {
                eliminarArcos(nodoVert);
                if (nodoVertAnt == null) {
                    this.inicio = nodoVert.getSigVertice();
                } else {
                    nodoVertAnt.setSigVertice(nodoVert.getSigVertice());
                }
            }
        }

        return exito;
    }

    private void eliminarArcos(NodoVert<E> nodoVert) {
        NodoAdy<E> nodoAdy = nodoVert.getPrimerAdy();
        while (nodoAdy != null) {
            nodoAdy.getVertice().eliminarAdyacente(nodoVert);
            nodoAdy = nodoAdy.getSigAdyacente();
        }
    }

    @Override
    public boolean insertarArco(E verticeOrigen, E verticeDestino) {
        boolean exito = false;

        NodoVert<E>[] nodos = ubicarVertices(verticeOrigen, verticeDestino);
        NodoVert<E> nodoVertOrigen = nodos[0];
        NodoVert<E> nodoVertDestino = nodos[1];
        if (nodoVertOrigen != null && nodoVertDestino != null) {
            if (!existeArco(nodoVertOrigen, nodoVertDestino)) {
                nodoVertOrigen.setPrimerAdy(new NodoAdy<>(nodoVertDestino, nodoVertOrigen.getPrimerAdy()));
                nodoVertDestino.setPrimerAdy(new NodoAdy<>(nodoVertOrigen, nodoVertDestino.getPrimerAdy()));
                exito = true;
            }
        }

        return exito;
    }

    @Override
    public boolean eliminarArco(E verticeOrigen, E verticeDestino) {
        boolean exito = false;

        if (verticeOrigen != null && verticeDestino != null) {
            NodoVert<E> nodoVert = ubicarPrimerVertice(verticeOrigen, verticeDestino);
            E vertice = verticeDestino;
            if (nodoVert.getElem().equals(verticeDestino)) {
                vertice = verticeOrigen;
            }

            exito = eliminarArco(nodoVert, vertice);
        }

        return exito;
    }

    /**
     * Elimina ambos arcos desde ambos vertices
     *
     * @param nodoVert donde empieza a buscar
     * @param vertice  el vertice adyacente buscado
     * @return si elimino
     */
    private boolean eliminarArco(NodoVert<E> nodoVert, E vertice) {
        boolean exito = false;
        NodoAdy<E> adyAnt = null;
        NodoAdy<E> ady = nodoVert.getPrimerAdy();
        while (ady != null && !ady.getVertice().getElem().equals(vertice)) {
            adyAnt = ady;
            ady = ady.getSigAdyacente();
        }

        if (ady != null) {
            if (adyAnt == null) {
                nodoVert.setPrimerAdy(ady.getSigAdyacente());
            } else {
                adyAnt.setSigAdyacente(ady.getSigAdyacente());
            }

            // elimina el arco del otro vertice
            vertice = nodoVert.getElem();
            nodoVert = ady.getVertice();

            adyAnt = null;
            ady = nodoVert.getPrimerAdy();
            while (ady != null && !ady.getVertice().getElem().equals(vertice)) {
                adyAnt = ady;
                ady = ady.getSigAdyacente();
            }

            if (ady != null) {
                if (adyAnt == null) {
                    nodoVert.setPrimerAdy(ady.getSigAdyacente());
                } else {
                    adyAnt.setSigAdyacente(ady.getSigAdyacente());
                }
            }

            exito = true;
        }
        return exito;
    }

    @Override
    public boolean existe(E vertice) {
        return ubicarVertice(vertice) != null;
    }

    @Override
    public boolean existeArco(E verticeOrigen, E verticeDestino) {
        boolean exito = false;

        NodoVert<E>[] nodos = ubicarVertices(verticeOrigen, verticeDestino);
        NodoVert<E> nodoVertOrigen = nodos[0];
        NodoVert<E> nodoVertDestino = nodos[1];
        if (nodoVertOrigen != null && nodoVertDestino != null) {
            exito = existeArco(nodoVertOrigen, nodoVertDestino);
        }

        return exito;
    }

    private boolean existeArco(NodoVert<E> verticeOrigen, NodoVert<E> verticeDestino) {
        boolean existe = false;

        NodoAdy<E> nodoAdy = verticeOrigen.getPrimerAdy();
        while (!existe && nodoAdy != null) {
            existe = nodoAdy.getVertice() == verticeDestino;
            nodoAdy = nodoAdy.getSigAdyacente();
        }

        return existe;
    }

    @Override
    public boolean existeCamino(E verticeOrigen, E verticeDestino) {
        boolean exito = false;

        NodoVert<E>[] nodos = ubicarVertices(verticeOrigen, verticeDestino);
        NodoVert<E> nodoVertOrigen = nodos[0];
        NodoVert<E> nodoVertDestino = nodos[1];

        if (nodoVertOrigen != null && nodoVertDestino != null) {
            Lista<E> visitados = new ListaDinamica<>();
            exito = existeCaminoAux(visitados, nodoVertOrigen, nodoVertDestino);
        }

        return exito;
    }

    private boolean existeCaminoAux(Lista<E> visitados, NodoVert<E> vertice, NodoVert<E> verticeDestino) {
        boolean exito = false;

        if (vertice == verticeDestino) {
            exito = true;
        } else {
            visitados.insertar(vertice.getElem(), visitados.longitud());
            NodoAdy<E> ady = vertice.getPrimerAdy();
            while (!exito && ady != null) {
                if (visitados.localizar(ady.getVertice().getElem()) == -1) {
                    exito = existeCaminoAux(visitados, ady.getVertice(), verticeDestino);
                }
                ady = ady.getSigAdyacente();
            }
        }

        return exito;
    }

    @Override
    public Lista<E> caminoMasCorto(E verticeOrigen, E verticeDestino) {
        Lista<E> camino = new ListaDinamica<>();

        NodoVert<E>[] nodos = ubicarVertices(verticeOrigen, verticeDestino);
        NodoVert<E> nodoVertOrigen = nodos[0];
        NodoVert<E> nodoVertDestino = nodos[1];
        if (nodoVertOrigen != null && nodoVertDestino != null) {
            ListaDinamica<E> visitados = new ListaDinamica<>();
            Valor<Integer> distMin = new Valor<>(Integer.MAX_VALUE);
            camino = caminoMasCortoAux(camino, visitados, distMin, 0, nodoVertOrigen, nodoVertDestino);
        }

        return camino;
    }

    private Lista<E> caminoMasCortoAux(Lista<E> camino, ListaDinamica<E> visitados, Valor<Integer> distMin, int distActual, NodoVert<E> nodoActual, NodoVert<E> destino) {
        if (distActual < distMin.getValor()) {
            visitados.insertar(nodoActual.getElem(), visitados.longitud() + 1);
            if (nodoActual == destino) {
                camino = visitados.clone();
                distMin.setValor(distActual);
            } else {
                NodoAdy<E> ady = nodoActual.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getElem()) == -1) {

                        camino = caminoMasCortoAux(
                                camino,
                                visitados,
                                distMin,
                                distActual + 1,
                                ady.getVertice(),
                                destino);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return camino;
    }

    @Override
    public Lista<E> caminoMasLargo(E verticeOrigen, E verticeDestino) {
        Lista<E> camino = new ListaDinamica<>();

        NodoVert<E>[] nodos = ubicarVertices(verticeOrigen, verticeDestino);
        NodoVert<E> nodoVertOrigen = nodos[0];
        NodoVert<E> nodoVertDestino = nodos[1];
        if (nodoVertOrigen != null && nodoVertDestino != null) {
            ListaDinamica<E> visitados = new ListaDinamica<>();
            Valor<Integer> distMin = new Valor<>(Integer.MIN_VALUE);
            camino = caminoMasLargoAux(camino, visitados, distMin, 0, nodoVertOrigen, nodoVertDestino);
        }

        return camino;
    }

    private Lista<E> caminoMasLargoAux(Lista<E> camino, ListaDinamica<E> visitados, Valor<Integer> distMin, int distAct, NodoVert<E> nodoVert, NodoVert<E> destino) {
        visitados.insertar(nodoVert.getElem(), visitados.longitud() + 1);
        if (nodoVert == destino) {
            if (distAct > distMin.getValor()) {
                camino = visitados.clone();
                distMin.setValor(distAct);
            }
        } else {
            NodoAdy<E> ady = nodoVert.getPrimerAdy();
            while (ady != null) {
                if (visitados.localizar(ady.getVertice().getElem()) == -1) {
                    camino = caminoMasLargoAux(
                            camino,
                            visitados,
                            distMin,
                            distAct + 1,
                            ady.getVertice(),
                            destino);
                }
                ady = ady.getSigAdyacente();
            }
        }
        visitados.eliminar(visitados.longitud());

        return camino;
    }

    @Override
    public Lista<E> listarAnchura() {
        Lista<E> listaVisitados = new ListaDinamica<>();
        NodoVert<E> vertice = this.inicio;
        while (vertice != null) {
            if (listaVisitados.localizar(vertice.getElem()) == -1) {
                listarAnchuraAux(listaVisitados, vertice);
            }
            vertice = vertice.getSigVertice();
        }
        return listaVisitados;
    }

    private void listarAnchuraAux(Lista<E> listaVisitados, NodoVert<E> vertice) {
        Cola<NodoVert<E>> cola = new ColaDinamica<>();
        listaVisitados.insertar(vertice.getElem(), listaVisitados.longitud() + 1);
        cola.poner(vertice);
        while (!cola.esVacia()) {
            vertice = cola.obtenerFrente();
            cola.sacar();
            NodoAdy<E> ady = vertice.getPrimerAdy();
            while (ady != null) {
                vertice = ady.getVertice();
                if (listaVisitados.localizar(vertice.getElem()) == -1) {
                    listaVisitados.insertar(vertice.getElem(), listaVisitados.longitud() + 1);
                    cola.poner(ady.getVertice());
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    @Override
    public Lista<E> listarProfundidad() {
        Lista<E> listaVisitados = new ListaDinamica<>();
        listarProfundidadAux(listaVisitados, this.inicio);
        return listaVisitados;
    }

    private void listarProfundidadAux(Lista<E> listaVisitados, NodoVert<E> vertice) {
        if (vertice != null) {
            if (listaVisitados.localizar(vertice.getElem()) == -1) {
                listaVisitados.insertar(vertice.getElem(), listaVisitados.longitud() + 1);
                NodoAdy<E> ady = vertice.getPrimerAdy();
                while (ady != null) {
                    listarProfundidadAux(listaVisitados, ady.getVertice());
                    ady = ady.getSigAdyacente();
                }
            }
        }
    }

    @Override
    public boolean esVacio() {
        return this.inicio == null;
    }

    @Override
    public void vaciar() {
        this.inicio = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GrafoSimple{\n");

        NodoVert<E> nodo = this.inicio;
        while (nodo != null) {
            sb.append(nodo.getElem()).append(" --> ");
            NodoAdy<E> ady = nodo.getPrimerAdy();
            while (ady != null) {
                sb.append(ady.getVertice().getElem()).append(", ");
                ady = ady.getSigAdyacente();
            }
            sb.append('\n');
            nodo = nodo.getSigVertice();
        }
        sb.append('}');
        return sb.toString();
    }
}
