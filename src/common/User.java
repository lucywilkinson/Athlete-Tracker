package common;

/**
 * Created by mattu on 11/14/16.
 */
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String userType;
    private String password;

    public User(int userId, String firstName, String lastName, String username, String userType, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userType = userType;
        this.password = password;
    }

    public int getUserId() {
        return this.userId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getUsername() {
        return this.username;
    }
    public String getUserType()  {
        return this.userType;
    }
    public String getPassword() {
        return this.password;
    }
}
