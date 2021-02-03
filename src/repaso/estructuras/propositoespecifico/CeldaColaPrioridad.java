package repaso.estructuras.propositoespecifico;

public class CeldaColaPrioridad<E> implements Comparable<CeldaColaPrioridad<E>> {

    private Integer prioridad;
    private Integer ordenLlegada;
    private E elemento;

    public CeldaColaPrioridad(E elemento, Integer prioridad, Integer ordenLlegada) {
        this.prioridad = prioridad;
        this.ordenLlegada = ordenLlegada;
        this.elemento = elemento;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getOrdenLlegada() {
        return ordenLlegada;
    }

    public void setOrdenLlegada(Integer ordenLlegada) {
        this.ordenLlegada = ordenLlegada;
    }

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    @Override
    public int compareTo(CeldaColaPrioridad<E> o) {
        int compPrioridad = this.prioridad.compareTo(o.prioridad);

        if (compPrioridad == 0)
            compPrioridad = this.ordenLlegada.compareTo(o.ordenLlegada);

        return compPrioridad;
    }
}
