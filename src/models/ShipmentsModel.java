package models;

import com.sun.org.apache.xpath.internal.operations.Bool;
import common.Shipment;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Vector;

public class ShipmentsModel extends Model {

    public ShipmentsModel() throws SQLException, IOException, ClassNotFoundException {
        super();
    }

    public DefaultTableModel filterShipments(Boolean pending, Boolean fullfilled) throws SQLException {
        String shipmentFields = "shipments.shipment_creator, " +
                "shipments.shipment_worker, " +
                "products.product_name, " +
                "shipments.shipment_quantity, " +
                "users.first_name, " +
                "users.last_name, " +
                "addresses.address, " +
                "cities.city_name, " +
                "cities.state_name, " +
                "countries.country_name";


        String query = "SELECT " + shipmentFields + " FROM shipments " +
                "inner join addresses on shipments.shipment_address = addresses.address_id " +
                "inner join cities on addresses.city_id = cities.city_id " +
                "inner join countries on countries.country_id = addresses.country_id " +
                "inner join users on shipments.shipment_reciever = users.user_id " +
                "inner join products on products.product_id = shipments.shipment_product";

        if (pending && fullfilled) {
        } else if (pending) {
            query += " WHERE shipment_fulfilled = 0";
        } else {
            query += " WHERE shipment_fulfilled = 1";
        }

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet res = preparedStatement.executeQuery();

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

    private void updateProductStock(String product, int shipmentQuantity) throws SQLException {
        String query = "UPDATE products SET product_quantity = product_quantity + ? WHERE product_name = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, shipmentQuantity);
        preparedStatement.setString(2, product);

        preparedStatement.executeUpdate();
    }

    public int getShipmentID(String productName) throws SQLException {
        String query = "SELECT shipment_id FROM shipments " +
                "WHERE shipment_product = (SELECT product_id FROM products WHERE product_name = ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, productName);

        ResultSet res = preparedStatement.executeQuery();

        int shipmentID = 0;
        while (res.next()) {
            shipmentID = res.getInt(1);
            break;
        }

        return shipmentID;
    }

    public int getProductQuantity(String productName) throws SQLException {
        String query = "SELECT product_quantity FROM products WHERE product_name = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, productName);

        ResultSet res = preparedStatement.executeQuery();

        int productQuantity = 0;
        while (res.next()) {
            productQuantity = res.getInt(1);
            break;
        }

        return productQuantity;
    }

    public void editShipment(int shipmentID, Shipment shipment) throws SQLException {
        String query = "SELECT shipment_quantity FROM shipments WHERE shipment_id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(shipmentID));

        ResultSet res = preparedStatement.executeQuery();

        int previousShipmentQuantity = 0;

        while(res.next()) {
           previousShipmentQuantity = res.getInt(1);
        }

        int quantityDifference =  previousShipmentQuantity - shipment.getQuantity();
        updateProductStock(shipment.getProduct(), quantityDifference);

        query = "UPDATE shipments SET " +
                "shipment_product  = (SELECT product_id FROM products WHERE product_name = ?), " +
                "shipment_creator  = ?, " +
                "shipment_worker   = ?, " +
                "shipment_reciever = ?, " +
                "shipment_quantity = ?, " +
                "shipment_fulfilled = ? " +
                "WHERE shipment_id = ?";

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, shipment.getProduct());
        preparedStatement.setInt(2, shipment.getCreatorID());
        preparedStatement.setInt(3, shipment.getWorkerID());
        preparedStatement.setInt(4, shipment.getAthleteID());
        preparedStatement.setInt(5, shipment.getQuantity());
        preparedStatement.setInt(6, shipmentID);
        preparedStatement.setBoolean(7, shipment.getFulfilled());

        preparedStatement.executeUpdate();
    }

    public void addShipment(Shipment shipment) throws SQLException {
        String query = "INSERT INTO shipments (shipment_product, shipment_creator, shipment_worker, shipment_reciever, shipment_address, shipment_quantity, shipment_fulfilled)" +
                "VALUES (" +
                "(SELECT product_id FROM products WHERE product_name = ?)," +
                "?, ?, ?," +
                "(SELECT address_id FROM users_addresses WHERE user_id = ?), " +
                "?, ?)";


        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, shipment.getProduct());
        preparedStatement.setInt(2, shipment.getCreatorID());
        preparedStatement.setInt(3, shipment.getWorkerID());
        preparedStatement.setInt(4, shipment.getAthleteID());
        preparedStatement.setInt(5, shipment.getAthleteID());
        preparedStatement.setInt(6, shipment.getQuantity());
        preparedStatement.setBoolean(7, shipment.getFulfilled());

        preparedStatement.executeUpdate();

        int quantityDifference = (shipment.getQuantity() * -1);
        updateProductStock(shipment.getProduct(), quantityDifference);
    }

    private ResultSet getRawShipmentData() throws SQLException {
        String shipmentFields = "shipments.shipment_creator, " +
                "shipments.shipment_worker, " +
                "products.product_name, " +
                "shipments.shipment_quantity, " +
                "users.first_name, " +
                "users.last_name, " +
                "addresses.address, " +
                "cities.city_name, " +
                "cities.state_name, " +
                "countries.country_name";

        String query = "SELECT " + shipmentFields + " FROM shipments " +
                "inner join addresses on shipments.shipment_address = addresses.address_id " +
                "inner join cities on addresses.city_id = cities.city_id " +
                "inner join countries on countries.country_id = addresses.country_id " +
                "inner join users on shipments.shipment_reciever = users.user_id " +
                "inner join products on products.product_id = shipments.shipment_product;";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet res = preparedStatement.executeQuery();

        return res;
    }

    public DefaultTableModel buildTableModel() throws SQLException {
        ResultSet res = getRawShipmentData();
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

