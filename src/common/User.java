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
     * @param userId: UUID
     * @param firstName: users first name
     * @param lastName: users last name
     * @param username: unique username
     * @param userType: Athelete, Admin, Worker
     * @param password: user password
     * @param email: user email
     */
    public User(int userId, String firstName, String lastName, String username, String userType, String password, String email) {
        this.userId    = userId;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.username  = username;
        this.userType  = userType;
        this.password  = password;
        this.email     = email;
    }

    /**
     * Existing user object. Used to pass user data through the application. Username and password not needed after login.
     * @param userId: UUID
     * @param firstName: users first name
     * @param lastName: users last name
     * @param userType: Athelete, Admin, Worker
     */
    public User(int userId, String firstName, String lastName, String userType, String email) {
        this.userId    = userId;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.userType  = userType;
        this.email     = email;
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
