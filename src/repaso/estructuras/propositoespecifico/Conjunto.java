package repaso.estructuras.propositoespecifico;

public interface Conjunto<E> {
    boolean agregar(E valor);

    boolean quitar(E valor);

    boolean pertenece(E valor);

    boolean esVacio();
}
