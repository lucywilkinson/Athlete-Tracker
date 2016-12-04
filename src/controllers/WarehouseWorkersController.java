package controllers;

import common.User;
import models.ProductModel;
import views.warehouseWorkersCard;

/**
 * Created by mattu on 11/29/16.
 */
public class WarehouseWorkersController extends BasicController {
    ProductModel productModel;

    public WarehouseWorkersController(User user) {
        super();

        warehouseWorkersCard warehouseWorkersCard = new warehouseWorkersCard(actionListeners);
        masterView.addCard("WarehouseWorkers", warehouseWorkersCard);
    }

}
