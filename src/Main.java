import utilidades.DataHelper;
import utilidades.FilesHelper;
import utilidades.Logger;

import javax.swing.*;
import java.awt.*;
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
