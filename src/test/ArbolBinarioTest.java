package test;

import jerarquicas.ArbolBinario;
import lineales.Lista;
import lineales.ListaDinamica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        arbol.insertar(1,null);
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
    }

    @Test
    void padre() {
    }

    @Test
    void listarPreorden() {
        Lista<String> esperado = new ListaDinamica<>();
        ArbolBinario<String> abVacio = new ArbolBinario<>();
        assertEquals(esperado, abVacio.listarPreorden());

        esperado = new ListaDinamica<>(new String[]{"A", "B", "D", "H","E","C","F","I","J","G"});
        assertEquals(esperado, arbolLetras.listarPreorden());
    }

    @Test
    void listarInorden() {
        Lista<String> esperado = new ListaDinamica<>();
        ArbolBinario<String> abVacio = new ArbolBinario<>();
        assertEquals(esperado, abVacio.listarInorden());

        esperado = new ListaDinamica<>(new String[]{"D", "H", "B", "E","A","I","F","J","C","G"});
        assertEquals(esperado, arbolLetras.listarInorden());
    }

    @Test
    void listarPosorden() {
        Lista<String> esperado = new ListaDinamica<>();
        ArbolBinario<String> abVacio = new ArbolBinario<>();
        assertEquals(esperado, abVacio.listarPosorden());

        esperado = new ListaDinamica<>(new String[]{"H", "D", "E", "B","I","J","F","G","C","A"});
        assertEquals(esperado, arbolLetras.listarPosorden());
    }

    @Test
    void listarNiveles() {
    }

    @Test
    void vaciar() {
    }
}