package jerarquicas;

import lineales.Lista;

public class ArbolGenerico<T> implements Arbol<T> {
    @Override
    public boolean insertar(T elemNuevo, T elemPadre) {
        return false;
    }

    @Override
    public boolean esVacio() {
        return false;
    }

    @Override
    public int altura() {
        return 0;
    }

    @Override
    public int nivel(T elemento) {
        return 0;
    }

    @Override
    public T padre(T elemento) {
        return null;
    }

    @Override
    public Lista<T> listarPreorden() {
        return null;
    }

    @Override
    public Lista<T> listarInorden() {
        return null;
    }

    @Override
    public Lista<T> listarPosorden() {
        return null;
    }

    @Override
    public Lista<T> listarNiveles() {
        return null;
    }

    @Override
    public void vaciar() {

    }
}
