package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class athletesCard extends card {
    JPanel leftPanel = new JPanel(new GridBagLayout());
    JPanel statusFilterPanel = new JPanel();
    JLabel statusFilterHeader = new JLabel("Filter By Status");
    JCheckBox activeCheckbox = new JCheckBox("Active", true);
    JCheckBox inactiveCheckbox = new JCheckBox("Inactive", true);
    JPanel dateFilterPanel = new JPanel();
    JLabel dateFilterHeader = new JLabel("Filter By Date");
    JTextField dateStartField = new JTextField("12/1/2012");
    JTextField dateEndField = new JTextField("12/31/2016");

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

    JPanel rightPanel = new JPanel(new GridBagLayout());
    JPanel headerPanel = new JPanel(new GridBagLayout());
    JLabel titleLabel = new JLabel("Athletes");
    JButton newAthleteButton = new JButton("New Athlete");
    JTable dataTable = new JTable(data, columnNames);
    JPanel editDataPanel = new JPanel();

    GridBagConstraints constraints = new GridBagConstraints();

    public athletesCard(HashMap<String, ActionListener> actionListeners) {
        super(actionListeners); // adds nav bar

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        this.buildLeftPanel();
        this.add(leftPanel);

        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(rightPanel);
        headerPanel.add(titleLabel);
        headerPanel.add(newAthleteButton);
        rightPanel.add(headerPanel);
        rightPanel.add(dataTable);
        rightPanel.add(editDataPanel);
    }

    void buildLeftPanel() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;

        constraints.gridy = 0;
        statusFilterPanel.add(statusFilterHeader);

        constraints.gridy++;
        statusFilterPanel.add(activeCheckbox);

        constraints.gridy++;
        statusFilterPanel.add(inactiveCheckbox);

        constraints.gridy++;
        leftPanel.add(statusFilterPanel);

        constraints.gridy++;
        dateFilterPanel.add(dateFilterHeader);

        constraints.gridy++;
        dateFilterPanel.add(dateStartField);

        constraints.gridy++;
        dateFilterPanel.add(dateEndField);

        leftPanel.add(dateFilterPanel);
    }
}
