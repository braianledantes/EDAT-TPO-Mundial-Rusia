package repaso.estructuras.propositoespecifico;

public class NodoAVLDicc<C extends Comparable<C>, D> {
    private C clave;
    private D dato;
    private int altura;
    private NodoAVLDicc<C, D> hijoIzquierdo, hijoDerecho;

    public NodoAVLDicc(C clave, D dato) {
        this.clave = clave;
        this.dato = dato;
    }

    public C getClave() {
        return clave;
    }

    public void setClave(C clave) {
        this.clave = clave;
    }

    public D getDato() {
        return dato;
    }

    public void setDato(D dato) {
        this.dato = dato;
    }

    public NodoAVLDicc<C, D> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoAVLDicc<C, D> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoAVLDicc<C, D> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoAVLDicc<C, D> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public void recalcularAltura() {
        int altIzq = -1;
        int altDer = -1;
        if (this.hijoIzquierdo != null)
            altIzq = this.hijoIzquierdo.altura;
        if (this.hijoDerecho != null)
            altDer = this.hijoDerecho.altura;
        this.altura = Math.max(altIzq, altDer) + 1;
    }

    public int calcularBalance() {
        int altIzq = -1, altDer = -1;
        if (this.hijoIzquierdo != null) {
            altIzq = this.hijoIzquierdo.altura;
        }
        if (this.hijoDerecho != null) {
            altDer = this.hijoDerecho.altura;
        }
        return altIzq - altDer;
    }
}
