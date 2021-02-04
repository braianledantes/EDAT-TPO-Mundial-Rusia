package repaso.estructuras.conjuntistas;

import repaso.estructuras.lineales.Lista;

public interface AAVL<E extends Comparable<E>> {

    boolean insertar(E elemento);

    boolean eliminar(E elemento);

    boolean pertenece(E elemento);

    boolean esVacio();

    Lista<E> listar();

    Lista<E> lisarRango(E elementoMinimo, E elementoMaximo);

    E minimoElemento();

    E maximoElemento();

    void vaciar();
}
