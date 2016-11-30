package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class shipmentsCard extends card {
    JLabel titleLabel = new JLabel("Shipments");

    public shipmentsCard(HashMap<String, ActionListener> actionListeners) {
        super(actionListeners); // adds nav bar

        this.add(this.titleLabel);
    }
}
