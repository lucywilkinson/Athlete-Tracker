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

/**
 * Created by alex on 11/29/16.
 */
public class productsCard extends card {

    // left panel elements
    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel statusFilterPanel = new JPanel(new GridBagLayout());
    JLabel statusFilterHeader = new JLabel("Filter By Status");
    public JCheckBox activeCheckbox = new JCheckBox("Active", true);
    public JCheckBox inactiveCheckbox = new JCheckBox("Inactive", true);
    JButton filterButton = new JButton("Filter");

    // right panel elements
    JPanel rightPanel = new JPanel(new GridBagLayout());
    JPanel headerPanel = new JPanel(new GridBagLayout());
    JLabel titleLabel = new JLabel("Products");
    JButton newProductButton = new JButton("New Product");
    JButton editProductButton = new JButton("Edit");
    public JTable dataTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(dataTable);

    // new product elements
    public JFrame newProductFrame = new JFrame("Create New Product");
    Dimension newProductFrameDimensions = new Dimension(400, 400);
    JPanel newProductPanel = new JPanel(new GridBagLayout());
    JLabel newProductNameLabel = new JLabel("Name:", SwingConstants.RIGHT);
    public JTextField newProductNameField = new JTextField(20);
    JLabel newProductValueLabel = new JLabel("Value:", SwingConstants.RIGHT);
    public JTextField newProductValueField = new JTextField("0.00", 20);
    JLabel newProductQuantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
    public JTextField newProductQuantityField = new JTextField ("0", 20);
    JButton newProductSaveButton = new JButton("Save");

    // edit product elements
    public JFrame editProductFrame = new JFrame("Edit Product");
    Dimension editProductFrameDimensions = new Dimension(400, 400);
    JPanel editProductPanel = new JPanel(new GridBagLayout());
    JLabel editProductIdLabel = new JLabel("ID:", SwingConstants.RIGHT);
    public JTextField editProductIdField = new JTextField(20);
    JLabel editProductNameLabel = new JLabel("Name:", SwingConstants.RIGHT);
    public JTextField editProductNameField = new JTextField(20);
    JLabel editProductValueLabel = new JLabel("Value:", SwingConstants.RIGHT);
    public JTextField editProductValueField = new JTextField("0.00", 20);
    JLabel editProductQuantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
    public JTextField editProductQuantityField = new JTextField ("0", 20);
    JLabel editProductStatusLabel = new JLabel("Status", SwingConstants.RIGHT);
    public JCheckBox editProductStatusField = new JCheckBox("Active", true);
    JButton editProductSaveButton = new JButton("Save");

    GridBagConstraints constraints = new GridBagConstraints();

    public productsCard(HashMap<String, ActionListener> actionListeners) {
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
        newProductButton.addActionListener(actionListeners.get("addProduct"));
        filterButton.addActionListener(actionListeners.get("filterProducts"));
        newProductSaveButton.addActionListener(actionListeners.get("saveNewProduct"));
        editProductButton.addActionListener(actionListeners.get("editProducts"));
        editProductSaveButton.addActionListener(actionListeners.get("saveEditProduct"));

        // add row selection listener
        rowSelectionListener selectionListener = new rowSelectionListener();
        dataTable.getSelectionModel().addListSelectionListener(selectionListener);

        // add document listeners to each "new product" field
        SaveButtonDocumentListener saveButtonEnabler = new SaveButtonDocumentListener();
        newProductNameField.getDocument().addDocumentListener(saveButtonEnabler);
        newProductValueField.getDocument().addDocumentListener(saveButtonEnabler);
        newProductQuantityField.getDocument().addDocumentListener(saveButtonEnabler);
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

        constraints.weightx = 0.6;
        headerPanel.add(titleLabel, constraints);

        constraints.gridx++;
        constraints.weightx = 0.2;
        headerPanel.add(newProductButton, constraints);

        constraints.gridx++;
        constraints.weightx = 0.2;
        editProductButton.setEnabled(false);
        headerPanel.add(editProductButton, constraints);

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
    }

    public void launchNewProduct() {
        newProductFrame.setPreferredSize(this.newProductFrameDimensions);
        this.buildNewProductFrame();
        newProductFrame.pack();
        newProductFrame.setVisible(true);
    }

    void buildNewProductFrame() {
        constraints.weightx = 0.5;
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        newProductPanel.add(newProductNameLabel, constraints);

        constraints.gridx = 1;
        newProductPanel.add(newProductNameField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newProductPanel.add(newProductValueLabel, constraints);

        constraints.gridx = 1;
        newProductPanel.add(newProductValueField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        newProductPanel.add(newProductQuantityLabel, constraints);

        constraints.gridx = 1;
        newProductPanel.add(newProductQuantityField, constraints);

        constraints.gridy++;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        newProductSaveButton.setEnabled(false);
        newProductPanel.add(newProductSaveButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10,10,10,10);
        newProductFrame.add(this.newProductPanel);
    }

    public void launchEditProduct() {
        editProductFrame.setPreferredSize(this.editProductFrameDimensions);
        this.buildEditProductFrame();
        editProductFrame.pack();
        editProductFrame.setVisible(true);
    }

    void buildEditProductFrame() {
        constraints.weightx = 0.5;
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        editProductPanel.add(editProductNameLabel, constraints);

        constraints.gridx = 1;
        editProductPanel.add(editProductNameField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editProductPanel.add(editProductIdLabel, constraints);

        constraints.gridx = 1;
        editProductIdField.setEnabled(false);
        editProductPanel.add(editProductIdField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editProductPanel.add(editProductValueLabel, constraints);

        constraints.gridx = 1;
        editProductPanel.add(editProductValueField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editProductPanel.add(editProductQuantityLabel, constraints);

        constraints.gridx = 1;
        editProductPanel.add(editProductQuantityField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        editProductPanel.add(editProductStatusLabel, constraints);

        constraints.gridx = 1;
        editProductPanel.add(editProductStatusField, constraints);

        constraints.gridy++;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        editProductPanel.add(editProductSaveButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10,10,10,10);
        editProductFrame.add(this.editProductPanel);
    }

    private class rowSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (dataTable.getSelectedRow() < 0) {
                editProductButton.setEnabled(false);
            }
            else {
                editProductButton.setEnabled(true);
            }
        }
    }

    /**
     * Checks that all fields are filled before enabling newProductSaveButton.
     */
    private void checkFields() {
        boolean name = !newProductNameField.getText().trim().isEmpty();
        boolean value= !newProductValueField.getText().trim().isEmpty();
        boolean quantity= !newProductQuantityField.getText().trim().isEmpty();

        if (name && value && quantity) {
            newProductSaveButton.setEnabled(true);
        } else {
            newProductSaveButton.setEnabled(false);
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
