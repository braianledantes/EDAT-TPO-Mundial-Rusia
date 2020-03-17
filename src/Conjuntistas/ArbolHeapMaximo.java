package Conjuntistas;

public class ArbolHeapMaximo<T> extends ArbolHeap<T> {

    public ArbolHeapMaximo(int cant) {
        super(cant);
    }

    @Override
    protected boolean funcionHeap(Comparable<T> c1, Comparable<T> c2) {
        return c1.compareTo((T) c2) < 0;
    }
}
