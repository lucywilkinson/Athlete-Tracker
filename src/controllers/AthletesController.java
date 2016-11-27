package controllers;

import common.User;
import views.athletesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/27/16.
 */
public class AthletesController {
    athletesView view;
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public AthletesController(User user) {
        this._user = user;

        // attach event listeners
        this.attachNavMenuListeners();

        view = new athletesView(actionListeners);
    }

    private void attachNavMenuListeners() {
        actionListeners.put("clickAthletesButton", new clickAthletesButton());
        actionListeners.put("clickMyProfileButton", new clickMyProfileButton());
    }

    private class clickAthletesButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AthletesController AthletesController = new AthletesController(_user);
            view.close();
        }
    }

    private class clickMyProfileButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MyProfileController MyProfileController = new MyProfileController(_user);
            view.close();
        }
    }
}
