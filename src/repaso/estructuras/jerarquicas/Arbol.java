package repaso.estructuras.jerarquicas;

import repaso.estructuras.lineales.Lista;

public interface Arbol<E> {

    boolean insertar(E elementoNuevo, E elementoPadre, int posicion);

    boolean esVacio();

    int altura();

    int nivel(E elemento);

    E padre(E elemento);

    Lista<E> listarPreorden();

    Lista<E> listarInorden();

    Lista<E> listarPosorden();

    Lista<E> listarNiveles();

    void vaciar();

    boolean verificarPatron(Lista<E> listaPatron);

    Lista<E> frontera();
}
