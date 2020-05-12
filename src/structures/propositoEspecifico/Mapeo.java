package structures.propositoEspecifico;

import structures.lineales.Lista;

public interface Mapeo<D, R> {

    boolean asociar(D key, R value);

    boolean desasociar(D key);

    boolean obtenerValor(D key);

    Lista<D> obtenerConjuntoDominio();

    Lista<R> obtenerConjuntoRango();

    boolean eVacio();
}
