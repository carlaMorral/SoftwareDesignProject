package edu.ub.ubflix.view;

import edu.ub.ubflix.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.Normalizer;

public class FormUser extends JDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldUsername;
    private JLabel usernameLabel;

    public FormUser(Frame owner, boolean modal) {
        super(owner, modal);
        setTitle("REGISTER NEW USER");
        initComponents();
        setResizable(false);
    }

    private void initComponents() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRegister();
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

        textFieldUsername.requestFocusInWindow();
    }

    private void onRegister() {
        FormUBFLIX owner = (FormUBFLIX)getOwner();
        Controller c = Controller.getInstance();
        String result = c.addUser(owner.getLoggedInClient(), textFieldUsername.getText(), FormRegistre.idUsuari);
        FormRegistre.idUsuari++;
        String info = result.equals("User created")? "Usuari registrat correctament" : result;
        JOptionPane.showMessageDialog(this, info, "INFO REGISTER USER", JOptionPane.INFORMATION_MESSAGE);
        if (result.equals("User created")) {
            owner.updateUsersList(true);
            dispose();
        }
    }

    private void onCancel() {
        dispose();
    }
}
