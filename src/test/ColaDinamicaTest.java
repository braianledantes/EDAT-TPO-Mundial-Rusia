package test;

import lineales.ColaDinamica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColaDinamicaTest {

    ColaDinamica<Integer> cola, esperado;
    public static final int TAM = 8;

    @BeforeEach
    void setUp() {
        cola = new ColaDinamica<>();
        esperado = new ColaDinamica<>();
    }

    @Test
    void poner() {
        boolean poner;

        poner = cola.poner(1);
        assertTrue(poner);
        assertEquals(1, cola.obtenerFrente());

        cola.sacar();

        for (int i = 1; i <= 10; i++) {
            poner = cola.poner(i);
            assertTrue(poner);
        }
        int valor;
        for (int i = 1; i <= 10; i++) {
            valor = cola.obtenerFrente();
            assertEquals(i, valor);
            cola.sacar();
        }

        poner = cola.poner(1);
        assertTrue(poner);
    }

    @Test
    void sacar() {
        boolean sacar;
        sacar = cola.sacar();
        assertFalse(sacar);

        for (int i = 1; i < TAM; i++) {
            cola.poner(i);
        }

        for (int i = 1; i < TAM; i++) {
            sacar = cola.sacar();
            assertTrue(sacar);
        }

        sacar = cola.sacar();
        assertFalse(sacar);

        for (int i = 1; i < TAM; i++) {
            cola.poner(i);
        }

        sacar = cola.sacar();
        assertTrue(sacar);
        sacar = cola.sacar();
        assertTrue(sacar);
        sacar = cola.sacar();
        assertTrue(sacar);
        cola.poner(1);
        cola.poner(1);
        cola.poner(1);

        for (int i = 1; i < 3; i++) {
            sacar = cola.sacar();
            assertTrue(sacar);
        }

    }

    @Test
    void obtenerFrente() {
    }

    @Test
    void esVacia() {
        assertTrue(cola.esVacia());
        cola.poner(1);
        assertFalse(cola.esVacia());
        cola.vaciar();
        assertTrue(cola.esVacia());
        for (int i = 1; i < TAM; i++) {
            cola.poner(i);
        }
        assertFalse(cola.esVacia());
        for (int i = 1; i < TAM; i++) {
            cola.sacar();
        }
        assertTrue(cola.esVacia());
    }

    @Test
    void vaciar() {
    }

    @Test
    void testClone() {
    }

    @Test
    void testEquals() {
        assertEquals(cola, esperado);

        cola.poner(1);
        cola.poner(12);
        esperado.poner(1);
        esperado.poner(12);
        assertEquals(cola, esperado);

        esperado.poner(12);
        assertNotEquals(cola, esperado);

        cola.vaciar();
        esperado.vaciar();
        assertEquals(cola, esperado);

        for (int i = 1; i < TAM; i++) {
            cola.poner(i);
            esperado.poner(i);
        }
        assertEquals(cola, esperado);

    }
}
