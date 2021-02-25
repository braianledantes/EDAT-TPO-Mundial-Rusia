package repaso.estructuras.grafos;

import repaso.estructuras.lineales.Lista;

public interface Grafo<E> {

    boolean insertar(E vertice);

    boolean eliminar(E vertice);

    boolean insertarArco(E verticeOrigen, E verticeDestino);

    boolean eliminarArco(E verticeOrigen, E verticeDestino);

    boolean existe(E vertice);

    boolean existeArco(E verticeOrigen, E verticeDestino);

    boolean existeCamino(E verticeOrigen, E verticeDestino);

    Lista<E> caminoMasCorto(E verticeOrigen, E verticeDestino);

    Lista<E> caminoMasLargo(E verticeOrigen, E verticeDestino);

    Lista<E> listarAnchura();

    Lista<E> listarProfundidad();

    boolean esVacio();

    void vaciar();
}
