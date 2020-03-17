package test;

import Conjuntistas.ArbolHeap;
import Conjuntistas.ArbolHeapMinimo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbolHeapMinimoTest {
    static ArbolHeap<Integer> arbolHeap;

    @BeforeAll
    static void setUp() {
        arbolHeap = new ArbolHeapMinimo<>(10);
    }

    @BeforeEach
    void beforeEach() {
        arbolHeap.vaciar();
    }

    @Test
    void recuperarCima() {
        System.out.println(arbolHeap);
        //assertEquals(16, albolHeap.recuperarCima());
    }

    @Test
    void vaciar() {
    }

    @Test
    void insertar() {
        assertTrue(arbolHeap.insertar(6));
        assertEquals("ArbolHeap{6}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(4));
        assertEquals("ArbolHeap{4; 6}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(7));
        assertEquals("ArbolHeap{4; 6; 7}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(2));
        assertEquals("ArbolHeap{2; 4; 7; 6}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(0));
        assertEquals("ArbolHeap{0; 2; 7; 6; 4}", arbolHeap.toString());
        assertTrue(arbolHeap.insertar(8));
        assertEquals("ArbolHeap{0; 2; 7; 6; 4; 8}", arbolHeap.toString());
    }

    @Test
    void eliminarCima() {
        assertNull(arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(6));
        assertEquals(6, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(4));
        assertEquals(4, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(7));
        assertEquals(4, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(2));
        assertEquals(2, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(0));
        assertEquals(0, arbolHeap.recuperarCima());
        assertTrue(arbolHeap.insertar(8));
        assertEquals(0, arbolHeap.recuperarCima());
    }
}