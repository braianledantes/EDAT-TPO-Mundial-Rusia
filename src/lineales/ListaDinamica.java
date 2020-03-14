package lineales;

/**
 * Esta implementación de lista es dinamica, lo que quiere decir que se pueden almacenar infinitos elementos en ella.
 * Solo hay que tener en cuenta el rendimiento ya que para obener un elemento el tiempo de ejecucion es de O(n).
 *
 * @param <T> tipo de dato que almacena
 */
public class ListaDinamica<T> implements Lista<T> {
    private Nodo<T> cabecera, fin;
    private int longitud;

    public ListaDinamica() {
        cabecera = null;
        fin = null;
        longitud = 0;
    }

    @Override
    public boolean insertar(T elem) {
        Nodo<T> nuevoNodo = new Nodo<>(elem);

        if (cabecera == null) {
            cabecera = fin = nuevoNodo;
        } else {
            fin.setEnlace(nuevoNodo);
            fin = nuevoNodo;
        }
        longitud++;

        return true;
    }

    @Override
    public boolean insertar(T elem, int pos) {
        boolean exito = false;
        Nodo<T> nuevoNodo, aux;

        if (pos >= 1 && pos <= longitud + 1) {
            if (pos == 1) {
                if (cabecera == fin) {
                    cabecera = new Nodo<>(elem, cabecera);
                    fin = cabecera;
                } else {
                    cabecera = new Nodo<>(elem, cabecera);
                }
            } else if (pos == longitud) {
                nuevoNodo = new Nodo<>(elem);
                fin.setEnlace(nuevoNodo);
                fin = nuevoNodo;
            } else {
                // me posiciono en la posicion anterior al nodo para obtener el enlace
                aux = cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                // inserto el nuevo nodo en el medio
                nuevoNodo = new Nodo<>(elem, aux.getEnlace());
                aux.setEnlace(nuevoNodo);
            }
            longitud++;
            exito = true;
        }

        return exito;
    }

    @Override
    public boolean eliminar(int pos) {
        boolean exito = false;
        Nodo<T> aux;

        if (pos >= 1 && pos <= longitud) {
            if (pos == 1) {
                if (cabecera == fin) {
                    fin = cabecera.getEnlace();
                }
                cabecera = cabecera.getEnlace();
            } else {
                // me posiciono en la posicion anterior al nodo para obtener el enlace
                int i = 1;
                aux = cabecera;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace()); // corto
                if (aux.getEnlace() == fin) {
                    fin = aux;
                }
            }

            exito = true;
            longitud--;
        }

        return exito;
    }

    @Override
    public T recuperar(int pos) {
        T elem = null;
        Nodo<T> aux;

        if (pos >= 1 && pos <= longitud) {
            if (pos == longitud) {
                elem = fin.getElem();
            } else {
                // me posiciono en el nodo para obtener el elemento
                int i = 1;
                aux = cabecera;
                while (i < pos) {
                    aux = aux.getEnlace();
                    i++;
                }
                elem = aux.getElem();
            }
        }

        return elem;
    }

    @Override
    public int localizar(T elem) {
        int pos = -1, i = 1;
        Nodo<T> aux;

        if (cabecera != null) {
            aux = cabecera;
            while (aux != null) {
                if (elem == aux.getElem() || elem.equals(aux.getElem())) {
                    pos = i;
                    aux = null;
                } else {
                    aux = aux.getEnlace();
                    i++;
                }
            }
        }

        return pos;
    }

    @Override
    public boolean estaVacia() {
        return cabecera == null;
    }

    @Override
    public void vaciar() {
        cabecera = fin = null;
        longitud = 0;
    }

    @Override
    public int longitud() {
        return longitud;
    }

    @Override
    public boolean equals(Object obj) {
        ListaDinamica<?> that;
        Nodo<?> thisNodo, thatNodo;
        boolean equals = false;

        if (this != obj) {
            if (obj instanceof ListaDinamica) {
                that = (ListaDinamica<?>) obj;
                thisNodo = this.cabecera;
                thatNodo = that.cabecera;

                if (this.longitud == that.longitud) {
                    if (thisNodo == null && thatNodo == null) {
                        equals = true;
                    } else if (thisNodo != null && thatNodo != null) {
                        do {
                            equals = thisNodo.getElem().equals(thatNodo.getElem())
                                    && thisNodo.tieneEnlace() == thatNodo.tieneEnlace();

                            if (equals) {
                                thisNodo = thisNodo.getEnlace();
                                thatNodo = thatNodo.getEnlace();
                            }
                        } while (equals && thisNodo != null && thatNodo != null);
                    }
                }
            }
        } else {
            equals = true;
        }
        return equals;
    }

    @Override
    protected ListaDinamica<T> clone() {
        ListaDinamica<T> clon = new ListaDinamica<>();
        Nodo<T> nodoThis = cabecera, nodoClon, enlaceClon;

        if (nodoThis != null) {
            nodoClon = new Nodo<>(nodoThis.getElem());
            clon.cabecera = nodoClon;

            while (nodoThis.tieneEnlace()) {
                nodoThis = nodoThis.getEnlace();
                enlaceClon = new Nodo<>(nodoThis.getElem());
                nodoClon.setEnlace(enlaceClon);
                nodoClon = enlaceClon;
            }
        }

        return clon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getName() + "{");

        sb.append("cabecera=");
        if (cabecera != null)
            sb.append(cabecera.getElem());
        else
            sb.append("null");
        sb.append(", longitud=").append(longitud);
        sb.append(", [");

        Nodo<T> nodo = cabecera;

        while (nodo != null) {
            if (nodo == cabecera)
                sb.append(nodo.getElem());
            else
                sb.append("; ").append(nodo.getElem());
            nodo = nodo.getEnlace();
        }

        sb.append("]}");
        return sb.toString();
    }
}
