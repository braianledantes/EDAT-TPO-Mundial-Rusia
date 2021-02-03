package estructuras.propositoEspecifico;

import estructuras.lineales.Lista;

import java.io.Serializable;

public class MapeoAUno<D, R> implements Mapeo<D, R>, Serializable {

    @Override
    public boolean asociar(D key, R value) {
        return false;
    }

    @Override
    public boolean desasociar(D key) {
        return false;
    }

    @Override
    public boolean obtenerValor(D key) {
        return false;
    }

    @Override
    public Lista<D> obtenerConjuntoDominio() {
        return null;
    }

    @Override
    public Lista<R> obtenerConjuntoRango() {
        return null;
    }

    @Override
    public boolean eVacio() {
        return false;
    }
}
