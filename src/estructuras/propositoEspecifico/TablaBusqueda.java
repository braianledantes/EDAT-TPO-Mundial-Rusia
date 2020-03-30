package estructuras.propositoEspecifico;

import estructuras.conjuntistas.ArbolAVL;
import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;

import java.io.Serializable;

public class TablaBusqueda<K extends Comparable<K>, V> implements Diccionario<K, V>, Serializable {
    // TODO class TablaBusqueda hacer todos los metodos sin usar un arbol AVL como colaborador interno
    private ArbolAVL<Par<K, V>> arbolAVL;

    public TablaBusqueda() {
        arbolAVL = new ArbolAVL<>();
    }

    @Override
    public boolean insertar(K key, V value) {
        return arbolAVL.insertar(new Par<>(key, value));
    }

    @Override
    public boolean eliminar(K key) {
        return arbolAVL.eliminar(new Par<>(key));
    }

    @Override
    public boolean existeClave(K key) {
        return arbolAVL.pertenece(new Par<>(key));
    }

    @Override
    public V obtenerDato(K key) {
        V valor = null;
        Par<K, V> par = new Par<>(key);
        Lista<Par<K, V>> l = arbolAVL.listarRango(par, par);
        if (!l.estaVacia())
            valor = l.recuperar(1).getValor();
        return valor;
    }

    @Override
    public Lista<K> listarClaves() {
        // TODO listarClaves() hay que hacer todo pero esto es temporal
        Lista<K> claves = new ListaDinamica<>();
        Lista<Par<K, V>> pares = arbolAVL.listar();
        for (int i = 1; i <= pares.longitud(); i++) {
            claves.insertar(pares.recuperar(i).getKey());
        }
        return claves;
    }

    public Lista<V> listarRango(K keyMin, K keyMax) {
        // TODO listarRango() hay que hacer todo pero esto es temporal
        Lista<V> datos = new ListaDinamica<>();
        Lista<Par<K, V>> pares = arbolAVL.listarRango(new Par<>(keyMin), new Par<>(keyMax));
        for (int i = 1; i <= pares.longitud(); i++) {
            datos.insertar(pares.recuperar(i).getValor());
        }
        return datos;
    }

    @Override
    public Lista<V> listarDatos() {
        // TODO listarDatos() hay que hacer todo pero esto es temporal
        Lista<V> datos = new ListaDinamica<>();
        Lista<Par<K, V>> pares = arbolAVL.listar();
        for (int i = 1; i <= pares.longitud(); i++) {
            datos.insertar(pares.recuperar(i).getValor());
        }
        return datos;
    }

    @Override
    public boolean esVacio() {
        return arbolAVL.esVacio();
    }

    @Override
    public void vaciar() {
        arbolAVL.vaciar();
    }

    @Override
    public String toString() {
        return "TablaBusqueda{" +
                "arbolAVL=" + arbolAVL +
                '}';
    }

    private static class Par<K extends Comparable<K>, V> implements Comparable<Par<K, V>>, Serializable {
        private K key;
        private V valor;

        public Par(K key, V valor) {
            this.key = key;
            this.valor = valor;
        }

        public Par(K key) {
            this(key, null);
        }

        public K getKey() {
            return key;
        }

        public V getValor() {
            return valor;
        }

        @Override
        public int compareTo(Par<K, V> par) {
            return this.key.compareTo(par.key);
        }

        @Override
        public String toString() {
            return "(key=" + key +
                    ", valor=" + valor +
                    ')';
        }
    }
}
