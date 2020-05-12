package models;

import java.io.Serializable;

public enum Ronda implements Serializable {
    GRUPO, OCTAVOS, CUARTOS, SEMIFINAL, FINAL;

    public static Ronda parseToRonda(String cad) {
        if (cad.equalsIgnoreCase("GRUPO"))
            return GRUPO;
        if (cad.equalsIgnoreCase("OCTAVOS"))
            return OCTAVOS;
        if (cad.equalsIgnoreCase("CUARTOS"))
            return CUARTOS;
        if (cad.equalsIgnoreCase("SEMIFINAL"))
            return SEMIFINAL;
        if (cad.equalsIgnoreCase("FINAL"))
            return FINAL;
        else throw new NumberFormatException(cad + " no es un grupo valido");
    }
}
