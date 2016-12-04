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
            String id = myProfileCard.idField.getText();
            String firstName = myProfileCard.firstNameField.getText();
            String lastName = myProfileCard.lastNameField.getText();
            String email = myProfileCard.emailField.getText();
            String accountType = myProfileCard.accountTypeField.getSelectedItem().toString();
            String accountEnabled = String.valueOf(!myProfileCard.accountStatusField.isSelected());

            HashMap<String, String> edits = new HashMap<String, String>();
            edits.put("id", id);
            edits.put("firstName", firstName);
            edits.put("lastName", lastName);
            edits.put("email", email);
            edits.put("accountType", accountType);
            edits.put("accountEnabled", accountEnabled);

            try {
                // Update user in DB
                userModel.editUser(edits);

                // Retrieve updated user from DB
                User updatedUser = userModel.getUserData(Integer.parseInt(id));


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

    private void insertData(User user) {
    }
}
