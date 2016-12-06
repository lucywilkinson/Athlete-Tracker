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

    JLabel idLabel = new JLabel("ID");
    public JTextField idField = new JTextField("", 5);
    JLabel firstNameLabel = new JLabel("First Name");
    public JTextField firstNameField = new JTextField("", 10);
    JLabel lastNameLabel = new JLabel("Last Name");
    public JTextField lastNameField = new JTextField("", 10);
    JLabel emailLabel = new JLabel("Email");
    public JTextField emailField = new JTextField("", 10);
    JLabel accountTypeLabel = new JLabel("Account Type");
    public JComboBox accountTypeField = new JComboBox();
    public JButton editProfileButton = new JButton("Edit Profile");
    public JButton saveProfileButton = new JButton("Save");

    public myProfileCard(User user, HashMap<String, ActionListener> actionListeners) {
        super(actionListeners); // adds nav bar

        constraints.weighty = 1;
        constraints.weightx = 1;

        // Left Panel
        constraints.gridx = 0;
        constraints.weightx = 0.75;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.insets = new Insets(0, 20, 20, 20);
        this.add(this.leftPanel, constraints);

        // Right Panel
        constraints.gridx = 1;
        constraints.weightx = 0.25;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0, 20, 20, 20);
        this.add(this.rightPanel, constraints);

        // Title
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0, 0, 0, 0);
        this.leftPanel.add(this.titleLabel, constraints);

        // ID
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        this.leftPanel.add(this.idLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.idField, constraints);
        this.idField.setEnabled(false);

        // Name
        constraints.gridy++;
        constraints.gridx = 0;
        this.leftPanel.add(this.firstNameLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.firstNameField, constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        this.leftPanel.add(this.lastNameLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.lastNameField, constraints);

        // Email
        constraints.gridy++;
        constraints.gridx = 0;
        this.leftPanel.add(this.emailLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.emailField, constraints);

        // Account Type
        constraints.gridy++;
        constraints.gridx = 0;
        this.leftPanel.add(this.accountTypeLabel, constraints);
        constraints.gridx = 1;
        this.leftPanel.add(this.accountTypeField, constraints);
        for(int i = 0; i < accountTypes.length; i++) {
            this.accountTypeField.addItem(accountTypes[i]);
        }
        this.accountTypeField.setEnabled(false);

        // Edit/Save Profile Buttons
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill= GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.gridx = 0;
        this.rightPanel.add(this.editProfileButton, constraints);
        constraints.gridx = 1;
        this.rightPanel.add(this.saveProfileButton, constraints);

        // add action listeners
        editProfileButton.addActionListener(actionListeners.get("editProfileAction"));
        saveProfileButton.addActionListener(actionListeners.get("saveProfileAction"));

        // editing disabled by default
        makeEditable(false);
        populate(user); //populates fields in myProfile
    }

    public void populate(User user) {
        idField.setText(Integer.toString(user.getUserId()));
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        accountTypeField.setSelectedItem(user.getUserType());
    }

    public void makeEditable(Boolean editable) {
        if(editable) {
            firstNameField.setEnabled(true);
            lastNameField.setEnabled(true);
            emailField.setEnabled(true);
        } else {
            firstNameField.setEnabled(false);
            lastNameField.setEnabled(false);
            emailField.setEnabled(false);
        }
    }
}
