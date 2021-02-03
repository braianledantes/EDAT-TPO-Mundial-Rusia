package test.estructuras;

import org.junit.jupiter.api.Test;
import estructuras.grafo.GrafoEtiquetado;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrafoEtiquetadoTest {

    @Test
    void insertarVertice() {
        GrafoEtiquetado<Integer> grafo = new GrafoEtiquetado<>();
        System.out.println(grafo);
        assertTrue(grafo.esVacio());

        assertTrue(grafo.insertarVertice(1));
        assertFalse(grafo.insertarVertice(1));
        assertTrue(grafo.insertarVertice(2));
        assertFalse(grafo.insertarVertice(2));
        assertFalse(grafo.insertarVertice(null));
        assertTrue(grafo.insertarVertice(3));
        assertTrue(grafo.insertarVertice(4));
        assertTrue(grafo.insertarVertice(5));
        assertTrue(grafo.insertarVertice(6));
        assertTrue(grafo.insertarVertice(7));
        assertTrue(grafo.insertarVertice(8));
        assertTrue(grafo.insertarArco(1, 2, 1));
        assertTrue(grafo.insertarArco(2, 5, 1));
        assertTrue(grafo.insertarArco(1, 3, 1));
        assertFalse(grafo.insertarArco(3, 1, 1));
        assertTrue(grafo.insertarArco(3, 4, 1));
        assertTrue(grafo.insertarArco(4, 6, 1));
        assertTrue(grafo.insertarArco(6, 7, 1));
        assertFalse(grafo.insertarArco(6, 7, 1));
        assertTrue(grafo.insertarArco(5, 7, 1));
        assertTrue(grafo.insertarArco(7, 8, 1));
        assertTrue(grafo.insertarArco(3, 8, 1));
        System.out.println(grafo);
        System.out.println(grafo.listarEnProfundidad().toString());
        System.out.println(grafo.listarEnAnchura().toString());
        assertTrue(grafo.existeCamino(1, 2));
        assertTrue(grafo.existeCamino(2, 4));
        assertTrue(grafo.existeCamino(2, 3));
        assertTrue(grafo.existeCamino(2, 1));
        assertTrue(grafo.existeCamino(1, 7));
        assertTrue(grafo.existeCamino(7, 4));

        assertTrue(grafo.eliminarVertice(8));
        assertTrue(grafo.eliminarVertice(7));
        assertFalse(grafo.eliminarVertice(7));
        assertTrue(grafo.eliminarArco(4, 6));


        System.out.println(grafo);
        assertFalse(grafo.esVacio());
    }
}
