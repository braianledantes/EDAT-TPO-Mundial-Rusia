package structures.conjuntistas;

public class ArbolAVL<T extends Comparable<T>> extends ABB<T> {

    // TODO VEVIFICAR TODAS LAS ROTACIONES A NIVEL RAIZ Y NODO INTERNO
    @Override
    public boolean insertar(T elem) {
        boolean exito = true;

        if (elem != null) {
            if (this.raiz == null)
                this.raiz = new Nodo<>(elem);
            else
                exito = insertar(elem, this.raiz, null);
        }
        return exito;
    }

    private boolean insertar(T elem, Nodo<T> nodo, Nodo<T> nodoPadre) {
        boolean exito = true;

        if (elem.compareTo(nodo.getElem()) == 0) {
            exito = false;
        } else {
            if (elem.compareTo(nodo.getElem()) < 0) {
                if (nodo.tieneIzq())
                    exito = insertar(elem, nodo.getIzq(), nodo);
                else
                    nodo.setIzq(new Nodo<>(elem));
            } else {
                if (nodo.tieneDer())
                    exito = insertar(elem, nodo.getDer(), nodo);
                else
                    nodo.setDer(new Nodo<>(elem));
            }
            nodo.recalcularAltura();
            balancear(nodo, nodoPadre);
        }
        return exito;
    }

    @Override
    protected boolean eliminar(T elem, Nodo<T> nodo, Nodo<T> nodoPadre) {
        boolean exito = super.eliminar(elem, nodo, nodoPadre);
        if (exito) {
            nodo.recalcularAltura();
            balancear(nodo, nodoPadre);
        }
        return exito;
    }

    private void balancear(Nodo<T> nodo, Nodo<T> nodoPadre) {
        int balance = calcularBalance(nodo);
        int balanceHijo;
        Nodo<T> nodoRaizSubarbol = null;

        if (balance == -2) { // inclinado hacia la derecha
            balanceHijo = calcularBalance(nodo.getDer());
            if (balanceHijo == 0 || balanceHijo == -1)
                nodoRaizSubarbol = rotarIzquierda(nodo);
            else if (balanceHijo == 1) {
                nodo.setDer(rotarDerecha(nodo.getDer()));
                nodoRaizSubarbol = rotarIzquierda(nodo);
            }
        } else if (balance == 2) { // inclinado hacia la izquierda
            balanceHijo = calcularBalance(nodo.getIzq());
            if (balanceHijo == 0 || balanceHijo == 1)
                nodoRaizSubarbol = rotarDerecha(nodo);
            else if (balanceHijo == -1) {
                nodo.setIzq(rotarIzquierda(nodo.getIzq()));
                nodoRaizSubarbol = rotarDerecha(nodo);
            }
        }

        // lo "engancho" al nodo padre
        if (nodoRaizSubarbol != null) {
            if (nodoPadre == null) {
                this.raiz = nodoRaizSubarbol;
            } else {
                if (nodo == nodoPadre.getDer()) { // si era el hijo derecho
                    nodoPadre.setDer(nodoRaizSubarbol);
                } else { // si era el hijo derecho
                    nodoPadre.setIzq(nodoRaizSubarbol);
                }
            }
        }
    }

    private int calcularBalance(Nodo<T> nodo) {
        int altIzq = -1, altDer = -1;
        if (nodo.tieneIzq())
            altIzq = nodo.getIzq().getAltura();
        if (nodo.tieneDer())
            altDer = nodo.getDer().getAltura();
        return altIzq - altDer;
    }

    private Nodo<T> rotarIzquierda(Nodo<T> r) {
        Nodo<T> h = r.getDer();
        Nodo<T> temp = h.getIzq();
        h.setIzq(r);
        r.setDer(temp);

        h.recalcularAltura();
        return h;
    }

    private Nodo<T> rotarDerecha(Nodo<T> r) {
        Nodo<T> h = r.getIzq();
        Nodo<T> temp = h.getDer();
        h.setDer(r);
        r.setIzq(temp);

        h.recalcularAltura();
        return h;
    }
}
