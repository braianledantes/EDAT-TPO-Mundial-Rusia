package utilidades;


import java.io.BufferedWriter;
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
            bufferedWriter.write("\n" + log);
            bufferedWriter.close();
            if (mostrarEnConsola)
                System.out.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inicioPrograma() {
        escribir("-----------------------------------------------------\n" +
                "Se inicio el programa el " + Calendar.getInstance().getTime());
    }

    public void cierrePrograma() {
        escribir("\nse cerro el programa a " + Calendar.getInstance().getTime());
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

    public void modificaEquipo(String pais, String directorTecnico, String grupo) {
        String log = "se modifico el equipo: " +
                pais + ", directorTecnico: " + directorTecnico + " grupo: " + grupo;
        escribir(log);
    }

    public void insertarRuta(String origen, String destino, String distancia) {
        escribir("se creo la ruta desde " + origen + " hasta " + destino + " en " + distancia + " KM");
    }

    public void eliminarRuta(String origen, String destino) {
        escribir("se elimino la ruta desde " + origen + " hasta " + destino);
    }

    public void altaDePartido(String equipoA, String equipoB, String ronda, String golesA, String golesB) {
        escribir("se creo el partido " + equipoA + "-" + equipoB +
                " en " + ronda + " con " + golesA + "/" + golesB);
    }
}
