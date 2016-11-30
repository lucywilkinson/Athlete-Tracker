package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class adminsCard extends card {
    JLabel titleLabel = new JLabel("Admins");

    public adminsCard(HashMap<String, ActionListener> actionListeners) {
        super(actionListeners); // adds nav bar

        this.add(this.titleLabel);
    }
}
