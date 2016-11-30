import common.Product;
import controllers.SessionsController;
import models.ProductModel;

import java.io.IOException;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        // launch sessions window
        //SessionsController sessController = new SessionsController();
        Product product = new Product("test", 12.50, 3);
        ProductModel productModel = new ProductModel();
        productModel.addProduct(product);
    }
}
