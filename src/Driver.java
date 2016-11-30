/**
 * Created by mattu on 11/14/16.
 */

import controllers.SessionsController;
import controllers.TestController;
import views.sessionsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        // launch sessions window
//        SessionsController sessController = new SessionsController();

        TestController TestController = new TestController();
    }
}
