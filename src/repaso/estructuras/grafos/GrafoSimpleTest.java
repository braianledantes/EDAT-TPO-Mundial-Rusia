package repaso.estructuras.grafos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GrafoSimpleTest {
    GrafoSimple<Character> grafo;

    @BeforeEach
    void seeUp() {
        grafo = new GrafoSimple<>();
    }

    @Test
    void add() {
        boolean valor;
        valor = grafo.insertar('I');
        assertTrue(valor);
        valor = grafo.insertar('H');
        assertTrue(valor);
        valor = grafo.insertar('G');
        assertTrue(valor);
        valor = grafo.insertar('F');
        assertTrue(valor);
        valor = grafo.insertar('E');
        assertTrue(valor);
        valor = grafo.insertar('D');
        assertTrue(valor);
        valor = grafo.insertar('C');
        assertTrue(valor);
        valor = grafo.insertar('A');
        assertTrue(valor);
        valor = grafo.insertar('B');
        assertTrue(valor);

        valor = grafo.insertar('A');
        assertFalse(valor);

        valor = grafo.insertarArco('I', 'G');
        assertTrue(valor);
        valor = grafo.insertarArco('H', 'D');
        assertTrue(valor);
        valor = grafo.insertarArco('G', 'D');
        assertTrue(valor);
        valor = grafo.insertarArco('F', 'D');
        assertTrue(valor);
        valor = grafo.insertarArco('F', 'B');
        assertTrue(valor);
        valor = grafo.insertarArco('E', 'B');
        assertTrue(valor);
        valor = grafo.insertarArco('E', 'A');
        assertTrue(valor);
        valor = grafo.insertarArco('D', 'B');
        assertTrue(valor);
        valor = grafo.insertarArco('C', 'B');
        assertTrue(valor);
        valor = grafo.insertarArco('B', 'A');
        assertTrue(valor);

        valor = grafo.insertarArco('A', 'B');
        assertFalse(valor);

        //System.out.println(grafo);
        //System.out.println(grafo.caminoMasLargo('A', 'I'));
        //System.out.println(grafo.listarAnchura());

        System.out.println(grafo.cantCaminosMenNoPasanPor('A', 'D', 'H', 2));
    }

}