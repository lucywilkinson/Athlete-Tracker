package common;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String userType;
    private String password;
    private String email;
    private Boolean active = true;

    /**
     * User Object User to create and add a new user to the DB
     * @param firstName: users first name
     * @param lastName: users last name
     * @param username: unique username
     * @param userType: Athelete, Admin, Worker
     * @param password: user password
     * @param email: user email
     */
    public User(String firstName, String lastName, String username, String userType, String password, String email) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.username  = username;
        this.userType  = userType;
        this.password  = password;
        this.email     = email;
    }

    /**
     * Existing user object
     * @param id
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param email
     * @param userType
     */
    public User(int id, String firstName, String lastName, String username, String password, String email, String userType, boolean active) {
        this.userId    = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.username  = username;
        this.userType  = userType;
        this.password  = password;
        this.email     = email;
        this.active    = active;
    }

    // Getters
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
    public String getEmail() { return this.email; }
    public Boolean getStatus() { return this.active; }
}
