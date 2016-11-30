import controllers.SessionsController;

import java.io.IOException;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        SessionsController sessController = new SessionsController();
    }
}
