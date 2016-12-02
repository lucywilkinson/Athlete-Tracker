package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class card extends JPanel {

    GridBagConstraints constraints = new GridBagConstraints();

    public card() {

    }

    public card(HashMap<String, ActionListener> actionListeners) {
        this.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        this.add(new navBar(actionListeners), constraints);
    }
}
