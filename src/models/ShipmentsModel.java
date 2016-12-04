package models;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentsModel extends Model {
    public ShipmentsModel() throws SQLException, IOException, ClassNotFoundException {
        super();
    }

    public ResultSet getRawShipmentData() throws SQLException {
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
}
