package utilidades;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Log {
    public static final String FILE_NAME = "log.txt";
    private boolean mostrarEnConsola;

    public Log(boolean mostrarEnConsola) {
        this.mostrarEnConsola = mostrarEnConsola;
    }

    public void mostrarEstructuras(DatosHelper datosHelper) {
        if (mostrarEnConsola)
            System.out.println(datosHelper);
    }

    public synchronized void escribir(String log) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bufferedWriter.write("\n- " + log);
            bufferedWriter.close();
            if (mostrarEnConsola)
                System.out.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inicioPrograma() {
        File file = new File(FILE_NAME);
        file.delete();
        escribir("-----------------------------------------------------\n" +
                "- Se inicio el programa el " + Calendar.getInstance().getTime());
    }

    public void cierrePrograma() {
        escribir("se cerro el programa el " + Calendar.getInstance().getTime());
    }

    public void importacionDatos(DatosHelper dh) {
        escribir("Se imporaron los datos: \n" + dh.toString());
    }

    public void guardarDatos() {
        escribir("se guardo una instancia del sistema");
    }

    public void altaCiudad(String nombre, String superficie, String cantHabitantes, String sede) {
        String log = "se crea la ciudad: " +
                nombre + ", superficie: " + superficie + " cantHabitantes: " + cantHabitantes + ", sede: " + sede;
        escribir(log);
    }

    public void bajaCiudad(String nombre) {
        String log = "se elimino la ciudad: " + nombre;
        escribir(log);
    }

    public void modificaCiudad(String nombre, String superficie, String cantHabitantes, String sede) {
        String log = "se modifico la ciudad " + nombre +
                " con: superficie: " + superficie + " cantHabitantes: " + cantHabitantes + ", sede: " + sede;
        escribir(log);
    }

    public void altaEquipo(String pais, String directorTecnico, String grupo) {
        String log = "se crea el equipo: " +
                pais + ", directorTecnico: " + directorTecnico + " grupo: " + grupo;
        escribir(log);
    }

    public void bajaEquipo(String pais) {
        String log = "se elimino el equipo: " + pais;
        escribir(log);
    }

    public void modificaEquipo(String nombre, String directorTecnico, char grupo, int puntos, int golesAFavor, int golesEnContra) {
        String log = "se modifico el equipo: " +
                nombre + ", directorTecnico: " + directorTecnico + ", grupo: " + grupo + ", puntos: " + puntos + ", golesAFavor: " + golesAFavor + ", golesEnContra: " + golesEnContra;
        escribir(log);
    }

    public void altaDePartido(String equipoA, String equipoB, String ronda, String golesA, String golesB) {
        escribir("se creo el partido " + equipoA + "-" + equipoB +
                " en " + ronda + " con " + golesA + "/" + golesB);
    }

    public void escribirSistema(DatosHelper datosHelper) {
        escribir(datosHelper.toString());
        escribir(datosHelper.obtenerTablaPosiciones());
    }
}
