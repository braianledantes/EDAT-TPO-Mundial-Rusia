package repaso.estructuras.propositoespecifico;

import repaso.estructuras.conjuntistas.AAVL;
import repaso.estructuras.conjuntistas.ArbolAVL;

public class ConjuntoAVL<E extends Comparable<E>> implements Conjunto<E> {
    private AAVL<E> avl = new ArbolAVL<>();

    @Override
    public boolean agregar(E valor) {
        return avl.insertar(valor);
    }

    @Override
    public boolean quitar(E valor) {
        return avl.eliminar(valor);
    }

    @Override
    public boolean pertenece(E valor) {
        return avl.pertenece(valor);
    }

    @Override
    public boolean esVacio() {
        return avl.esVacio();
    }
}
