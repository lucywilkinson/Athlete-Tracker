package controllers;

import common.Product;
import common.User;
import models.ProductModel;
import models.ShipmentsModel;
import models.UserModel;
import views.shipmentsCard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ShipmentsController extends BasicController {
    shipmentsCard view;
    ProductModel productModel;
    UserModel userModel;
    ShipmentsModel shipmentsModel;
    HashMap itemListeners = new HashMap<String, ItemListener>();

    public ShipmentsController() throws SQLException, IOException, ClassNotFoundException {
        shipmentsModel = new ShipmentsModel();
        actionListeners.put("addShipment", new addShipment());

        view = new shipmentsCard(actionListeners);
        masterView.addCard("Shipments", view);

        view.dataTable.setModel(shipmentsModel.buildTableModel());
    }

    private class addShipment implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            itemListeners.put("productChanged", new productChanged());

            view.launchNewShipment(itemListeners);

            populateProducts();
            populateWorkers();
            populateAthletes();
        }
    }

    /*
        When the user changes the product selection, update the max quantity field with the current stock of that product
     */
    class productChanged implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object item = event.getItem();

                String productName = view.newShipmentProductField.getSelectedItem().toString();

                try {
                    productModel = new ProductModel();

                    Product product = productModel.getProduct(productName);

                    view.newShipmentProductMaxQuantityField.setText(Integer.toString(product.getQuantity()));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
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
            ResultSet workersResult = userModel.returnUsersOfType("worker");
            ArrayList workers = new ArrayList();

            // convert ResultSet to ArrayList
            while (workersResult.next()) {
                workers.add(workersResult.getString("first_name") + " " + workersResult.getString("last_name"));
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
            ResultSet athletesResult = userModel.returnUsersOfType("athlete");
            ArrayList athletes = new ArrayList();

            // convert ResultSet to ArrayList
            while (athletesResult.next()) {
                athletes.add(athletesResult.getString("first_name") + " " + athletesResult.getString("last_name"));
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
}
