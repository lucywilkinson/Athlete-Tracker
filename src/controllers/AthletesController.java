package controllers;

import common.User;
import views.athletesView;

import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/27/16.
 */
public class AthletesController extends BasicController {
    athletesView view;
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public AthletesController(User user) {
        super(user);

        view = new athletesView(actionListeners);
    }
}
