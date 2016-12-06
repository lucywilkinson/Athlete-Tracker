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
    private myProfileCard view;

    public MyProfileController() throws SQLException, IOException, ClassNotFoundException {
        // attach action listeners
        actionListeners.put("editProfileAction", new editProfileAction());
        actionListeners.put("saveProfileAction", new saveProfileAction());

        view = new myProfileCard(_user, actionListeners);

        masterView.addCard("My Profile", view);
    }

    private class saveProfileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = _user.getUserId(); //Don't want to be able to edit userID from text field...
            String firstName = view.firstNameField.getText();
            String lastName = view.lastNameField.getText();
            String email = view.emailField.getText();
            String accountType = view.accountTypeField.getSelectedItem().toString();

            User updatedUser = new User(id, firstName, lastName, _user.getUsername(), _user.getPassword(), email, accountType, _user.getStatus());

            try {
                // Update user in DB
                userModel.editUser(updatedUser);
                _user = updatedUser;
                view.populate(_user);
                view.makeEditable(false);

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class editProfileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.makeEditable(true);
        }
    }
}
