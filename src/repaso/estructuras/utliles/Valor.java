package repaso.estructuras.utliles;

import java.util.Objects;

public class Valor<V> {
    private V valor;

    public Valor(V valor) {
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
        Valor<?> valor1 = (Valor<?>) o;
        return Objects.equals(valor, valor1.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
