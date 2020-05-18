package test.structures;

import org.junit.jupiter.api.Test;
import structures.grafo.GrafoDirigidoEtiquetado;
import structures.lineales.Lista;
import structures.lineales.ListaDinamica;

import static org.junit.jupiter.api.Assertions.*;

class GrafoDirigidoEtiquetadoTest {

    // TODO testear los arcos dobles :)

    @Test
    void insertarVertice() {
        GrafoDirigidoEtiquetado<Integer> grafo = new GrafoDirigidoEtiquetado<>();
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
        GrafoDirigidoEtiquetado<Character> grafo = new GrafoDirigidoEtiquetado<>();
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
        GrafoDirigidoEtiquetado<Character> grafo = new GrafoDirigidoEtiquetado<>();
        Lista<Character> caminoEsp;
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

        caminoEsp = new ListaDinamica<>(new Character[]{});
        assertEquals(caminoEsp, grafo.caminoMasCorto('b', 'a'));
        assertEquals(caminoEsp, grafo.caminoMasLargo('b', 'a'));

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'c', 'e'});
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'e'));

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'b', 'e'});
        assertEquals(caminoEsp, grafo.caminoMasLargo('a', 'e'));

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'b'});
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'b'));

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'c', 'f', 'g', 'c', 'e', 'h', 'd', 'e', 'b'});
        assertEquals(caminoEsp, grafo.caminoMasLargo('a', 'b'));

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'b'});
        assertEquals(caminoEsp, grafo.caminoConMenosVertices('a', 'b'));

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'c', 'e'});
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'e', 'e'));
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'a', 'e'));
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'c', 'e'));

        caminoEsp = new ListaDinamica<>(new Character[]{'a'});
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'a', 'a'));

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'c', 'e', 'h', 'd'});
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'h', 'd'));
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'h', 'd'));

        Lista<Lista<Character>> caminosPosibles = grafo.caminosPosibles('a', 'd');
        Lista<Lista<Character>> caminosEsperados = new ListaDinamica<>();
        caminosEsperados.insertar(new ListaDinamica<>(new Character[]{'a', 'f', 'g', 'c', 'e', 'b', 'd'}));
        caminosEsperados.insertar(new ListaDinamica<>(new Character[]{'a', 'f', 'g', 'c', 'e', 'd'}));
        caminosEsperados.insertar(new ListaDinamica<>(new Character[]{'a', 'f', 'g', 'c', 'e', 'h', 'd'}));
        caminosEsperados.insertar(new ListaDinamica<>(new Character[]{'a', 'c', 'e', 'b', 'd'}));
        caminosEsperados.insertar(new ListaDinamica<>(new Character[]{'a', 'c', 'e', 'd'}));
        caminosEsperados.insertar(new ListaDinamica<>(new Character[]{'a', 'c', 'e', 'h', 'd'}));
        caminosEsperados.insertar(new ListaDinamica<>(new Character[]{'a', 'b', 'e', 'd'}));
        caminosEsperados.insertar(new ListaDinamica<>(new Character[]{'a', 'b', 'e', 'h', 'd'}));
        caminosEsperados.insertar(new ListaDinamica<>(new Character[]{'a', 'b', 'd'}));
        for (int i = 1; i <= 9; i++) {
            assertEquals(caminosEsperados.recuperar(i), caminosPosibles.recuperar(i));
        }
    }

    @Test
    void caminoMasCorto2() {
        GrafoDirigidoEtiquetado<Character> grafo = new GrafoDirigidoEtiquetado<>();
        Lista<Character> caminoEsp;
        grafo.insertarVertice('i');
        grafo.insertarVertice('h');
        grafo.insertarVertice('g');
        grafo.insertarVertice('f');
        grafo.insertarVertice('e');
        grafo.insertarVertice('d');
        grafo.insertarVertice('c');
        grafo.insertarVertice('b');
        grafo.insertarVertice('a');
        grafo.insertarArco('a', 'd', 1);
        grafo.insertarArco('a', 'c', 1);
        grafo.insertarArco('a', 'b', 1);
        grafo.insertarArco('b', 'e', 10);
        grafo.insertarArco('c', 'd', 1);
        grafo.insertarArco('c', 'e', 10);
        grafo.insertarArco('d', 'e', 1);
        grafo.insertarArco('d', 'f', 1);
        grafo.insertarArco('d', 'i', 1);
        grafo.insertarArco('e', 'f', 10);
        grafo.insertarArco('e', 'h', 1);
        grafo.insertarArco('e', 'g', 10);
        grafo.insertarArco('f', 'i', 10);
        grafo.insertarArco('g', 'h', 10);
        grafo.insertarArco('i', 'h', 10);

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'd', 'e', 'h'});
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'e', 'h'), grafo.toString());

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'b', 'e', 'f', 'i', 'h'});
        assertEquals(caminoEsp, grafo.caminoMasLargo('a', 'h'), grafo.toString());
    }

    @Test
    void test() {
        GrafoDirigidoEtiquetado<Character> grafo = new GrafoDirigidoEtiquetado<>();
        Lista<Character> caminoEsp;
        grafo.insertarVertice('i');
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
        grafo.insertarArco('b', 'e', 1);
        grafo.insertarArco('b', 'h', 1);
        grafo.insertarArco('c', 'e', 1);
        grafo.insertarArco('c', 'd', 1);
        grafo.insertarArco('d', 'i', 1);
        grafo.insertarArco('d', 'b', 1);
        grafo.insertarArco('d', 'g', 1);
        grafo.insertarArco('e', 'g', 1);
        grafo.insertarArco('e', 'f', 1);
        grafo.insertarArco('f', 'h', 1);
        grafo.insertarArco('f', 'a', 1);
        grafo.insertarArco('f', 'c', 1);
        grafo.insertarArco('g', 'd', 1);
        grafo.insertarArco('g', 'f', 1);
        grafo.insertarArco('h', 'f', 1);

        caminoEsp = new ListaDinamica<>();
        assertEquals(caminoEsp, grafo.caminoMasCorto('i', 'a'), grafo.toString());

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'b', 'h'});
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'h'), grafo.toString());

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'c', 'd', 'g', 'f', 'c', 'e', 'f', 'a', 'b', 'e', 'g', 'd', 'b', 'h'});
        assertEquals(caminoEsp, grafo.caminoMasLargo('a', 'h'), grafo.toString());

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'b', 'h', 'f', 'c', 'd', 'b'});
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'h', 'b'), grafo.toString());
    }

    @Test
    void test2() {
        GrafoDirigidoEtiquetado<Character> grafo = new GrafoDirigidoEtiquetado<>();
        Lista<Character> caminoEsp;
        grafo.insertarVertice('d');
        grafo.insertarVertice('c');
        grafo.insertarVertice('b');
        grafo.insertarVertice('a');
        grafo.insertarArco('a', 'b', 1);
        grafo.insertarArco('b', 'd', 1);
        grafo.insertarArco('b', 'c', 1);
        grafo.insertarArco('c', 'b', 1);

        assertTrue(grafo.existeArcoDoble('b', 'c'));
        assertFalse(grafo.existeArcoDoble('b', 'd'));
        assertFalse(grafo.existeArcoDoble('a', 'd'));
        assertFalse(grafo.existeArcoDoble('a', 'a'));
        assertFalse(grafo.existeArcoDoble('b', 'e'));
        assertFalse(grafo.existeArcoDoble('d', 'f'));

        caminoEsp = new ListaDinamica<>(new Character[]{'a', 'b', 'c', 'b', 'd'});
        assertEquals(caminoEsp, grafo.caminoMasCorto('a', 'c', 'd'), grafo.toString());
    }

    @Test
    void testEqualsNull() {
        assertFalse(equalsNull());
    }

    private boolean equalsNull() {
        Object obj1 = new Object();
        Object obj2 = null;
        return obj1.equals(obj2);
    }
}