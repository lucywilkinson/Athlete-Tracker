package controllers;

import common.User;
import models.ProductModel;
import views.warehouseWorkersCard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mattu on 11/29/16.
 */
public class WarehouseWorkersController extends BasicController {
    ProductModel productModel;
    warehouseWorkersCard view;

    public WarehouseWorkersController(User user) {
        super();

        actionListeners.put("newUserAction", new newUserAction());

        view = new warehouseWorkersCard(actionListeners);
        masterView.addCard("WarehouseWorkers", view);
    }

    private class newUserAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.launchNewUser();
        }
    }
}
