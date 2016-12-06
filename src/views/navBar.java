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
    public JButton logoutButton  = new JButton("Logout");

    public navBar(HashMap<String, ActionListener> actionListeners) {
        this.setLayout(new GridBagLayout());

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        constraints.gridx = 0;
        this.add(this.adminsButton, constraints);
        constraints.gridx++;
        this.add(this.athletesButton, constraints);
        constraints.gridx++;
        this.add(this.warehouseWorkersButton, constraints);
        constraints.gridx++;
        this.add(this.shipmentsButton, constraints);
        constraints.gridx++;
        this.add(this.productsButton, constraints);
        constraints.gridx++;
        this.add(this.myProfileButton, constraints);
        constraints.gridx++;
        this.add(this.logoutButton, constraints);

        adminsButton.addActionListener(actionListeners.get("navigateToAdmins"));
        athletesButton.addActionListener(actionListeners.get("navigateToAthletes"));
        warehouseWorkersButton.addActionListener(actionListeners.get("navigateToWarehouseWorkers"));
        shipmentsButton.addActionListener(actionListeners.get("navigateToShipments"));
        productsButton.addActionListener(actionListeners.get("navigateToProducts"));
        myProfileButton.addActionListener(actionListeners.get("navigateToMyProfile"));
        logoutButton.addActionListener(actionListeners.get("logout"));
    }
}
