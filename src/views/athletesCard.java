package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class athletesCard extends card {
    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel statusFilterPanel = new JPanel(new GridBagLayout());
    JLabel statusFilterHeader = new JLabel("Filter By Status");
    JCheckBox activeCheckbox = new JCheckBox("Active", true);
    JCheckBox inactiveCheckbox = new JCheckBox("Inactive", true);
    JPanel dateFilterPanel = new JPanel(new GridBagLayout());
    JLabel dateFilterHeader = new JLabel("Filter By Date");
    JTextField dateStartField = new JTextField("12/1/2012");
    JTextField dateEndField = new JTextField("12/31/2016");

    JPanel rightPanel = new JPanel(new GridBagLayout());
    JPanel headerPanel = new JPanel(new GridBagLayout());
    JLabel titleLabel = new JLabel("Athletes");
    JButton newAthleteButton = new JButton("New Athlete");
    JButton saveChangesButton = new JButton("Save Changes");
    JTable dataTable = new JTable();
    JPanel editDataPanel = new JPanel(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();

    public athletesCard(HashMap<String, ActionListener> actionListeners) {
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
        headerPanel.add(newAthleteButton, constraints);

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
        rightPanel.remove(dataTable);
        rightPanel.add(dataTable, constraints);
        rightPanel.repaint();
        rightPanel.revalidate();
    }
}
