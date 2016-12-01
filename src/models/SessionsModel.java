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
     * @param username Username used to validate user
     * @param password Password used to validate user
     * @return User object if validation is successful. Null if validation fails.
     * @throws SQLException
     */
    public User validateLogin(String username, String password) throws SQLException {
<<<<<<< HEAD
        System.out.println("-------------------");
        System.out.println("       Model"       );
        System.out.println("-------------------");

=======
>>>>>>> master
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet res = preparedStatement.executeQuery();

        /* Validation Successful. Create user object */
        if (res.next()) {
            int userID       = res.getInt(1);
            String firstName = res.getString(2);
            String lastName  = res.getString(3);
            String userType  = res.getString(6);

            return new User(userID, firstName, lastName, username, userType, password);
        }

        /* Invalid username || password */
        return null;
    }
}
