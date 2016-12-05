package controllers;

import common.User;
import models.ShipmentsModel;
import views.shipmentsCard;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * Created by mattu on 11/29/16. Updated by lucywilkinson on 12/5/16.
 */

public class ShipmentsController extends BasicController {

    ShipmentsModel shipmentsModel = new ShipmentsModel();
    shipmentsCard view;
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();


    public ShipmentsController(User user) throws SQLException, IOException, ClassNotFoundException{
        super(user);

        DefaultTableModel tableData = shipmentsModel.buildTableModel(shipmentsModel.getRawShipmentData());

        view = new shipmentsCard(actionListeners);
        view.populate(tableData);

        masterView.addCard("My Profile", view);
    }

    private class fillTableAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = shipmentsModel.getRawShipmentData();
                view.populate(shipmentsModel.buildTableModel(rs));
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}
