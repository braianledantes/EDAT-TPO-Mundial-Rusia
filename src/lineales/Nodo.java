package lineales;

/**
 * Este nodo es una celda en la que se almacenan dos cosas: un dato y un enlace a un nodo del mismo tipo
 * que él. El enlace es como un "hilo" que une un nodo con el siguiente. De esta manera se arma una estructura
 * con nodos enlazados de manera lineal.
 * @param <T> tipo de dato que almacena el nodo.
 */
class Nodo<T> {
    private T elem;
    private Nodo<T> enlace;

    public Nodo(T elem, Nodo<T> enlace) {
        this.elem = elem;
        this.enlace = enlace;
    }

    public Nodo(T elem) {
        this(elem, null);
    }

    public Nodo() {
        this(null, null);
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public Nodo<T> getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo<T> enlace) {
        this.enlace = enlace;
    }

    public boolean tieneEnlace() {
        return enlace != null;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "elem=" + elem +
                '}';
    }
}