package controllers;

import common.User;
import models.UserModel;
import views.athletesCard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * Created by alex on 11/27/16. Updated by Lucy 12/4/16
 */
public class AthletesController extends BasicController {
    UserModel userModel = new UserModel();
    athletesCard view;
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();


    public AthletesController(User user) throws SQLException, IOException, ClassNotFoundException {
        super(user);

        DefaultTableModel tableData = userModel.buildTableModel(userModel.returnUsersofType("Athletes"));

        view = new athletesCard(actionListeners);
        view.populate(tableData);

        masterView.addCard("My Profile", view);
    }

    private class fillTableAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = userModel.returnUsersofType("athlete");
                view.populate(userModel.buildTableModel(rs));
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
