package repaso.estructuras.lineales;

public interface Lista<E> {
    boolean insertar(E elemento, int posicion);

    boolean eliminar(int posicion);

    E recuperar(int posicion);

    int localizar(E elemento);

    int longitud();

    boolean esVacia();

    void vaciar();
}
