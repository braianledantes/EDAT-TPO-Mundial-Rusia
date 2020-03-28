package entidades;

public class Partido {
    private Equipo equipoA, equipoB;
    private Ronda ronda;
    private int golesEquipoA, golesEquipoB;

    public Partido(Equipo equipoA, Equipo equipoB, Ronda ronda) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.ronda = ronda;
    }

    public Partido(Equipo equipoA, Equipo equipoB, Ronda ronda, int golesEquipoA, int golesEquipoB) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.ronda = ronda;
        this.golesEquipoA = golesEquipoA;
        this.golesEquipoB = golesEquipoB;
    }

    public Equipo getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(Equipo equipoA) {
        this.equipoA = equipoA;
    }

    public Equipo getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(Equipo equipoB) {
        this.equipoB = equipoB;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public int getGolesEquipoA() {
        return golesEquipoA;
    }

    public void setGolesEquipoA(int golesEquipoA) {
        this.golesEquipoA = golesEquipoA;
    }

    public int getGolesEquipoB() {
        return golesEquipoB;
    }

    public void setGolesEquipoB(int golesEquipoB) {
        this.golesEquipoB = golesEquipoB;
    }

    public class KeyPartido {

    }

    public class ValuePartido {

    }
}
