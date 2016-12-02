package models;

import common.User;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.*;

public class UserModel extends Model {

    public UserModel() throws SQLException, IOException, ClassNotFoundException {
        super();
    }

    /**
     * Accepts a userID and checks if user exxists in DB.
     * @param userID ID of user in question. used to determine if user exists.
     * @return true if user exists, false if user does not exist.
     * @throws SQLException
     */
    private boolean userExists(int userID) throws SQLException {
        String query = "SELECT user_id FROM users WHERE user_id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, valueOf(userID));
        ResultSet res = preparedStatement.executeQuery();

        return res.next();
    }

    /**
     * Updates all user fields in DB. Map must contain values for all user fields. To keep previous values in DB, set
     * the user values to the current value in the edits map.
     * @param edits: Map of user field name to user field value.
     * @throws SQLException
     */
    public void editUser(HashMap<String, String> edits) throws SQLException {

        String query = "UPDATE users SET first_name=?, last_name=?, email=?, active=?, user_type=? WHERE user_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, edits.get("firstName"));
        preparedStatement.setString(2, edits.get("lastName"));
        preparedStatement.setString(3, edits.get("email"));
        preparedStatement.setBoolean(4, Boolean.parseBoolean(edits.get("accountEnabled")));
        preparedStatement.setString(5, edits.get("accountType"));
        preparedStatement.setString(6, edits.get("id"));

        preparedStatement.executeUpdate();
    }

    public User getUserData(int userID) throws SQLException {
        String query = "SELECT * FROM users WHERE user_id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, valueOf(userID));

        ResultSet res = preparedStatement.executeQuery();

        if (res.next()) {
            int id           = res.getInt(1);
            String firstName = res.getString(2);
            String lastName  = res.getString(3);
            String email     = res.getString(6);
            String userType  = res.getString(8);

            return new User(id, firstName, lastName, userType, email);
        }

        throw new SQLException();
    }

    /**
     * "Deletes" a user profile by deactivating their account. Active attribute in DB set to false.
     * User info is not deleted as it could still be linked to shipments / orders.
     * @param userID ID of user to deactivate.
     * @return true if deactivation is successful. false if deactivation fails.
     */
    public boolean deleteProfile(int userID) throws SQLException {

        // Query will succeed for any userID. Check user exists before updating table.
        if (!userExists(userID)) {
           return false;
        }

        String query = "UPDATE users SET active = false WHERE user_id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, valueOf(userID));
        ResultSet res = preparedStatement.executeQuery();

        return true;
    }
}
