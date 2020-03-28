package test.estructuras;

import estructuras.lineales.ColaEstatica;
import estructuras.lineales.EstructuraLlenaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ColaEstaticaTest {

    ColaEstatica<Integer> cola, esperado;

    @BeforeEach
    void setUp(){
        cola = new ColaEstatica<>(Integer.class);
        esperado = new ColaEstatica<>(Integer.class);
    }

    @Test()
    void poner() {
        boolean poner;
        for (int i = 1; i < ColaEstatica.TAM; i++) {
            poner =  cola.poner(i);
            assertTrue(poner);
        }

        EstructuraLlenaException exception = assertThrows(EstructuraLlenaException.class, () ->
                cola.poner(0));
        assertEquals("La estructura esta llena", exception.getMessage());

        cola.sacar();
        cola.sacar();
        cola.sacar();
        for (int i = 0; i < 3; i++) {
            poner = cola.poner(i);
            assertTrue(poner);
        }
    }

    @Test
    void sacar() {
        boolean sacar;
        sacar = cola.sacar();
        assertFalse(sacar);

        for (int i = 1; i < ColaEstatica.TAM; i++) {
            cola.poner(i);
        }

        for (int i = 1; i < ColaEstatica.TAM; i++) {
            sacar =  cola.sacar();
            assertTrue(sacar);
        }

        sacar =  cola.sacar();
        assertFalse(sacar);

        for (int i = 1; i < ColaEstatica.TAM; i++) {
            cola.poner(i);
        }

        sacar =  cola.sacar();
        assertTrue(sacar);
        sacar =  cola.sacar();
        assertTrue(sacar);
        sacar =  cola.sacar();
        assertTrue(sacar);
        cola.poner(1);
        cola.poner(1);
        cola.poner(1);

        for (int i = 1; i < 3; i++) {
            sacar =  cola.sacar();
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
        for (int i = 1; i < ColaEstatica.TAM; i++) {
            cola.poner(i);
        }
        assertFalse(cola.esVacia());
        for (int i = 1; i < ColaEstatica.TAM; i++) {
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

        for (int i = 1; i < ColaEstatica.TAM; i++) {
            cola.poner(i);
            esperado.poner(i);
        }
        assertEquals(cola, esperado);

    }
}
