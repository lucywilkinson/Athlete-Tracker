package controllers;

import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import common.Product;
import common.Shipment;
import common.User;
import models.ProductModel;
import models.ShipmentsModel;
import models.UserModel;
import views.shipmentsCard;

import javax.swing.*;
import java.awt.event.*;
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
        actionListeners.put("saveNewShipmentAction", new saveNewShipmentAction());
        actionListeners.put("editShipmentAction", new editShipmentAction());
        actionListeners.put("saveEditShipmentAction", new saveEditShipmentAction());
        actionListeners.put("filterShipmentsAction", new filterShipmentsAction());

        view = new shipmentsCard(actionListeners);
        masterView.addCard("Shipments", view);

        view.dataTable.setModel(shipmentsModel.buildTableModel());
    }

    private class filterShipmentsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean pending = view.activeCheckbox.isSelected();
            Boolean fullfilled = view.inactiveCheckbox.isSelected();

            try {
                view.dataTable.setModel(shipmentsModel.filterShipments(pending, fullfilled));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
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

    private class saveNewShipmentAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int workerID  = Character.getNumericValue((String.valueOf(view.newShipmentWorkerField.getSelectedItem()).charAt(0)));
            int athleteID = Character.getNumericValue((String.valueOf(view.newShipmentAthleteField.getSelectedItem()).charAt(0)));
            String product = String.valueOf(view.newShipmentProductField.getSelectedItem());
            int quantity   = Integer.parseInt(view.newShipmentQuantityField.getText());
            int inStock    = Integer.parseInt(view.newShipmentProductMaxQuantityField.getText());
            Boolean fulfilled = false;

            if (quantity > inStock || quantity < 0) {
                JOptionPane.showMessageDialog(new JFrame(), "Invalid Quantity", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Shipment newShipment = new Shipment(workerID, athleteID, _user.getUserId(), product, quantity, fulfilled);

            try {
                shipmentsModel.addShipment(newShipment);
                view.newShipmentFrame.dispatchEvent(new WindowEvent(view.newShipmentFrame, WindowEvent.WINDOW_CLOSING));
                view.newShipmentProductMaxQuantityField.setText(String.valueOf(inStock - quantity));
                view.dataTable.setModel(shipmentsModel.buildTableModel());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    private class editShipmentAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.launchEditShipment();

            int row = view.dataTable.getSelectedRow();

            String product = String.valueOf(view.dataTable.getValueAt(row, 2));
            String worker = String.valueOf(view.dataTable.getValueAt(row, 1));
            String reciever = String.valueOf(view.dataTable.getValueAt(row, 4));
            String quantity = String.valueOf(view.dataTable.getValueAt(row, 3));
            String id = null;

            try {
                id = String.valueOf(shipmentsModel.getShipmentID(product));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            view.editShipmentIdField.setText(id);
            view.editShipmentWorkerField.setSelectedItem(worker);
            view.editShipmentAthleteField.setSelectedItem(reciever);
            view.editShipmentProductField.setSelectedItem(product);
            view.editShipmentQuantityField.setText(quantity);

            try {
                view.editShipmentProductMaxQuantityField.setText(String.valueOf(shipmentsModel.getProductQuantity(product)));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            view.editShipmentIdField.setEnabled(false);

            populateAthletes();
            populateProducts();
            populateWorkers();

        }
    }

    private class saveEditShipmentAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.editShipmentIdField.getText());
            int workerID = Character.getNumericValue((String.valueOf(view.editShipmentWorkerField.getSelectedItem()).charAt(0)));
            int athleteID = Character.getNumericValue((String.valueOf(view.editShipmentAthleteField.getSelectedItem()).charAt(0)));
            String product = String.valueOf(view.editShipmentProductField.getSelectedItem());
            int quantity = Integer.parseInt(view.editShipmentQuantityField.getText());
            int inStock = Integer.parseInt(view.editShipmentProductMaxQuantityField.getText());
            Boolean fulfilled = view.editShipmentFulfilledField.isSelected();

            if (quantity > inStock || quantity < 0) {
                JOptionPane.showMessageDialog(new JFrame(), "Invalid Quantity", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Shipment updatedShipment = new Shipment(workerID, athleteID, _user.getUserId(), product, quantity, fulfilled);

            try {
                shipmentsModel.editShipment(id, updatedShipment);
                view.editShipmentFrame.dispatchEvent(new WindowEvent(view.editShipmentFrame, WindowEvent.WINDOW_CLOSING));
                view.dataTable.setModel(shipmentsModel.buildTableModel());

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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
                workers.add(workersResult.getString("user_id") + " (" +workersResult.getString("first_name") + " " + workersResult.getString("last_name") + ")");
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
                athletes.add(athletesResult.getString("user_id") + " (" + athletesResult.getString("first_name") + " " + athletesResult.getString("last_name") + ")");
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
