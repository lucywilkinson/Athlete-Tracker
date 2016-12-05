package controllers;

import common.User;
import views.basicView;
import views.masterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by alex on 11/27/16.
 */
public abstract class BasicController {
    basicView view;
    masterView masterView = new masterView();
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public BasicController() {
        this.attachNavMenuListeners();
    }

    public BasicController(User user) {
        this._user = user;
        this.attachNavMenuListeners();
    }

    private void attachNavMenuListeners() {
        actionListeners.put("navigateToAthletes", new clickAthletesButton());
        actionListeners.put("navigateToAdmins", new clickAdminsButton());
        actionListeners.put("navigateToMyProfile", new clickMyProfileButton());
        actionListeners.put("navigateToProducts", new clickProductsButton());
        actionListeners.put("navigateToShipments", new clickShipmentsButton());
        actionListeners.put("navigateToWarehouseWorkers", new clickWarehouseWorkersButton());
    }

    private class clickAthletesButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                AthletesController AthletesController = new AthletesController(_user);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class clickShipmentsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShipmentsController ShipmentsController = new ShipmentsController(_user);
        }
    }

    private class clickWarehouseWorkersButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WarehouseWorkersController WarehouseWorkersController = new WarehouseWorkersController(_user);
        }
    }

    private class clickProductsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ProductsController ProductsController = new ProductsController(_user);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class clickMyProfileButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MyProfileController MyProfileController = new MyProfileController(_user);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class clickAdminsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminsController AdminsController = new AdminsController(_user);
        }
    }
}
