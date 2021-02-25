package repaso.estructuras.conjuntistas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArbolHeapMinTest {

    ArbolHeapMaximo<Integer> heap = new ArbolHeapMaximo<>(14);

    @AfterEach
    void afterEach() {
        heap.vaciar();
    }

    @Test
    void insertar() {
        boolean condition;
        condition = heap.insertar(16);
        assertTrue(condition);
        condition = heap.insertar(14);
        assertTrue(condition);
        condition = heap.insertar(10);
        assertTrue(condition);
        condition = heap.insertar(8);
        assertTrue(condition);
        condition = heap.insertar(7);
        assertTrue(condition);
        condition = heap.insertar(9);
        assertTrue(condition);
        condition = heap.insertar(3);
        assertTrue(condition);
        condition = heap.insertar(2);
        assertTrue(condition);
        condition = heap.insertar(4);
        assertTrue(condition);
        condition = heap.insertar(1);
        assertTrue(condition);
        condition = heap.insertar(11);
        assertTrue(condition);

        System.out.println(heap);
    }
}