package utilidades;

import estructuras.grafo.Arco;
import estructuras.lineales.Lista;
import modelos.Ciudad;
import modelos.Equipo;
import modelos.Partido;

import java.io.*;
import java.util.Collection;

public class FilesHelper {
    public static final String FILE_NAME = "datos.edat";

    private static FilesHelper instance;

    private FilesHelper() {
    }

    public static FilesHelper getInstance() {
        if (instance == null)
            instance = new FilesHelper();
        return instance;
    }

    public synchronized void importData(DataHelper dataHelper, String fileName) throws IOException {
        dataHelper.vaciar();
        FileReader file = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line;
        int pos = 1;
        while ((line = bufferedReader.readLine()) != null) {
            readData(dataHelper, line, pos);
            pos++;
        }
        bufferedReader.close();
        file.close();
    }

    private synchronized void readData(DataHelper dataHelper, String line, int pos) throws IOException {
        try {
            boolean exito;
            char tipo = line.charAt(0);
            String[] datos = line.substring(3).split(";");
            switch (tipo) {
                case 'E':
                    exito = dataHelper.altaEquipo(
                            datos[0].trim().toUpperCase(),
                            datos[1].trim().toUpperCase(),
                            datos[2].toUpperCase().trim(),
                            datos[3].trim().toUpperCase(),
                            datos[4].trim().toUpperCase(),
                            datos[5].trim().toUpperCase()
                    );
                    break;
                case 'P':
                    exito = dataHelper.altaDePartido(
                            datos[0].trim().toUpperCase(),
                            datos[1].trim().toUpperCase(),
                            datos[2].trim().toUpperCase(),
                            datos[3].trim().toUpperCase(),
                            datos[4].trim().toUpperCase(),
                            datos[5].trim().toUpperCase()
                    );
                    break;
                case 'C':
                    exito = dataHelper.altaCiudad(
                            datos[0].trim().toUpperCase(),
                            datos[1].trim().toUpperCase(),
                            datos[2].trim().toUpperCase(),
                            datos[3].trim().toUpperCase()
                    );
                    break;
                case 'R':
                    exito = dataHelper.insertarRuta(
                            datos[0].trim().toUpperCase(),
                            datos[1].trim().toUpperCase(),
                            datos[2].trim().toUpperCase()
                    );
                    break;
                default:
                    throw new IOException("Formato invalido de archivo de importacion:");
            }
            if (!exito)
                throw new IOException("Error al importar en linea " + pos);
        } catch (Exception e) {
            throw new IOException(e.getMessage() + "\n" + line);
        }
    }

    public synchronized void exportData(DataHelper dataHelper, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Lista<Ciudad> ciudades = dataHelper.listarCiudades();
        Lista<Arco<Ciudad, Integer>> rutas = dataHelper.listarRutas();
        Lista<Equipo> equipos = dataHelper.listarEquipos();
        Collection<Partido> partidos = dataHelper.getPartidos();

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= ciudades.longitud(); i++) {
            Ciudad c = ciudades.recuperar(i);
            sb.append("C: ").append(c.getNombre()).append("; ")
                    .append(c.getSuperfice()).append("; ")
                    .append(c.getCantHabitantes()).append("; ")
                    .append(c.isSede()).append('\n');
        }

        for (int i = 1; i <= equipos.longitud(); i++) {
            Equipo e = equipos.recuperar(i);
            sb.append("E: ").append(e.getPais()).append("; ")
                    .append(e.getDirectorTecnico()).append("; ")
                    .append(e.getGrupo()).append("; ")
                    .append(e.getGolesAFavor()).append("; ")
                    .append(e.getGolesEnContra()).append("; ")
                    .append(e.getGolesEnContra()).append('\n');
        }

        for (Partido p : partidos) {
            sb.append("P: ").append(p.getEquipoA().getPais()).append("; ")
                    .append(p.getEquipoB().getPais()).append("; ")
                    .append(p.getRonda()).append("; ")
                    .append(p.getGolesEquipoA()).append("; ")
                    .append(p.getGolesEquipoB()).append('\n');
        }

        for (int i = 1; i <= rutas.longitud(); i++) {
            Arco<Ciudad, Integer> r = rutas.recuperar(i);
            sb.append("R: ").append(r.getVerticeA().getNombre()).append("; ")
                    .append(r.getVerticeB().getNombre()).append("; ")
                    .append(r.getEtiqueta()).append('\n');
        }

        bufferedWriter.write(sb.toString());
        bufferedWriter.close();
    }

    public synchronized void saveData(DataHelper dataHelper) throws IOException {
        File file = new File(FILE_NAME);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(dataHelper);
        outputStream.close();
    }

    public synchronized DataHelper getData() throws IOException {
        DataHelper dataHelper = null;
        File file = new File(FILE_NAME);
        ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(file));
        try {
            dataHelper = (DataHelper) outputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        outputStream.close();
        return dataHelper;
    }
}
