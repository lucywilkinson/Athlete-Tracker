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

    public ProductsController(User user) throws SQLException, IOException, ClassNotFoundException {
        super();

        productModel = new ProductModel();

        actionListeners.put("addProduct",     new addProduct());
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
