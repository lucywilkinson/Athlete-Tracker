package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class productsCard extends card {
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
    JLabel titleLabel = new JLabel("Products");
    JButton newProductButton = new JButton("New Product");
    JButton saveChangesButton = new JButton("Save Changes");
    JTable dataTable = new JTable(data, columnNames);
    JPanel editDataPanel = new JPanel(new GridBagLayout());

    // new product elements
    JFrame newUserFrame = new JFrame("Create New Product");
    Dimension newUserFrameDimensions = new Dimension(400, 400);
    JPanel newProductPanel = new JPanel(new GridBagLayout());


    JLabel newProductNameLabel = new JLabel("Name:", SwingConstants.RIGHT);
    JTextField newProductNameField = new JTextField(20);
    JLabel newProductValueLabel = new JLabel("Value:", SwingConstants.RIGHT);
    JTextField newProductValueField = new JTextField("0.00", 20);
    JLabel newProductQuantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
    JTextField newProductQuantityField = new JTextField ("0", 20);
    JButton newProductSaveButton = new JButton("Save");

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
        headerPanel.add(newProductButton, constraints);

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

    public void launchNewProduct() {
        newUserFrame.setPreferredSize(this.newUserFrameDimensions);
        this.buildNewProductFrame();
        newUserFrame.pack();
        newUserFrame.setVisible(true);
    }

    void buildNewProductFrame() {
        constraints.weightx = 0.5;
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridy = 0;
        constraints.gridx = 0;
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
        newProductPanel.add(newProductSaveButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10,10,10,10);
        newUserFrame.add(this.newProductPanel);
    }

}
