package controllers;

import common.User;
import models.ProductModel;
import models.UserModel;
import views.warehouseWorkersCard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class WarehouseWorkersController extends BasicController {
    UserModel userModel = new UserModel();
    warehouseWorkersCard view;

    public WarehouseWorkersController() throws SQLException, IOException, ClassNotFoundException {

        actionListeners.put("newUserAction", new newUserAction());
        actionListeners.put("saveNewUserAction", new saveNewUserAction());
        actionListeners.put("editWorkerAction", new editWorkerAction());
        actionListeners.put("saveEditsAction", new saveEditsAction());

        view = new warehouseWorkersCard(actionListeners);
        masterView.addCard("Workers", view);

        view.dataTable.setModel(userModel.buildTableModel("worker"));
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
            int    id        = Integer.parseInt(view.editUserIdField.getText());
            String firstName = view.editUserFirstNameField.getText();
            String lastName  = view.editUserLastNameField.getText();
            String username  = view.editUserUsernameField.getText();
            String email     = view.editUserEmailField.getText();
            String userType  = String.valueOf(view.editUserAccountTypeField.getSelectedItem());

            // Placeholder password
            String password  = "";

            User updatedUser = new User(id, firstName, lastName, username, password, email, userType);

            try {
                // Update user in DB
                userModel.editUser(updatedUser);
                view.editUserFrame.dispatchEvent(new WindowEvent(view.editUserFrame, WindowEvent.WINDOW_CLOSING));
                view.dataTable.setModel(userModel.buildTableModel(userType));

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class editWorkerAction implements ActionListener {
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

    private class saveEditsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int    id        = Integer.parseInt(view.editUserIdField.getText());
            String firstName = view.editUserFirstNameField.getText();
            String lastName  = view.editUserLastNameField.getText();
            String username  = view.editUserUsernameField.getText();
            String email     = view.editUserEmailField.getText();
            String userType  = String.valueOf(view.editUserAccountTypeField.getSelectedItem());

            // Placeholder
            String password  = "";

            User updatedUser = new User(id, firstName, lastName, username, password, email, userType);

            try {
                // Update user in DB
                userModel.editUser(updatedUser);
                view.editUserFrame.dispatchEvent(new WindowEvent(view.editUserFrame, WindowEvent.WINDOW_CLOSING));
                view.dataTable.setModel(userModel.buildTableModel(userType));


            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
