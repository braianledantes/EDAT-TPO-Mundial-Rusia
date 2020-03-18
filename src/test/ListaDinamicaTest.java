package test;

import lineales.*;
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
            assertTrue(lista.insertar(i, i));
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

    /**
     * Recibe una lista L1 cargada con dígitos enteros (de 0 a 9) y verica si los elementos
     * que contiene tienen la forma cadena0cadena0cadena' (donde cadena' es cadena invertida). Ej: si
     * L1=[9,6,5,0,9,6,5,0,5,6,9], cadena=965 y cadena'=569, entonces la lista L1 cumple con la condición
     * deseada. Atención: la longitud de cadena no se conoce de antemano, hay que identicarla por la
     * primera posición de 0 en la lista. Nota: Utilizar una Pila y una Cola como estructuras auxiliares.
     */
    boolean comprobar(Lista<Integer> lista) {
        Cola<Integer> cola = new ColaDinamica<>();
        Pila<Integer> pila = new PilaDinamica<>();

        boolean verifica = false, cont = true;
        int i, longitud;
        i = 1;
        longitud = lista.longitud();
        Integer elemLista, elemCola, elemPila;

        if (longitud >= 2 && ((longitud - 2) % 3 == 0)) {

            do { // poner los elementos en la cola
                elemLista = lista.recuperar(i);
                verifica = elemLista != null;
                if (verifica && elemLista == 0) {
                    cont = false;
                } else {
                    cola.poner(elemLista);
                }
                i++;
            } while (verifica && cont);

            while (verifica && !cola.esVacia()) { // apilo en la pila los elementos de la cola
                elemCola = cola.obtenerFrente();
                pila.apilar(elemCola);
                cola.sacar();
                elemLista = lista.recuperar(i);
                verifica = elemCola.equals(elemLista);
                i++;
            }

            elemLista = lista.recuperar(i);
            if (elemLista == 0) {
                i++;
                while (verifica && !pila.esVacia()) { // comprebo los elementos de la pila con la lista
                    elemLista = lista.recuperar(i);
                    elemPila = pila.obtenerTope();
                    pila.desapilar();
                    verifica = elemPila.equals(elemLista);
                    i++;
                }
            }
        }
        return verifica;
    }


    @Test
    void comprobartest() {
        Lista<Integer> lista = new ListaDinamica<>(new Integer[]{9, 6, 5, 9, 6, 5, 0, 5, 6, 9});
        assertFalse(comprobar(lista));
        lista = new ListaDinamica<>(new Integer[]{9, 6, 5, 0, 9, 6, 5, 0, 5, 6, 9});
        assertTrue(comprobar(lista));
        lista = new ListaDinamica<>(new Integer[]{0, 0});
        assertTrue(comprobar(lista));
    }
}