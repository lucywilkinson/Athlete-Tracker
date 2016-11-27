package controllers;

import common.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import models.myProfileModel;
import views.myProfileView;

/**
 * Created by alex on 11/26/16.
 */
public class MyProfileController {
    myProfileView view;
    myProfileModel model;
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public MyProfileController(User user) {
        this._user = user;

        // attach action listeners
        actionListeners.put("editProfileAction", new editProfileAction());
        actionListeners.put("saveProfileAction", new saveProfileAction());
        this.attachNavMenuListeners();

        view = new myProfileView(actionListeners, _user);
    }

    private void attachNavMenuListeners() {
        actionListeners.put("clickAthletesButton", new clickAthletesButton());
        actionListeners.put("clickMyProfileButton", new clickMyProfileButton());
    }

    private class editProfileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.startEditProfile();
        }
    }

    private class saveProfileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.saveEditProfile();
        }
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
