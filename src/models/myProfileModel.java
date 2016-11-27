package models;

import common.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by alex on 11/27/16.
 */
public class myProfileModel extends Model {
    private User _user;

    public myProfileModel(User user) throws SQLException, IOException, ClassNotFoundException {
        super();
    }
}
