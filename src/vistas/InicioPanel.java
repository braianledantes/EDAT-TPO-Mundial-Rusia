package vistas;

import utilidades.ImportadorDatos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class InicioPanel extends JPanel {

    public InicioPanel() throws HeadlessException {
        super(new GridLayout(1, 2));
        setBackground(Color.WHITE);
        JPanel panelBotones = new JPanel(new GridLayout(7, 1));
        JButton btnABMCiudades = new JButton("ABM Ciudades");
        JButton btnABMEquipos = new JButton("ABM Equipos");
        JButton btnAltaPartidos = new JButton("Alta de Partidos");
        JButton btnConsultarEquipos = new JButton("Consultar Equipos");
        JButton btnConsultarCiudades = new JButton("Consultar Ciudades");
        JButton btnConsultarViajes = new JButton("Consultar Viajes");
        JButton btnImportar = new JButton("Importar Datos");
        panelBotones.add(btnABMCiudades);

        panelBotones.add(btnABMEquipos);
        panelBotones.add(btnAltaPartidos);
        panelBotones.add(btnConsultarEquipos);
        panelBotones.add(btnConsultarCiudades);
        panelBotones.add(btnConsultarViajes);
        panelBotones.add(btnImportar);
        add(panelBotones);
        try {
            add(new JLabel(new ImageIcon(ImageIO.read(new File("res/imgs/mundial-rusia.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBounds(new Rectangle(500, 400));


        btnImportar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarDialogoImportar();

            }
        });
    }

    public void mostrarDialogoImportar() {
        System.out.println("mostrarDialogoImportar()");
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            importarDatos(chooser.getSelectedFile());
        }
    }

    public void importarDatos(File file) {
        ImportadorDatos importadorDatos = new ImportadorDatos(file);
        importadorDatos.importarCiudades();
    }

}
