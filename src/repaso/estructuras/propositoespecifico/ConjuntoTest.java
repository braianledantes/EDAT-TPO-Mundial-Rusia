package repaso.estructuras.propositoespecifico;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConjuntoTest {

    Conjunto<String> set = new ConjuntoLista<>();

    @Test
    void Conjunto() {
        assertTrue(set.esVacio());
        assertFalse(set.quitar("sadasd"));
        assertFalse(set.pertenece("1"));
        assertFalse(set.pertenece(null));
        assertTrue(set.esVacio());
        assertTrue(set.agregar("1"));
        assertFalse(set.agregar("1"));
        assertFalse(set.agregar(null));
        assertTrue(set.pertenece("1"));
        assertFalse(set.esVacio());
        assertTrue(set.agregar("10001"));
        assertTrue(set.agregar("2"));
        assertTrue(set.agregar("-asdasd"));
        assertTrue(set.agregar("''¿sdn1--"));
        assertTrue(set.agregar("''¿sdn1--1"));
        assertFalse(set.agregar("2"));
        assertFalse(set.quitar(null));
        assertTrue(set.quitar("2"));
        assertTrue(set.agregar("2"));
    }
}