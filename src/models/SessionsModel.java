package models;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import common.User;

/**
 * Created by mattu on 11/14/16.
 */

public class SessionsModel extends Model {

    public SessionsModel() throws SQLException, ClassNotFoundException, IOException {
        super();
    }

    /**
     * Validates user login credentials.
     * @param _username Username used to validate user
     * @param _password Password used to validate user
     * @return User object if validation is successful. Null if validation fails.
     * @throws SQLException
     */
    public User validateLogin(String _username, String _password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, _username);
        preparedStatement.setString(2, _password);

        ResultSet res = preparedStatement.executeQuery();

        /* Validation Successful. Create user object */
        if (res.next()) {
            int userId       = res.getInt(1);
            String firstName = res.getString(2);
            String lastName  = res.getString(3);
            String username  = res.getString(4);
            String password  = res.getString(5);
            String email     = res.getString(6);
            String userType  = res.getString(8);

            return new User(userId, firstName, lastName, username, password, email, userType);
        }

        /* Invalid username || password */
        return null;
    }
}
