package controllers;

import common.User;
import models.UserModel;
import views.adminsCard;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by alex on 11/27/16. Updated by Lucy on 12/4/16
 */

public class AdminsController extends BasicController {

    UserModel userModel = new UserModel();
    adminsCard view;
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public AdminsController(User user) throws SQLException, IOException, ClassNotFoundException {
        super(user);

        DefaultTableModel tableData = userModel.buildTableModel(userModel.returnUsersofType("admin"));

        view = new adminsCard(actionListeners);
        view.populate(tableData);

        masterView.addCard("My Profile", view);
    }

    private class fillTableAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = userModel.returnUsersofType("admin");
                view.populate(userModel.buildTableModel(rs));
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}
