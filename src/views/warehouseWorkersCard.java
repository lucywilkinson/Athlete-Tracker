package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class warehouseWorkersCard extends card {
    // dummy data
    String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};

    Object[][] data = {
            {"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)}
    };

    // left panel elements
    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel statusFilterPanel = new JPanel(new GridBagLayout());
    JLabel statusFilterHeader = new JLabel("Filter By Status");
    JCheckBox activeCheckbox = new JCheckBox("Active", true);
    JCheckBox inactiveCheckbox = new JCheckBox("Inactive", true);
    JPanel dateFilterPanel = new JPanel(new GridBagLayout());
    JLabel dateFilterHeader = new JLabel("Filter By Date");
    JTextField dateStartField = new JTextField("12/1/2012");
    JTextField dateEndField = new JTextField("12/31/2016");

    // right panel elements
    JPanel rightPanel = new JPanel(new GridBagLayout());
    JPanel headerPanel = new JPanel(new GridBagLayout());
    JLabel titleLabel = new JLabel("Warehouse Workers");
    JButton newWorkerButton = new JButton("New Worker");
    JButton saveChangesButton = new JButton("Save Changes");
    JTable dataTable = new JTable(data, columnNames);
    JPanel editDataPanel = new JPanel(new GridBagLayout());

    // new user elements
    JFrame newUserFrame = new JFrame("Create New Warehouse Worker");
    Dimension newUserFrameDimensions = new Dimension(400, 400);
    JPanel newUserPanel = new JPanel(new GridBagLayout());
    JLabel newUserUsernameLabel = new JLabel("Username:", SwingConstants.RIGHT);
    JTextField newUserUsernameField = new JTextField(20);
    JLabel newUserFirstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
    JTextField newUserFirstNameField = new JTextField(20);
    JLabel newUserLastNameLabel = new JLabel("Last Name:", SwingConstants.RIGHT);
    JTextField newUserLastNameField = new JTextField(20);
    JLabel newUserEmailLabel = new JLabel("Email:", SwingConstants.RIGHT);
    JTextField newUserEmailField = new JTextField(20);
    JLabel newUserAccountTypeLabel = new JLabel("Account Type:", SwingConstants.RIGHT);
    JLabel newUserPasswordLabel = new JLabel("Password:", SwingConstants.RIGHT);
    JPasswordField newUserPasswordField = new JPasswordField();
    JLabel newUserConfirmPasswordLabel = new JLabel("Confirm Password:", SwingConstants.RIGHT);
    JPasswordField newUserConfirmPasswordField = new JPasswordField();
    JComboBox newUserAccountTypeField = new JComboBox();
    JButton newUserSaveButton = new JButton("Save");

    GridBagConstraints constraints = new GridBagConstraints();

    public warehouseWorkersCard(HashMap<String, ActionListener> actionListeners) {
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
        newWorkerButton.addActionListener(actionListeners.get("newUserAction"));
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

        constraints.gridx = 0;
        constraints.gridy++;
        dateFilterPanel.add(dateFilterHeader, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        dateFilterPanel.add(dateStartField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        dateFilterPanel.add(dateEndField, constraints);

        leftPanel.add(dateFilterPanel, constraints);
    }

    void buildRightPanel() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.weightx = 0.8;
        headerPanel.add(titleLabel, constraints);

        constraints.gridx++;
        constraints.weightx = 0.2;
        constraints.anchor = GridBagConstraints.NORTH;
        headerPanel.add(newWorkerButton, constraints);

        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        rightPanel.add(headerPanel, constraints);

        constraints.gridy++;
        rightPanel.add(dataTable, constraints);

        constraints.gridy++;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        constraints.insets = new Insets(10, 0, 10, 0);
        rightPanel.add(saveChangesButton, constraints);

        constraints.gridy++;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        rightPanel.add(editDataPanel, constraints);
    }

    public void populate(DefaultTableModel data) {
        dataTable = new JTable(data);
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
        newUserAccountTypeField.setSelectedItem("worker");
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
}
