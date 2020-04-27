package utilidades;

import entidades.Ciudad;
import entidades.Equipo;
import entidades.Partido;
import estructuras.grafo.Arco;
import estructuras.lineales.Lista;

import java.io.*;
import java.util.Collection;

public class ArchivosHelper {
    public static final String FILE_NAME = "datos.edat";

    private static ArchivosHelper instance;

    private ArchivosHelper() {
    }

    public static ArchivosHelper getInstance() {
        if (instance == null)
            instance = new ArchivosHelper();
        return instance;
    }

    public synchronized void importarDatos(DatosHelper datosHelper, String fileName) throws IOException {
        datosHelper.vaciar();
        FileReader file = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line;
        int pos = 1;
        while ((line = bufferedReader.readLine()) != null) {
            leerDato(datosHelper, line, pos);
            pos++;
        }
        bufferedReader.close();
        file.close();
    }

    private synchronized void leerDato(DatosHelper datosHelper, String line, int pos) throws IOException {
        try {
            boolean exito;
            char tipo = line.charAt(0);
            String[] datos = line.substring(3).split(";");
            switch (tipo) {
                case 'E':
                    exito = datosHelper.altaEquipo(datos[0].trim().toUpperCase(), datos[1].trim().toUpperCase(), datos[2].toUpperCase().trim(), datos[3].trim().toUpperCase(), datos[4].trim().toUpperCase(), datos[5].trim().toUpperCase());
                    break;
                case 'P':
                    exito = datosHelper.altaDePartido(datos[0].trim().toUpperCase(), datos[1].trim().toUpperCase(), datos[2].trim().toUpperCase(), datos[3].trim().toUpperCase(), datos[4].trim().toUpperCase(), datos[5].trim().toUpperCase());
                    break;
                case 'C':
                    exito = datosHelper.altaCiudad(datos[0].trim().toUpperCase(), datos[1].trim().toUpperCase(), datos[2].trim().toUpperCase(), datos[3].trim().toUpperCase());
                    break;
                case 'R':
                    exito = datosHelper.insertarRuta(datos[0].trim().toUpperCase(), datos[1].trim().toUpperCase(), datos[2].trim().toUpperCase());
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

    public synchronized void exportarDatos(DatosHelper datosHelper, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Lista<Ciudad> ciudades = datosHelper.listarCiudades();
        Lista<Arco<Ciudad, Integer>> rutas = datosHelper.listarRutas();
        Lista<Equipo> equipos = datosHelper.listarEquipos();
        Collection<Partido> partidos = datosHelper.getPartidos();

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

    public synchronized void guardarDatos(DatosHelper datosHelper) throws IOException {
        File file = new File(FILE_NAME);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(datosHelper);
        outputStream.close();
    }

    public synchronized DatosHelper obtenerDatos() throws IOException {
        DatosHelper datosHelper = null;
        File file = new File(FILE_NAME);
        ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(file));
        try {
            datosHelper = (DatosHelper) outputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        outputStream.close();
        return datosHelper;
    }

}
