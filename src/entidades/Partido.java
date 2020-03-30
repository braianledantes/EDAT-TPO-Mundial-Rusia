package entidades;

import java.io.Serializable;

public class Partido implements Serializable {
    private String key;
    private Equipo equipoA, equipoB;
    private Ronda ronda;
    private int golesEquipoA, golesEquipoB;

    public Partido(Equipo equipoA, Equipo equipoB, Ronda ronda) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.ronda = ronda;
        this.key = crearKey();
    }

    private String crearKey() {
        String clave = equipoA.getPais() + equipoB.getPais();
        if (equipoB.getPais().compareTo(equipoA.getPais()) < 0) {
            clave = equipoB.getPais() + equipoA.getPais();
        }
        return clave;
    }

    public Partido(Equipo equipoA, Equipo equipoB, Ronda ronda, int golesEquipoA, int golesEquipoB) {
        this(equipoA, equipoB, ronda);
        this.golesEquipoA = golesEquipoA;
        this.golesEquipoB = golesEquipoB;

        if (golesEquipoA == golesEquipoB) {
            equipoA.setPuntos(equipoA.getPuntos() + 1);
            equipoB.setPuntos(equipoB.getPuntos() + 1);
        } else if (golesEquipoA > golesEquipoB) {
            equipoA.setPuntos(equipoA.getPuntos() + 3);
        } else {
            equipoB.setPuntos(equipoB.getPuntos() + 3);
        }
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

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "(" + equipoA.getPais() + "/" + equipoB.getPais() + "){" +
                "ronda=" + ronda +
                ", golesEquipoA=" + golesEquipoA +
                ", golesEquipoB=" + golesEquipoB +
                '}';
    }
}
