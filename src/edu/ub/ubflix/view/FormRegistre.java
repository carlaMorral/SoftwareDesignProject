package edu.ub.ubflix.view;

import edu.ub.ubflix.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Formulari de registre d'un usuari a la APP, es demana el nom i cognoms, el DNI, l'adreça, l'usuari i la contrassenya.
 * Aquesta classe hereta de JDialog
 */
class FormRegistre extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel labelNomReal;
    private JLabel labelAdreca;
    private JLabel labelDNI;
    private JLabel labelUsername;
    private JLabel nomUsuariAssociat;
    private JLabel labelPassword1;
    private JLabel labelPassword2;
    private JTextField textNomReal;
    private JTextField textDNI;
    private JTextField textAdreca;
    private JTextField textUsername;
    private JTextField textUsuariAssociat;
    private JPasswordField textPassword1;
    private JPasswordField textPassword2;
    public static int idUsuari = 11;

    /**
     * Constructor de la finestra del Registre on es fixa l'aspecte d'aquesta i s'inicialitzen els components
     */
    protected FormRegistre(JDialog owner, boolean modal) {
        super(owner, modal);
        setTitle("REGISTER NEW CLIENT");
        initComponents();
        setResizable(false);
    }

    /**
     * Mètode que inicialitza tots els components de la GUI del Registre d'usuaris i s'afegeixen els listeners dels events per quan es fa la acció sobre els components.
     */
    private void initComponents() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Registrar' per enregistrar un nou usuari si tots els camps són correctes.
     * En cas que salti una excepció es mostra per pantalla el missatge d'error corresponent.
     */
    private void onOK() {
        // add your code here
        try {
            String contrassenya1 = new String(textPassword1.getPassword());
            if (!contrassenya1.equals(new String(textPassword2.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Les contrassenyes no coincideixen!\nTorna a introduir les contrassenyes.", "ERROR AL REGISTRAR", JOptionPane.ERROR_MESSAGE);
                textPassword1.setText("");
                textPassword2.setText("");
            }
            else{
                Controller c = Controller.getInstance();
                String result = c.addClient(textUsername.getText(), new String(textPassword1.getPassword()), textNomReal.getText(), textDNI.getText(), textAdreca.getText(), false);
                String info = result.equals("Client created")? "Client afegit correctament" : result;
                JOptionPane.showMessageDialog(this, info, "INFORMACIÓ REGISTRE", JOptionPane.INFORMATION_MESSAGE);
                if (result.equals("Client created")) {
                    c.addUser(textUsername.getText(), textUsuariAssociat.getText(), idUsuari);
                    idUsuari++;
                    dispose();
                } else {
                    textUsername.setText("");
                    textPassword1.setText("");
                    textPassword2.setText("");
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR AL REGISTRAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Cancel' per tancar la finestra i tornar al formulari de LogIn, mostra un missatge de confirmació.
     */
    private void onCancel() {
        // add your code here if necessary
        if (JOptionPane.showConfirmDialog(this, "VOLS CANCELAR EL REGISTRE? ", "SORTIR REGISTRE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
            dispose();
    }
}
