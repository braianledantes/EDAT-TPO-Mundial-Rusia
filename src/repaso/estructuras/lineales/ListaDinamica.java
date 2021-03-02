package repaso.estructuras.lineales;

public class ListaDinamica<E> implements Lista<E> {
    private NodoLineal<E> cabecera = null;

    @Override
    public boolean insertar(E elemento, int posicion) {
        boolean resultado = false;
        if (elemento != null && posicion > 0) {
            if (posicion == 1) {
                cabecera = new NodoLineal<>(elemento, cabecera);
                resultado = true;
            } else {
                NodoLineal<E> nodo = cabecera;
                int pos = 2;
                while (nodo != null && pos < posicion) {
                    nodo = nodo.getEnlace();
                    pos++;
                }
                if (nodo != null && pos == posicion) {
                    nodo.setEnlace(new NodoLineal<>(elemento, nodo.getEnlace()));
                    resultado = true;
                }
            }
        }
        return resultado;
    }

    @Override
    public E recuperar(int posicion) {
        E elemento = null;
        if (cabecera != null && posicion > 0) {
            NodoLineal<E> aux = cabecera;
            int pos = 1;
            while (aux != null && pos < posicion) {
                aux = aux.getEnlace();
                pos++;
            }
            if (pos == posicion && aux != null)
                elemento = aux.getElemento();
        }
        return elemento;
    }

    @Override
    public int localizar(E elemento) {
        int resultado = -1;
        if (elemento != null) {
            int pos = 1;
            NodoLineal<E> nodo = cabecera;
            while (nodo != null && resultado == -1) {
                if (elemento.equals(nodo.getElemento())) {
                    resultado = pos;
                }
                nodo = nodo.getEnlace();
                pos++;
            }
        }
        return resultado;
    }

    @Override
    public boolean eliminar(int posicion) {
        boolean resultado = false;
        if (cabecera != null && posicion > 0) {
            if (posicion == 1) {
                cabecera = cabecera.getEnlace();
                resultado = true;
            } else {
                int pos = 1;
                NodoLineal<E> nodo = cabecera;
                while (nodo != null && pos < (posicion - 1)) {
                    nodo = nodo.getEnlace();
                    pos++;
                }
                if (nodo != null && nodo.getEnlace() != null && pos == (posicion - 1)) {
                    nodo.setEnlace(nodo.getEnlace().getEnlace());
                    resultado = true;
                }
            }
        }
        return resultado;
    }

    @Override
    public int longitud() {
        int longitud = 0;
        NodoLineal<E> aux = cabecera;
        while (aux != null) {
            longitud++;
            aux = aux.getEnlace();
        }
        return longitud;
    }

    @Override
    public void vaciar() {
        cabecera = null;
    }

    @Override
    public boolean esVacia() {
        return cabecera == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaDinamica<?> that = (ListaDinamica<?>) o;

        NodoLineal<?> nodoThis = this.cabecera;
        NodoLineal<?> nodoThat = that.cabecera;

        boolean iguales = true;
        while (iguales && nodoThis != null && nodoThat != null) {
            iguales = nodoThis.getElemento().equals(nodoThat.getElemento());
            nodoThis = nodoThis.getEnlace();
            nodoThat = nodoThat.getEnlace();
        }
        iguales = nodoThis == null && nodoThat == null;

        return iguales;
    }

    @Override
    public ListaDinamica<E> clone() {
        ListaDinamica<E> clon = new ListaDinamica<>();

        if (this.cabecera != null) {
            clon.cabecera = new NodoLineal<>(this.cabecera.getElemento());
            NodoLineal<E> nodoThis = this.cabecera.getEnlace();
            NodoLineal<E> nodoClon = clon.cabecera;
            while (nodoThis != null) {
                nodoClon.setEnlace(new NodoLineal<>(nodoThis.getElemento()));
                nodoClon = nodoClon.getEnlace();
                nodoThis = nodoThis.getEnlace();
            }
        }
        return clon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListaDinamica{ ");
        NodoLineal<E> nodo = this.cabecera;
        while (nodo != null) {
            sb.append(nodo.getElemento()).append(" ");
            nodo = nodo.getEnlace();
        }
        sb.append('}');
        return sb.toString();
    }
}
