package views;

import common.User;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class sessionsView extends basicView {
    public JButton loginBtn;   // Declare a Button component
    public JTextField usernameField;
    public JPasswordField passwordField;
    private JPanel mainWindow;
    private JPanel loginRow;
    private JPanel information;
    public JLabel notification;

    private JPanel navMenu = new views.navMenuView();

    // Constructor to setup GUI components and event handlers
    public sessionsView (HashMap<String, ActionListener> actionListeners) {
        this.setContentPane(mainWindow);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        loginBtn.addActionListener(actionListeners.get("loginAction"));

        this.setTitle("Login Example");
        this.pack();
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }

    public void setNotification(String message) {
        notification.setText(message);
    }

    public void cleanUp() {
        this.setVisible(false);
    }
}
