package controllers;

import common.User;
import views.athletesCard;

/**
 * Created by alex on 11/27/16.
 */
public class AthletesController extends BasicController {
    athletesCard view;
    User _user;

    public AthletesController(User user) {
        super(user);

        view = new athletesCard(actionListeners);

        masterView.addCard("My Profile", view);
    }
}
