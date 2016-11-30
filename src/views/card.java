package views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alex on 11/29/16.
 */
public class card extends JPanel {

    public card(HashMap<String, ActionListener> actionListeners) {
        this.add(new navBar(actionListeners));
    }
}
