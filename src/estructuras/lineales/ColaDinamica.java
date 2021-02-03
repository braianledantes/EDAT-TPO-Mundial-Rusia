package estructuras.lineales;

import java.io.Serializable;

/**
 * Esta implementación de cola es dinamica, lo que quiere decir que se pueden almacenar infinitos elementos en ella.
 * Solo se puede acceder al elemento que en frente de la cola.
 *
 * @param <T>
 */
public class ColaDinamica<T> implements Cola<T>, Serializable {
    private Nodo<T> inicio, fin;

    /**
     * Crea una cola dinamica vacia.
     */
    public ColaDinamica() {
        this.inicio = null;
        this.fin = null;
    }

    @Override
    public boolean poner(T elem) {
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (elem != null) {
            if (inicio == null) {
                inicio = fin = nuevoNodo;
            } else {
                fin.setEnlace(nuevoNodo);
                fin = nuevoNodo;
            }
        }
        return true;
    }

    @Override
    public boolean sacar() {
        boolean exito = false;

        if (inicio != null) {
            if (inicio == fin)
                inicio = fin = null;
            else
                inicio = inicio.getEnlace();
            exito = true;
        }

        return exito;
    }

    @Override
    public T obtenerFrente() {
        return inicio != null ? inicio.getElem() : null;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void vaciar() {
        inicio = fin = null;
    }

    @Override
    public boolean equals(Object obj) {
        ColaDinamica<?> that;
        Nodo<?> thisNodo, thatNodo;
        boolean equals = false;

        if (this != obj) {
            if (obj instanceof ColaDinamica) {
                that = (ColaDinamica<?>) obj;
                thisNodo = this.inicio;
                thatNodo = that.inicio;

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
        } else {
            equals = true;
        }
        return equals;
    }

    @Override
    protected ColaDinamica<T> clone() {
        ColaDinamica<T> clon = new ColaDinamica<>();
        Nodo<T> nodoThis = inicio, nodoClon, enlaceClon;

        if (nodoThis != null) {
            nodoClon = new Nodo<>(nodoThis.getElem());
            clon.inicio = nodoClon;

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
        StringBuilder sb = new StringBuilder(this.getClass().getName() + "{" +
                "inicio=");

        if (inicio != null)
            sb.append(inicio.getElem());
        else
            sb.append("null");
        sb.append(", fin=");
        if (fin != null)
            sb.append(fin.getElem());
        else
            sb.append("null");

        sb.append(", [");
        Nodo<T> nodo = inicio;

        while (nodo != null) {
            if (nodo == inicio)
                sb.append(nodo.getElem());
            else
                sb.append("; ").append(nodo.getElem());
            nodo = nodo.getEnlace();
        }

        sb.append("]}");
        return sb.toString();
    }
}
