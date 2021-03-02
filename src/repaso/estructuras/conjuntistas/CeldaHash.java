package repaso.estructuras.conjuntistas;

import estructuras.conjuntistas.HashCerrado;

public class CeldaHash<E> {
    private E elem;
    private int estado;

    public CeldaHash(E elem, int estado) {
        this.elem = elem;
        this.estado = estado;
    }

    public E getElem() {
        return elem;
    }

    public void setElem(E elem) {
        this.elem = elem;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
