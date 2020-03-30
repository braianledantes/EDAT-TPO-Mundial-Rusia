package estructuras.lineales;

import java.io.Serializable;

/**
 * Esta implementación de lista es dinamica, lo que quiere decir que se pueden almacenar infinitos elementos en ella.
 * Solo hay que tener en cuenta el rendimiento ya que para obener un elemento el tiempo de ejecucion es de O(n).
 *
 * @param <T> tipo de dato que almacena
 */
public class ListaDinamica<T> implements Lista<T>, Serializable {
    private Nodo<T> cabecera, fin;
    private int longitud;

    /**
     * Crea una lista dinamica vacia.
     */
    public ListaDinamica() {
        cabecera = null;
        fin = null;
        longitud = 0;
    }

    /**
     * Crea una lista a partir del arreglo enviado por parametro.
     *
     * @param arr arreglo de elementos
     */
    public ListaDinamica(T[] arr) {
        this();
        Nodo<T> nodo;

        if (arr != null) {
            longitud = arr.length;
            if (arr.length > 0) {
                cabecera = nodo = new Nodo<>(arr[0]);
                for (int i = 1; i < arr.length; i++) {
                    nodo.setEnlace(new Nodo<>(arr[i]));
                    nodo = nodo.getEnlace();
                }
                fin = nodo;
            }
        }
    }

    @Override
    public boolean insertar(T elem) {
        boolean exito = false;
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (elem != null) {
            if (cabecera == null) {
                cabecera = fin = nuevoNodo;
            } else {
                fin.setEnlace(nuevoNodo);
                fin = nuevoNodo;
            }
            longitud++;
            exito = true;
        }
        return exito;
    }

    @Override
    public boolean insertar(T elem, int pos) {
        boolean exito = false;
        Nodo<T> nuevoNodo, aux;

        if (elem != null && pos >= 1 && pos <= longitud + 1) {
            if (pos == 1) {
                if (cabecera == null) {
                    cabecera = new Nodo<>(elem);
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
                if (aux == fin)
                    fin = nuevoNodo;
            }
            longitud++;
            exito = true;
        }

        return exito;
    }

    @Override
    public boolean eliminar(T elem) {
        boolean exito = false;
        Nodo<T> aux, auxAnt = null;

        if (elem != null && longitud > 0) {
            aux = cabecera;
            int i = 1;
            while (!exito && i <= longitud) {
                if (elem.equals(aux.getElem())) {
                    exito = true;
                } else {
                    auxAnt = aux;
                    aux = aux.getEnlace();
                    i++;
                }
            }
            if (exito) {
                if (aux == cabecera && aux == fin) {
                    cabecera = fin = null;
                } else if (aux == cabecera) {
                    cabecera = cabecera.getEnlace();
                } else if (aux == fin) {
                    fin = auxAnt;
                }
                if (auxAnt != null) {
                    auxAnt.setEnlace(aux.getEnlace());
                }
                longitud--;
            }
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
                    fin = null;
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
                if (aux.getEnlace() == null) {
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
            // me posiciono en el nodo para obtener el elemento
            int i = 1;
            aux = cabecera;
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            elem = aux.getElem();
        }

        return elem;
    }

    @Override
    public int localizar(T elem) {
        int pos = -1, i = 1;
        Nodo<T> aux;

        if (elem != null && cabecera != null) {
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
    public boolean existe(T elem) {
        return localizar(elem) != -1;
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
                    } else if (thisNodo != null && thatNodo != null &&
                            this.fin.getElem().equals(that.fin.getElem()) &&
                            this.cabecera.getElem().equals(that.cabecera.getElem())) {
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
    public ListaDinamica<T> clone() {
        ListaDinamica<T> clon = new ListaDinamica<>();
        Nodo<T> nodoThis = cabecera, nodoClon, enlaceClon;

        clon.longitud = this.longitud;
        if (nodoThis != null) {
            nodoClon = new Nodo<>(nodoThis.getElem());
            clon.cabecera = nodoClon;

            while (nodoThis.tieneEnlace()) {
                nodoThis = nodoThis.getEnlace();
                enlaceClon = new Nodo<>(nodoThis.getElem());
                nodoClon.setEnlace(enlaceClon);
                nodoClon = enlaceClon;
            }

            clon.fin = nodoClon;
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
        sb.append(", fin=");
        if (fin != null)
            sb.append(fin.getElem());
        else
            sb.append("null");
        sb.append(", longitud=").append(longitud);
        sb.append(", [");

        Nodo<T> nodo = cabecera;

        while (nodo != null) {
            if (nodo == cabecera)
                sb.append('\n').append(nodo.getElem());
            else
                sb.append(";\n ").append(nodo.getElem());
            nodo = nodo.getEnlace();
        }

        sb.append("]}");
        return sb.toString();
    }
}
