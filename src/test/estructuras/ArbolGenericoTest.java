package test.estructuras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import estructuras.jerarquicas.ArbolGenerico;
import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;

import static org.junit.jupiter.api.Assertions.*;

class ArbolGenericoTest {
    ArbolGenerico<Character> arbol, arbolVacio;

    @BeforeEach
    void setUp() {
        arbolVacio = new ArbolGenerico<>();
        arbol = new ArbolGenerico<>();
        assertTrue(arbol.insertar('a', null));
        assertTrue(arbol.insertar('b', 'a'));
        assertTrue(arbol.insertar('c', 'a'));
        assertTrue(arbol.insertar('d', 'a'));
        assertTrue(arbol.insertar('e', 'b'));
        assertTrue(arbol.insertar('f', 'b'));
        assertTrue(arbol.insertar('g', 'd'));
        assertTrue(arbol.insertar('h', 'd'));
        assertTrue(arbol.insertar('i', 'd'));
        assertTrue(arbol.insertar('j', 'f'));
        assertTrue(arbol.insertar('k', 'f'));
        assertTrue(arbol.insertar('l', 'f'));
        assertTrue(arbol.insertar('m', 'g'));
        assertTrue(arbol.insertar('n', 'i'));
        assertTrue(arbol.insertar('o', 'i'));
        assertTrue(arbol.insertar('p', 'm'));
        assertTrue(arbol.insertar('q', 'm'));
    }

    @Test
    void ancestros() {
        Lista<Character> listaVacia = new ListaDinamica<>();

        assertEquals(new ListaDinamica<>(new Character[]{'a'}), arbol.ancestros('a'));
        assertEquals(listaVacia, arbol.ancestros('z'));
        assertEquals(listaVacia, arbol.ancestros(null));

        Lista<Character> esperado = new ListaDinamica<>(new Character[]{'a', 'b', 'f', 'l'});
        assertEquals(esperado, arbol.ancestros('l'));

        esperado = new ListaDinamica<>(new Character[]{'a', 'd', 'h'});
        assertEquals(esperado, arbol.ancestros('h'));

        esperado = new ListaDinamica<>(new Character[]{'a', 'b'});
        assertEquals(esperado, arbol.ancestros('b'));

        esperado = new ListaDinamica<>(new Character[]{'a', 'd', 'g', 'm', 'p'});
        assertEquals(esperado, arbol.ancestros('p'));
    }

    @Test
    void pertenece() {
        assertFalse(arbol.pertenece(null));
        assertFalse(arbol.pertenece('z'));
        assertTrue(arbol.pertenece('a'));
        assertTrue(arbol.pertenece('b'));
        assertTrue(arbol.pertenece('c'));
        assertTrue(arbol.pertenece('d'));
        assertTrue(arbol.pertenece('e'));
        assertTrue(arbol.pertenece('f'));
        assertTrue(arbol.pertenece('g'));
        assertTrue(arbol.pertenece('h'));
        assertTrue(arbol.pertenece('i'));
        assertTrue(arbol.pertenece('j'));
        assertTrue(arbol.pertenece('k'));
        assertTrue(arbol.pertenece('l'));
        assertTrue(arbol.pertenece('m'));
        assertTrue(arbol.pertenece('n'));
        assertTrue(arbol.pertenece('o'));
        assertTrue(arbol.pertenece('p'));
        assertTrue(arbol.pertenece('q'));
    }

    @Test
    void altura() {
        arbol.vaciar();
        assertEquals(-1, arbol.altura());
        assertTrue(arbol.insertar('a', null));
        assertEquals(0, arbol.altura());
        assertTrue(arbol.insertar('b', 'a'));
        assertEquals(1, arbol.altura());
        assertTrue(arbol.insertar('c', 'a'));
        assertEquals(1, arbol.altura());
        assertTrue(arbol.insertar('d', 'a'));
        assertEquals(1, arbol.altura());
        assertTrue(arbol.insertar('e', 'b'));
        assertEquals(2, arbol.altura());
        assertTrue(arbol.insertar('f', 'b'));
        assertEquals(2, arbol.altura());
        assertTrue(arbol.insertar('g', 'd'));
        assertEquals(2, arbol.altura());
        assertTrue(arbol.insertar('h', 'd'));
        assertEquals(2, arbol.altura());
        assertTrue(arbol.insertar('i', 'd'));
        assertEquals(2, arbol.altura());
        assertTrue(arbol.insertar('j', 'f'));
        assertEquals(3, arbol.altura());
        assertTrue(arbol.insertar('k', 'f'));
        assertEquals(3, arbol.altura());
        assertTrue(arbol.insertar('l', 'f'));
        assertEquals(3, arbol.altura());
        assertTrue(arbol.insertar('m', 'g'));
        assertEquals(3, arbol.altura());
        assertTrue(arbol.insertar('n', 'i'));
        assertEquals(3, arbol.altura());
        assertTrue(arbol.insertar('o', 'i'));
        assertTrue(arbol.insertar('p', 'm'));
        assertEquals(4, arbol.altura());
        assertTrue(arbol.insertar('q', 'm'));
        assertEquals(4, arbol.altura());
    }

    @Test
    void nivel() {
        assertEquals(-1, arbol.nivel(null));
        assertEquals(-1, arbol.nivel('z'));
        assertEquals(0, arbol.nivel('a'));
        assertEquals(1, arbol.nivel('b'));
        assertEquals(1, arbol.nivel('c'));
        assertEquals(1, arbol.nivel('d'));
        assertEquals(2, arbol.nivel('e'));
        assertEquals(2, arbol.nivel('f'));
        assertEquals(2, arbol.nivel('g'));
        assertEquals(2, arbol.nivel('h'));
        assertEquals(2, arbol.nivel('i'));
        assertEquals(3, arbol.nivel('j'));
        assertEquals(3, arbol.nivel('k'));
        assertEquals(3, arbol.nivel('l'));
        assertEquals(3, arbol.nivel('m'));
        assertEquals(3, arbol.nivel('n'));
        assertEquals(3, arbol.nivel('o'));
        assertEquals(4, arbol.nivel('p'));
        assertEquals(4, arbol.nivel('q'));
    }

    @Test
    void padre() {
        assertNull(arbol.padre(null));
        assertNull(arbol.padre('z'));
        assertNull(arbol.padre('a'));
        assertEquals('a', arbol.padre('b'));
        assertEquals('a', arbol.padre('c'));
        assertEquals('a', arbol.padre('d'));
        assertEquals('b', arbol.padre('e'));
        assertEquals('b', arbol.padre('f'));
        assertEquals('d', arbol.padre('g'));
        assertEquals('d', arbol.padre('h'));
        assertEquals('d', arbol.padre('i'));
        assertEquals('f', arbol.padre('j'));
        assertEquals('f', arbol.padre('k'));
        assertEquals('f', arbol.padre('l'));
        assertEquals('g', arbol.padre('m'));
        assertEquals('i', arbol.padre('n'));
        assertEquals('i', arbol.padre('o'));
        assertEquals('m', arbol.padre('p'));
        assertEquals('m', arbol.padre('q'));
    }

    @Test
    void listarPreorden() {
        Lista<Character> esperado = new ListaDinamica<>();
        assertEquals(esperado, arbolVacio.listarPreorden());

        esperado = new ListaDinamica<>(new Character[]{'a', 'b', 'e', 'f', 'j', 'k', 'l', 'c', 'd', 'g', 'm', 'p', 'q', 'h', 'i', 'n', 'o'});
        assertEquals(esperado, arbol.listarPreorden());
    }

    @Test
    void listarInorden() {
        Lista<Character> esperado = new ListaDinamica<>();
        assertEquals(esperado, arbolVacio.listarInorden());

        esperado = new ListaDinamica<>(new Character[]{'e', 'b', 'j', 'f', 'k', 'l', 'a', 'c', 'p', 'm', 'q', 'g', 'd', 'h', 'n', 'i', 'o'});
        assertEquals(esperado, arbol.listarInorden());
    }

    @Test
    void listarPosorden() {
        Lista<Character> esperado = new ListaDinamica<>();
        assertEquals(esperado, arbolVacio.listarPosorden());

        esperado = new ListaDinamica<>(new Character[]{'e', 'j', 'k', 'l', 'f', 'b', 'c', 'p', 'q', 'm', 'g', 'h', 'n', 'o', 'i', 'd', 'a'});
        assertEquals(esperado, arbol.listarPosorden());
    }

    @Test
    void listarNiveles() {
        Lista<Character> esperado = new ListaDinamica<>();
        assertEquals(esperado, arbolVacio.listarNiveles());

        esperado = new ListaDinamica<>(new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q'});
        assertEquals(esperado, arbol.listarNiveles());
    }

    @Test
    void frontera() {
        assertEquals(new ListaDinamica<Character>(), new ArbolGenerico<Character>().frontera());
        Lista<Character> frontera = new ListaDinamica<>(new Character[]{'e', 'j', 'k', 'l', 'c', 'p', 'q', 'h', 'n', 'o'});
        assertEquals(frontera, arbol.frontera());
    }

    @Test
    void equalsYClone() {
        ArbolGenerico<Character> arbolVacio2 = arbolVacio.clone();
        assertEquals(arbolVacio, arbolVacio2);

        ArbolGenerico<Character> clon = arbol.clone();
        assertEquals(arbol, clon);

        ArbolGenerico<Character> arbol2 = new ArbolGenerico<>();
        arbol2.insertar('a', null);
        arbol2.insertar('b', 'a');
        arbol2.insertar('c', 'a');
        arbol2.insertar('d', 'a');
        arbol2.insertar('e', 'b');
        arbol2.insertar('f', 'b');
        arbol2.insertar('g', 'd');
        arbol2.insertar('h', 'd');
        arbol2.insertar('i', 'd');
        arbol2.insertar('j', 'f');
        arbol2.insertar('k', 'f');
        arbol2.insertar('l', 'f');
        arbol2.insertar('m', 'g');
        arbol2.insertar('n', 'i');
        arbol2.insertar('o', 'i');
        arbol2.insertar('p', 'm');
        arbol2.insertar('q', 'm');
        assertEquals(arbol, arbol2);
        arbol2.insertar('z', 'd');
        assertNotEquals(arbol, arbol2);
    }
}