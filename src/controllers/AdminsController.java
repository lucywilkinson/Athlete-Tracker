package controllers;

import common.User;
import models.UserModel;
import views.adminsCard;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class AdminsController extends BasicController {

    UserModel userModel = new UserModel();
    adminsCard view;

    public AdminsController() throws SQLException, IOException, ClassNotFoundException {

        DefaultTableModel tableData = userModel.buildTableModel("admin");

        actionListeners.put("newUserAction", new newUserAction());
        actionListeners.put("saveNewUserAction", new saveNewUserAction());
        actionListeners.put("editAdminAction", new editAdminAction());
        actionListeners.put("saveEditAdminAction", new saveEditAdminAction());
        actionListeners.put("filterUsersAction", new filterUsers());

        view = new adminsCard(tableData, actionListeners);
        masterView.addCard("Admins", view);

        view.dataTable.setModel(userModel.buildTableModel("admin"));
    }

    private class filterUsers implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean active = view.activeCheckbox.isSelected();
            boolean inactive = view.inactiveCheckbox.isSelected();

            try {
                view.dataTable.setModel(userModel.filterUsers("admin", active, inactive));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
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

    private class editAdminAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.launchEditUser();

            int row = view.dataTable.getSelectedRow();

            String id        = String.valueOf(view.dataTable.getValueAt(row, 0));
            String firstName = String.valueOf(view.dataTable.getValueAt(row, 1));
            String lastName  = String.valueOf(view.dataTable.getValueAt(row, 2));
            String username  = String.valueOf(view.dataTable.getValueAt(row, 3));
            String email     = String.valueOf(view.dataTable.getValueAt(row, 4));

            view.editUserIdField.setText(id);
            view.editUserFirstNameField.setText(firstName);
            view.editUserLastNameField.setText(lastName);
            view.editUserUsernameField.setText(username);
            view.editUserEmailField.setText(email);
        }
    }

    private class saveEditAdminAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int    id        = Integer.parseInt(view.editUserIdField.getText());
            String firstName = view.editUserFirstNameField.getText();
            String lastName  = view.editUserLastNameField.getText();
            String username  = view.editUserUsernameField.getText();
            String email     = view.editUserEmailField.getText();
            String userType  = String.valueOf(view.editUserAccountTypeField.getSelectedItem());
            Boolean active   = view.editUserStatusField.isSelected();

            // Placeholder
            String password  = "";

            User updatedUser = new User(id, firstName, lastName, username, password, email, userType, active);

            try {
                // Update user in DB
                userModel.editUser(updatedUser);
                view.editUserFrame.dispatchEvent(new WindowEvent(view.editUserFrame, WindowEvent.WINDOW_CLOSING));
                view.dataTable.setModel(userModel.buildTableModel("admin"));


            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}
