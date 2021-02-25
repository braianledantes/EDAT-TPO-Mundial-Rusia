package estructuras.propositoEspecifico;

import estructuras.lineales.Lista;

public interface Mapeo<D, R> {

    /**
     * Recibe un valor que representa a un elemento del dominio y un segundo valor que representa a
     * un elemento del rango. Si no existe otro par que contenga a valorDominio, agrega en el mapeo el
     * par (valorDominio, valorRango). Si la operación termina con éxito devuelve verdadero y falso en
     * caso contrario.
     * Nota: Cada valor del dominio puede aparecer en un único par, es decir, no se aceptan valores del
     * dominio repetidos.
     *
     * @param key
     * @param value
     * @return
     */
    boolean asociar(D key, R value);

    /**
     * Elimina el par cuyo dominio coincida con el valor recibido por parámetro. Si lo encuentra y la
     * operación de eliminación termina con éxito devuelve verdadero y falso en caso contrario.
     *
     * @param key
     * @return
     */
    boolean desasociar(D key);

    /**
     * Si en el mapeo se encuentra almacenado algún par cuyo dominio es valorDominio, esta operación
     * devuelve el valor de rango asociado a él. Precondición: valorDominio está en el mapeo (si no existe,
     * no se puede asegurar el funcionamiento de la operación).
     *
     * @param key
     * @return
     */
    boolean obtenerValor(D key);

    /**
     * Devuelve un conjunto con todos los valores de tipo dominio almacenados en el mapeo.
     *
     * @return Conjunto de elementos de tipo dominio
     */
    Lista<D> obtenerConjuntoDominio();

    /**
     * Devuelve un conjunto con la unión de todos los valores de tipo rango almacenados en el mapeo.
     * @return Conjunto de elementos de tipo rango
     */
    Lista<R> obtenerConjuntoRango();

    /**
     * Devuelve falso si hay al menos un par cargado en el mapeo y verdadero en caso contrario.
     * @return
     */
    boolean eVacio();
}
