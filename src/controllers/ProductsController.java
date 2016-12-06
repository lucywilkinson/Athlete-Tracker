package controllers;

import models.ProductModel;
import common.User;
import common.Product;
import views.productsCard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductsController extends BasicController {
    ProductModel productModel;
    productsCard view;

    public ProductsController() throws SQLException, IOException, ClassNotFoundException {

        productModel = new ProductModel();

        actionListeners.put("addProduct",     new addProduct());
        actionListeners.put("saveNewProduct", new saveNewProduct());
        actionListeners.put("editProducts",   new editProduct());
        actionListeners.put("saveEditProduct", new saveEditProduct());
        actionListeners.put("filterProducts", new filterProducts());

        view = new productsCard(actionListeners);
        masterView.addCard("Shipments", view);

        view.dataTable.setModel(productModel.buildProductsTable());
    }

    private class filterProducts implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Boolean active = view.activeCheckbox.isSelected();
            Boolean inactive = view.inactiveCheckbox.isSelected();

            try {
                view.dataTable.setModel(productModel.filterProducts(active, inactive));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class addProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.launchNewProduct();
        }
    }

    private class saveNewProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // gather data
            String productName = view.newProductNameField.getText();
            Float value = Float.parseFloat(view.newProductValueField.getText());
            Integer quantity = Integer.parseInt(view.newProductQuantityField.getText());

            Product product = new Product(productName, value, quantity);

            try {
                productModel.addProduct(product);
                view.newProductFrame.dispatchEvent(new WindowEvent(view.newProductFrame, WindowEvent.WINDOW_CLOSING));
                view.dataTable.setModel(productModel.buildProductsTable());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class editProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.launchEditProduct();

            int row = view.dataTable.getSelectedRow();

            String id = String.valueOf(view.dataTable.getValueAt(row, 0));
            String productName = String.valueOf(view.dataTable.getValueAt(row, 2));
            String value = String.valueOf(view.dataTable.getValueAt(row, 3));
            String quantity = String.valueOf(view.dataTable.getValueAt(row, 3));

            view.editProductIdField.setText(id);
            view.editProductNameField.setText(productName);
            view.editProductValueField.setText(value);
            view.editProductQuantityField.setText(quantity);
        }
    }

    private class saveEditProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.launchEditProduct();

            int id = Integer.parseInt(view.editProductIdField.getText());
            String productName = view.editProductNameField.getText();
            double value = Double.parseDouble(view.editProductValueField.getText());
            int quantity = Integer.parseInt(view.editProductQuantityField.getText());
            Boolean active = view.editProductStatusField.isSelected();

            Product updatedProduct = new Product(id, productName, value, quantity, active);

            try {
                productModel.editProduct(id, updatedProduct);
                view.editProductFrame.dispatchEvent(new WindowEvent(view.editProductFrame, WindowEvent.WINDOW_CLOSING));
                view.dataTable.setModel(productModel.buildProductsTable());

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
