package edu.ub.ubflix.view;

import edu.ub.ubflix.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Formulari d'inici de sessió de la APP, es demana l'usuari i la contrassenya. Aquesta classe hereta de JDialog
 */
class FormLogIn extends JDialog {
    private JPanel contentPane;
    private JButton btnLogIn;
    private JButton buttonCancel;
    private JPasswordField textPassword;
    private JTextField textUsername;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JButton btnRegistrar;

    /**
     * Constructor de la finestra del LogIn on es fixa l'aspecte d'aquesta i s'inicialitzen els components
     */
    protected FormLogIn(Frame owner, boolean modal) {
        super(owner, modal);
        setTitle("LOG IN");
        initComponents();
        setResizable(false);
    }

    /**
     * Mètode que inicialitza tots els components de la GUI del LogIn i s'afegeixen els listeners dels events per quan es fa la acció sobre els botons.
     */
    private void initComponents() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnLogIn);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        btnLogIn.addActionListener(new ActionListener() {
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

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegistrar();
            }
        });
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Log In' per accedir al contingut en cas que l'usuari estigui registrat i la contrassenya sigui correcta.
     * En cas que salti una excepció es mostra per pantalla el missatge d'error corresponent.
     */
    private void onOK() {
        try {
            FormUBFLIX owner = (FormUBFLIX)getOwner();
            Controller c = Controller.getInstance();
            boolean valid = c.validateClient(textUsername.getText(), new String(textPassword.getPassword()));
            String info = valid? "Log-in correcte" : "Invalid username or password";
            JOptionPane.showMessageDialog(this, info, "INFO LOG-IN", JOptionPane.INFORMATION_MESSAGE);
            if (valid) {
                owner.setLoggedInClient(textUsername.getText());
                owner.updateUsersList(false);
                owner.updateSeriesLists();
                dispose();
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Cancel' per tancar la finestra i alhora sortir de l'APP amb missatge de confirmació.
     */
    private void onCancel() {
        // add your code here if necessary
        if (JOptionPane.showConfirmDialog(this, "VOLS SORTIR? ", "SORTIR APP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
            System.exit(0);
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Registrar' per obrir la finestra de registre d'usuari.
     */
    private void onRegistrar() {
        FormRegistre dialog = new FormRegistre(this, true);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
