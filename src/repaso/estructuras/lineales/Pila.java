package repaso.estructuras.lineales;

public interface Pila<E> {

    boolean apilar(E elemento);

    boolean desapilar();

    E obtenerTope();

    void vaciar();

    boolean esVacia();
}
