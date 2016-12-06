package views;

import com.sun.tools.javac.util.StringUtils;
import sun.swing.StringUIClientPropertyKey;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by alex on 11/29/16.
 */
public class shipmentsCard extends card {
    // left panel elements
    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel statusFilterPanel = new JPanel(new GridBagLayout());
    JLabel statusFilterHeader = new JLabel("Filter By Status");
    public JCheckBox activeCheckbox = new JCheckBox("Pending", true);
    public JCheckBox inactiveCheckbox = new JCheckBox("Fulfilled", true);
    JButton filterButton = new JButton("Filter");

    // right panel elements
    JPanel rightPanel = new JPanel(new GridBagLayout());
    JPanel headerPanel = new JPanel(new GridBagLayout());
    JLabel titleLabel = new JLabel("Shipments");
    JButton newShipmentButton = new JButton("New Shipment");
    JButton editButton = new JButton("Edit");
    public JTable dataTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(dataTable);
    JPanel editDataPanel = new JPanel(new GridBagLayout());

    // new shipment elements
    public JFrame newShipmentFrame = new JFrame("Create New Shipment");
    Dimension newShipmentFrameDimensions = new Dimension(400, 400);
    JPanel newShipmentPanel = new JPanel(new GridBagLayout());
    JLabel newShipmentWorkerLabel = new JLabel("Worker:", SwingConstants.RIGHT);
    public JComboBox newShipmentWorkerField = new JComboBox();
    JLabel newShipmentAthleteLabel = new JLabel("Athlete:", SwingConstants.RIGHT);
    public JComboBox newShipmentAthleteField = new JComboBox();
    JLabel newShipmentProductLabel = new JLabel("Product:", SwingConstants.RIGHT);
    JLabel newShipmentProductMaxQuantityLabel = new JLabel("Quantity in stock:", SwingConstants.RIGHT);
    public JTextField newShipmentProductMaxQuantityField = new JTextField("0", 20);
    public JComboBox newShipmentProductField = new JComboBox();
    JLabel newShipmentQuantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
    public JTextField newShipmentQuantityField = new JTextField("0", 20);
    JButton newShipmentSaveButton = new JButton("Save");

    //edit shipment elements
    public JFrame editShipmentFrame = new JFrame("Edit Shipment");
    Dimension editShipmentFrameDimensions = new Dimension(400,400);
    JPanel editShipmentPanel = new JPanel(new GridBagLayout());
    JLabel editShipmentIdLabel = new JLabel("ID:", SwingConstants.RIGHT);
    public JTextField editShipmentIdField = new JTextField(20);
    JLabel editShipmentWorkerLabel = new JLabel("Worker:", SwingConstants.RIGHT);
    public JComboBox editShipmentWorkerField = new JComboBox();
    JLabel editShipmentAthleteLabel = new JLabel("Athlete:", SwingConstants.RIGHT);
    public JComboBox editShipmentAthleteField = new JComboBox();
    JLabel editShipmentProductLabel = new JLabel("Product:", SwingConstants.RIGHT);
    JLabel editShipmentProductMaxQuantityLabel = new JLabel("Quantity in stock:", SwingConstants.RIGHT);
    public JTextField editShipmentProductMaxQuantityField = new JTextField("0", 20);
    public JComboBox editShipmentProductField = new JComboBox();
    JLabel editShipmentQuantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
    public JTextField editShipmentQuantityField = new JTextField("0", 20);
    JLabel editShipmentFulfilledLabel = new JLabel("Fulfilled:", SwingConstants.RIGHT);
    public JCheckBox editShipmentFulfilledField = new JCheckBox("Fulfilled", false);
    JButton editShipmentSaveButton = new JButton("Save");

    GridBagConstraints constraints = new GridBagConstraints();

    public shipmentsCard(HashMap<String, ActionListener> actionListeners) {
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
        newShipmentButton.addActionListener(actionListeners.get("addShipment"));
        newShipmentSaveButton.addActionListener(actionListeners.get("saveNewShipmentAction"));
        filterButton.addActionListener(actionListeners.get("filterShipmentsAction"));
        editButton.addActionListener(actionListeners.get("editShipmentAction"));
        editShipmentSaveButton.addActionListener(actionListeners.get("saveEditShipmentAction"));

        editButton.setEnabled(false);
        editButton.addActionListener(actionListeners.get("editShipmentAction"));

        // build table
        rightPanel.repaint();
        rightPanel.revalidate();

        // add rowSelectionListener to table
        rowSelectionListener selectionListener = new rowSelectionListener();
        dataTable.getSelectionModel().addListSelectionListener(selectionListener);

        // add document listeners to each "new product" field
        SaveButtonDocumentListener saveButtonEnabler = new SaveButtonDocumentListener();
        newShipmentQuantityField.getDocument().addDocumentListener(saveButtonEnabler);
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

        constraints.gridy++;
        statusFilterPanel.add(filterButton, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.insets = new Insets(0, 0, 0, 0);
        leftPanel.add(statusFilterPanel, constraints);
    }

    void buildRightPanel() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.weightx = 0.8;
        headerPanel.add(titleLabel, constraints);

        constraints.gridx++;
        constraints.weightx = 0.2;
        constraints.anchor = GridBagConstraints.NORTH;
        headerPanel.add(newShipmentButton, constraints);

        constraints.gridx++;
        constraints.weightx = 0.2;
        headerPanel.add(editButton, constraints);

        /*constraints.gridx = 0;
        constraints.gridx++;
        constraints.weightx = 0.2;
        editShipmentButton.setEnabled(false);
        headerPanel.add(editShipmentButton, constraints);*/

        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.weighty = 0;
        rightPanel.add(headerPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        rightPanel.add(scrollPane, constraints);

        constraints.gridy++;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        rightPanel.add(editDataPanel, constraints);
    }

    public void populateWorkers(ArrayList data) {
        newShipmentWorkerField.removeAllItems();
        editShipmentWorkerField.removeAllItems();
        for(int i = 0; i < data.size(); i++) {
            newShipmentWorkerField.addItem(data.get(i));
            editShipmentWorkerField.addItem(data.get(i));
        }
    }

    public void populateAthletes(ArrayList data) {
        newShipmentAthleteField.removeAllItems();
        editShipmentAthleteField.removeAllItems();
        for(int i = 0; i < data.size(); i++) {
            newShipmentAthleteField.addItem(data.get(i));
            editShipmentAthleteField.addItem(data.get(i));
        }
    }

    public void populateProducts(ArrayList data) {
        newShipmentProductField.removeAllItems();
        editShipmentProductField.removeAllItems();
        for(int i = 0; i < data.size(); i++) {
            newShipmentProductField.addItem(data.get(i));
            editShipmentProductField.addItem(data.get(i));
        }
    }

    public void launchEditShipment() {
        editShipmentFrame.setPreferredSize(this.editShipmentFrameDimensions);
        this.buildEditShipmentFrame();
        editShipmentFrame.pack();
        editShipmentFrame.setVisible(true);
    }

    public void buildEditShipmentFrame() {
        constraints.weightx = 0.5;
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        editShipmentPanel.add(editShipmentIdLabel, constraints);

        constraints.gridx++;
        editShipmentPanel.add(editShipmentIdField, constraints);
        editShipmentIdField.setEnabled(false);

        constraints.gridy++;
        constraints.gridx = 0;
        editShipmentPanel.add(editShipmentWorkerLabel, constraints);

        constraints.gridx++;
        editShipmentPanel.add(editShipmentWorkerField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editShipmentPanel.add(editShipmentAthleteLabel, constraints);

        constraints.gridx++;
        editShipmentPanel.add(editShipmentAthleteField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editShipmentPanel.add(editShipmentProductLabel, constraints);

        constraints.gridx++;
        editShipmentPanel.add(editShipmentProductField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editShipmentPanel.add(editShipmentProductMaxQuantityLabel, constraints);

        constraints.gridx = 1;
        editShipmentProductMaxQuantityField.setEnabled(false);
        editShipmentPanel.add(editShipmentProductMaxQuantityField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editShipmentPanel.add(editShipmentQuantityLabel, constraints);

        constraints.gridx = 1;
        editShipmentPanel.add(editShipmentQuantityField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editShipmentPanel.add(editShipmentFulfilledLabel, constraints);

        constraints.gridx = 1;
        editShipmentPanel.add(editShipmentFulfilledField, constraints);

        constraints.gridy++;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        editShipmentPanel.add(editShipmentSaveButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10,10,10,10);
        editShipmentFrame.add(this.editShipmentPanel);
    }

    public void launchNewShipment(HashMap itemListeners) {
        this.buildNewShipmentFrame();

        newShipmentProductField.addItemListener((ItemListener) itemListeners.get("productChanged"));

        newShipmentFrame .setPreferredSize(this.newShipmentFrameDimensions);
        newShipmentFrame .pack();
        newShipmentFrame .setVisible(true);
    }

    void buildNewShipmentFrame() {
        constraints.weightx = 0.5;
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        newShipmentPanel.add(newShipmentWorkerLabel, constraints);

        constraints.gridx = 1;
        newShipmentPanel.add(newShipmentWorkerField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newShipmentPanel.add(newShipmentAthleteLabel, constraints);

        constraints.gridx = 1;
        newShipmentPanel.add(newShipmentAthleteField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newShipmentPanel.add(newShipmentProductLabel, constraints);

        constraints.gridx = 1;
        newShipmentPanel.add(newShipmentProductField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newShipmentPanel.add(newShipmentProductMaxQuantityLabel, constraints);

        constraints.gridx = 1;
        newShipmentProductMaxQuantityField.setEnabled(false);
        newShipmentPanel.add(newShipmentProductMaxQuantityField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newShipmentPanel.add(newShipmentQuantityLabel, constraints);

        constraints.gridx = 1;
        newShipmentPanel.add(newShipmentQuantityField, constraints);

        constraints.gridy++;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        newShipmentPanel.add(newShipmentSaveButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10,10,10,10);
        newShipmentFrame.add(this.newShipmentPanel);
    }

    private class rowSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (dataTable.getSelectedRow() < 0) {
                editButton.setEnabled(false);
            }
            else {
                editButton.setEnabled(true);
            }
        }
    }

    /**
     * Checks that all fields are filled before enabling newShipmentSaveButton.
     */
    private void checkFields() {
        boolean quantityExists = !newShipmentQuantityField.getText().trim().isEmpty();
        boolean quantityIsInt = newShipmentQuantityField.getText().trim().matches("-?\\d+(\\.\\d+)?"); // check if integer

        if (quantityExists && quantityIsInt) {
            newShipmentSaveButton.setEnabled(true);
        } else {
            newShipmentSaveButton.setEnabled(false);
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
}
