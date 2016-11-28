package controllers;

import common.User;
import views.adminsView;

import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/27/16.
 */
public class AdminsController extends BasicController {
    adminsView view;
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public AdminsController(User user) {
        super(user);

        this.view = new adminsView(actionListeners);
    }

}
