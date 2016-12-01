package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class navBar extends JPanel {

    public JButton adminsButton = new JButton("Admins");
    public JButton athletesButton = new JButton("Athletes");
    public JButton warehouseWorkersButton = new JButton("Warehouse Workers");
    public JButton shipmentsButton = new JButton("Shipments");
    public JButton productsButton = new JButton("Products");
    public JButton myProfileButton  = new JButton("My Profile");

    public navBar(HashMap<String, ActionListener> actionListeners) {
        this.add(this.adminsButton);
        this.add(this.athletesButton);
        this.add(this.warehouseWorkersButton);
        this.add(this.shipmentsButton);
        this.add(this.productsButton);
        this.add(this.myProfileButton);

        adminsButton.addActionListener(actionListeners.get("navigateToAdmins"));
        athletesButton.addActionListener(actionListeners.get("navigateToAthletes"));
        warehouseWorkersButton.addActionListener(actionListeners.get("navigateToWarehouseWorkers"));
        shipmentsButton.addActionListener(actionListeners.get("navigateToShipments"));
        productsButton.addActionListener(actionListeners.get("navigateToProducts"));
        myProfileButton.addActionListener(actionListeners.get("navigateToMyProfile"));
    }
}
