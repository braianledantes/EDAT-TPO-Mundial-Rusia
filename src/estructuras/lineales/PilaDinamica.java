package estructuras.lineales;

import java.io.Serializable;

/**
 * Implementación de una pila dinámica, esta formada por varios nodos enlazados entre sí, con el tope apuntando al
 * primer nodo de la pila. Las inserciones y eliminaciones se harán siempre sobre el tope de la pila.
 *
 * @param <T> tipo de dato de la pila
 */
public class PilaDinamica<T> implements Pila<T>, Serializable {
    private Nodo<T> tope;

    /**
     * Crea una pil dinamica vacia.
     */
    public PilaDinamica() {
        this.tope = null;
    }

    @Override
    public boolean apilar(T elem) {
        boolean exito = false;
        if (elem != null) {
            tope = new Nodo<>(elem, tope);
            exito = true;
        }
        return exito;
    }

    @Override
    public boolean desapilar() {
        boolean exito = false;
        if (tope != null) {
            tope = tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    @Override
    public T obtenerTope() {
        return (tope != null) ? tope.getElem() : null;
    }

    @Override
    public void vaciar() {
        tope = null;
    }

    @Override
    public boolean esVacia() {
        return tope == null;
    }

    @Override
    public boolean equals(Object obj) {
        PilaDinamica<?> that;
        Nodo<?> thisNodo, thatNodo;
        boolean equals = false;

        if (this != obj) {
            if (obj instanceof PilaDinamica) {
                that = (PilaDinamica<?>) obj;
                thisNodo = tope;
                thatNodo = that.tope;

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
    public PilaDinamica<T> clone() {
        PilaDinamica<T> clon = new PilaDinamica<>();
        Nodo<T> nodoThis = tope, nodoClon, enlaceClon;

        if (nodoThis != null) {
            nodoClon = new Nodo<>(nodoThis.getElem());
            clon.tope = nodoClon;

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
        StringBuilder sb = new StringBuilder(this.getClass().getName() + "{tope=");
        Nodo<T> nodo = tope;

        if (tope != null)
            sb.append(tope.getElem());
        else
            sb.append("null");

        sb.append(", elementos=[");
        while (nodo != null) {
            if (nodo != tope)
                sb.append("; ");
            sb.append(nodo.getElem().toString());
            nodo = nodo.getEnlace();
        }
        sb.append("]}");
        return sb.toString();
    }
}
