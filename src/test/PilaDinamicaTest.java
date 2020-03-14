package test;

import lineales.PilaDinamica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PilaDinamicaTest {

    PilaDinamica<Integer> pila;
    public static final int TAM = 5;

    @BeforeEach
    void setUp() {
        pila = new PilaDinamica<>();
    }

    @Test
    void apilar() {
        PilaDinamica<Integer> esperado = new PilaDinamica<>();
        boolean resultEsperado;

        for (int i = 1; i <= TAM; i++) {
            resultEsperado = pila.apilar(i);
            assertTrue(resultEsperado);
            // assertEquals(i, pila.obtenerTope());
            esperado.apilar(i);
        }
        assertEquals(esperado, pila);
    }

    @Test
    void desapilar() {
        PilaDinamica<Integer> esperado = new PilaDinamica<>();
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
        for (int i = 1; i <= TAM; i++) {
            pila.apilar(i);
            assertEquals(i, pila.obtenerTope());
        }
        for (int i = TAM; i >= 1; i--) {
            assertEquals(i, pila.obtenerTope());
            pila.desapilar();
        }
        assertNull(pila.obtenerTope());
    }

    @Test
    void vaciar() {
        PilaDinamica<Integer> esperado = new PilaDinamica<>();

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
        PilaDinamica<Dato> pila = new PilaDinamica<>();
        PilaDinamica<Dato> clon = pila.clone();
        assertEquals(pila, clon);

        pila.apilar(new Dato("c1", 1));
        pila.apilar(new Dato("c2", 2));
        pila.apilar(new Dato("c3", 3));
        clon = pila.clone();
        assertEquals(pila, clon);

        Dato dato = new Dato("c4", 4);
        pila.apilar(dato);
        clon = pila.clone();
        clon.obtenerTope().setCad("c5");
        assertEquals(pila, clon);

    }

    @Test
    void equals() {
        assertEquals(pila, pila);

        PilaDinamica<Integer> esperado = new PilaDinamica<>();
        assertEquals(esperado, pila);

        pila.apilar(1);
        assertNotEquals(esperado, pila);

        pila.desapilar();
        pila.desapilar();
        assertEquals(esperado, pila);
        pila.apilar(1);
        esperado.apilar(1);
        assertEquals(esperado, pila);
        pila.apilar(2);
        assertNotEquals(esperado,pila);
        esperado.apilar(2);
        assertEquals(esperado, pila);
        pila.desapilar();
        pila.apilar(3);
        assertNotEquals(esperado, pila);
        pila.vaciar();
        esperado = new PilaDinamica<>();
        assertEquals(esperado, pila);
    }

    private class Dato {
        private String cad;
        private int num;

        public Dato(String cad, int num) {
            this.cad = cad;
            this.num = num;
        }

        public String getCad() {
            return cad;
        }

        public void setCad(String cad) {
            this.cad = cad;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dato dato = (Dato) o;
            return num == dato.num &&
                    cad.equals(dato.cad);
        }
    }
}