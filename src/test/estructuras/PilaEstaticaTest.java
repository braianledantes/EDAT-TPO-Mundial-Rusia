package test.estructuras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import estructuras.lineales.EstructuraLlenaException;
import estructuras.lineales.PilaEstatica;

import static org.junit.jupiter.api.Assertions.*;

class PilaEstaticaTest {
    PilaEstatica<Integer> pila;

    @BeforeEach
    void setUp() {
        pila = new PilaEstatica<>(Integer.class);
    }

    @Test
    void apilar() {
        assertTrue(pila.esVacia());
        PilaEstatica<Integer> esperado = new PilaEstatica<>(Integer.class);
        boolean resultEsperado;

        for (int i = 1; i <= PilaEstatica.TAM; i++) {
            resultEsperado = pila.apilar(i);
            assertTrue(resultEsperado);
            assertEquals(i, pila.obtenerTope());
            esperado.apilar(i);
        }
        assertEquals(esperado, pila);

        EstructuraLlenaException exception = assertThrows(EstructuraLlenaException.class, () ->
                pila.apilar(-1));
        assertEquals("La estructura esta llena", exception.getMessage());

        assertEquals(esperado, pila);
    }

    @Test
    void desapilar() {
        PilaEstatica<Integer> esperado = new PilaEstatica<>(Integer.class);
        boolean desapilado = pila.desapilar();
        assertFalse(desapilado);
        assertEquals(esperado, pila);

        pila.apilar(1);
        pila.apilar(2);
        desapilado = pila.desapilar();
        assertTrue(desapilado);
        pila.apilar(3);
        pila.apilar(4);
        pila.desapilar();
        pila.apilar(5);

        esperado.apilar(1);
        esperado.apilar(3);
        esperado.apilar(5);

        assertEquals(esperado, pila);
    }

    @Test
    void obtenerTope() {
        assertNull(pila.obtenerTope());
        for (int i = 1; i <= PilaEstatica.TAM; i++) {
            pila.apilar(i);
            assertEquals(i, pila.obtenerTope());
        }
        for (int i = PilaEstatica.TAM; i >= 1; i--) {
            assertEquals(i, pila.obtenerTope());
            pila.desapilar();
        }
        assertNull(pila.obtenerTope());
    }

    @Test
    void vaciar() {
        PilaEstatica<Integer> esperado = new PilaEstatica<>(Integer.class);

        pila.vaciar();
        assertEquals(esperado, pila);

        pila.apilar(1);
        pila.vaciar();
        assertEquals(esperado, pila);

        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        pila.apilar(4);
        pila.apilar(5);
        esperado.apilar(1);
        esperado.apilar(2);
        esperado.apilar(3);
        esperado.apilar(4);
        esperado.apilar(5);
        assertEquals(esperado, pila);
    }

    @Test
    void esVacia() {
        assertTrue(pila.esVacia());
        pila.apilar(2);
        assertFalse(pila.esVacia());
        pila.desapilar();
        assertTrue(pila.esVacia());
        pila.desapilar();
        assertTrue(pila.esVacia());
    }

    @Test
    void testClone() {
        PilaEstatica<Integer> clon = pila.clone();
        assertEquals(pila, clon);
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        pila.apilar(4);
        PilaEstatica<Integer> clon2 = pila.clone();
        assertEquals(pila, clon2);

    }


}