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
        Lista<Integer> lesp = new ListaDinamica<>();
        assertTrue(lista.insertar(1));
        assertTrue(lista.eliminar(1));
        assertTrue(lista.estaVacia());
        assertEquals(lesp, lista);
        System.out.println(lista);

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
    void elimimarElem() {
        ListaDinamica<Integer> esp = new ListaDinamica<>();
        assertTrue(lista.insertar(1));
        assertTrue(lista.eliminar(1));
        assertEquals(esp, lista);

        lista.vaciar();
        esp.vaciar();
        assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(2));
        assertTrue(esp.insertar(1));
        assertTrue(lista.eliminar(2));
        assertEquals(esp, lista);

        lista.vaciar();
        esp.vaciar();
        assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(2));
        assertTrue(esp.insertar(2));
        assertTrue(lista.eliminar(1));
        assertEquals(esp, lista);

        lista.vaciar();
        esp.vaciar();
        assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(2));
        assertTrue(lista.insertar(3));
        assertTrue(esp.insertar(1));
        assertTrue(esp.insertar(3));
        assertTrue(lista.eliminar(2));
        assertEquals(esp, lista);
    }

    @Test
    void insertar2() {
        assertTrue(lista.insertar(1));
        assertTrue(lista.insertar(2));
        assertTrue(lista.insertar(3));
        assertTrue(lista.insertar(4));
        assertTrue(lista.insertar(5));
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
        assertTrue(lista.insertar(2));
        assertTrue(lista.insertar(3));
        assertTrue(lista.insertar(4));
        assertTrue(lista.insertar(5));
        assertEquals(5, lista.longitud());
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
     * recibe dos listas L1 y L2 y devuelve una lista nueva con los elementos de L1 y L2
     * concatenados. Ej: si L1=[2,4,6] y L2=[5,1,6,7] debe devolver [2,4,6,5,1,6,7]
     */
    Lista<Integer> concatenar(ListaDinamica<Integer> l1, ListaDinamica<Integer> l2) {
        Lista<Integer> concatenada = l1.clone();
        int i = 1;

        while (i <= l2.longitud())
            concatenada.insertar(l2.recuperar(i));

        return concatenada;
    }

    /**
     * recibe una lista L1 y devuelve una lista nueva donde los elementos de L1 aparecen en orden
     * invertido. Ej: si L1=[1,2,3,4] debe devolver [4,3,2,1]. Nota: Utilizar una Pila como estructura auxiliar.
     */
    Lista<Integer> invertir(Lista<Integer> lista) {
        ListaDinamica<Integer> invertida = new ListaDinamica<>();
        Pila<Integer> pila = new PilaDinamica<>();

        for (int i = 1; i <= lista.longitud(); i++)
            pila.apilar(lista.recuperar(i));

        while (!pila.esVacia()) {
            invertida.insertar(pila.obtenerTope());
            pila.desapilar();
        }

        return invertida;
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

                if (verifica && elemLista == 0) cont = false;
                else cola.poner(elemLista);

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

    /**
     * Dadas dos listas L1 y L2, devuelva una lista nueva con los elementos de L1 y L2
     * intercalados. Por ejemplo, si L1=[1,3,5] y L2=[2,4,6,7] debe devolver [1,2,3,4,5,6,7]
     */
    Lista<Integer> intercalar(Lista<Integer> l1, Lista<Integer> l2) {
        Lista<Integer> intercalada = new ListaDinamica<>();
        int i = 1, pos = 1;
        Integer e1, e2;

        while (i <= l1.longitud() || i <= l2.longitud()) {
            e1 = l1.recuperar(i);
            e2 = l2.recuperar(i);
            if (e1 != null) intercalada.insertar(e1, ++pos);
            if (e2 != null) intercalada.insertar(e2, ++pos);
        }

        return intercalada;
    }

    /**
     * Dada una lista que admite elementos repetidos, cuente cuántas veces aparece un elemento
     * dado elem dentro de la lista.
     */
    int contarIterativamente(Lista<Integer> lista, Integer elem) {
        int aparece = 0;

        for (int i = 1; i <= lista.longitud(); i++)
            if (elem.equals(lista.recuperar(i))) aparece++;

        return aparece;
    }

    /**
     * Dada una lista que admite elementos repetidos, cuente cuántas veces aparece un elemento
     * dado elem dentro de la lista.
     */
    int contarRecursivamente(Lista<Integer> lista, Integer elem) {
        return contarRecursivamente(lista, elem, lista.longitud());
    }

    int contarRecursivamente(Lista<Integer> lista, Integer elem, int pos) {
        int aparece = 0;

        if (pos >= 1) {
            if (elem.equals(lista.recuperar(pos)))
                aparece = contarRecursivamente(lista, elem, pos - 1) + 1;
            else
                aparece = contarRecursivamente(lista, elem, pos - 1);
        }

        return aparece;
    }

    /**
     * que verifica si los elementos en una lista son capicúa. Nota: Utilizar una estructura Pila
     * para facilitar la operación.
     */
    boolean esCapicua(Lista<Integer> lista) {
        boolean capicua = true;
        int pos = 1;
        Pila<Integer> pila = new PilaDinamica<>();

        while (pos <= lista.longitud() / 2) {
            pila.apilar(lista.recuperar(pos));
        }
        if (lista.longitud() % 2 != 0) pos++;
        while (capicua && pos <= lista.longitud()) {
            capicua = pila.obtenerTope().equals(lista.recuperar(pos));
            pila.desapilar();
        }

        return capicua;
    }
}