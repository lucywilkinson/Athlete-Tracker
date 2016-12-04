package controllers;

import common.User;
import views.shipmentsCard;

/**
 * Created by mattu on 11/29/16.
 */
public class ShipmentsController extends BasicController {
    public ShipmentsController(User user) {
        super();

        shipmentsCard shipmentsCard= new shipmentsCard(actionListeners);
        masterView.addCard("Shipments", shipmentsCard);
    }
}
