package controllers;

import common.User;
import views.adminsCard;
import views.card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alex on 11/27/16.
 */
public class AdminsController extends BasicController {
    adminsCard view;

    public AdminsController(User user) {
        super(user);

        actionListeners.put("newUserAction", new newUserAction());

        view = new adminsCard(actionListeners);
        masterView.addCard("Shipments", view);
    }

    private class newUserAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.launchNewUser();
        }
    }
}
