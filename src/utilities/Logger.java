package utilities;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Logger {
    public static final String FILE_NAME = "log.txt";
    private final boolean showOnConsole;

    public Logger(boolean showOnConsole) {
        this.showOnConsole = showOnConsole;
    }

    public void showStructures(DataHelper dataHelper) {
        if (showOnConsole)
            System.out.println(dataHelper);
    }

    public synchronized void write(String log) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bufferedWriter.write("\n- " + log);
            bufferedWriter.close();
            if (showOnConsole)
                System.out.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startProgram() {
        File file = new File(FILE_NAME);
        file.delete();
        write("-----------------------------------------------------\n" +
                "- Se inicio el programa el " + Calendar.getInstance().getTime());
    }

    public void closeProgram() {
        write("se cerro el programa el " + Calendar.getInstance().getTime());
    }

    public void importData(String fileName) {
        write("Se imporaron datos desde " + fileName);
    }

    public void saveData() {
        write("se guardo una instancia del sistema");
    }

    public void createCity(String name, String area, String population, String sede) {
        String log = "se crea la ciudad: " +
                name + ", superficie: " + area + " cantHabitantes: " + population + ", sede: " + sede;
        write(log);
    }

    public void deleteCity(String name) {
        String log = "se elimino la ciudad: " + name;
        write(log);
    }

    public void modifyCity(String name, String area, String cantHabitantes, String sede) {
        String log = "se modifico la ciudad " + name +
                " con: superficie: " + area + " cantHabitantes: " + cantHabitantes + ", sede: " + sede;
        write(log);
    }

    public void createTeam(String country, String dt, String group) {
        String log = "se crea el equipo: " +
                country + ", directorTecnico: " + dt + " grupo: " + group;
        write(log);
    }

    public void daleteTeam(String pais) {
        String log = "se elimino el equipo: " + pais;
        write(log);
    }

    public void modifyTeam(String nombre, String directorTecnico,
                           char grupo, int puntos, int golesAFavor, int golesEnContra) {
        String log = "se modifico el equipo: " + nombre +
                ", directorTecnico: " + directorTecnico +
                ", grupo: " + grupo + ", puntos: " + puntos +
                ", golesAFavor: " + golesAFavor +
                ", golesEnContra: " + golesEnContra;
        write(log);
    }

    public void altaDePartido(String equipoA, String equipoB, String ronda, String ciudad, String golesA, String golesB) {
        write("se creo el partido " + equipoA + "-" + equipoB +
                " en " + ciudad + " en la ronda " + ronda + " con " + golesA + "/" + golesB);
    }

    public void showTeam(String equipo) {
        write("Se mostro el equipo " + equipo);
    }

    public void mostrarEquiposPorRango(String equipoA, String equipoB) {
        write("Se mostro el rango de equipos de " + equipoA + " a " + equipoB);
    }

    public void mostrarEquiposConDifGol() {
        write("Se mostro los equipos con diferencia de gol negativa");
    }

    public void mostrarCiudad(String nombre) {
        write("Se mostro la ciudad " + nombre);
    }

    public void mostrarCaminoConMenorDistancia(String c1, String c2) {
        write("Se mostro el camino con menor distancia desde " + c1 + " hasta " + c2);
    }

    public void mostrarCaminoConMenosCiudades(String c1, String c2) {
        write("Se mostro el camino con menos ciudades desde " + c1 + " hasta " + c2);
    }

    public void mostrarCaminoPosibles(String c1, String c2) {
        write("Se mostraron todos los posibles caminos desde " + c1 + " hasta " + c2);
    }

    public void mostrarCaminoMasCortoEntreCiudad(String c1, String c2, String c3) {
        write("Se mostro el camino con menos ciudades desde " + c1 + " por " + c2 + " hasta " + c3);
    }

    public void mostrarTablaPosiciones() {
        write("Se mostro tabla de posiciones de los equipos");
    }

    public void escribirSistema(DataHelper dataHelper) {
        write(dataHelper.toString());
        write(dataHelper.obtenerTablaPosiciones());
    }
}
