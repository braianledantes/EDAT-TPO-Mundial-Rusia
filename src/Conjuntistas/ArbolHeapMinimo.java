package Conjuntistas;

public class ArbolHeapMinimo<T> extends ArbolHeap<T> {

    public ArbolHeapMinimo(int cant) {
        super(cant);
    }

    @Override
    protected boolean funcionHeap(Comparable<T> c1, Comparable<T> c2) {
        return c1.compareTo((T) c2) > 0;
    }
}
