import models.Ciudad;
import models.Equipo;
import models.Partido;
import structures.lineales.Lista;
import structures.propositoEspecifico.ColaPrioridad;
import utilities.DataHelper;
import utilities.FilesHelper;
import utilities.Logger;
import utilities.TecladoIn;

import java.io.IOException;

public class Main {
    public static DataHelper dataHelper;
    public static FilesHelper filesHelper;
    public static Logger logger;
    public static VistaTerminal vistaTerminal;

    public static void main(String[] args) {
        filesHelper = FilesHelper.getInstance();
        logger = new Logger(false);
        logger.startProgram();
        try {
            dataHelper = filesHelper.getData();
        } catch (IOException e) {
            dataHelper = DataHelper.getInstance();
        }
        vistaTerminal = new VistaTerminal(dataHelper, filesHelper, logger);
        logger.escribirSistema(dataHelper);
        vistaTerminal.iniciar();
        logger.escribirSistema(dataHelper);
        logger.closeProgram();
    }
}
