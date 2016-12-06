package models;

import common.Product;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

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

    public Product getProduct(String product_name) throws SQLException {
        String query = "SELECT * FROM products WHERE product_name = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, product_name);
        ResultSet res = preparedStatement.executeQuery();

        if (res.next()) {
            int productID       = res.getInt(1);
            String productName  = res.getString(2);
            float productValue  = res.getFloat(3);
            int productQuantity = res.getInt(4);

            Product product = new Product(productName, productID, productValue, productQuantity);
            return product;
        } else {
            return null;
        }
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

    private ResultSet getRawProductsData() throws SQLException {
        String productFields = "products.product_id, " +
                "products.product_name, " +
                "products.product_cost, " +
                "products.product_quantity, " +
                "products.product_active";

        String query = "SELECT " + productFields + " FROM products ";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet res = preparedStatement.executeQuery();

        return res;
    }

    public DefaultTableModel buildTableModel() throws SQLException {
        ResultSet res = getRawProductsData();
        ResultSetMetaData metaData = res.getMetaData();

        //fill column names
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        //fill table data
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (res.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(res.getObject(columnIndex));
            }
            data.add(vector);
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return tableModel;
    }
}
