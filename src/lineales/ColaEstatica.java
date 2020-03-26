package lineales;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Esta cola esta implmentada por un arreglo circular, por lo tanto la cantidad de elementos es limitada por el tamaño del arreglo.
 * La cola crece y decrece dentro del espacio disponible.
 *
 * @param <T>
 */
public class ColaEstatica<T> implements Cola<T> {
    public static final int TAM = 8;
    private T[] cola;
    private int inicio, fin;
    private Class<T> clase;

    /**
     * Crea una cola estatica vacia.
     *
     * @param clase tipo de dato que almacena
     */
    public ColaEstatica(Class<T> clase) {
        this.clase = clase;
        cola = (T[]) Array.newInstance(clase, TAM);
        inicio = 0;
        fin = 0;
    }

    @Override
    public boolean poner(T elem) throws EstructuraLlenaException {
        boolean exito = false;
        if (elem != null) {
            int finAux = (fin + 1) % TAM;
            if (finAux != inicio) {
                cola[fin] = elem;
                fin = finAux;
                exito = true;
            } else {
                throw new EstructuraLlenaException();
            }
        }
        return exito;
    }

    @Override
    public boolean sacar() {
        boolean exito = false;
        if (!esVacia()) {
            inicio = (inicio + 1) % TAM;
            exito = true;
        }
        return exito;
    }

    @Override
    public T obtenerFrente() {
        return !esVacia() ? cola[inicio] : null;
    }

    @Override
    public boolean esVacia() {
        return fin == inicio;
    }

    @Override
    public void vaciar() {
        inicio = fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColaEstatica<?> that = (ColaEstatica<?>) o;

        boolean equals;

        //calcula la cantidad
        int thisCant, thatCant;
        if (inicio < fin) {
            thisCant = TAM - this.fin + this.inicio;
            thatCant = TAM - that.fin + that.inicio;
        } else if (inicio > fin) {
            thisCant = TAM - this.inicio + this.fin;
            thatCant = TAM - that.inicio + that.fin;
        } else {
            thatCant = thisCant = 0;
        }

        if (thisCant == thatCant) {
            int i = 0;
            equals = true;
            while (equals && i < thisCant) {
                equals = this.cola[(inicio + i) % TAM] == that.cola[(that.inicio + i) % TAM];
                i++;
            }
        } else {
            equals = false;
        }
        return equals;
    }

    @Override
    protected ColaEstatica<T> clone() {
        ColaEstatica<T> clon = new ColaEstatica<>(clase);
        int i = inicio;
        clon.fin = this.fin;
        clon.inicio = this.inicio;

        while (i != fin) {
            clon.cola[i] = cola[i];
            i = (i + 1) % TAM;
        }
        return clon;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "cola=" + Arrays.toString(cola) +
                ", frente=" + inicio +
                ", fin=" + fin +
                '}';
    }
}
