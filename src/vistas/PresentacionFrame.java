package vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PresentacionFrame extends JFrame {

    public PresentacionFrame() {
        ImageIcon image = new ImageIcon("res/imgs/mundial-rusia.png");
        setTitle("Mundial Rusia 2018");
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/imgs/mundial-rusia.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBounds(new Rectangle(400, 350));
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(image.getImage());
        setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void iniciar() {
        setVisible(true);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            InicioFrame inicioFrame = new InicioFrame();
            inicioFrame.iniciar();
            dispose();
        }
    }
}
