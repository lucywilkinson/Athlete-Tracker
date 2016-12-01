package views;

<<<<<<< HEAD
import common.User;

import java.awt.*;
=======
>>>>>>> master
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

<<<<<<< HEAD
public class sessionsView extends JFrame {
    private Button loginBtn;
    public TextField usernameField;
    public TextField passwordField;

    // Constructor to setup GUI components and event handlers
    public sessionsView (HashMap<String, ActionListener> actionListeners) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create username text field
        usernameField = new TextField("username", 10);
        usernameField.setEditable(true);
        add(usernameField);

        // Create password text field
        passwordField = new TextField("password", 10);
        passwordField.setEditable(true);
        add(passwordField);

        // Add Login button
        loginBtn = new Button("Login");
        loginBtn.addActionListener(actionListeners.get("loginAction"));
        add(loginBtn);

        setTitle("Login Example");
        setSize(250, 100);
        setVisible(true);
    }

    public void displayUserInformation(User user) {
        System.out.println("-------------------");
        System.out.println("       VIEW"        );
        System.out.println("-------------------");

        if (null == user) {
            System.out.println("-------------------");
            System.out.println("   LOGIN FAILED"    );
            System.out.println("-------------------");
            return;
        }

        System.out.println("-------------------");
        System.out.println("  LOGIN SUCCESSFUL ");
        System.out.println("-------------------");
        System.out.println("Username  : " + user.getUsername());
        System.out.println("Password  : " + user.getPassword());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name : " + user.getLastName());
        System.out.println("User Type : " + user.getUserType());
        System.out.println("User ID   : " + user.getUserId());
        System.out.println("-------------------");

        usernameField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
=======
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
>>>>>>> master
    }

    public void cleanUp() {
        this.setVisible(false);
    }
}
