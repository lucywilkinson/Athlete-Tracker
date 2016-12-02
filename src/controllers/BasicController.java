package controllers;

import common.User;
import views.basicView;
import views.masterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by alex on 11/27/16.
 */
public abstract class BasicController {
    basicView view;
    masterView masterView = new masterView();
    User _user;
    HashMap actionListeners = new HashMap<String, ActionListener>();

    public BasicController() {
        this.attachNavMenuListeners();
    }

    public BasicController(User user) {
        this.attachNavMenuListeners();
        this._user = user;
    }

    private void attachNavMenuListeners() {
        actionListeners.put("clickAthletesButton", new clickAthletesButton());
        actionListeners.put("clickAdminsButton", new clickAdminsButton());
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
            try {
                MyProfileController MyProfileController = new MyProfileController(_user);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            view.close();
        }
    }

    private class clickAdminsButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminsController AdminsController = new AdminsController(_user);
            view.close();
        }
    }
}
