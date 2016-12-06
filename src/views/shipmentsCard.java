package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class shipmentsCard extends card {
    // left panel elements
    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel statusFilterPanel = new JPanel(new GridBagLayout());
    JLabel statusFilterHeader = new JLabel("Filter By Status");
    JCheckBox activeCheckbox = new JCheckBox("Pending", true);
    JCheckBox inactiveCheckbox = new JCheckBox("Fulfilled", true);
    JButton filterButton = new JButton("Filter");

    // right panel elements
    JPanel rightPanel = new JPanel(new GridBagLayout());
    JPanel headerPanel = new JPanel(new GridBagLayout());
    JLabel titleLabel = new JLabel("Shipments");
    JButton newShipmentButton = new JButton("New Shipment");
    JButton editShipmentButton = new JButton("Edit");
    public JTable dataTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(dataTable);

    // new shipment elements
    JFrame newShipmentFrame = new JFrame("Create New Shipment");
    Dimension newShipmentFrameDimensions = new Dimension(400, 400);
    JPanel newShipmentPanel = new JPanel(new GridBagLayout());
    JLabel newShipmentWorkerLabel = new JLabel("Worker:", SwingConstants.RIGHT);
    JComboBox newShipmentWorkerField = new JComboBox();
    JLabel newShipmentAthleteLabel = new JLabel("Athlete:", SwingConstants.RIGHT);
    JComboBox newShipmentAthleteField = new JComboBox();
    JLabel newShipmentProductLabel = new JLabel("Product:", SwingConstants.RIGHT);
    JLabel newShipmentProductMaxQuantityLabel = new JLabel("Quantity in stock:", SwingConstants.RIGHT);
    public JTextField newShipmentProductMaxQuantityField = new JTextField("0", 20);
    public JComboBox newShipmentProductField = new JComboBox();
    JLabel newShipmentQuantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
    JTextField newShipmentQuantityField = new JTextField("0", 20);
    JButton newShipmentSaveButton = new JButton("Save");

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
        filterButton.addActionListener(actionListeners.get("filterShipmentsAction"));

        // build table
        rightPanel.repaint();
        rightPanel.revalidate();
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
        headerPanel.add(newShipmentButton, constraints);

        constraints.gridx++;
        constraints.weightx = 0.2;
        editShipmentButton.setEnabled(false);
        headerPanel.add(editShipmentButton, constraints);

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

    public void populateWorkers(ArrayList data) {
        for(int i = 0; i < data.size(); i++) {
            newShipmentWorkerField.addItem(data.get(i));
        }
    }

    public void populateAthletes(ArrayList data) {
        for(int i = 0; i < data.size(); i++) {
            newShipmentAthleteField.addItem(data.get(i));
        }
    }

    public void populateProducts(ArrayList data) {
        for(int i = 0; i < data.size(); i++) {
            newShipmentProductField.addItem(data.get(i));
        }
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
}
