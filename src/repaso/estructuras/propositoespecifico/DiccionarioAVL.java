package repaso.estructuras.propositoespecifico;

import repaso.estructuras.lineales.Lista;
import repaso.estructuras.lineales.ListaDinamica;
import repaso.estructuras.utliles.Resultado;

public class DiccionarioAVL<C extends Comparable<C>, D> implements Diccionario<C, D> {
    private NodoAVLDicc<C, D> raiz;

    public DiccionarioAVL() {
        this.raiz = null;
    }

    @Override
    public boolean insertar(C clave, D dato) {
        Resultado<Boolean> exito = new Resultado<>(false);
        if (clave != null && dato != null) {
            this.raiz = insertarAux(clave, dato, this.raiz, exito);
        }
        return exito.getValor();
    }

    private NodoAVLDicc<C, D> insertarAux(C clave, D dato, NodoAVLDicc<C, D> nodo, Resultado<Boolean> exito) {
        NodoAVLDicc<C, D> salida = nodo;
        if (nodo == null) {
            salida = new NodoAVLDicc<>(clave, dato);
            exito.setValor(true);
        } else if (clave.compareTo(nodo.getClave()) < 0) {
            salida = insertarAux(clave, dato, nodo.getHijoIzquierdo(), exito);
        } else if (clave.compareTo(nodo.getClave()) > 0) {
            salida = insertarAux(clave, dato, nodo.getHijoDerecho(), exito);
        }
        if (exito.getValor()) {
            salida.recalcularAltura();
            salida = balancear(salida);
        }
        return salida;
    }

    private NodoAVLDicc<C, D> balancear(NodoAVLDicc<C, D> nodo) {
        int balanceNodo = nodo.calcularBalance();
        int balanceHijo;
        if (balanceNodo == 2) {
            balanceHijo = nodo.getHijoIzquierdo().calcularBalance();
            if (balanceHijo == -1) {
                nodo.setHijoIzquierdo(rotarIzquierda(nodo.getHijoIzquierdo()));
            }
            nodo = rotarDerecha(nodo);
        } else if (balanceNodo == -2) {
            balanceHijo = nodo.getHijoDerecho().calcularBalance();
            if (balanceHijo == 1) {
                nodo.setHijoDerecho(rotarDerecha(nodo.getHijoDerecho()));
            }
            nodo = rotarIzquierda(nodo);
        }
        return nodo;
    }

    private NodoAVLDicc<C, D> rotarIzquierda(NodoAVLDicc<C, D> r) {
        NodoAVLDicc<C, D> h = r.getHijoDerecho();
        NodoAVLDicc<C, D> temp = h.getHijoIzquierdo();
        r.setHijoDerecho(temp);
        h.setHijoIzquierdo(r);

        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVLDicc<C, D> rotarDerecha(NodoAVLDicc<C, D> r) {
        NodoAVLDicc<C, D> h = r.getHijoIzquierdo();
        NodoAVLDicc<C, D> temp = h.getHijoDerecho();
        r.setHijoIzquierdo(temp);
        h.setHijoDerecho(r);

        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    @Override
    public boolean eliminar(C clave) {
        Resultado<Boolean> exito = new Resultado<>(false);
        if (clave != null) {
            this.raiz = eliminarAux(clave, this.raiz, exito);
        }
        return exito.getValor();
    }

    private NodoAVLDicc<C, D> eliminarAux(C clave, NodoAVLDicc<C, D> nodo, Resultado<Boolean> exito) {
        NodoAVLDicc<C, D> salida = nodo;
        if (nodo != null) {
            if (clave.compareTo(nodo.getClave()) < 0) {
                salida = eliminarAux(clave, nodo.getHijoIzquierdo(), exito);
            } else if (clave.compareTo(nodo.getClave()) > 0) {
                salida = eliminarAux(clave, nodo.getHijoDerecho(), exito);
            } else {
                if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) {
                    salida = null;
                } else if (nodo.getHijoIzquierdo() != null && nodo.getHijoDerecho() != null) {
                    salida = buscarCandidatoMenor(nodo);
                    salida.setHijoIzquierdo(nodo.getHijoIzquierdo());
                    salida.setHijoDerecho(nodo.getHijoDerecho());
                } else {
                    salida = (nodo.getHijoDerecho() != null) ? nodo.getHijoDerecho() : nodo.getHijoIzquierdo();
                }
                exito.setValor(true);
            }
        }
        if (exito.getValor() && salida != null) {
            salida.recalcularAltura();
            salida = balancear(salida);
        }
        return salida;
    }

    private NodoAVLDicc<C, D> buscarCandidatoMenor(NodoAVLDicc<C, D> nodo) {
        // precondicion: el nodo tiene ambos hijos
        Resultado<NodoAVLDicc<C, D>> nodoCandidato = new Resultado<>(null);
        nodo.setHijoDerecho(obtenerCandidatoMenor(nodo.getHijoDerecho(), nodoCandidato));
        return nodoCandidato.getValor();
    }

    private NodoAVLDicc<C, D> obtenerCandidatoMenor(NodoAVLDicc<C, D> nodo, Resultado<NodoAVLDicc<C, D>> nodoCandidato) {
        if (nodo.getHijoIzquierdo() == null) {
            nodoCandidato.setValor(nodo);
            nodo = nodo.getHijoDerecho(); // elimina el nodo candidato
        } else {
            nodo.setHijoIzquierdo(obtenerCandidatoMenor(nodo.getHijoIzquierdo(), nodoCandidato));
        }
        return nodo;
    }

    @Override
    public boolean existeClave(C clave) {
        boolean exito = false;
        if (clave != null) {
            exito = existeClaveAux(clave, this.raiz);
        }
        return exito;
    }

    private boolean existeClaveAux(C clave, NodoAVLDicc<C, D> nodo) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getClave().compareTo(clave) == 0) {
                exito = true;
            } else {
                if (clave.compareTo(nodo.getClave()) < 0) {
                    exito = existeClaveAux(clave, nodo.getHijoIzquierdo());
                } else {
                    exito = existeClaveAux(clave, nodo.getHijoDerecho());
                }
            }
        }
        return exito;
    }

    @Override
    public D obtenerInformacion(C clave) {
        D dato = null;
        if (clave != null) {
            dato = obtenerInformacionAux(clave, this.raiz);
        }
        return dato;
    }

    private D obtenerInformacionAux(C clave, NodoAVLDicc<C, D> nodo) {
        D dato = null;
        if (nodo != null) {
            if (nodo.getClave().compareTo(clave) == 0) {
                dato = nodo.getDato();
            } else {
                if (clave.compareTo(nodo.getClave()) < 0) {
                    dato = obtenerInformacionAux(clave, nodo.getHijoIzquierdo());
                } else {
                    dato = obtenerInformacionAux(clave, nodo.getHijoDerecho());
                }
            }
        }
        return dato;
    }

    @Override
    public Lista<C> listarClaves() {
        Lista<C> lista = new ListaDinamica<>();
        listarClavesAux(this.raiz, lista);
        return lista;
    }

    private void listarClavesAux(NodoAVLDicc<C, D> nodo, Lista<C> lista) {
        if (nodo != null) {
            listarClavesAux(nodo.getHijoIzquierdo(), lista);
            lista.insertar(nodo.getClave(), lista.longitud() + 1);
            listarClavesAux(nodo.getHijoDerecho(), lista);
        }
    }

    @Override
    public Lista<D> listarDatos() {
        Lista<D> lista = new ListaDinamica<>();
        listarDatosAux(this.raiz, lista);
        return lista;
    }

    private void listarDatosAux(NodoAVLDicc<C, D> nodo, Lista<D> lista) {
        if (nodo != null) {
            listarDatosAux(nodo.getHijoIzquierdo(), lista);
            lista.insertar(nodo.getDato(), lista.longitud() + 1);
            listarDatosAux(nodo.getHijoDerecho(), lista);
        }
    }

    @Override
    public boolean esVacio() {
        return this.raiz == null;
    }
}
