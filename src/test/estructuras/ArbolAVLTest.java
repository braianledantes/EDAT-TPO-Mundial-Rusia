package test.estructuras;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import structures.conjuntistas.ArbolAVL;
import structures.jerarquicas.ArbolBinario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArbolAVLTest {

    ArbolAVL<Integer> avl = new ArbolAVL<>();
    ArbolBinario<Integer> esp = new ArbolBinario<>();

    @AfterEach
    void afterEach() {
        avl.vaciar();
        esp.vaciar();
    }

    @Test
    void insertar() {
        assertTrue(avl.insertar(3));
        assertTrue(avl.insertar(2));
        assertTrue(avl.insertar(1));
        esp.insertar(2, null);
        esp.insertar(1, 2, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(3, 2, ArbolBinario.HIJO_DERECHO);
        assertEquals(esp, avl);

        //assertTrue(avl.eliminar(3));
        avl.eliminar(3);
        esp.vaciar();
        esp.insertar(2, null);
        esp.insertar(1, 2, ArbolBinario.HIJO_IZQUIERDO);
        assertEquals(esp, avl);

        assertTrue(avl.eliminar(2));
        esp.vaciar();
        esp.insertar(1, 2, ArbolBinario.HIJO_IZQUIERDO);
        assertEquals(esp, avl);

        assertTrue(avl.eliminar(1));
        esp.vaciar();
        assertEquals(esp, avl);
    }

    @Test
    void RotaciónSimpleIzquierda() {
        esp.insertar(15, null);
        esp.insertar(8, 15, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(20, 15, ArbolBinario.HIJO_DERECHO);
        esp.insertar(5, 8, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(13, 8, ArbolBinario.HIJO_DERECHO);
        esp.insertar(29, 20, ArbolBinario.HIJO_DERECHO);

        avl.insertar(8);
        avl.insertar(5);
        avl.insertar(15);
        avl.insertar(13);
        avl.insertar(20);
        avl.insertar(29);

        assertEquals(esp, avl);
    }

    @Test
    void RotaciónSimpleDerecha() {
        esp.insertar(5, null);
        esp.insertar(3, 5, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(10, 5, ArbolBinario.HIJO_DERECHO);
        esp.insertar(4, 3, ArbolBinario.HIJO_DERECHO);
        esp.insertar(7, 10, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(15, 10, ArbolBinario.HIJO_DERECHO);

        avl.insertar(10);
        avl.insertar(5);
        avl.insertar(15);
        avl.insertar(3);
        avl.insertar(7);
        avl.insertar(4);

        assertEquals(esp, avl);
    }

    @Test
    void RotaciónDobleDerechaIzquierda() {
        esp.insertar(12, null);
        esp.insertar(10, 12, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(15, 12, ArbolBinario.HIJO_DERECHO);
        esp.insertar(5, 10, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(13, 15, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(17, 15, ArbolBinario.HIJO_DERECHO);

        avl.insertar(10);
        avl.insertar(5);
        avl.insertar(15);
        avl.insertar(12);
        avl.insertar(17);
        avl.insertar(13);

        assertEquals(esp, avl);
    }

    @Test
    void RotaciónDobleIzquierdaDerecha() {
        esp.insertar(8, null);
        esp.insertar(5, 8, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(12, 8, ArbolBinario.HIJO_DERECHO);
        esp.insertar(3, 5, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(10, 12, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(23, 12, ArbolBinario.HIJO_DERECHO);

        avl.insertar(12);
        avl.insertar(5);
        avl.insertar(23);
        avl.insertar(3);
        avl.insertar(8);
        avl.insertar(10);

        assertEquals(esp, avl);
    }

    @Test
    void definitiva() {
        esp.insertar(5, null);
        esp.insertar(3, 5, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(8, 5, ArbolBinario.HIJO_DERECHO);
        esp.insertar(2, 3, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(4, 3, ArbolBinario.HIJO_DERECHO);
        esp.insertar(1, 2, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(6, 8, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(10, 8, ArbolBinario.HIJO_DERECHO);
        esp.insertar(7, 6, ArbolBinario.HIJO_DERECHO);
        esp.insertar(9, 10, ArbolBinario.HIJO_IZQUIERDO);
        esp.insertar(11, 10, ArbolBinario.HIJO_DERECHO);

        avl.insertar(5);
        avl.insertar(2);
        avl.insertar(3);
        avl.insertar(6);
        avl.insertar(8);
        avl.insertar(1);
        avl.insertar(11);
        avl.insertar(7);
        avl.insertar(10);
        avl.insertar(9);
        avl.insertar(4);

        assertEquals(esp, avl);
    }
}