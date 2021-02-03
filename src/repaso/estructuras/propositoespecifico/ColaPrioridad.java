package repaso.estructuras.propositoespecifico;

public interface ColaPrioridad<E> {

    boolean insertar(E elemento, int prioridad);

    boolean eliminarFrente();

    E obtenerFrente();

    boolean esVacia();

}
