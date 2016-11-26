package controllers;

import models.SessionsModel;
import views.myProfileView;
import views.sessionsView;
import common.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by mattu on 11/14/16.
 */
public class SessionsController {
    SessionsModel sessionsModel = new SessionsModel();
    sessionsView view;
    User user;

    public SessionsController() throws SQLException, IOException, ClassNotFoundException {
        loginAction loginAction = new loginAction();

        HashMap actionListeners = new HashMap<String, ActionListener>();
        actionListeners.put("loginAction", new loginAction());

        view = new sessionsView(actionListeners);
    }

    private class loginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Get text from view
            String username = view.usernameField.getText();
            String password = String.valueOf(view.passwordField.getPassword());

            try {
                // Use session model to validate login credentials
                user = sessionsModel.validateLogin(username, password);

                if(user != null) {
                    // launch myProfile
                    MyProfileController MyProfileController = new MyProfileController(user);
                    view.close();
                } else {
                    // Return user info to view
                    // view.displayUserInformation(user);

                    // display error message in notification
                    view.setNotification("Login failed. Please try again.");
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
    }
}
