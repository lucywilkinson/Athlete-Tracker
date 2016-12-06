package models;

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

