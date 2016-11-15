package main.common;

/**
 * Created by mattu on 11/14/16.
 */
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String userType;

    public User(int userId, String firstName, String lastName, String username, String userType) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userType = userType;
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

    public String getUserType() {
        return this.userType;
    }
}
