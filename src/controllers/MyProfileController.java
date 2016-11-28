package controllers;

import common.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import views.myProfileView;

/**
 * Created by alex on 11/26/16.
 */
public class MyProfileController extends BasicController {
    myProfileView view;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public MyProfileController(User user) {
        super(user);

        // attach action listeners
        actionListeners.put("editProfileAction", new editProfileAction());
        actionListeners.put("saveProfileAction", new saveProfileAction());

        view = new myProfileView(actionListeners);

        this.insertData(_user);
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

    private void insertData(User user) {
        view.setNameText(user.getFirstName() + " " + user.getLastName());
        view.setIdText(Integer.toString(user.getUserId()));
        view.setUserType(user.getUserType());
    }
}
