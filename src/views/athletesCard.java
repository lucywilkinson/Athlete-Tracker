package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class athletesCard extends card {
    JLabel titleLabel = new JLabel("Athletes");

    public athletesCard(HashMap<String, ActionListener> actionListeners) {
        super(actionListeners); // adds nav bar

        this.add(this.titleLabel);
    }
}
