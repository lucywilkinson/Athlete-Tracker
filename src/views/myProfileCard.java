package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class myProfileCard extends card {
    JLabel titleLabel = new JLabel("My Profile");

    public myProfileCard(HashMap<String, ActionListener> actionListeners) {
        super(actionListeners); // adds nav bar

        this.add(this.titleLabel);
    }
}
