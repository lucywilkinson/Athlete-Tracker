package models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
/**
 * Created by mattu on 11/18/16.
 */
public class UserModel extends Model {

    public UserModel() throws SQLException, IOException, ClassNotFoundException {
        super();
    }

    /**
     * Edits all user info fields in the DB.
     * @param edits map of userAttributeName -> NewValue
     * @return true if edit successful. False if edit unsuccessful
     */
    public boolean editUser(Map<String, String> edits) {

        return true;
    }

    /**
     * "Deletes" a user profile by deactivating their account. Active attribute in DB set to false.
     * User info is not deleted as it could still be linked to shipments / orders.
     * @param userID ID of user to deactivate.
     * @return true if deactivation is successful. false if deactivation fails.
     */
    public boolean deleteProfile(int userID) {
        return true;
    }
}
