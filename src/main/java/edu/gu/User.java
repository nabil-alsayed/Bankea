package edu.gu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// Represents a user in the banking system
public class User {
    // Unique identifier for the user
    private final String id;
    // Username chosen by the user
    private final String username;
    // Role assigned to the user (e.g., CLIENT, CUSTOMER_SERVICE, CREDIT, ADMIN)
    private final Role role;
    // Password for user authentication
    private String password;
    // Email address associated with the user
    private String email;
    // Flag indicating whether the user account is active
    private boolean active;

    // Constructor for creating a user using JSON properties
    @JsonCreator
    public User(
            @JsonProperty("id") String id,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("email") String email,
            @JsonProperty("role") Role role
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = true;
    }

    // Getter for user ID
    public String getId() {
        return id;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter and setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for user role
    public Role getRole() {
        return role;
    }

    // Method to change user password
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // Additional methods for managing user properties

    // Method to deactivate the user account
    public void deactivate() {
        this.active = false;
    }

    // Method to check if the user account is inactive
    public boolean isInactive() {
        return !this.active;
    }

    // Enumeration representing user roles
    public enum Role {
        CLIENT,
        CUSTOMER_SERVICE,
        CREDIT,
        ADMIN
    }
}
