package test;

import grafo.GrafoDinamico;
import lineales.ListaDinamica;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrafoDinamicoTest {

    @Test
    void insertarVertice() {
        GrafoDinamico<Integer> grafo = new GrafoDinamico<>();
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
        assertFalse(grafo.existeCamino(2, 4));
        assertFalse(grafo.existeCamino(2, 3));
        assertFalse(grafo.existeCamino(2, 1));
        assertTrue(grafo.existeCamino(1, 7));
        assertFalse(grafo.existeCamino(7, 4));

        assertTrue(grafo.eliminarVertice(7));
        assertTrue(grafo.eliminarVertice(8));
        assertTrue(grafo.eliminarArco(4, 6));


        System.out.println(grafo);
        assertFalse(grafo.esVacio());
    }

    @Test
    void listarEnProfundidad() {
        GrafoDinamico<Character> grafo = new GrafoDinamico<>();
        assertTrue(grafo.insertarVertice('h'));
        grafo.insertarVertice('g');
        grafo.insertarVertice('f');
        grafo.insertarVertice('e');
        grafo.insertarVertice('d');
        grafo.insertarVertice('c');
        grafo.insertarVertice('b');
        grafo.insertarVertice('a');
        grafo.insertarArco('a', 'b');
        grafo.insertarArco('b', 'f');
        grafo.insertarArco('b', 'd');
        grafo.insertarArco('c', 'h');
        grafo.insertarArco('c', 'f');
        grafo.insertarArco('c', 'a');
        grafo.insertarArco('d', 'g');
        grafo.insertarArco('d', 'e');
        grafo.insertarArco('g', 'e');
        System.out.println(grafo);
        ListaDinamica<Character> le = new ListaDinamica<>(new Character[]{'a', 'b', 'd', 'e', 'g', 'f', 'c', 'h'});
        assertEquals(le, grafo.listarEnProfundidad());
        le = new ListaDinamica<>(new Character[]{'a', 'b', 'd', 'f', 'e', 'g', 'c', 'h'});
        assertEquals(le, grafo.listarEnAnchura());
    }

    @Test
    void caminoMasCorto() {
        GrafoDinamico<Character> grafo = new GrafoDinamico<>();
        grafo.insertarVertice('h');
        grafo.insertarVertice('g');
        grafo.insertarVertice('f');
        grafo.insertarVertice('e');
        grafo.insertarVertice('d');
        grafo.insertarVertice('c');
        grafo.insertarVertice('b');
        grafo.insertarVertice('a');
        grafo.insertarArco('a', 'b', 1);
        grafo.insertarArco('a', 'c', 1);
        grafo.insertarArco('a', 'f', 1);
        grafo.insertarArco('b', 'd', 1);
        grafo.insertarArco('b', 'e', 20);
        grafo.insertarArco('c', 'f', 1);
        grafo.insertarArco('c', 'g', 1);
        grafo.insertarArco('c', 'e', 1);
        grafo.insertarArco('d', 'e', 1);
        grafo.insertarArco('e', 'h', 1);
        grafo.insertarArco('e', 'd', 1);
        grafo.insertarArco('e', 'b', 1);
        grafo.insertarArco('f', 'g', 1);
        grafo.insertarArco('g', 'c', 1);
        grafo.insertarArco('g', 'c', 1);
        grafo.insertarArco('h', 'g', 1);
        grafo.insertarArco('h', 'd', 1);
        System.out.println(grafo);
        System.out.println(grafo.caminoMasCorto('a', 'e'));
        System.out.println(grafo.caminoMasLargo('a', 'e'));
        //System.out.println(grafo.caminoMasCorto('h', 'a'));
        //  System.out.println(grafo.caminoMasCorto('h', 'b'));
    }

    @Test
    void esVacio() {
    }
}