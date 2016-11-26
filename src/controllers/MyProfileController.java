package controllers;

import common.User;
import models.SessionsModel;
import views.myProfileView;
import views.sessionsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by alex on 11/26/16.
 */
public class MyProfileController {
    myProfileView view;
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public MyProfileController(User user) {
        this._user = user;

        // attach action listeners
        actionListeners.put("editProfileAction", new editProfileAction());

        view = new myProfileView(actionListeners, _user);
    }

    public void setName(String name) {
        view.setNameText(name);
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

        }
    }
}
