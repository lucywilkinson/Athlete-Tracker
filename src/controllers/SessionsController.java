package controllers;

import models.SessionsModel;
import views.masterView;
import views.sessionsCard;
import common.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class SessionsController extends BasicController {
    SessionsModel sessionsModel = new SessionsModel();
    sessionsCard sessionsCard;

    public SessionsController() throws SQLException, IOException, ClassNotFoundException {
        actionListeners.put("loginAction", new loginAction());

        sessionsCard = new sessionsCard(actionListeners);
        masterView.addCard("Sessions", sessionsCard);
    }

    private class loginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Get text from view
            String username = sessionsCard.usernameField.getText();
            String password = String.valueOf(sessionsCard.passwordField.getPassword());

            try {
                // Use session model to validate login credentials
                _user = sessionsModel.validateLogin(username, password);
                
                if(_user != null) {
                    // launch myProfile
                    MyProfileController MyProfileController = new MyProfileController();
                } else {
                    System.out.println("LOGIN FAILED");
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
