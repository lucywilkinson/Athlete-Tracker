package views;

import common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class myProfileCard extends card {
    public JLabel titleLabel = new JLabel("My Profile");

    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel rightPanel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    public JLabel idLabel = new JLabel("ID");
    public JTextField idField = new JTextField("", 5);
    public JLabel firstNameLabel = new JLabel("First Name");
    public JTextField firstNameField = new JTextField("", 10);
    public JLabel lastNameLabel = new JLabel("Last Name");
    public JTextField lastNameField = new JTextField("", 10);
    public JLabel emailLabel = new JLabel("Email");
    public JTextField emailField = new JTextField("", 10);
    public JLabel accountTypeLabel = new JLabel("Account Type");
    public JComboBox accountTypeField = new JComboBox();
    public JLabel accountStatusLabel = new JLabel("Account Status");
    public JCheckBox accountStatusField = new JCheckBox("Enabled", true);
    public JButton viewMyShipmentsButton = new JButton("View My Shipments");
    public JButton editProfileButton = new JButton("Edit Profile");
    public JButton saveProfileButton = new JButton("Save");

    String[] accountTypes = {
            "admin",
            "athletes",
            "worker"
    };

    public myProfileCard(User user, HashMap<String, ActionListener> actionListeners) {
        super(actionListeners); // adds nav bar

        /*
            GridBagLayout Docs:
            https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
            https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html
         */

        // Left Panel
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(this.leftPanel, constraints);

        // Right Panel
        constraints.gridy = 2;
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        this.add(this.rightPanel, constraints);

        // Title
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        this.leftPanel.add(this.titleLabel, constraints);

        // ID
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        this.leftPanel.add(this.idLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.idField, constraints);

        // Name
        constraints.gridy = 2;
        constraints.gridx = 0;
        this.leftPanel.add(this.firstNameLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.firstNameField, constraints);
        constraints.gridy = 3;
        constraints.gridx = 0;
        this.leftPanel.add(this.lastNameLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.lastNameField, constraints);

        // Email
        constraints.gridy = 4;
        constraints.gridx = 0;
        this.leftPanel.add(this.emailLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.emailField, constraints);

        // Account Type
        constraints.gridy = 5;
        constraints.gridx = 0;
        this.leftPanel.add(this.accountTypeLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.accountTypeField, constraints);
        for(int i = 0; i < accountTypes.length; i++ ) {
            this.accountTypeField.addItem(accountTypes[i]);
        }

        // Account Status
        constraints.gridy = 6;
        constraints.gridx = 0;
        this.leftPanel.add(this.accountStatusLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.accountStatusField, constraints);

        // Edit/Save Profile Buttons
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill= GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.gridx = 0;
        this.rightPanel.add(this.viewMyShipmentsButton, constraints);
        constraints.gridy = 1;
        constraints.gridx = 0;
        this.rightPanel.add(this.editProfileButton, constraints);
        constraints.gridx = 1;
        this.rightPanel.add(this.saveProfileButton, constraints);

        this.populate(user);
    }

    public void populate(User user) {

    }
}
