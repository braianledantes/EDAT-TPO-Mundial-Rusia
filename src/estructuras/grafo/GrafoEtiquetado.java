package estructuras.grafo;

import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;

public class GrafoEtiquetado<E> extends DigrafoEtiquetado<E> {

    @Override
    public boolean insertarArco(E vert1, E vert2, int etiqueta) {
        boolean inserto = false;
        NodoVert<E>[] vertices = buscarDosVertices(vert1, vert2);
        NodoVert<E> nodoVert1 = vertices[0];
        NodoVert<E> nodoVert2 = vertices[1];

        if (nodoVert1 != null && nodoVert2 != null) {
            if (nodoVert1 == nodoVert2) { // por si es un lazo
                if (!existeArco(nodoVert1, nodoVert2)) {
                    nodoVert1.setPrimerAdy(new NodoAdy<>(nodoVert2, nodoVert1.getPrimerAdy(), etiqueta));
                    inserto = true;
                }
            } else {
                if (!existeArco(nodoVert1, nodoVert2)) {
                    nodoVert1.setPrimerAdy(new NodoAdy<>(nodoVert2, nodoVert1.getPrimerAdy(), etiqueta));
                    inserto = true;
                }
                if (!existeArco(nodoVert2, nodoVert1)) {
                    nodoVert2.setPrimerAdy(new NodoAdy<>(nodoVert1, nodoVert2.getPrimerAdy(), etiqueta));
                    inserto = true;
                }
            }
        }

        return inserto;
    }

    @Override
    public boolean eliminarVertice(E elem) {
        boolean elimino = false;
        NodoVert<E> vert, vertAnt;

        if (elem != null && inicio != null) {
            // busco el vértice y lo quito de la lista de vértices
            if (inicio.getElem().equals(elem)) { // si está en el inicio
                vert = inicio;
                inicio = inicio.getSigVertice();
                elimino = true;
            } else {
                vertAnt = inicio;
                vert = inicio.getSigVertice();
                while (!elimino && vert != null) {
                    if (vert.getElem().equals(elem)) {
                        vertAnt.setSigVertice(vert.getSigVertice());
                        elimino = true;
                    } else {
                        vertAnt = vert;
                        vert = vert.getSigVertice();
                    }
                }
            }
            if (vert != null) { // si lo encontró y lo eliminó de la lista de vertices
                NodoAdy<E> ady = vert.getPrimerAdy();
                while (ady != null) {
                    eliminarArco(ady.getVertice(), elem);
                    ady = ady.getSigAdy();
                }
                elimino = true;
            }
        }

        return elimino;
    }

    @Override
    public boolean eliminarArco(E vert1, E vert2) {
        boolean elimino = false;
        NodoVert<E> vertEliminado, nodoVert;
        if (vert1 != null && vert2 != null) {
            nodoVert = buscarPrimerVertice(vert1, vert2);
            if (nodoVert != null) {
                if (nodoVert.getElem().equals(vert1)) {
                    vertEliminado = eliminarArco(nodoVert, vert2);
                    if (vertEliminado != null) {
                        eliminarArco(vertEliminado, vert1);
                        elimino = true;
                    }
                } else if (nodoVert.getElem().equals(vert2)) {
                    vertEliminado = eliminarArco(nodoVert, vert1);
                    if (vertEliminado != null) {
                        eliminarArco(vertEliminado, vert2);
                        elimino = true;
                    }
                }
            }
        }
        return elimino;
    }

    @Override
    public boolean existeArco(E vert1, E vert2) {
        boolean existe = false;
        NodoVert<E> vert = buscarPrimerVertice(vert1, vert2);
        NodoVert<E> otroVert = null;

        if (vert != null) {
            E otroElem = (vert.getElem().equals(vert1)) ? vert2 : vert1;
            // busco el vertice adyacente
            NodoAdy<E> ady = vert.getPrimerAdy();
            while (otroVert == null && ady != null) {
                if (ady.getVertice().getElem().equals(otroElem)) {
                    otroVert = ady.getVertice();
                }
                ady = ady.getSigAdy();
            }

            // y si lo encontro busca el otro arco
            if (otroVert != null) {
                ady = otroVert.getPrimerAdy();
                while (!existe && ady != null) {
                    existe = ady.getVertice().getElem().equals(vert.getElem());
                    ady = ady.getSigAdy();
                }
            }
        }

        return existe;
    }

    @Override
    public Lista<E> caminoMasCorto(E origen, E destino1, E destino2) {
        NodoVert<E>[] vertices = buscarTresVertices(origen, destino1, destino2);
        NodoVert<E> vertOrigen = vertices[0];
        NodoVert<E> vertDestino1 = vertices[1];
        NodoVert<E> vertDestino2 = vertices[2];
        ListaDinamica<E> visitados = new ListaDinamica<>();
        ListaDinamica<E> camino = new ListaDinamica<>();
        int[] distanciaMinima = {Integer.MAX_VALUE}; // Se usa un arreglo para mantener la referencia y poder modificarlo.
        boolean[] bandera = {false}; // si paso por el primer destino.

        if (vertOrigen != null && vertDestino1 != null && vertDestino2 != null) {
            if (destino1 != destino2) {
                camino = caminoMasCorto(
                        vertOrigen,
                        vertDestino1,
                        vertDestino2,
                        visitados,
                        0,
                        camino,
                        distanciaMinima,
                        bandera);
            } else {
                camino = caminoMasCorto(vertOrigen, vertDestino1, visitados, 0, camino, distanciaMinima);
            }
        }
        return camino;
    }

    private ListaDinamica<E> caminoMasCorto(NodoVert<E> vertice,
                                            NodoVert<E> destino1,
                                            NodoVert<E> destino2,
                                            ListaDinamica<E> visitados,
                                            int distActual,
                                            ListaDinamica<E> camino,
                                            int[] distMin,
                                            boolean[] bandera) {
        if (distActual < distMin[0]) {
            visitados.insertar(vertice.getElem());

            if (bandera[0] && vertice == destino2) { // si llego al final
                camino = visitados.clone();
                distMin[0] = distActual;
            } else {
                if (!bandera[0] && vertice == destino1) { // si llego a la mitad
                    bandera[0] = true;
                }
                NodoAdy<E> ady = vertice.getPrimerAdy();
                while (ady != null) {
                    if (!visitados.existe(ady.getVertice().getElem())) { // mientras no haya pasado por el mismo vertice

                        camino = caminoMasCorto(
                                ady.getVertice(),
                                destino1,
                                destino2,
                                visitados,
                                distActual + ady.getEtiqueta(),
                                camino,
                                distMin,
                                bandera);

                        if (bandera[0] && ady.getVertice() == destino1) // por si volvio de la mitad, ese camino no sirve
                            bandera[0] = false;
                    }
                    ady = ady.getSigAdy();
                }
            }

            visitados.eliminar(visitados.longitud());
        }
        return camino;
    }

    @Override
    public Lista<E> caminoMasLargo(E origen, E destino) {
        NodoVert<E>[] vertices = buscarDosVertices(origen, destino);
        NodoVert<E> vertOrigen = vertices[0];
        NodoVert<E> vertDestino = vertices[1];
        ListaDinamica<E> vertVisitados = new ListaDinamica<>();
        ListaDinamica<E> camino = new ListaDinamica<>();
        int[] distanciaMaxima = {Integer.MIN_VALUE};

        if (vertOrigen != null && vertDestino != null) {
            camino = caminoMasLargo(vertOrigen, vertDestino, vertVisitados, 0, camino, distanciaMaxima);
        }
        return camino;
    }

    private ListaDinamica<E> caminoMasLargo(NodoVert<E> vertice,
                                            NodoVert<E> destino,
                                            ListaDinamica<E> vertVisitados,
                                            int distActual,
                                            ListaDinamica<E> camino,
                                            int[] distMax) {
        vertVisitados.insertar(vertice.getElem());
        if (vertice == destino) { // si llego al destino, los vertices visitados es un posible camino
            if (distActual > distMax[0]) {
                camino = vertVisitados.clone();
                distMax[0] = distActual;
            }
        } else { // mientras sea cualquier vertice menos el destino
            NodoAdy<E> ady = vertice.getPrimerAdy();
            while (ady != null) {
                if (!vertVisitados.existe(ady.getVertice().getElem())) {
                    camino = caminoMasLargo(
                            ady.getVertice(),
                            destino,
                            vertVisitados,
                            distActual + ady.getEtiqueta(),
                            camino,
                            distMax);
                }
                ady = ady.getSigAdy();
            }
        }
        vertVisitados.eliminar(vertVisitados.longitud());

        return camino;
    }
}
