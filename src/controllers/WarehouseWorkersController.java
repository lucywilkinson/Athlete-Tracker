package controllers;

import common.User;
import models.ProductModel;
import models.UserModel;
import views.warehouseWorkersCard;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class WarehouseWorkersController extends BasicController {
    UserModel userModel = new UserModel();
    warehouseWorkersCard view;
    User _user;


    public WarehouseWorkersController() throws SQLException, IOException, ClassNotFoundException {

        DefaultTableModel tableData = userModel.buildTableModel("worker");

        actionListeners.put("newUserAction", new newUserAction());

        view = new warehouseWorkersCard(actionListeners);
        masterView.addCard("WarehouseWorkers", view);

        view = new warehouseWorkersCard(actionListeners);
        view.populate(tableData);
    }


    private class fillTableAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                view.populate(userModel.buildTableModel("worker"));
            }
            catch (SQLException e1) {
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
}
