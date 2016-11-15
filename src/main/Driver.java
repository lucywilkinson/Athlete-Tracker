package main;

import main.models.SessionsModel;
import main.common.User;
import java.sql.SQLException;

/**
 * Created by mattu on 11/14/16.
 */
public class Driver {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SessionsModel sessionModel = new SessionsModel();
        User user = sessionModel.validateLogin("Clacious", "monkeyfunky");

        if (user == null) {
            System.out.println("INVALID LOGIN");
        } else {
            String username  = user.getUsername();
            String firstName = user.getFirstName();
            String lastName  = user.getLastName();
            String userType  = user.getUserType();

            System.out.println(username);
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(userType);
        }
    }
}
