package utilidades;

import java.io.File;

public class ImportadorDatos {
    File file;

    public ImportadorDatos(File file) {
        this.file = file;
    }

    public void importarCiudades() {
        System.out.println("importando ciudades");
    }
}
