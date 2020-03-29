package utilidades;

import java.io.*;

public class ArchivosHelper {
    public static final String FILE_NAME = "datos.edat";

    static ArchivosHelper instance;

    private ArchivosHelper() {
    }

    public static ArchivosHelper getInstance() {
        if (instance == null)
            instance = new ArchivosHelper();
        return instance;
    }

    public synchronized void importarDatos(DatosHelper datosHelper, String fileName) throws IOException {
        FileReader file = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            leerDato(datosHelper, line);
        }
        bufferedReader.close();
        file.close();
    }

    private synchronized void leerDato(DatosHelper datosHelper, String line) throws IOException {
        try {
            char tipo = line.charAt(0);
            String[] datos = line.substring(3).split("; ");
            switch (tipo) {
                case 'E':
                    datosHelper.altaEquipo(datos[0].trim(), datos[1].trim(), datos[2].trim(), datos[3].trim(), datos[4].trim(), datos[5].trim());
                    break;
                case 'P':
                    datosHelper.altaDePartido(datos[0], datos[1], datos[2], datos[3], datos[4]);
                    break;
                case 'C':
                    datosHelper.altaCiudad(datos[0], datos[1], datos[2], datos[3]);
                    break;
                case 'R':
                    datosHelper.insertarRuta(datos[0], datos[1], datos[2]);
                    break;
                default:
                    throw new IOException("Formato invalido de archivo de importacion:");
            }
        } catch (Exception e) {
            throw new IOException(e.getMessage() + "\n" + line);
        }
    }

    public synchronized void exportarDatos(DatosHelper datosHelper, String fileName) throws IOException {
        // TODO
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String cadena = "E: ARGENTINA; SAMPAOLI; D; 0; 0; 0\n" +
                "P: ARGENTINA; ISLANDIA; GRUPO; 0; 0\n" +
                "C: MOSCU; 2511; 12500123; TRUE\n" +
                "C: TOLYATTI; 314,8; 719514; FALSE\n" +
                "R: MOSCU; TOLYATTI; 989";
        bufferedWriter.write(cadena);
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
