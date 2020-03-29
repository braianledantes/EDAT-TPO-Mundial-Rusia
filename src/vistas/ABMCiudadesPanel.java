package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ABMCiudadesPanel extends JPanel {
    InicioFrame ventana;
    JPanel panelCentral, panelOpciones;

    public ABMCiudadesPanel(InicioFrame ventana) {
        this.ventana = ventana;
        setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        crearPanelCentral();
        crearPanelOpciones();
    }

    private void crearPanelCentral() {
        panelCentral = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Box box = Box.createVerticalBox();

        JLabel labelNombre = new JLabel("Nombre:");
        JTextField textNombre = new JTextField(50);
        textNombre.setMaximumSize(textNombre.getPreferredSize());
        JLabel labelSuperficie = new JLabel("Superficie:");
        JTextField textSuperficie = new JTextField(50);
        textSuperficie.setMaximumSize(textSuperficie.getPreferredSize());
        JLabel labelCantHab = new JLabel("Cant. habitantes:");
        JTextField textCantHab = new JTextField(50);
        textCantHab.setMaximumSize(textCantHab.getPreferredSize());

        box.add(labelNombre);
        box.add(textNombre);
        box.add(Box.createVerticalStrut(10));
        box.add(labelSuperficie);
        box.add(textSuperficie);
        box.add(Box.createVerticalStrut(10));
        box.add(labelCantHab);
        box.add(textCantHab);
        panelCentral.add(box);
        add(panelCentral, BorderLayout.CENTER);
    }

    private void crearPanelOpciones() {
        panelOpciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnBack = new JButton("Volver");
        btnBack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ventana.irAInicioPanel();
            }
        });
        JButton btnCrear = new JButton("Crear");

        btnCrear.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(ventana, "Crear ciudad");
            }
        });
        JButton btnEliminar = new JButton("Eliminar");

        btnEliminar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(ventana, "Eliminar ciudad");
            }
        });

        Box boxVertical = Box.createVerticalBox();
        boxVertical.add(Box.createVerticalStrut(5));
        boxVertical.add(btnBack);
        boxVertical.add(Box.createVerticalStrut(10));
        boxVertical.add(btnCrear);
        boxVertical.add(Box.createVerticalStrut(10));
        boxVertical.add(btnEliminar);
        panelOpciones.add(boxVertical);
        add(panelOpciones, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        InicioFrame frame = new InicioFrame();
        frame.setContentPane(new ABMCiudadesPanel(frame));
        frame.setVisible(true);
    }
}
