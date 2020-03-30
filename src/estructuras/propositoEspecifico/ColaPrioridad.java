package estructuras.propositoEspecifico;

public interface ColaPrioridad<E> {

    /**
     * recibe por parámetro el elemento y la prioridad del mismo. Se agrega el elemento en la cola detrás
     * de los de prioridad mayor o igual y por delante de los de prioridad menor.
     *
     * @param elemento  elemento a insertar
     * @param prioridad prioridad de 1 a infinito
     * @return Si la operación termina con éxito devuelve verdadero y falso en caso contrario
     */
    boolean insertar(E elemento, int prioridad);

    /**
     * Elimina el elemento de mayor prioridad. Si hay más de uno con igual prioridad, elimina el que llegó
     * primero.
     *
     * @return Si la operación de eliminación termina con éxito devuelve verdadero y falso en caso contrario
     */
    boolean eliminarFrente();

    /**
     * Devuelve el elemento de mayor prioridad. Si hay más de uno con igual prioridad, devuelve el que
     * llegó primero. Precondición: la cola no está vacía (si está vacía no se puede asegurar el funcionamiento
     * de la operación).
     *
     * @return el elemento de mayor prioridad
     */
    E obtenerFrente();

    /**
     * Devuelve verdadero si la lista no tiene elementos y falso en caso contrario.
     *
     * @return verdadero si la lista no tiene elementos y falso en caso contrario.
     */
    boolean estaVacia();

    /**
     * Quita todos los elementos de la lista.
     */
    void vaciar();

}
