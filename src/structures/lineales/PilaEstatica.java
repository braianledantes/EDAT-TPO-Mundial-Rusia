package structures.lineales;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * En la pila estática se reserva un espacio fijo en memoria, siendo este la constante TAM, esta crece y decrece dentro del arreglo
 * previamente definido. Puede dar error de estructura llena si se intenta agregar más datos que la capacidad
 * disponible en el arreglo
 *
 * @param <T> tipo de dato que almacena la pila
 */
public class PilaEstatica<T> implements Pila<T>, Serializable {

    /**
     * Indica el tamaño fijo de la pila.
     */
    public static final int TAM = 5;
    private T[] pila;
    private int tope;
    private Class<T> clase;

    /**
     * Crea una pila estatica vacia.
     *
     * @param clase tipo de dato que almacena
     */
    public PilaEstatica(Class<T> clase) {
        this.tope = -1;
        pila = (T[]) Array.newInstance(clase, TAM);
        this.clase = clase;
    }

    @Override
    public boolean apilar(T elem) throws EstructuraLlenaException {
        boolean exito = false;

        if (elem != null) {
            if (tope < TAM - 1) {
                tope++;
                pila[tope] = elem;
                exito = true;
            } else {
                throw new EstructuraLlenaException();
            }
        }

        return exito;
    }

    @Override
    public boolean desapilar() {
        boolean exito = false;

        if (tope > -1) {
            tope--;
            exito = true;
        }

        return exito;
    }

    @Override
    public T obtenerTope() {
        T elem = null;

        if (tope > -1) {
            elem = pila[tope];
        }

        return elem;
    }

    @Override
    public void vaciar() {
        tope = -1;
    }

    @Override
    public boolean esVacia() {
        return tope == -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PilaEstatica<?> that = (PilaEstatica<?>) o;

        if (clase.equals(that.clase) &&
                tope == that.tope) {
            int i = 0;
            boolean iguales = true;
            while (i < tope && iguales) {
                iguales = this.pila[i].equals(that.pila[i]);
                i++;
            }
            return iguales;
        }
        return false;
    }

    @Override
    public PilaEstatica<T> clone() {
        PilaEstatica<T> clon = new PilaEstatica<>(clase);

        clon.tope = this.tope;
        if (this.tope >= 0)
            System.arraycopy(this.pila, 0, clon.pila, 0, TAM);

        return clon;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "pila=" + Arrays.toString(pila) +
                ", tope=" + tope +
                ", clase=" + clase +
                '}';
    }
}
