package test.estructuras;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.conjuntistas.ArbolHeap;
import structures.conjuntistas.ArbolHeapMaximo;

import static org.junit.jupiter.api.Assertions.*;

class ArbolHeapMaximoTest {
    static ArbolHeap<Integer> arbolHeap;

    @BeforeAll
    static void setUp() {
        arbolHeap = new ArbolHeapMaximo<>(10);

    }

    @BeforeEach
    void beforeEach() {
        arbolHeap.vaciar();
    }

    @Test
    void recuperarCima() {
        //System.out.println(arbolHeap);
        //assertEquals(16, albolHeap.recuperarCima());
    }

    @Test
    void vaciar() {
    }

    @Test
    void insertar() {
        assertTrue(arbolHeap.insertar(16));
        assertEquals("ArbolHeap{16}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(14));
        assertEquals("ArbolHeap{16; 14}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(17));
        assertEquals("ArbolHeap{17; 14; 16}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(12));
        assertEquals("ArbolHeap{17; 14; 16; 12}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(20));
        assertEquals("ArbolHeap{20; 17; 16; 12; 14}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(21));
        assertEquals("ArbolHeap{21; 17; 20; 12; 14; 16}", arbolHeap.toString());
    }

    @Test
    void eliminarCima() {
        assertNull(arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(6));
        assertEquals(6, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(4));
        assertEquals(6, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(7));
        assertEquals(7, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(2));
        assertEquals(7, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(0));
        assertEquals(7, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(8));
        assertEquals(8, arbolHeap.recuperarCima());
    }
}