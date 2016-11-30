package models;

import common.Product;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ProductModel extends Model {

    public ProductModel() throws SQLException, IOException, ClassNotFoundException {
        super();
    }

    /**
     * Returns an ArrayList of all currently active products.
     * @return ArrayList<Product> products. ArrayList products.
     * @throws SQLException
     */
    public ArrayList<Product> getProducts() throws SQLException {
        String query = "SELECT * FROM products WHERE product_active = true";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet res = preparedStatement.executeQuery();

        ArrayList<Product> products = new ArrayList<Product>();

        while(res.next()) {
            int productID       = res.getInt(1);
            String productName  = res.getString(2);
            float productValue  = res.getFloat(3);
            int productQuantity = res.getInt(4);

            Product product = new Product(productName, productID, productValue, productQuantity);
            products.add(product);
        }

        return products;
    }

    /**
     * Inserts a new product into the DB.
     * @param product: Product object to insert.
     */
    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (product_name, product_cost, product_quantity) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, valueOf(product.getValue()));
        preparedStatement.setString(3, valueOf(product.getQuantity()));
        preparedStatement.executeUpdate();
    }
}
