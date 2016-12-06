package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class sessionsCard extends card {
    JPanel centerPanel = new JPanel(new GridBagLayout());
    public JButton loginBtn = new JButton("Log In");
    JLabel usernameLabel = new JLabel("Username:", SwingConstants.RIGHT);
    public JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);
    public JPasswordField passwordField = new JPasswordField();

    GridBagConstraints constraints = new GridBagConstraints();

    public sessionsCard(HashMap<String, ActionListener> actionListeners) {
        this.setLayout(new GridBagLayout());

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.insets = new Insets(5,5,5,5);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.weightx = 0.5;
        centerPanel.add(this.usernameLabel, constraints);
        constraints.gridx++;
        constraints.weightx = 0.5;
        centerPanel.add(this.usernameField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.weightx = 0.5;
        centerPanel.add(this.passwordLabel, constraints);
        constraints.gridx++;
        constraints.weightx = 0.5;
        centerPanel.add(this.passwordField, constraints);

        constraints.gridy++;
        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        centerPanel.add(this.loginBtn, constraints);

        loginBtn.addActionListener(actionListeners.get("loginAction"));

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 200, 0, 200);
        this.add(centerPanel, constraints);
    }
}
