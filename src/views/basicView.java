package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/26/16.
 */
public abstract class basicView extends JFrame {
    public JPanel mainWindow;

    // nav menu
    public JToolBar navMenu;
    public JButton adminsButton;
    public JButton athletesButton;
    public JButton warehouseWorkersButton;
    public JButton shipmentsButton;
    public JButton productsButton;
    public JButton myProfileButton;

    public basicView() {
        this.setContentPane(mainWindow);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    public basicView(HashMap<String, ActionListener> actionListeners) {
        this.setContentPane(mainWindow);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        this.attachNavMenuActionListeners(actionListeners);
    }

    public void close() {
        this.setVisible(false);
    }

    public void attachNavMenuActionListeners(HashMap<String, ActionListener> actionListeners) {
        athletesButton.addActionListener(actionListeners.get("clickAthletesButton"));
        adminsButton.addActionListener(actionListeners.get("clickAdminsButton"));
        myProfileButton.addActionListener(actionListeners.get("clickMyProfileButton"));
    }
}
