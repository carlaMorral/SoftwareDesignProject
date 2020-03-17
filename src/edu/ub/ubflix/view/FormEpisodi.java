/*

Autors:
* Juan Cano Pradas
* Nil Ballus Riu
* David Rial Fígols

 */

package edu.ub.ubflix.view;

import edu.ub.ubflix.controller.Controller;
import edu.ub.ubflix.model.Serie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Formulari amb el detall d'un episodi, permet visualitzar, subscriure's i valorar un episodi. Aquesta classe hereta de JDialog
 */
class FormEpisodi extends JDialog{
    private JButton valorarButton;
    private JButton visualitzarButton;
    private JLabel titolEpisodi;
    private JLabel descripcioEpisodi;
    private JButton tornarAlMenuButton;
    private JPanel jpanel;
    private JLabel duracioEpisodi;
    private String loggedClient;
    private String loggedUser;


    /**
     * Constructor de la classe FormEpisodi que crida initComponents()
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     * @param descripcio descripció de l'episodi seleccionat
     * @param loggedInClient id del client loguejat
     * @param loggedInUser id de l'usuari loguejat
     */
    protected FormEpisodi(Frame owner, String idSerie, int numTemporada, String episodi, String duracio, int duracioVisualitzada,
                          String descripcio, String loggedInClient, String loggedInUser) {
        super(owner, true);
        this.loggedClient = loggedInClient;
        this.loggedUser = loggedInUser;
        initComponents(idSerie, numTemporada, episodi, duracio, descripcio, duracioVisualitzada);
        setResizable(false);
        setTitle("Detall de l'episodi");
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de FormEpisodi i s'afegeixen els listeners dels events per quan es fa l'acció sobre els diferents components de Java.
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param titol títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     * @param descripcio descripció de l'episodi seleccionat
     */
    private void initComponents(String idSerie, int numTemporada, String titol, String duracio, String descripcio, int duracioVisualitzada) {

        add(jpanel);
        setModal(true);
        setSize(500, 400);
        setMinimumSize(new Dimension(500, 400));
        setMaximumSize(new Dimension(600, 400));

        titolEpisodi.setText("<html><u> Títol de l'episodi:</u> " + " "+ "<html></u> " + titol);
        duracioEpisodi.setText("<html><u> Duració:</u> " + duracioVisualitzada + " segons");
        descripcioEpisodi.setText("<html><body style=' width: 300 px'>"+"<html><u> Descripció:</u> " + descripcio);
        valorarButton.setEnabled(estaValorat(idSerie, numTemporada, titol));
        tornarAlMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        visualitzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onVisualitzar(idSerie, numTemporada, titol, duracio, duracioVisualitzada);
            }
        });

        valorarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormUBFLIX owner = (FormUBFLIX)getOwner();
                FormValoracio dialog = new FormValoracio(owner, idSerie, numTemporada, titol);
                dialog.setLoggedClient(loggedClient);
                dialog.setLoggedUser(loggedUser);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    /**
     * Mètode que serveix per instanciar la finestra de visualització d'un episodi
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param titol títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     */
    private void onVisualitzar(String idSerie, int numTemporada, String titol, String duracio, int duracioVisualitzada) {

        Controller c = Controller.getInstance();

        int duracio_en_segons = (Integer.parseInt(duracio.split(":")[0])*60) + Integer.parseInt(duracio.split(":")[1]);
        if(duracio_en_segons == duracioVisualitzada) duracioVisualitzada=0;

        FormUBFLIX owner = (FormUBFLIX)getOwner();
        FormReproduccio fr = new FormReproduccio(owner, idSerie, numTemporada, titol, duracio_en_segons, duracioVisualitzada,loggedClient,loggedUser);
        fr.pack();
        fr.setVisible(true);
        valorarButton.setEnabled(estaValorat(idSerie, numTemporada, titol));

    }

    /**
     * Mètode que serveix per saber si el client ha valorat l'episodi.
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param titol títol de l'episodi seleccionat
     * @return True si el client hi està subscrit. False en cas contrari.
     */
    private boolean estaValorat(String idSerie, int numTemporada, String titol) {
        Controller c = Controller.getInstance();
        return !(c.isEpisodiValorat(idSerie,numTemporada,titol,this.loggedClient,this.loggedUser));
    }

    public void setLoggedClient(String loggedClient) {
        this.loggedClient = loggedClient;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }
}
