package com.example.recycleme.model;

/**
 * An entity class for a User object
 * A User has the following attributes:
 * - String id: the user ID
 * - String email: the user Email address
 * - String password: the user password
 * @author Le Thanh Nguyen - u7594144
 */
public class User {
    private String id;
    private String email;
    private String password;

    public User() {

    }
    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
