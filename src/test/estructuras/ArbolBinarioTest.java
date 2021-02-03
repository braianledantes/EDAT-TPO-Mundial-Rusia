package test.estructuras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import estructuras.jerarquicas.ArbolBinario;
import estructuras.jerarquicas.ArbolBinarioDinamico;
import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;

import static org.junit.jupiter.api.Assertions.*;

class ArbolBinarioTest {

    ArbolBinario<Integer> arbol;
    ArbolBinario<String> arbolLetras;

    @BeforeEach
    void setUp() {
        arbol = new ArbolBinario<>();
        arbolLetras = new ArbolBinario<>();
        arbolLetras.insertar("A", null);
        arbolLetras.insertar("B", "A", ArbolBinario.HIJO_IZQUIERDO);
        arbolLetras.insertar("C", "A", ArbolBinario.HIJO_DERECHO);
        arbolLetras.insertar("D", "B", ArbolBinario.HIJO_IZQUIERDO);
        arbolLetras.insertar("E", "B", ArbolBinario.HIJO_DERECHO);
        arbolLetras.insertar("F", "C", ArbolBinario.HIJO_IZQUIERDO);
        arbolLetras.insertar("G", "C", ArbolBinario.HIJO_DERECHO);
        arbolLetras.insertar("H", "D", ArbolBinario.HIJO_DERECHO);
        arbolLetras.insertar("I", "F", ArbolBinario.HIJO_IZQUIERDO);
        arbolLetras.insertar("J", "F", ArbolBinario.HIJO_DERECHO);
    }

    @Test
    void insertar() {
        assertTrue(arbol.insertar(0, null));
        for (int i = 0; i < 10; i = i + 2) {
            assertTrue(arbol.insertar(i + 1, i));
            assertTrue(arbol.insertar(i + 2, i));
        }
    }

    @Test
    void insertarConPosicion() {
    }

    @Test
    void esVacio() {
        assertTrue(arbol.esVacio());
        arbol.insertar(1, null);
        assertFalse(arbol.esVacio());
        arbol.vaciar();
        assertTrue(arbol.esVacio());
    }

    @Test
    void altura() {
        ArbolBinario<String> arbolLetras = new ArbolBinario<>();
        assertEquals(-1, arbolLetras.altura());

        arbolLetras.insertar("A", null);
        assertEquals(0, arbolLetras.altura());
        arbolLetras.insertar("B", "A");
        assertEquals(1, arbolLetras.altura());
        arbolLetras.insertar("C", "A");
        assertEquals(1, arbolLetras.altura());
        arbolLetras.insertar("D", "B");
        assertEquals(2, arbolLetras.altura());
        arbolLetras.insertar("E", "B");
        assertEquals(2, arbolLetras.altura());
        arbolLetras.insertar("F", "C");
        assertEquals(2, arbolLetras.altura());
        arbolLetras.insertar("G", "C");
        assertEquals(2, arbolLetras.altura());
        arbolLetras.insertar("H", "D");
        assertEquals(3, arbolLetras.altura());
        arbolLetras.insertar("I", "F");
        assertEquals(3, arbolLetras.altura());
        arbolLetras.insertar("J", "F");
        assertEquals(3, arbolLetras.altura());


        arbolLetras = new ArbolBinario<>();
        assertEquals(-1, arbolLetras.altura());

        arbolLetras.insertar("A", null);
        assertEquals(0, arbolLetras.altura());
        arbolLetras.insertar("B", "A", ArbolBinario.HIJO_IZQUIERDO);
        assertEquals(1, arbolLetras.altura());
        arbolLetras.insertar("C", "A", ArbolBinario.HIJO_DERECHO);
        assertEquals(1, arbolLetras.altura());
        arbolLetras.insertar("D", "B", ArbolBinario.HIJO_IZQUIERDO);
        assertEquals(2, arbolLetras.altura());
        arbolLetras.insertar("E", "B", ArbolBinario.HIJO_DERECHO);
        assertEquals(2, arbolLetras.altura());
        arbolLetras.insertar("F", "C", ArbolBinario.HIJO_IZQUIERDO);
        assertEquals(2, arbolLetras.altura());
        arbolLetras.insertar("G", "C", ArbolBinario.HIJO_DERECHO);
        assertEquals(2, arbolLetras.altura());
        arbolLetras.insertar("H", "D", ArbolBinario.HIJO_DERECHO);
        assertEquals(3, arbolLetras.altura());
        arbolLetras.insertar("I", "F", ArbolBinario.HIJO_IZQUIERDO);
        assertEquals(3, arbolLetras.altura());
        arbolLetras.insertar("J", "F", ArbolBinario.HIJO_DERECHO);
        assertEquals(3, arbolLetras.altura());
    }

    @Test
    void nivel() {
        assertEquals(-1, arbol.nivel(1));
        assertEquals(-1, arbolLetras.nivel("Z"));
        assertEquals(0, arbolLetras.nivel("A"));
        assertEquals(1, arbolLetras.nivel("B"));
        assertEquals(1, arbolLetras.nivel("C"));
        assertEquals(2, arbolLetras.nivel("D"));
        assertEquals(2, arbolLetras.nivel("E"));
        assertEquals(2, arbolLetras.nivel("F"));
        assertEquals(2, arbolLetras.nivel("G"));
        assertEquals(3, arbolLetras.nivel("H"));
        assertEquals(3, arbolLetras.nivel("I"));
        assertEquals(3, arbolLetras.nivel("J"));
    }

    @Test
    void padre() {
        assertNull(arbolLetras.padre(null));
        assertNull(arbolLetras.padre("Z"));
        assertNull(arbolLetras.padre("A"));
        assertEquals("A", arbolLetras.padre("B"));
        assertEquals("A", arbolLetras.padre("C"));
        assertEquals("B", arbolLetras.padre("D"));
        assertEquals("B", arbolLetras.padre("E"));
        assertEquals("C", arbolLetras.padre("F"));
        assertEquals("C", arbolLetras.padre("G"));
        assertEquals("D", arbolLetras.padre("H"));
        assertEquals("F", arbolLetras.padre("I"));
        assertEquals("F", arbolLetras.padre("J"));
    }

    @Test
    void listarPreorden() {
        Lista<String> esperado = new ListaDinamica<>();
        ArbolBinarioDinamico<String> abVacio = new ArbolBinario<>();
        assertEquals(esperado, abVacio.listarPreorden());

        esperado = new ListaDinamica<>(new String[]{"A", "B", "D", "H", "E", "C", "F", "I", "J", "G"});
        assertEquals(esperado, arbolLetras.listarPreorden());
    }

    @Test
    void listarInorden() {
        Lista<String> esperado = new ListaDinamica<>();
        ArbolBinarioDinamico<String> abVacio = new ArbolBinario<>();
        assertEquals(esperado, abVacio.listarInorden());

        esperado = new ListaDinamica<>(new String[]{"D", "H", "B", "E", "A", "I", "F", "J", "C", "G"});
        assertEquals(esperado, arbolLetras.listarInorden());
    }

    @Test
    void listarPosorden() {
        Lista<String> esperado = new ListaDinamica<>();
        ArbolBinarioDinamico<String> abVacio = new ArbolBinario<>();
        assertEquals(esperado, abVacio.listarPosorden());

        esperado = new ListaDinamica<>(new String[]{"H", "D", "E", "B", "I", "J", "F", "G", "C", "A"});
        assertEquals(esperado, arbolLetras.listarPosorden());
    }

    @Test
    void listarNiveles() {
        Lista<String> esperado = new ListaDinamica<>();
        ArbolBinarioDinamico<String> abVacio = new ArbolBinario<>();
        assertEquals(esperado, abVacio.listarNiveles());

        esperado = new ListaDinamica<>(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"});
        assertEquals(esperado, arbolLetras.listarNiveles());
    }

    @Test
    void equals() {
        ArbolBinario<Integer> a1 = new ArbolBinario<>();
        ArbolBinario<Integer> a2 = new ArbolBinario<>();
        ArbolBinario<Integer> a3 = new ArbolBinario<>();
        assertEquals(a1, a2);
        a1.insertar(1, null);
        assertNotEquals(a1, a2);
        a2.insertar(1, null);
        a2.insertar(1, null);
        assertEquals(a1, a2);
        a3.insertar(1, null);
        a3.insertar(2, 1);
        assertNotEquals(a1, a3);


        ArbolBinario<String> arbolLetras2 = new ArbolBinario<>();
        arbolLetras2.insertar("A", null);
        arbolLetras2.insertar("B", "A");
        arbolLetras2.insertar("C", "A");
        arbolLetras2.insertar("D", "B");
        arbolLetras2.insertar("E", "B");
        arbolLetras2.insertar("F", "C");
        arbolLetras2.insertar("G", "C");
        //arbolLetras2.insertar(null, "D");
        arbolLetras2.insertar("H", "D", ArbolBinario.HIJO_DERECHO);
        arbolLetras2.insertar("I", "F");
        arbolLetras2.insertar("J", "F");
        assertEquals(arbolLetras, arbolLetras2);
        //  assertNotEquals(arbolLetras, arbolLetras2);
    }

    @Test
    void frontera() {
        assertEquals(new ListaDinamica<String>(), new ArbolBinario<String>().frontera());
        Lista<String> frontera = new ListaDinamica<>(new String[]{"H", "E", "I", "J", "G"});
        assertEquals(frontera, arbolLetras.frontera());
    }


}