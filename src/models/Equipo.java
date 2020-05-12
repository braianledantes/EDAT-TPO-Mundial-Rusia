package models;

import structures.lineales.Lista;
import structures.lineales.ListaDinamica;

import java.io.Serializable;

public class Equipo implements Comparable<Equipo>, Serializable {
    private String pais, directorTecnico;
    private char grupo;
    private int puntos, golesAFavor, golesEnContra;
    private Lista<Equipo> equiposJugados;
    private Lista<Partido> partidosJugados;

    public Equipo(String pais, String directorTecnico, char grupo, int puntos, int golesAFavor, int golesEnContra) {
        this.pais = pais;
        this.directorTecnico = directorTecnico;
        this.grupo = grupo;
        this.puntos = puntos;
        this.golesAFavor = golesAFavor;
        this.golesEnContra = golesEnContra;
        this.equiposJugados = new ListaDinamica<>();
        this.partidosJugados = new ListaDinamica<>();
    }

    public Equipo(String pais, String directorTecnico) {
        this.pais = pais;
        this.directorTecnico = directorTecnico;
        this.equiposJugados = new ListaDinamica<>();
        this.partidosJugados = new ListaDinamica<>();
    }

    public boolean agragarPartido(Partido partido) {
        boolean exito = false;
        if (partido != null && (this.equals(partido.getEquipoA()) || this.equals(partido.getEquipoB()))) {
            if (this == partido.getEquipoA()) {
                this.golesAFavor += partido.getGolesEquipoA();
                this.golesEnContra += partido.getGolesEquipoB();
            } else if (this == partido.getEquipoB()) {
                this.golesAFavor += partido.getGolesEquipoB();
                this.golesEnContra += partido.getGolesEquipoA();
            }
            exito = partidosJugados.insertar(partido);
        }
        return exito;
    }

    public Lista<Partido> getPartidosJugados() {
        return partidosJugados;
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

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    @Override
    public int compareTo(Equipo equipo) {
        return this.pais.compareTo(equipo.getPais());
    }

    @Override
    public String toString() {
        return pais + "{" +
                "DT='" + directorTecnico + '\'' +
                ", grupo=" + grupo +
                ", puntos=" + puntos +
                ", golesAFavor=" + golesAFavor +
                ", golesEnContra=" + golesEnContra +
                '}';
    }
}
