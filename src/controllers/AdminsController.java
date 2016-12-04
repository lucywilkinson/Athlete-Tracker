package controllers;

import common.User;
import views.adminsCard;

/**
 * Created by alex on 11/27/16.
 */
public class AdminsController extends BasicController {
    public AdminsController(User user) {
        super(user);

        adminsCard adminsCard = new adminsCard(actionListeners);
        masterView.addCard("Shipments", adminsCard);
    }

}
