package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class sessionsCard extends card {
    JLabel titleLabel = new JLabel("Log In");
    public JButton loginBtn = new JButton("Submit");
    public JTextField usernameField = new JTextField("Dragon");
    public JPasswordField passwordField = new JPasswordField("Dragon-1");

    public sessionsCard(HashMap<String, ActionListener> actionListeners) {
        this.add(this.titleLabel);
        this.add(this.usernameField);
        this.add(this.passwordField);
        this.add(this.loginBtn);

        loginBtn.addActionListener(actionListeners.get("loginAction"));
    }
}
