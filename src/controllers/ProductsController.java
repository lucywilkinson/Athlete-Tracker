package controllers;

import models.ProductModel;
import common.User;
import common.Product;
import views.productsCard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            String productName = view.newProductNameField.getText();
            Float value = Float.parseFloat(view.newProductValueField.getText());
            Integer quantity = Integer.parseInt(view.newProductQuantityField.getText());

            System.out.println(productName + " " + value.toString() + " " + quantity.toString()); // working well, leaving this data for you to implement, Matt
        }
    }

    private class editProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO
        }
    }

    private class disableProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO
        }
    }
}
