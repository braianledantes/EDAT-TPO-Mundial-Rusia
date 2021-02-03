package repaso.estructuras.lineales;

public interface Cola<E> {
    boolean poner(E elemento);

    E obtenerFrente();

    boolean sacar();

    boolean esVacia();

    void vaciar();
}
