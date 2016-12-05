package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class card extends JPanel {

    GridBagConstraints constraints = new GridBagConstraints();

    String[] accountTypes = {
            "admin",
            "athlete",
            "worker"
    };

    public card() {

    }

    public card(HashMap<String, ActionListener> actionListeners) {
        this.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridwidth =  GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(0, 0, 0, 0);
        this.add(new navBar(actionListeners), constraints);
    }
}
