package controllers;

import common.User;
import views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/27/16.
 */
public class TestController extends BasicController {
    masterView view;
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public TestController() {
        super();

        actionListeners.put("navigateToAdmins", new navigateToAdmins());
        actionListeners.put("navigateToAthletes", new navigateToAthletes());
        actionListeners.put("navigateToWarehouseWorkers", new navigateToWarehouseWorkers());
        actionListeners.put("navigateToShipments", new navigateToShipments());
        actionListeners.put("navigateToProducts", new navigateToProducts());
        actionListeners.put("navigateToMyProfile", new navigateToMyProfile());

        this.view = new masterView(actionListeners);

        view.addCard("Admins", new adminsCard(actionListeners));
        view.addCard("Athletes", new athletesCard(actionListeners));
        view.addCard("Warehouse Workers", new warehouseWorkersCard(actionListeners));
        view.addCard("Shipments", new shipmentsCard(actionListeners));
        view.addCard("Products", new productsCard(actionListeners));
        view.addCard("My Profile", new myProfileCard(actionListeners));
    }

    private class navigateToAdmins implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showCard("Admins");
        }
    }

    private class navigateToAthletes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showCard("Athletes");
        }
    }

    private class navigateToWarehouseWorkers implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showCard("Warehouse Workers");
        }
    }

    private class navigateToShipments implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showCard("Shipments");
        }
    }

    private class navigateToProducts implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showCard("Products");
        }
    }

    private class navigateToMyProfile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showCard("My Profile");
        }
    }

}
