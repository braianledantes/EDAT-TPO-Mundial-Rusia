package repaso.estructuras.lineales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListaDinamicaTest {
    ListaDinamica<Integer> lista;

    @BeforeEach
    void seeUp() {
        lista = new ListaDinamica<>();
    }

    @Test
    void add() {
        Lista<Integer> lesp = new ListaDinamica<>();
        assertTrue(lista.insertar(1,1));
        assertTrue(lista.eliminar(1));
        assertTrue(lista.esVacia());
        assertEquals(lesp, lista);
        System.out.println(lista);

        assertTrue(lista.insertar(1,1));
        assertTrue(lista.insertar(1,2));
        assertTrue(lista.insertar(1,3));
        assertTrue(lista.insertar(1,4));
        assertEquals(4, lista.longitud());
        lista.vaciar();
        assertTrue(lista.insertar(1,1));
        assertTrue(lista.insertar(1,2));
        assertTrue(lista.insertar(1,3));
        assertEquals(3, lista.longitud());
    }

    @Test
    void remove() {
        ListaDinamica<Integer> esp = new ListaDinamica<>();
        assertTrue(lista.insertar(1,1));
        assertTrue(lista.eliminar(1));
        assertEquals(esp, lista);

        lista.vaciar();
        esp.vaciar();
        assertTrue(lista.insertar(1,1));
        assertTrue(lista.insertar(2,2));
        assertTrue(esp.insertar(1,1));
        assertTrue(lista.eliminar(2));
        assertEquals(esp, lista);

        lista.vaciar();
        esp.vaciar();
        assertTrue(lista.insertar(1,1));
        assertTrue(lista.insertar(2,2));
        assertTrue(esp.insertar(2,1));
        assertTrue(lista.eliminar(1));
        assertEquals(esp, lista);

        lista.vaciar();
        esp.vaciar();
        assertTrue(lista.insertar(1,1));
        assertTrue(lista.insertar(2,2));
        assertTrue(lista.insertar(3,3));
        assertTrue(esp.insertar(1,1));
        assertTrue(esp.insertar(3,2));
        assertTrue(lista.eliminar(2));
        assertEquals(esp, lista);
    }

    @Test
    void add2() {
        assertTrue(lista.insertar(1,1));
        assertTrue(lista.insertar(2,2));
        assertTrue(lista.insertar(3,3));
        assertTrue(lista.insertar(4,4));
        assertTrue(lista.insertar(5,5));
        assertEquals(5, lista.longitud());
        assertTrue(lista.eliminar(5));
        assertEquals(4, lista.recuperar(4));
        assertTrue(lista.eliminar(4));
        assertEquals(3, lista.recuperar(3));
        assertTrue(lista.eliminar(3));
        assertEquals(2, lista.recuperar(2));
        assertTrue(lista.eliminar(2));
        assertEquals(1, lista.recuperar(1));
        // assertTrue(lista.eliminar(1));
        assertEquals(1, lista.longitud());
        // assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(2,2));
        assertTrue(lista.insertar(3,3));
        assertTrue(lista.insertar(4,4));
        assertTrue(lista.insertar(5,5));
        assertEquals(5, lista.longitud());
    }

    @Test
    void add3() {
        ListaDinamica<Integer> esperado = new ListaDinamica<>();
        for (int i = 1; i <= 10; i++) {
            assertTrue(lista.insertar(i, i));
            esperado.insertar(i,i);
        }
        assertEquals(esperado, lista);
    }

    @Test
    void getPosition() {
        int esperado;

        // sin ningun elemento
        esperado = -1;
        assertEquals(esperado, lista.localizar(0));
        assertEquals(esperado, lista.localizar(null));

        // con un elemento
        lista.insertar(10,1);
        assertEquals(esperado, lista.localizar(0));
        esperado = 1;
        int valor = lista.localizar(10);
        assertEquals(esperado, valor);

        // con varios elementos
        lista.vaciar();
        for (int i = 1; i <= 20; i++) {
            lista.insertar(i, i);
        }
        esperado = 8;
        assertEquals(esperado, lista.recuperar(8));
    }

}