package views;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class adminsCard extends card {
    // left panel elements
    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel statusFilterPanel = new JPanel(new GridBagLayout());
    JLabel statusFilterHeader = new JLabel("Filter By Status");
    JCheckBox activeCheckbox = new JCheckBox("Active", true);
    JCheckBox inactiveCheckbox = new JCheckBox("Inactive", true);

    // right panel elements
    JPanel rightPanel = new JPanel(new GridBagLayout());
    JPanel headerPanel = new JPanel(new GridBagLayout());
    JLabel titleLabel = new JLabel("Admins");
    JButton newAdminButton = new JButton("New Admin");
    JButton editButton = new JButton("Edit");
    public JTable dataTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(dataTable);

    // new user elements
    public JFrame newUserFrame = new JFrame("Create New Admin");
    Dimension newUserFrameDimensions = new Dimension(400, 400);
    JPanel newUserPanel = new JPanel(new GridBagLayout());
    JLabel newUserUsernameLabel = new JLabel("Username:", SwingConstants.RIGHT);
    public JTextField newUserUsernameField = new JTextField(20);
    JLabel newUserFirstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
    public JTextField newUserFirstNameField = new JTextField(20);
    JLabel newUserLastNameLabel = new JLabel("Last Name:", SwingConstants.RIGHT);
    public JTextField newUserLastNameField = new JTextField(20);
    JLabel newUserEmailLabel = new JLabel("Email:", SwingConstants.RIGHT);
    public JTextField newUserEmailField = new JTextField(20);
    JLabel newUserAccountTypeLabel = new JLabel("Account Type:", SwingConstants.RIGHT);
    JLabel newUserPasswordLabel = new JLabel("Password:", SwingConstants.RIGHT);
    public JPasswordField newUserPasswordField = new JPasswordField();
    JLabel newUserConfirmPasswordLabel = new JLabel("Confirm Password:", SwingConstants.RIGHT);
    public JPasswordField newUserConfirmPasswordField = new JPasswordField();
    public JComboBox newUserAccountTypeField = new JComboBox();
    JButton newUserSaveButton = new JButton("Save");

    // edit user elements
    public JFrame editUserFrame = new JFrame("Edit Admin");
    Dimension editUserFrameDimensions = new Dimension(400, 400);
    JPanel editUserPanel = new JPanel(new GridBagLayout());
    JLabel editUserIdLabel = new JLabel("ID:", SwingConstants.RIGHT);
    public JTextField editUserIdField = new JTextField(20);
    JLabel editUserUsernameLabel = new JLabel("Username:", SwingConstants.RIGHT);
    public JTextField editUserUsernameField = new JTextField(20);
    JLabel editUserFirstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
    public JTextField editUserFirstNameField = new JTextField(20);
    JLabel editUserLastNameLabel = new JLabel("Last Name:", SwingConstants.RIGHT);
    public JTextField editUserLastNameField = new JTextField(20);
    JLabel editUserEmailLabel = new JLabel("Email:", SwingConstants.RIGHT);
    public JTextField editUserEmailField = new JTextField(20);
    JLabel editUserPasswordLabel = new JLabel("Password:", SwingConstants.RIGHT);
    public JPasswordField editUserPasswordField = new JPasswordField();
    JLabel editUserAccountTypeLabel = new JLabel("Account Type:", SwingConstants.RIGHT);
    public JComboBox editUserAccountTypeField = new JComboBox();
    JButton editUserSaveButton = new JButton("Save");

    GridBagConstraints constraints = new GridBagConstraints();

    public adminsCard(DefaultTableModel tableData, HashMap<String, ActionListener> actionListeners) {
        super(actionListeners); // adds nav bar

        constraints.weighty = 1;
        constraints.weightx = 1;

        constraints.fill = GridBagConstraints.BOTH;

        this.buildLeftPanel();

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.25;
        constraints.insets = new Insets(20, 20, 20, 20);
        this.add(leftPanel, constraints);

        this.buildRightPanel();

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.75;
        constraints.insets = new Insets(20, 20, 20, 20);
        this.add(rightPanel, constraints);

        // add action listeners
        newAdminButton.addActionListener(actionListeners.get("newUserAction"));
        newUserSaveButton.addActionListener(actionListeners.get("saveNewUserAction"));
        editButton.addActionListener(actionListeners.get("editAdminAction"));
        editUserSaveButton.addActionListener(actionListeners.get("saveEditAdminAction"));

        editButton.setEnabled(false);
        editButton.addActionListener(actionListeners.get("editAthleteAction"));

        // Save button disabled until all fields are filled
        newUserSaveButton.setEnabled(false);

        // Add document listeners to each "New User Field".
        SaveButtonDocumentListener saveButtonEnabler = new SaveButtonDocumentListener();
        newUserFirstNameField.getDocument().addDocumentListener(saveButtonEnabler);
        newUserLastNameField.getDocument().addDocumentListener(saveButtonEnabler);
        newUserEmailField.getDocument().addDocumentListener(saveButtonEnabler);
        newUserPasswordField.getDocument().addDocumentListener(saveButtonEnabler);
        newUserConfirmPasswordField.getDocument().addDocumentListener(saveButtonEnabler);

        rightPanel.repaint();
        rightPanel.revalidate();

        rowSelectionListener selectionListener = new rowSelectionListener();
        dataTable.getSelectionModel().addListSelectionListener(selectionListener);
    }

    void buildLeftPanel() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 0;

        statusFilterPanel.add(statusFilterHeader, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        statusFilterPanel.add(activeCheckbox, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.insets = new Insets(0, 0, 10, 0);
        statusFilterPanel.add(inactiveCheckbox, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.insets = new Insets(0, 0, 0, 0);
        leftPanel.add(statusFilterPanel, constraints);
    }

    void buildRightPanel() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.weightx = 0.6;
        headerPanel.add(titleLabel, constraints);

        constraints.gridx++;
        constraints.weightx = 0.2;
        constraints.anchor = GridBagConstraints.NORTH;
        headerPanel.add(newAdminButton, constraints);

        constraints.gridx++;
        constraints.weightx = 0.2;
        headerPanel.add(editButton, constraints);

        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        rightPanel.add(headerPanel, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        rightPanel.add(scrollPane, constraints);
    }

    public void populate(DefaultTableModel data) {
        dataTable = new JTable(data);
    }

    public void launchEditUser() {
        editUserFrame.setPreferredSize(this.editUserFrameDimensions);
        this.buildEditUserFrame();
        editUserFrame.pack();
        editUserFrame.setVisible(true);
    }

    void buildEditUserFrame() {
        constraints.weightx = 0.5;
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        editUserPanel.add(editUserIdLabel, constraints);

        constraints.gridx++;
        editUserPanel.add(editUserIdField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editUserPanel.add(editUserUsernameLabel, constraints);

        constraints.gridx++;
        editUserPanel.add(editUserUsernameField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editUserPanel.add(editUserFirstNameLabel, constraints);

        constraints.gridx = 1;
        editUserPanel.add(editUserFirstNameField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editUserPanel.add(editUserLastNameLabel, constraints);

        constraints.gridx = 1;
        editUserPanel.add(editUserLastNameField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editUserPanel.add(editUserEmailLabel, constraints);

        constraints.gridx = 1;
        editUserPanel.add(editUserEmailField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editUserPanel.add(editUserPasswordLabel, constraints);

        constraints.gridx = 1;
        editUserPanel.add(editUserPasswordField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editUserPanel.add(editUserAccountTypeLabel, constraints);

        constraints.gridx = 1;
        editUserPanel.add(editUserAccountTypeField, constraints);
        for(int i = 0; i < accountTypes.length; i++) {
            editUserAccountTypeField.addItem(accountTypes[i]);
        }
        editUserAccountTypeField.setSelectedItem("admin");
        editUserAccountTypeField.setEnabled(false);

        constraints.gridy++;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        editUserPanel.add(editUserSaveButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10,10,10,10);
        editUserFrame.add(this.editUserPanel);
    }

    public void launchNewUser() {
        newUserFrame.setPreferredSize(this.newUserFrameDimensions);
        this.buildNewUserFrame();
        newUserFrame.pack();
        newUserFrame.setVisible(true);
    }

    void buildNewUserFrame() {
        constraints.weightx = 0.5;
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        newUserPanel.add(newUserUsernameLabel, constraints);

        constraints.gridx = 1;
        newUserPanel.add(newUserUsernameField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newUserPanel.add(newUserFirstNameLabel, constraints);

        constraints.gridx = 1;
        newUserPanel.add(newUserFirstNameField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newUserPanel.add(newUserLastNameLabel, constraints);

        constraints.gridx = 1;
        newUserPanel.add(newUserLastNameField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newUserPanel.add(newUserEmailLabel, constraints);

        constraints.gridx = 1;
        newUserPanel.add(newUserEmailField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newUserPanel.add(newUserPasswordLabel, constraints);

        constraints.gridx = 1;
        newUserPanel.add(newUserPasswordField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newUserPanel.add(newUserConfirmPasswordLabel, constraints);

        constraints.gridx = 1;
        newUserPanel.add(newUserConfirmPasswordField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newUserPanel.add(newUserAccountTypeLabel, constraints);

        constraints.gridx = 1;
        newUserPanel.add(newUserAccountTypeField, constraints);
        for(int i = 0; i < accountTypes.length; i++) {
            newUserAccountTypeField.addItem(accountTypes[i]);
        }
        newUserAccountTypeField.setSelectedItem("admin");
        newUserAccountTypeField.setEnabled(false);

        constraints.gridy++;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        newUserPanel.add(newUserSaveButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10,10,10,10);
        newUserFrame.add(this.newUserPanel);
    }

    /**
     * Checks that all fields are filled before enabling newUserSaveButton.
     */
    private void checkFields() {
        boolean email = !newUserEmailField.getText().trim().isEmpty();
        boolean firstName = !newUserFirstNameField.getText().trim().isEmpty();
        boolean lastName  = !newUserLastNameField.getText().trim().isEmpty();
        boolean username = !newUserUsernameField.getText().trim().isEmpty();
        boolean password  = (newUserPasswordField.getPassword().length != 0);
        boolean confirmPassword = (newUserConfirmPasswordField.getPassword().length != 0);


        if (email && firstName && lastName && username && password && confirmPassword) {
            newUserSaveButton.setEnabled(true);
        } else {
            newUserSaveButton.setEnabled(false);
        }
    }

    private class SaveButtonDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            checkFields();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkFields();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkFields();
        }
    }

    private class rowSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (dataTable.getSelectedRow() < 0){
                editButton.setEnabled(false);
            } else {
                editButton.setEnabled(true);
            }
        }
    }
}
