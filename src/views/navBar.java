package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static java.awt.GridBagConstraints.FIRST_LINE_START;

/**
 * Created by alex on 11/29/16.
 */
public class navBar extends JPanel {

    GridBagConstraints constraints = new GridBagConstraints();

    public JButton adminsButton = new JButton("Admins");
    public JButton athletesButton = new JButton("Athletes");
    public JButton warehouseWorkersButton = new JButton("Warehouse Workers");
    public JButton shipmentsButton = new JButton("Shipments");
    public JButton productsButton = new JButton("Products");
    public JButton myProfileButton  = new JButton("My Profile");

    public navBar(HashMap<String, ActionListener> actionListeners) {
        this.setLayout(new GridBagLayout());

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        constraints.gridx = 0;
        this.add(this.adminsButton, constraints);
        constraints.gridx = 1;
        this.add(this.athletesButton, constraints);
        constraints.gridx = 2;
        this.add(this.warehouseWorkersButton, constraints);
        constraints.gridx = 3;
        this.add(this.shipmentsButton, constraints);
        constraints.gridx = 4;
        this.add(this.productsButton, constraints);
        constraints.gridx = 5;
        this.add(this.myProfileButton, constraints);

        adminsButton.addActionListener(actionListeners.get("navigateToAdmins"));
        athletesButton.addActionListener(actionListeners.get("navigateToAthletes"));
        warehouseWorkersButton.addActionListener(actionListeners.get("navigateToWarehouseWorkers"));
        shipmentsButton.addActionListener(actionListeners.get("navigateToShipments"));
        productsButton.addActionListener(actionListeners.get("navigateToProducts"));
        myProfileButton.addActionListener(actionListeners.get("navigateToMyProfile"));
    }
}
