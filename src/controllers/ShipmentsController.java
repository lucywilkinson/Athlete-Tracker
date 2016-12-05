package controllers;

import common.Product;
import common.User;
import models.ProductModel;
import models.ShipmentsModel;
import models.UserModel;
import views.shipmentsCard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mattu on 11/29/16. Updated by lucywilkinson on 12/5/16.
 */

public class ShipmentsController extends BasicController {
    shipmentsCard view;
    ProductModel productModel;
    UserModel userModel;
    ShipmentsModel shipmentsModel;

    public ShipmentsController(User user) {
        super();

        actionListeners.put("addShipment", new addShipment());

        view = new shipmentsCard(actionListeners);
        masterView.addCard("Shipments", view);
    }

    private class addShipment implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.launchNewShipment();

            populateProducts();
            populateWorkers();
            populateAthletes();
        }
    }

    void populateProducts() {
        try {
            productModel = new ProductModel();

            ArrayList<Product> productsResult = productModel.getProducts();
            ArrayList products = new ArrayList();

            for (int i = 0; i < productsResult.size(); i++) {
                products.add(productsResult.get(i).getName());
            }

            view.populateProducts(products);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    void populateWorkers() {
        try {
            userModel = new UserModel();
            ResultSet workersResult = userModel.returnUsersofType("worker");
            ArrayList workers = new ArrayList();

            // convert ResultSet to ArrayList
            while (workersResult.next()) {
                workers.add(workersResult.getString("first_name") + workersResult.getString("last_name"));
            }

            view.populateWorkers(workers);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    void populateAthletes() {
        try {
            userModel = new UserModel();
            ResultSet athletesResult = userModel.returnUsersofType("athlete");
            ArrayList athletes = new ArrayList();

            // convert ResultSet to ArrayList
            while (athletesResult.next()) {
                athletes.add(athletesResult.getString("first_name") + athletesResult.getString("last_name"));
            }

            view.populateAthletes(athletes);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

    }

    private class fillTableAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                shipmentsModel = new ShipmentsModel();
                ResultSet rs = shipmentsModel.getRawShipmentData();
                view.populate(shipmentsModel.buildTableModel(rs));
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

}
