package test;

import conjuntistas.ArbolAVL;
import jerarquicas.ArbolBinario;
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
    void eliminar() {
    }
}