package repaso.estructuras.conjuntistas;

import repaso.estructuras.lineales.Lista;

public interface TablaHash<E> {

    boolean insertar(E elemento);

    boolean eliminar(E elemento);

    boolean pertenece(E elemento);

    boolean esVacia();

    Lista<E> listar();

    void vaciar();
}
