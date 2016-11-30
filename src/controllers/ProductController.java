package controllers;

import models.ProductModel;
import common.User;
import common.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mattu on 11/29/16.
 */
public class ProductController extends BasicController {
    ProductModel productModel;
    HashMap<String, ActionListener> actionListeners;

    public ProductController(User user) throws SQLException, IOException, ClassNotFoundException {
        super();

        actionListeners = new HashMap<String, ActionListener>();
        productModel = new ProductModel();

        actionListeners.put("addProduct",     new addProduct());
        actionListeners.put("editProducts",   new editProduct());
        actionListeners.put("disableProduct", new disableProduct());

        ArrayList<Product> products = productModel.getProducts();

    }

    private class addProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO
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
