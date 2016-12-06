package controllers;

import common.User;
import models.UserModel;
import views.athletesCard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AthletesController extends BasicController {
    UserModel userModel = new UserModel();
    athletesCard view;

    public AthletesController(User user) throws SQLException, IOException, ClassNotFoundException {
        super(user);

        DefaultTableModel tableData = userModel.buildTableModel(("athlete"));

        actionListeners.put("newUserAction", new newUserAction());
        actionListeners.put("saveNewUserAction", new saveNewUserAction());

        view = new athletesCard(tableData, actionListeners);
        masterView.addCard("My Profile", view);

        view.dataTable.setModel(userModel.buildTableModel("athlete"));
    }


    private class newUserAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.launchNewUser();
        }
    }

    private class saveNewUserAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = view.newUserFirstNameField.getText();
            String lastName  = view.newUserLastNameField.getText();
            String username  = view.newUserUsernameField.getText();
            String email     = view.newUserEmailField.getText();
            String userType  = String.valueOf(view.newUserAccountTypeField.getSelectedItem());
            String password  = String.valueOf(view.newUserPasswordField.getPassword());
            String confirmPassword  = String.valueOf(view.newUserConfirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                User newUser = new User(firstName, lastName, username, userType, password, email);
                userModel.addUser(newUser);
                view.newUserFrame.dispatchEvent(new WindowEvent(view.newUserFrame, WindowEvent.WINDOW_CLOSING));
                view.dataTable.setModel(userModel.buildTableModel(userType));

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
