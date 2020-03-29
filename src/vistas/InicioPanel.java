package vistas;

import utilidades.ArchivosHelper;
import utilidades.DatosHelper;
import utilidades.Log;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class InicioPanel extends JPanel implements ActionListener {
    InicioFrame ventana;
    JButton btnABMCiudades;
    JButton btnABMEquipos;
    JButton btnAltaPartidos;
    JButton btnConsultarEquipos;
    JButton btnConsultarCiudades;
    JButton btnConsultarViajes;
    JButton btnImportar;

    public InicioPanel(InicioFrame ventana) throws HeadlessException {
        super(new GridLayout(1, 2));
        this.ventana = ventana;
        setBackground(Color.WHITE);
        JPanel panelBotones = new JPanel(new GridLayout(7, 1));
        btnABMCiudades = new JButton("ABM Ciudades");
        btnABMEquipos = new JButton("ABM Equipos");
        btnAltaPartidos = new JButton("Alta de Partidos");
        btnConsultarEquipos = new JButton("Consultar Equipos");
        btnConsultarCiudades = new JButton("Consultar Ciudades");
        btnConsultarViajes = new JButton("Consultar Viajes");
        btnImportar = new JButton("Importar Datos");
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

        btnABMCiudades.addActionListener(this);
        btnABMEquipos.addActionListener(this);
        btnAltaPartidos.addActionListener(this);
        btnConsultarEquipos.addActionListener(this);
        btnConsultarCiudades.addActionListener(this);
        btnConsultarViajes.addActionListener(this);
        btnImportar.addActionListener(this);
    }

    public void mostrarDialogoImportar() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            importarDatos(chooser.getSelectedFile());
        }
    }

    public void importarDatos(File file) {
        try {
            ArchivosHelper.getInstance().importarDatos(DatosHelper.getInstance(), file.getAbsolutePath());
            new Log(true).importacionDatos(DatosHelper.getInstance());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al importar archivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btnABMCiudades)) {
            ventana.irAABMCiudadesPanel();
        } else if (actionEvent.getSource().equals(btnImportar)) {
            mostrarDialogoImportar();
        }
    }
}
