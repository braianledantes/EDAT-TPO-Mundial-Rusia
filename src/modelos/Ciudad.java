package modelos;

import java.io.Serializable;
import java.util.Objects;

public class Ciudad implements Serializable {
    private String nombre;
    private double superfice;
    private int cantHabitantes;
    private boolean sede;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad(String nombre, double superfice, int cantHabitantes, boolean sede) {
        this.nombre = nombre;
        this.superfice = superfice;
        this.cantHabitantes = cantHabitantes;
        this.sede = sede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSuperfice() {
        return superfice;
    }

    public void setSuperfice(double superfice) {
        this.superfice = superfice;
    }

    public int getCantHabitantes() {
        return cantHabitantes;
    }

    public void setCantHabitantes(int cantHabitantes) {
        this.cantHabitantes = cantHabitantes;
    }

    public boolean isSede() {
        return sede;
    }

    public void setSede(boolean sede) {
        this.sede = sede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return Objects.equals(nombre, ciudad.nombre);
    }

    @Override
    public String toString() {
        return nombre + "{" +
                "superfice=" + superfice +
                ", cantHabitantes=" + cantHabitantes +
                ", sede=" + sede +
                '}';
    }
}
