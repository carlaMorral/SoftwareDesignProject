package edu.ub.ubflix.view;


import edu.ub.ubflix.controller.Controller;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Formulari de valorar un episodi, es pot valorar segons emoció, marcar el dispositiu i/o escollir el personatge favorit. Aquesta classe hereta de JDialog
 */
class FormValoracio extends JDialog {
    private JPanel contentPane;
    private JButton btnValorar;
    private JButton buttonCancel;
    private JRadioButton polzeRadioButton;
    private JRadioButton emocioRadioButton;
    private JPanel panelEmocio;
    private JSlider barraEmocio;
    private JPanel panelPolze;
    private JButton btnMarcar;
    private JButton btnUP;
    private JButton btnDOWN;
    private JButton btnEscollir;
    //Afegit manualment
    private ButtonGroup buttonGroup;
    private static int idValoracio = 6;
    private String loggedClient;
    private String loggedUser;

    /**
     * Constructor de la classe FrmValoracio
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     */
    protected FormValoracio(Frame owner, String idSerie, int numTemporada, String episodi) {
        super(owner,true);
        initComponents(idSerie, numTemporada, episodi);
        setResizable(false);
        setTitle("Valoració de l'episodi");
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de FrmValoracio i s'afegeixen els listeners dels events per quan es fa l'acció sobre els diferents components de Java.
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     */
    private void initComponents(String idSerie, int numTemporada, String episodi) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnValorar);
        groupButton();
        inici();

        btnValorar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onValorar(idSerie, numTemporada, episodi);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        polzeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPolze.setVisible(true);
                panelEmocio.setVisible(false);
            }
        });
        emocioRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPolze.setVisible(false);
                panelEmocio.setVisible(true);
            }
        });

        btnUP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String estat = "Episodi valorat";
                JOptionPane.showMessageDialog(contentPane, estat);
                dispose();
                confirmacioContinuarValoracio(estat);
            }
        });

        btnDOWN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String estat = "Episodi valorat";
                JOptionPane.showMessageDialog(contentPane, estat);
                dispose();
                confirmacioContinuarValoracio(estat);
            }
        });
    }

    /**
     * Condicions inicials a l'entrar al formulari de valoració, per defecte es mostra valorar per Emoció
     */
    private void inici(){
        emocioRadioButton.setSelected(true);
        panelPolze.setVisible(false);
        panelEmocio.setVisible(true);
    }

    /**
     * Mètode que crea un grup de radioButtons per fer que només un es pugui seleccionar alhora
     */
    private void groupButton() {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(polzeRadioButton);
        buttonGroup.add(emocioRadioButton);

    }

    /**
     * Mètode que es crida quan es prem el botó Cancel que tanca la finestra
     */
    private void onCancel() {
        dispose();
    }

    /**
     * Mètode que serveix per guardar la Valoració per Emoció un cop s'ha valorat
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     */
    private void onValorar(String idSerie, int numTemporada, String episodi) {

        Controller c = Controller.getInstance();
        int estrelles = barraEmocio.getValue();

        String thumb = "";
        if(btnDOWN.isSelected()) thumb = "no";
        else if(btnUP.isSelected()) thumb = "yes";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now();

        c.valorarEpisodi(idValoracio, loggedClient, loggedUser, idSerie, episodi, estrelles, thumb, dtf.format(localDate), numTemporada);
        String estat = "Episodi valorat";
        JOptionPane.showMessageDialog(contentPane, estat);
        idValoracio++;

        FormUBFLIX owner = (FormUBFLIX)getOwner();
        owner.refreshTopValoracions();
        dispose();
        this.confirmacioContinuarValoracio(estat);
    }

    /**
     * Mètode que serveix per preguntar al client si està segur de voler acabar la valoració o vol continuar valorant.
     * @param estat resultat de la valoració feta
     */
    private void confirmacioContinuarValoracio(String estat) {
        JOptionPane.showMessageDialog(contentPane, estat);
        if (JOptionPane.showConfirmDialog(contentPane, "Vols acabar la valoració?") == 0)
            dispose();
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void setLoggedClient(String loggedClient) {
        this.loggedClient = loggedClient;
    }
}
