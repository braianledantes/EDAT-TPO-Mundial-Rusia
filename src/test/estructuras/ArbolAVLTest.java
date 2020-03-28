package test.estructuras;

import estructuras.conjuntistas.ArbolAVL;
import estructuras.jerarquicas.ArbolBinario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArbolAVLTest {

    ArbolAVL<Integer> avl = new ArbolAVL<>();
    ArbolBinario<Integer> arbolEsp = new ArbolBinario<>();

    @AfterEach
    void afterEach() {
        avl.vaciar();
        arbolEsp.vaciar();
    }

    @Test
    void insertar() {
        assertTrue(avl.insertar(3));
        assertTrue(avl.insertar(2));
        assertTrue(avl.insertar(1));
        arbolEsp.insertar(2, null);
        arbolEsp.insertar(1, 2, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(3, 2, ArbolBinario.HIJO_DERECHO);
        assertEquals(arbolEsp, avl);

        //assertTrue(avl.eliminar(3));
        avl.eliminar(3);
        arbolEsp.vaciar();
        arbolEsp.insertar(2, null);
        arbolEsp.insertar(1, 2, ArbolBinario.HIJO_IZQUIERDO);
        assertEquals(arbolEsp, avl);

        assertTrue(avl.eliminar(2));
        arbolEsp.vaciar();
        arbolEsp.insertar(1, 2, ArbolBinario.HIJO_IZQUIERDO);
        assertEquals(arbolEsp, avl);

        assertTrue(avl.eliminar(1));
        arbolEsp.vaciar();
        assertEquals(arbolEsp, avl);
    }

    @Test
    void RotaciónSimpleIzquierda() {
        arbolEsp.insertar(15, null);
        arbolEsp.insertar(8, 15, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(20, 15, ArbolBinario.HIJO_DERECHO);
        arbolEsp.insertar(5, 8, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(13, 8, ArbolBinario.HIJO_DERECHO);
        arbolEsp.insertar(29, 20, ArbolBinario.HIJO_DERECHO);

        avl.insertar(8);
        avl.insertar(5);
        avl.insertar(15);
        avl.insertar(13);
        avl.insertar(20);
        avl.insertar(29);

        assertEquals(arbolEsp, avl);
    }

    @Test
    void RotaciónSimpleDerecha() {
        arbolEsp.insertar(5, null);
        arbolEsp.insertar(3, 5, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(10, 5, ArbolBinario.HIJO_DERECHO);
        arbolEsp.insertar(4, 3, ArbolBinario.HIJO_DERECHO);
        arbolEsp.insertar(7, 10, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(15, 10, ArbolBinario.HIJO_DERECHO);

        avl.insertar(10);
        avl.insertar(5);
        avl.insertar(15);
        avl.insertar(3);
        avl.insertar(7);
        avl.insertar(4);

        assertEquals(arbolEsp, avl);
    }

    @Test
    void RotaciónDobleDerechaIzquierda() {
        arbolEsp.insertar(12, null);
        arbolEsp.insertar(10, 12, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(15, 12, ArbolBinario.HIJO_DERECHO);
        arbolEsp.insertar(5, 10, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(13, 15, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(17, 15, ArbolBinario.HIJO_DERECHO);

        avl.insertar(10);
        avl.insertar(5);
        avl.insertar(15);
        avl.insertar(12);
        avl.insertar(17);
        avl.insertar(13);

        assertEquals(arbolEsp, avl);
    }

    @Test
    void RotaciónDobleIzquierdaDerecha() {
        arbolEsp.insertar(8, null);
        arbolEsp.insertar(5, 8, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(12, 8, ArbolBinario.HIJO_DERECHO);
        arbolEsp.insertar(3, 5, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(10, 12, ArbolBinario.HIJO_IZQUIERDO);
        arbolEsp.insertar(23, 12, ArbolBinario.HIJO_DERECHO);

        avl.insertar(12);
        avl.insertar(5);
        avl.insertar(23);
        avl.insertar(3);
        avl.insertar(8);
        avl.insertar(10);

        assertEquals(arbolEsp, avl);
    }

    @Test
    void eliminar() {
    }
}