package structures.grafo;

import structures.lineales.Lista;

public class GrafoEtiquetado<E> extends GrafoDirigidoEtiquetado<E> {

    @Override
    public boolean insertarArco(E origen, E destino) {
        // TODO tiene que ir y volver por el mismo arco
        return super.insertarArco(origen, destino);
    }

    @Override
    public boolean insertarArco(E origen, E destino, int etiqueta) {
        // TODO tiene que ir y volver por el mismo arco
        return super.insertarArco(origen, destino, etiqueta);
    }

    @Override
    public boolean eliminarVertice(E elem) {
        // TODO hacerlo grafo simple, NO PASAR POR TODO EL ARREGLO DE VERTICES
        return super.eliminarVertice(elem);
    }

    @Override
    public boolean eliminarArco(E origen, E destino) {
        // TODO borrar el arco de ambos vertices
        return super.eliminarArco(origen, destino);
    }

    @Override
    public Lista<E> caminoMasCorto(E origen, E destino1, E destino2) {
        // TODO no tiene que pasar por los mismos vertices, ni por los mismos arcos
        return super.caminoMasCorto(origen, destino1, destino2);
    }
}
