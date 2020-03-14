package lineales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaDinamicaTest {
    ListaDinamica<Integer> lista;

    @BeforeEach
    void seeUp() {
        lista = new ListaDinamica<>();
    }

    @Test
    void insertar() {
        assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(1));
        assertEquals(4, lista.longitud());
        lista.vaciar();
        assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(1));
        assertEquals(3, lista.longitud());
    }

    @Test
    void insertarEnPosicion() {
        ListaDinamica<Integer> esperado = new ListaDinamica<>();
        for (int i = 1; i <= 10; i++) {
            assertTrue(lista.insertar(i,i));
            esperado.insertar(i);
        }
        assertEquals(esperado, lista);
    }

    @Test
    void localizar() {
        int esperado;

        // sin ningun elemento
        esperado = -1;
        assertEquals(esperado, lista.localizar(0));
        assertEquals(esperado, lista.localizar(null));

        // con un elemento
        lista.insertar(10);
        assertEquals(esperado, lista.localizar(0));
        esperado = 1;
        assertEquals(esperado, lista.localizar(10));

        // con varios elementos
        lista.vaciar();
        for (int i = 1; i <= 20; i++) {
            lista.insertar(i, i);
        }
        esperado = 8;
        assertEquals(esperado, lista.localizar(8));
    }
}