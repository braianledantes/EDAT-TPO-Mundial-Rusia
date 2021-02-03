package estructuras.propositoEspecifico;

import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;

import java.io.Serializable;

public class TablaBusqueda<C extends Comparable<C>, D> implements Diccionario<C, D>, Serializable {
    private Nodo<C, D> raiz;

    @Override
    public boolean insertar(C clave, D dato) {
        boolean exito = true;
        if (dato != null) {
            if (this.raiz == null) {
                this.raiz = new Nodo<>(clave, dato);
            } else {
                exito = insertar(clave, dato, this.raiz, null);
            }
        }
        return exito;
    }

    private boolean insertar(C clave, D dato, Nodo<C, D> nodo, Nodo<C, D> nodoPadre) {
        boolean exito = true;

        if (clave.compareTo(nodo.getClave()) == 0) {
            exito = false;
        } else {
            if (clave.compareTo(nodo.getClave()) < 0) {
                if (nodo.tieneIzq())
                    exito = insertar(clave, dato, nodo.getIzq(), nodo);
                else
                    nodo.setIzq(new Nodo<>(clave, dato));
            } else {
                if (nodo.tieneDer())
                    exito = insertar(clave, dato, nodo.getDer(), nodo);
                else
                    nodo.setDer(new Nodo<>(clave, dato));
            }
            nodo.recalcularAltura();
            balancear(nodo, nodoPadre);
        }
        return exito;
    }

    private void balancear(Nodo<C, D> nodo, Nodo<C, D> nodoPadre) {
        int balance = calcularBalance(nodo);
        int balanceHijo;

        if (balance == -2) { // inclinado hacia la derecha
            balanceHijo = calcularBalance(nodo.getDer());
            if (balanceHijo == 0 || balanceHijo == -1) {
                rotarIzquierda(nodo);
            } else if (balanceHijo == 1) {
                rotarDerecha(nodo.getDer());
                rotarIzquierda(nodo);
            }
        } else if (balance == 2) { // inclinado hacia la izquierda
            balanceHijo = calcularBalance(nodo.getIzq());
            if (balanceHijo == 0 || balanceHijo == 1) {
                rotarDerecha(nodo);
            } else if (balanceHijo == -1) {
                rotarIzquierda(nodo.getIzq());
                rotarDerecha(nodo);
            }
        }
    }

    private int calcularBalance(Nodo<C, D> nodo) {
        int altIzq = -1, altDer = -1;
        if (nodo.tieneIzq())
            altIzq = nodo.getIzq().getAltura();
        if (nodo.tieneDer())
            altDer = nodo.getDer().getAltura();
        return altIzq - altDer;
    }

    private void rotarIzquierda(Nodo<C, D> nodoRaiz) {
        Nodo<C, D> nodoTemp = nodoRaiz.getDer();
        // intercambio elementos
        C claveTemp = nodoRaiz.getClave();
        D datoTemp = nodoRaiz.getDato();
        nodoRaiz.setClave(nodoTemp.getClave());
        nodoTemp.setClave(claveTemp);
        nodoRaiz.setDato(nodoTemp.getDato());
        nodoTemp.setDato(datoTemp);
        // le asigno a la raiz el hijo derecho del hijo derecho de la raiz
        nodoRaiz.setDer(nodoRaiz.getDer().getDer());
        // acomodo los enlaces al nodo temporal
        nodoTemp.setDer(nodoTemp.getIzq());
        nodoTemp.setIzq(nodoRaiz.getIzq());
        // cambio el hijo izquierdo de la raiz
        nodoRaiz.setIzq(nodoTemp);

        nodoTemp.recalcularAltura();
        nodoRaiz.recalcularAltura();
    }

    private void rotarDerecha(Nodo<C, D> nodoRaiz) {
        Nodo<C, D> nodoTemp = nodoRaiz.getIzq();
        // intercambio elementos
        C claveTemp = nodoRaiz.getClave();
        D datoTemp = nodoRaiz.getDato();
        nodoRaiz.setClave(nodoTemp.getClave());
        nodoTemp.setClave(claveTemp);
        nodoRaiz.setDato(nodoTemp.getDato());
        nodoTemp.setDato(datoTemp);
        // le asigno a la raiz el hijo izquiedo del hijo izquiedo de la raiz
        nodoRaiz.setIzq(nodoRaiz.getIzq().getIzq());
        // acomodo los enlaces al nodo temporal
        nodoTemp.setIzq(nodoTemp.getDer());
        nodoTemp.setDer(nodoRaiz.getDer());
        // cambio el hijo derecho de la raiz
        nodoRaiz.setDer(nodoTemp);

        nodoTemp.recalcularAltura();
        nodoRaiz.recalcularAltura();
    }

    @Override
    public boolean eliminar(C clave) {
        boolean exito = false;

        if (clave != null && this.raiz != null)
            exito = eliminar(clave, this.raiz, null);

        return exito;
    }

    private boolean eliminar(C clave, Nodo<C, D> nodo, Nodo<C, D> nodoPadre) {
        boolean exito = false;

        if (nodo != null) {
            if (clave.equals(nodo.getClave())) {
                if (nodo.tieneIzq() && nodo.tieneDer()) {
                    eliminarNodoConAmbosHijos(nodo, nodoPadre);
                } else if (nodo.tieneIzq() || nodo.tieneDer()) {
                    eliminarNodoConUnHijo(nodo, nodoPadre);
                } else {
                    eliminarNodoHoja(nodo, nodoPadre);
                }
                exito = true;
            } else {
                if (clave.compareTo(nodo.getClave()) < 0) {
                    exito = eliminar(clave, nodo.getIzq(), nodo);
                } else {
                    exito = eliminar(clave, nodo.getDer(), nodo);
                }
            }
        }
        if (exito) {
            nodo.recalcularAltura();
            balancear(nodo, nodoPadre);
        }

        return exito;
    }

    private void eliminarNodoHoja(Nodo<C, D> nodo, Nodo<C, D> nodoPadre) {
        if (nodoPadre == null) { // si es la raíz
            this.raiz = null;
        } else { // si es un nodo interno
            if (nodoPadre.getIzq() == nodo)
                nodoPadre.setIzq(null);
            else
                nodoPadre.setDer(null);
        }
    }

    private void eliminarNodoConUnHijo(Nodo<C, D> nodo, Nodo<C, D> nodoPadre) {
        Nodo<C, D> enlace;
        if (nodo.tieneIzq())
            enlace = nodo.getIzq();
        else
            enlace = nodo.getDer();

        if (nodoPadre == null) { // si es raíz
            raiz = enlace;
        } else { // si es un nodo interno
            if (nodo == nodoPadre.getIzq())
                nodoPadre.setIzq(enlace);
            else
                nodoPadre.setDer(enlace);
        }
    }

    private void eliminarNodoConAmbosHijos(Nodo<C, D> nodo, Nodo<C, D> nodoPadre) {
        Nodo<C, D> nodoCandidatoDer = buscarCandidatoMenor(nodo.getDer(), nodo);

        if (nodoPadre == null) { // si es raíz
            this.raiz.setClave(nodoCandidatoDer.getClave());
            this.raiz.setDato(nodoCandidatoDer.getDato());
        } else { // si es un nodo interno
            nodo.setClave(nodoCandidatoDer.getClave());
            nodo.setDato(nodoCandidatoDer.getDato());
        }
    }

    private Nodo<C, D> buscarCandidatoMenor(Nodo<C, D> nodo, Nodo<C, D> nodoPadre) {
        Nodo<C, D> nodoCandidato;

        if (!nodo.tieneIzq()) {
            nodoCandidato = nodo;
            if (nodoCandidato == nodoPadre.getDer()) { // si el candidato es el hijo inmediato al nodo a eliminar
                nodoPadre.setDer(nodoCandidato.getDer());
            } else {
                nodoPadre.setIzq(nodoCandidato.getDer());
            }
        } else {
            nodoCandidato = buscarCandidatoMenor(nodo.getIzq(), nodo);
        }

        return nodoCandidato;
    }

    @Override
    public boolean existeClave(C clave) {
        return obtenerNodo(clave, this.raiz) != null;
    }

    @Override
    public D obtenerDato(C clave) {
        return obtenerNodo(clave, this.raiz).getDato();
    }

    private Nodo<C, D> obtenerNodo(C clave, Nodo<C, D> nodo) {
        Nodo<C, D> nodo1 = null;

        if (clave != null && nodo != null) {
            if (clave.equals(nodo.getClave())) {
                nodo1 = nodo;
            } else {
                if (clave.compareTo(nodo.getClave()) < 0) {
                    nodo1 = obtenerNodo(clave, nodo.getIzq());
                } else {
                    nodo1 = obtenerNodo(clave, nodo.getDer());
                }
            }
        }

        return nodo1;
    }

    @Override
    public Lista<C> listarClaves() {
        Lista<C> lista = new ListaDinamica<>();
        listarClaves(lista, raiz);
        return lista;
    }

    private void listarClaves(Lista<C> lista, Nodo<C, D> nodo) {
        if (nodo != null) {
            listarClaves(lista, nodo.getIzq());
            lista.insertar(nodo.getClave());
            listarClaves(lista, nodo.getDer());
        }
    }

    @Override
    public Lista<D> listarDatosOrdenados() {
        Lista<D> lista = new ListaDinamica<>();
        listarDatos(lista, raiz);
        return lista;
    }

    private void listarDatos(Lista<D> lista, Nodo<C, D> nodo) {
        if (nodo != null) {
            listarDatos(lista, nodo.getIzq());
            lista.insertar(nodo.getDato());
            listarDatos(lista, nodo.getDer());
        }
    }

    public Lista<D> listarRango(C claveMin, C claveMax) {
        Lista<D> lista = new ListaDinamica<>();
        if (claveMin != null && claveMax != null)
            listarRango(lista, claveMin, claveMax, raiz);
        return lista;
    }

    private void listarRango(Lista<D> lista, C claveMin, C claveMax, Nodo<C, D> nodo) {
        if (nodo != null) {
            if (claveMin.compareTo(nodo.getClave()) < 0)
                listarRango(lista, claveMin, claveMax, nodo.getIzq());
            if (claveMin.compareTo(nodo.getClave()) <= 0 && claveMax.compareTo(nodo.getClave()) >= 0)
                lista.insertar(nodo.getDato());
            if (claveMax.compareTo(nodo.getClave()) > 0)
                listarRango(lista, claveMin, claveMax, nodo.getDer());
        }
    }

    @Override
    public boolean esVacio() {
        return this.raiz == null;
    }

    @Override
    public void vaciar() {
        this.raiz = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof TablaBusqueda)) return false;
        TablaBusqueda<C, D> that = (TablaBusqueda<C, D>) o;
        return equals(this.raiz, that.raiz);
    }

    private boolean equals(Nodo<C, D> nodothis, Nodo<C, D> nodoThat) {
        boolean equals = false;

        if (nodothis == null && nodoThat == null) {
            equals = true;
        } else if (nodothis != null && nodoThat != null) {
            if (nodothis.getClave().equals(nodoThat.getClave()) && nodothis.getDato().equals(nodoThat.getDato())) {
                equals = equals(nodothis.getIzq(), nodoThat.getIzq()) &&
                        equals(nodothis.getDer(), nodoThat.getDer());
            }
        }

        return equals;
    }

    protected Nodo<C, D> clone(Nodo<C, D> nodo) {
        Nodo<C, D> nodoNuevo = null;
        if (nodo != null) {
            nodoNuevo = new Nodo<>(nodo.getClave(), nodo.getDato(), clone(nodo.getIzq()), clone(nodo.getDer()));
        }
        return nodoNuevo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TablaBusqueda{ raiz=");
        if (raiz != null) {
            sb.append(raiz.getClave()).append(" -> ").append(raiz.getDato());
            //sb.append(raiz.getClave());
        } else {
            sb.append("null");
        }
        sb.append("\n");
        toString(sb, raiz);
        sb.append("}");
        return sb.toString();
    }

    private void toString(StringBuilder sb, Nodo<C, D> nodo) {
        if (nodo != null) {
            toString(sb, nodo.getIzq());

            sb.append('\t').append(nodo.getClave()).append(" -> ").append(nodo.getDato()).append(" // ");
            if (nodo.tieneIzq()) {
                sb.append(nodo.getIzq().getClave()).append("; ");
            } else {
                sb.append("null").append("; ");
            }
            if (nodo.tieneDer()) {
                sb.append(nodo.getDer().getClave());
            } else {
                sb.append("null");
            }
            sb.append("\n");

            toString(sb, nodo.getDer());
        }
    }

    private static class Nodo<C, D> implements Serializable {
        private C clave;
        private D dato;
        private int altura;
        private Nodo<C, D> izq, der;

        public Nodo(C clave, D dato, Nodo<C, D> izq, Nodo<C, D> der) {
            this.clave = clave;
            this.dato = dato;
            this.izq = izq;
            this.der = der;
            this.altura = 0;
        }

        public Nodo(C clave, D dato) {
            this.clave = clave;
            this.dato = dato;
            this.izq = this.der = null;
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

        public Nodo<C, D> getIzq() {
            return izq;
        }

        public void setIzq(Nodo<C, D> izq) {
            this.izq = izq;
        }

        public Nodo<C, D> getDer() {
            return der;
        }

        public void setDer(Nodo<C, D> der) {
            this.der = der;
        }

        public boolean tieneIzq() {
            return izq != null;
        }

        public boolean tieneDer() {
            return der != null;
        }

        public int getAltura() {
            return altura;
        }

        public void recalcularAltura() {
            int altIzq = -1, altDer = -1;
            if (izq != null) {
                altIzq = izq.getAltura();
            }
            if (der != null) {
                altDer = der.getAltura();
            }
            altura = Math.max(altIzq, altDer) + 1;
        }

        @Override
        public String toString() {
            return "Nodo{" +
                    "elem=" + dato +
                    ", altura=" + altura +
                    '}';
        }
    }
}
