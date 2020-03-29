package vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InicioFrame extends JFrame {
    private static final String INICIO_PANEL = "INICIO_PANEL";
    private static final String ABM_CIUDADES = "ABM_CIUDADES";
    private static final String ABM_EQUIPOS = "ABM_EQUIPOS";
    private static final String CONSULTAR_CIUDADES = "CONSULTAR_CIUDADES";
    private static final String CONSULTAR_EQUIPOS = "CONSULTAR_EQUIPOS";
    private static final String CONSULTAR_VIAJES = "CONSULTAR_VIAJES";

    private JPanel cards;

    public InicioFrame() {
        //  Log log = new Log();
        //log.inicioPrograma();
        setTitle("Mundial Rusia 2018");
        setResizable(true);
        setBounds(new Rectangle(854, 480));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cards = new JPanel(new CardLayout());
        cards.add(new InicioPanel(this), INICIO_PANEL);
        cards.add(new ABMCiudadesPanel(this), ABM_CIUDADES);
        cards.add(new ABMEquiposPanel(this), ABM_EQUIPOS);
        cards.add(new ConsultarCiudadesPanel(this), CONSULTAR_CIUDADES);
        cards.add(new ConsultarEquiposPanel(this), CONSULTAR_EQUIPOS);
        cards.add(new ConsultarViajesPanel(this), CONSULTAR_VIAJES);

        setContentPane(cards);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                //log.cierrePrograma();
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });
    }

    public void iniciar() {
        irAInicioPanel();
        setVisible(true);
    }

    public void irAInicioPanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, INICIO_PANEL);
    }

    public void irAABMEquiposPanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, ABM_EQUIPOS);
    }

    public void irAABMCiudadesPanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, ABM_CIUDADES);
    }

    public void irAconsultarCiudadesPanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, CONSULTAR_CIUDADES);
    }

    public void irAconsultarEquiposPanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, CONSULTAR_EQUIPOS);
    }

    public void irAconsultarViajesPanel() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, CONSULTAR_VIAJES);
    }
}
