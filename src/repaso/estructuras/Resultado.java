package repaso.estructuras;

import java.util.Objects;

public class Resultado<V> {
    private V valor;

    public Resultado(V valor) {
        this.valor = valor;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resultado<?> resultado = (Resultado<?>) o;
        return Objects.equals(valor, resultado.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
