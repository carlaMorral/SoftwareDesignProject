/*

Autors:
* Juan Cano Pradas
* Nil Ballus Riu
* David Rial Fígols

 */

package edu.ub.ubflix.view;

import edu.ub.ubflix.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Finestra amb la barra de reproducció d'un episodi. Aquesta classe hereta de JDialog
 */
class FormReproduccio extends JDialog {
    private JPanel jPanel;
    private JProgressBar progressBar;
    private Timer timer;
    private ActionListener actionListener;
    private int duracioVisualitzacio;
    private int duracioVisualitzada;
    private String serie;
    private int numTemporada;
    private String episodi;
    private static int idVisualitzacio = 6;
    private String loggedClient;
    private String loggedUser;


    /**
     * Constructor de la classe
     * @param serie identificador de la sèrie de l'episodi a reproduir
     * @param numTemporada número de temporada de l'episodi a reproduir
     * @param episodi títol de l'episodi a reproduir
     * @param duracioEpisodi duració de l'episodi a reproduir
     * @param duracioVisualitzada duració ja visualitzada anteriorment de l'episodi a reproduir
     */
    protected FormReproduccio(Frame owner, String serie, int numTemporada, String episodi, int duracioEpisodi, int duracioVisualitzada, String loggedClient, String loggedUser) {
        super(owner, true);
        this.duracioVisualitzacio = duracioEpisodi;
        this.duracioVisualitzada = duracioVisualitzada;
        this.serie = serie;
        this.numTemporada = numTemporada;
        this.episodi = episodi;
        this.loggedUser=loggedUser;
        this.loggedClient=loggedClient;
        initComponents();
        setResizable(false);
        setTitle("Reproducció");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de FormReproduccio i s'afegeixen els listeners dels events per quan es fa l'acció sobre els diferents components de Java.
     */
    private void initComponents() {
        add(jPanel);
        setModal(true);
        setMinimumSize(new Dimension(300,300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(WindowEvent evt) { formWindowClosing(evt, serie, numTemporada, episodi);
            }
        });
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
    }

    /**
     * Mètode que serveix per començar a reproduir l'episodi una vegada s'ha obert la finestra de reproducció
     * @param evt event que detecta quan s'obra la finestra
     */
    private void formWindowOpened(WindowEvent evt) {

        progressBar.setValue((duracioVisualitzada*100)/duracioVisualitzacio);
        int delay = duracioVisualitzacio/40;

        actionListener = e -> {
            if (progressBar.getValue() < 100)
                progressBar.setValue(progressBar.getValue() + 1);
            else {
                formWindowClosing(evt, serie, numTemporada, episodi);
            }
        };
        this.timer = new Timer(delay, actionListener);
        timer.start();
    }

    /**
     * Mètode que es crida quan es tanca la finestra de reproducció
     * @param evt event del mètode
     * @param serie identificador de la sèrie de l'episodi a reproduir
     * @param numTemporada número de temporada de l'episodi a reproduir
     * @param episodi títol de l'episodi a reproduir
     */
    private void formWindowClosing(WindowEvent evt, String serie, int numTemporada, String episodi) {

        timer.stop();

        int tempsVisualitzacio = (progressBar.getValue()*duracioVisualitzacio)/100;
        int segonsRestants = duracioVisualitzacio-tempsVisualitzacio-duracioVisualitzada;

        String estatVis;
        if(tempsVisualitzacio == duracioVisualitzacio) estatVis = "Watched";
        else estatVis = "Watching";


        Controller c = Controller.getInstance();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now();

        c.visualitzarEpisodi(idVisualitzacio, loggedClient, loggedUser, serie, episodi, estatVis, dtf.format(localDate), segonsRestants, numTemporada);
        idVisualitzacio++;
        String estat = "Episodi visualitzat";
        JOptionPane.showMessageDialog(jPanel, estat);

        dispose();

        FormUBFLIX owner = (FormUBFLIX) getOwner();
        if(estatVis.equals("Watched")){
            owner.refreshWatched();
            owner.refreshContinueWatching();
        }
        else owner.refreshContinueWatching();
        owner.refreshTopVisualitzacions();
    }

    public void setLoggedClient(String loggedClient) {
        this.loggedClient = loggedClient;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }
}
