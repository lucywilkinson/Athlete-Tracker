package models;

import common.User;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (first_name, last_name, username, password, email, user_type)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getUserType());

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
    
    public ResultSet returnUsersOfType (String type) throws SQLException {
        String query = "SELECT * FROM users WHERE user_type = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, type);

        ResultSet res = preparedStatement.executeQuery();

        return res;
    }

    /**
     * Builds table from ResultSet of users from database. Code adapted from http://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
     * @param userType type of user admin/worker/athlete
     * @return TableModel to be passed to JTable constructor
     * @throws SQLException
     */
    public DefaultTableModel buildTableModel(String userType) throws SQLException {
        ResultSet res = returnUsersOfType(userType);
        ResultSetMetaData metaData = res.getMetaData();

        //fill column names
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for(int column = 1; column<=columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        //fill table data
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while(res.next()) {
            Vector<Object> vector = new Vector<Object>();
            for(int columnIndex = 1; columnIndex<=columnCount; columnIndex++) {
                vector.add(res.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
}
