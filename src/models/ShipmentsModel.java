package models;

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

    public void editShipment(Shipment shipment) throws SQLException{
        String query = "UPDATE shipments SET shipment_product = ?, shipment_creator = ?, shipment_worker = ?, shipment_reciever = ?, shipment_address = ?, shipment_quantity = ?, shipment_fulfilled = ? WHERE shipment_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, shipment.getProduct());
        preparedStatement.setString(2, shipment.getCreator());
        preparedStatement.setString(3, shipment.getWorker());
        preparedStatement.setString(4, shipment.getReciever());
        preparedStatement.setString(5, shipment.getAddress());
        preparedStatement.setString(6, String.valueOf(shipment.getQuantity()));
        preparedStatement.setString(7, String.valueOf(shipment.getFulfilled()));
        preparedStatement.setString(8, String.valueOf(shipment.getShipmentId()));

        preparedStatement.executeUpdate();
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

