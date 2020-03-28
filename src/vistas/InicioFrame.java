package vistas;

import javax.swing.*;
import java.awt.*;

public class InicioFrame extends JFrame {


    public InicioFrame() {
        setTitle("Mundial Rusia 2018");
        setResizable(true);
        setContentPane(new InicioPanel());
        paintComponents(getGraphics());
        setBounds(new Rectangle(854, 480));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void iniciar() {
        setVisible(true);
    }

}
