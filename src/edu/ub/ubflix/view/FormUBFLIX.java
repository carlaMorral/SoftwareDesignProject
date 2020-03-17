package edu.ub.ubflix.view;

import edu.ub.ubflix.controller.Controller;
import edu.ub.ubflix.model.Episodi;
import edu.ub.ubflix.model.UBFLIX;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * GUI bàsica de l'app UBFLIX on es mostraran les diferents llistes corresponent a cada client que hagi realitzat el LogIn.
 * Aquesta classe hereta de JFrame i és la vista principal de l'aplicació.
 */
public class FormUBFLIX extends JFrame{

    private JPanel jPanel;
    private JTabbedPane llistes;
    private JPanel fieldAll;
    private JPanel fieldWatchNext;
    private JPanel fieldWatched;
    private JPanel fieldNotStarted;
    private JList<String> listAll;
    private JList<String> listMyList;
    private JList<String> listWatched;
    private JList<String> listContinueWatching;
    private JTable tableTopVisualitzacions;
    private JTable tableTopValoracions;
    private JLabel labelTopVisualitzacions;
    private JLabel labelTopValoracions;
    private JButton btnTancarSessio;
    private JButton btnCrearUsuari;
    private JButton btnAfegirMyList;
    private JComboBox<String> comboBoxUsuaris;

    private HashMap<String, JPopupMenu> popupMenuTemporades;
    private DefaultTableModel tableModelVis;
    private DefaultTableModel tableModelVal;

    private Controller controller;
    private String loggedInClient;
    private String loggedInUser;

    /**
     * Constructor de la classe FormUBFLIX que crida initComponents()
     */
    public FormUBFLIX() {
        super("UBFLIX");
        initComponents();
    }

    public void init() {
        this.controller = Controller.getInstance();
        setVisible(true);
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de l'app UBFLIX.
     * S'afegeixen els listeners dels events per quan es fa l'acció sobre els diferents components de Java.
     */
    private void initComponents(){
        add(jPanel);
        setSize(800,700);
        setMinimumSize(new Dimension(800,700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        btnTancarSessio.addActionListener(e -> ferLogIn());
        btnAfegirMyList.addActionListener(e -> {
            addToMyList(listAll.getSelectedValue());
            JOptionPane.showMessageDialog(jPanel, "Serie afegida a My List!");
        });
        btnCrearUsuari.addActionListener(e -> userActionPerformed());
        listAll.addListSelectionListener(e -> mostrarPopupMenuTemporades(listAll, fieldAll));
        listMyList.addListSelectionListener(e -> mostrarPopupMenuTemporades(listMyList, fieldWatchNext));
        listWatched.addListSelectionListener(e -> mostrarPopupMenuTemporades(listWatched, fieldWatched));
        listContinueWatching.addListSelectionListener(e -> mostrarPopupMenuTemporades(listContinueWatching, fieldNotStarted));
        comboBoxUsuaris.addActionListener(e -> {
            if (comboBoxUsuaris.getItemCount() > 0) {
                setLoggedInUser((String)comboBoxUsuaris.getSelectedItem());
                updateSeriesLists();
            }
        });
        popupMenuTemporades = new HashMap<>();
        inicialitzarLlistaTopVisualitzacions();
        inicialitzarLlistaTopValoracions();
    }

    /**
     * Mètode que serveix per inicialitzar el Top10 de sèries més visualitzades
     */
    private void inicialitzarLlistaTopVisualitzacions() {
        tableModelVis = new DefaultTableModel(new Object[][]{}, new Object[]{"nomSerie","visualitzacions"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTopVisualitzacions.setModel(tableModelVis);
        tableTopVisualitzacions.setShowGrid(false);
        tableTopVisualitzacions.setFocusable(false);
        tableTopVisualitzacions.setRowSelectionAllowed(false);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableTopVisualitzacions.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    }

    private void userActionPerformed() {
        FormUser dialog = new FormUser(this, true);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    /**
     * Mètode que serveix per inicialitzar el top10 de sèries més ben valorades
     */
    private void inicialitzarLlistaTopValoracions() {
        tableModelVal = new DefaultTableModel(new Object[][]{}, new Object[]{"nomSerie","valoracio"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTopValoracions.setModel(tableModelVal);
        tableTopValoracions.setFocusable(false);
        tableTopValoracions.setRowSelectionAllowed(false);
        tableTopValoracions.setShowGrid(false);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableTopValoracions.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    }

    /**
     * Mètode que serveix per mostrar les temporades disponibles d'una sèrie concreta
     * @param llista llista que conté la sèrie seleccionada
     * @param panel panel de la vista que conté les llistes
     */
    private void mostrarPopupMenuTemporades(JList llista, JPanel panel) {
        int index = llista.getSelectedIndex();
        Object element = llista.getSelectedValue();
        llista.removeSelectionInterval(llista.getLastVisibleIndex()+1, llista.getLastVisibleIndex()+1);
        if (popupMenuTemporades.get(llista.getSelectedValue()) != null)
            mostrarTemporades(element, index, panel);
    }

    /**
     * Mètode que mostra les diferents temporades de cada sèrie
     * @param serie sèrie de la qual s'han de mostrar les temporades
     * @param index índex de la sèrie seleccionada
     */
    private void mostrarTemporades(Object serie, int index, JPanel panel) {
        String s = (String) serie;
        JPopupMenu pm = popupMenuTemporades.get(s);
        pm.show(panel, panel.getWidth()/2, index*18);
    }

    /**
     * Mètode que fa visible el formulari de LogIn
     */
    private void ferLogIn() {
        jPanel.setVisible(false);
        llistes.setSelectedIndex(0);
        FormLogIn dialog = new FormLogIn(this, true);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        jPanel.setVisible(true);
    }

    /**
     * Mètode que es crida quan s'obre la finestra i crida a ferLogIn()
     * @param evt event que es dóna a l'obrir l'aplicació
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        refreshListAll();
        ferLogIn();
    }

    /**
     * Mètode que es crida quan es tanca la finestra, fet click sobre la 'x' amb missatge de confirmació de sortida.
     * @param evt event que es dóna al tancar la finestra
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "VOLS SORTIR? ", "SORTIR APP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
            System.exit(0);
    }

    /**
     * Mètode que actualitza les sèries del catàleg
     */
    private void refreshListAll() {
        List<String> series = controller.llistarCatalegSeries();
        listAll.setListData(new Vector<>(series));
        refreshTemporades(series);
    }

    /**
     * Mètode que serveix per actualitzar les temporades de les sèries passades per paràmetres
     * @param series sèries de les quals s'han d'actualitzar les temporades
     */
    private void refreshTemporades(List<String> series) {
        popupMenuTemporades.clear();
        for (String serie: series) {
            JPopupMenu s = new JPopupMenu();
            List<String> temps = controller.getTemporades(serie);
            String[] temporades = temps.toArray(new String[0]);

            for (String temporada : temporades) {
                JMenu temp = new JMenu(temporada);
                refreshEpisodis(serie, temporada, temp);
                s.add(temp);
            }
            popupMenuTemporades.put(serie, s);
        }
    }

    /**
     * Mètode que serveix per actualitzar els episodis de la temporada de la sèrie indicats per paràmetres
     * @param serie sèrie de la qual s'ha d'actualitzar els episodis
     * @param temporada temporadad de la qual s'ha d'actualitzar els episodis
     * @param jm JMenu de l'episodi
     */
    private void refreshEpisodis(String serie, String temporada, JMenu jm) {

        List<Episodi> eps = controller.getEpisodis(serie,temporada.split(" ")[1]);

        for (Episodi episodi : eps) {

            JMenuItem ep = new JMenuItem(episodi.getTitol());
            ep.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int numTemporada = Integer.parseInt(temporada.split(" ")[1]);
                    String duracio = episodi.getDuracio();
                    int duracioVisualitzada = controller.getDuracioVisualitzada(loggedInClient, loggedInUser, episodi);
                    String descripcio = controller.getDescripcioEpisodi(serie,numTemporada,episodi.getTitol());

                    onEpisodi(serie, numTemporada, episodi.getTitol(), duracio, duracioVisualitzada, descripcio);
                }
            });
            jm.add(ep);
        }
    }

    /**
     * Mètode que serveix per actualitzar totes les llistes de la vista
     */
    private void refreshLlistes() {
        refreshWatched();
        refreshMyList();
        refreshContinueWatching();
        refreshTopValoracions();
        refreshTopVisualitzacions();
    }

    /**
     * Mètode que actualitza les sèries de la llista MyList
     */
    private void addToMyList(String titolSerie) {
        controller.marcarSerieMyList(loggedInClient,loggedInUser,titolSerie);
        refreshMyList();
    }

    private void refreshMyList() {
        List<String> series = controller.listMyList(loggedInClient, loggedInUser);
        listMyList.setListData(new Vector<>(series));
    }

    /**
     * Mètode que actualitza les sèries de la llista Watched
     */
    public void refreshWatched() {
        List<String> series = controller.listWatchedList(loggedInClient, loggedInUser);
        listWatched.setListData(new Vector<>(series));
    }

    /**
     * Mètode que actualitza les sèries de la llista ContinueWatching
     */
    public void refreshContinueWatching() {
        List<String> series = controller.listContinueWatching(loggedInClient, loggedInUser);
        listContinueWatching.setListData(new Vector<>(series));
    }

    /**
     * Mètode que actualitza les sèries del top10 de sèries més ben valorades
     */
    public void refreshTopValoracions() {
        //Fem el clear de la llista del top 10 de valoracions
        int numRows = tableModelVal.getRowCount();
        for (int i = numRows - 1; i >= 0; i--)
            tableModelVal.removeRow(i);

        //com a molt es llistara el top 10, si n'hi ha, si no, es mostraran menys de 10
        List<String> topTen = controller.getTopTenVal(loggedInClient,loggedInUser);
        String [] topTenVal = topTen.toArray(new String[0]);
        for (String serie: topTenVal) {
            tableModelVal.addRow(new String[]{serie});
        }
    }

    /**
     * Mètode que actualitza les sèries del top10 de sèries més visualitzades
     */
    public void refreshTopVisualitzacions() {
        //Fem el clear de la llista del top 10 de visualitzacions
        int numRows = tableModelVis.getRowCount();
        for (int i = numRows - 1; i >= 0; i--)
            tableModelVis.removeRow(i);

        List<String> topTenVis = controller.getTopTenVis(loggedInClient,loggedInUser);
        String [] topTenVisualitzacions = topTenVis.toArray(new String[0]);
        for (String serie: topTenVisualitzacions) {
            tableModelVis.addRow(new Object[]{serie});
        }
    }

    /**
     * Mètode que serveix per obrir la finestra amb tota la informació i opcions disponibles d'un episodi seleccionat
     * @param idSerie identificador de la sèrie de l'episodi
     * @param temporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     * @param duracioVisualitzada duració ja visualitzada pel client de l'episodi seleccionat
     * @param descripcio descripció de l'episodi seleccionat
     */
    private void onEpisodi(String idSerie, int temporada, String episodi, String duracio, int duracioVisualitzada, String descripcio) {
        FormEpisodi dialog = new FormEpisodi(this, idSerie, temporada, episodi, duracio, duracioVisualitzada, descripcio,loggedInClient,loggedInUser);
        dialog.pack();
        dialog.setVisible(true);
    }

    public String getLoggedInClient() {
        return loggedInClient;
    }

    public void setLoggedInClient(String clientName) {
        this.loggedInClient = clientName;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String userName) {
        this.loggedInUser = userName;
    }

    public void updateUsersList(boolean isNewUser) {
        comboBoxUsuaris.removeAllItems();
        List<String> l = controller.listUsuaris(loggedInClient);
        for (String userName : l) {
            comboBoxUsuaris.addItem(userName);
        }
        if (isNewUser) {
            comboBoxUsuaris.setSelectedIndex(comboBoxUsuaris.getItemCount()-1);
        }
        setLoggedInUser((String)comboBoxUsuaris.getSelectedItem());
    }

    public void updateSeriesLists() {
        refreshLlistes();
    }

}
