package controllers;

import models.ProductModel;
import common.User;
import common.Product;
import views.productsCard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mattu on 11/29/16.
 */
public class ProductsController extends BasicController {
    ProductModel productModel;
    productsCard view;

    public ProductsController() throws SQLException, IOException, ClassNotFoundException {

        productModel = new ProductModel();

        actionListeners.put("addProduct",     new addProduct());
        actionListeners.put("saveNewProduct", new saveNewProduct());
        actionListeners.put("editProducts",   new editProduct());
        actionListeners.put("disableProduct", new disableProduct());

        ArrayList<Product> products = productModel.getProducts();

        view = new productsCard(actionListeners);
        masterView.addCard("Shipments", view);

        view.dataTable.setModel(productModel.buildTableModel());
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
                view.dataTable.setModel(productModel.buildTableModel());
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

            // the below column numbers likely need to change. I have no data to test with yet, so leaving that for you Matt

            // gather data from dataTable
            String id = String.valueOf(view.dataTable.getValueAt(row, 0));
            String productName = String.valueOf(view.dataTable.getValueAt(row, 2));
            String value = String.valueOf(view.dataTable.getValueAt(row, 3));
            String quantity = String.valueOf(view.dataTable.getValueAt(row, 3));

            // insert data into edit fields
            view.editProductIdField.setText(id);
            view.editProductNameField.setText(productName);
            view.editProductValueField.setText(value);
            view.editProductQuantityField.setText(quantity);
        }
    }

    private class disableProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO
        }
    }
}
