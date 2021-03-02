package repaso.estructuras.conjuntistas;

import repaso.estructuras.lineales.Lista;
import repaso.estructuras.lineales.ListaDinamica;

public class TablaHashCerrado<E> implements TablaHash<E> {
    private CeldaHash<E>[] tabla;
    private int cant;

    private static final int VACIO = 0;
    private static final int OCUPADO = 1;
    private static final int BORRADO = -1;

    public TablaHashCerrado(int cant) {
        this.tabla = new CeldaHash[cant];
        this.cant = 0;
        for (int i = 0; i < this.tabla.length; i++) {
            this.tabla[i] = new CeldaHash<>(null, VACIO);
        }
    }

    public TablaHashCerrado() {
        this(1000);
    }

    private int hash(E elem) {
        int hash = elem.hashCode();
        if (hash < 0) hash = -hash;
        return hash % this.tabla.length;
    }

    private int rehash(int hash) {
        return (hash + 1) % this.tabla.length;
    }

    @Override
    public boolean insertar(E elemento) {
        boolean exito = false;

        if (elemento != null && this.cant < this.tabla.length) {
            int pos = hash(elemento);
            boolean cont = true;
            CeldaHash<E> celda;
            while (cont) {
                celda = this.tabla[pos];
                if (celda.getEstado() == OCUPADO) {
                    if (celda.getElem().equals(elemento)) {
                        cont = false;
                    } else {
                        pos = rehash(pos);
                    }
                } else {
                    celda.setElem(elemento);
                    celda.setEstado(OCUPADO);
                    cant++;
                    cont = false;
                    exito = true;
                }
            }
        }

        return exito;
    }

    @Override
    public boolean eliminar(E elemento) {
        boolean exito = false;

        if (elemento != null) {
            int pos = hash(elemento);
            int intento = 1;
            while (!exito
                    && intento < this.tabla.length
                    && this.tabla[pos].getEstado() != VACIO) {

                if (this.tabla[pos].getEstado() == OCUPADO) {
                    if (this.tabla[pos].getElem().equals(elemento)) {
                        exito = true;
                        this.tabla[pos].setEstado(BORRADO);
                        this.cant--;
                    }
                }
                intento++;
                pos = rehash(pos);
            }
        }

        return exito;
    }

    @Override
    public boolean pertenece(E elemBuscado) {
        boolean encontrado = false;

        if (elemBuscado != null) {
            int pos = hash(elemBuscado);
            int intento = 1;
            while (!encontrado
                    && intento < this.tabla.length
                    && this.tabla[pos].getEstado() != VACIO) {

                if (this.tabla[pos].getEstado() == OCUPADO) {
                    encontrado = this.tabla[pos].getElem().equals(elemBuscado);
                }
                intento++;
                pos = rehash(pos);
            }
        }

        return encontrado;
    }

    @Override
    public boolean esVacia() {
        return cant == 0;
    }

    @Override
    public Lista<E> listar() {
        Lista<E> lista = new ListaDinamica<>();
        int i = 0;
        int cant = 0;
        while (cant < this.cant && i < tabla.length) {
            if (this.tabla[i].getEstado() == OCUPADO) {
                cant++;
                lista.insertar(this.tabla[i].getElem(), 1);
            }
            i++;
        }
        return lista;
    }

    @Override
    public void vaciar() {
        for (CeldaHash<E> celda : this.tabla) {
            celda.setEstado(VACIO);
        }
    }
}
