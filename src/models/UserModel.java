package models;

import java.io.IOException;
import java.sql.SQLException;
<<<<<<< HEAD
import java.util.Map;
=======
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Map;

import static java.lang.String.*;

>>>>>>> master
/**
 * Created by mattu on 11/18/16.
 */
public class UserModel extends Model {

    public UserModel() throws SQLException, IOException, ClassNotFoundException {
        super();
    }

    /**
<<<<<<< HEAD
     * Edits all user info fields in the DB.
     * @param edits map of userAttributeName -> NewValue
     * @return true if edit successful. False if edit unsuccessful
     */
    public boolean editUser(Map<String, String> edits) {
        return true;
=======
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
    public void editUser(Map<String, String> edits) throws SQLException {

        String query = "UPDATE users SET first_name=?, last_name=?, username=?, password=?, active=?, user_type=? WHERE user_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, edits.get("first_name"));
        preparedStatement.setString(2, edits.get("last_name" ));
        preparedStatement.setString(3, edits.get("username"  ));
        preparedStatement.setString(4, edits.get("password"  ));
        preparedStatement.setString(5, edits.get("active"    ));
        preparedStatement.setString(6, edits.get("user_type" ));
        preparedStatement.setString(7, edits.get("user_id"   ));

        preparedStatement.executeQuery();
>>>>>>> master
    }

    /**
     * "Deletes" a user profile by deactivating their account. Active attribute in DB set to false.
     * User info is not deleted as it could still be linked to shipments / orders.
     * @param userID ID of user to deactivate.
     * @return true if deactivation is successful. false if deactivation fails.
     */
<<<<<<< HEAD
    public boolean deleteProfile(int userID) {
=======
    public boolean deleteProfile(int userID) throws SQLException {

        // Query will succeed for any userID. Check user exists before updating table.
        if (!userExists(userID)) {
           return false;
        }

        String query = "UPDATE users SET active = false WHERE user_id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, valueOf(userID));
        ResultSet res = preparedStatement.executeQuery();

>>>>>>> master
        return true;
    }
}
