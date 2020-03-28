package entidades;

import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;

public class Equipo implements Comparable<Equipo> {
    private String pais, directorTecnico;
    private int puntos, golesAFavor, golesEnContra;
    private Lista<Equipo> equiposJugados;

    public Equipo(String pais, String directorTecnico, int puntos, int golesAFavor, int golesEnContra) {
        this.pais = pais;
        this.directorTecnico = directorTecnico;
        this.puntos = puntos;
        this.golesAFavor = golesAFavor;
        this.golesEnContra = golesEnContra;
        this.equiposJugados = new ListaDinamica<>();
    }

    public Equipo(String pais, String directorTecnico) {
        this.pais = pais;
        this.directorTecnico = directorTecnico;
        this.equiposJugados = new ListaDinamica<>();
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDirectorTecnico() {
        return directorTecnico;
    }

    public void setDirectorTecnico(String directorTecnico) {
        this.directorTecnico = directorTecnico;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public int diferenciaGoles() {
        return golesAFavor - golesEnContra;
    }

    public boolean insertarEquipoJugado(Equipo equipo) {
        return equiposJugados.insertar(equipo);
    }

    public Lista<Equipo> getEquiposJugados() {
        return equiposJugados;
    }

    @Override
    public int compareTo(Equipo equipo) {
        return this.pais.compareTo(equipo.pais);
    }
}
