package controllers;

import common.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import models.UserModel;
import views.myProfileCard;
import views.masterView;

public class MyProfileController extends BasicController {
    private UserModel userModel = new UserModel();
    private myProfileCard myProfileCard;

    public MyProfileController(User user) throws SQLException, IOException, ClassNotFoundException {
        super(user);

        // attach action listeners
        actionListeners.put("editProfileAction", new editProfileAction());
        actionListeners.put("saveProfileAction", new saveProfileAction());

        myProfileCard = new myProfileCard(user, actionListeners);
        masterView.addCard("My Profile", myProfileCard);
    }

    private class editProfileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(myProfileCard.idField.getText());
            String firstName = myProfileCard.firstNameField.getText();
            String lastName = myProfileCard.lastNameField.getText();
            String email = myProfileCard.emailField.getText();
            String accountType = myProfileCard.accountTypeField.getSelectedItem().toString();

            User updatedUser = new User(id, firstName, lastName, _user.getUsername(), _user.getPassword(), accountType, _user.getEmail());

            try {
                // Update user in DB
                userModel.editUser(updatedUser);

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class saveProfileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
}
