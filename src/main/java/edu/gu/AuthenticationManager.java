package edu.gu;

import edu.gu.exceptions.InvalidCredentialsException;

import java.util.List;
import java.util.UUID;

/**
 * Manages user authentication and registration.
 */
public class AuthenticationManager implements IAuthenticationManager {
    // List to store registered users
    private final List<User> users;

    /**
     * Constructs an AuthenticationManager with a list of existing users.
     *
     * @param users The list of users to be managed.
     */
    public AuthenticationManager(List<User> users) {
        this.users = users;
    }

    /**
     * Creates a new user with the specified details and registers the user.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @param email    The email address for the new user.
     * @param role     The role of the new user.
     * @return The newly created and registered user.
     */
    public User createUser(
            String username,
            String password,
            String email,
            User.Role role
    ) {
        return registerUser(new User(UUID.randomUUID().toString(), username, password, email, role));
    }

    /**
     * Registers a user if the username and email are unique.
     *
     * @param user The user to be registered.
     * @return The registered user or null if registration fails.
     */
    private User registerUser(User user) {
        // Check if the username or email is already registered
        for (User registeredUser : users) {
            if (registeredUser.getUsername().equals(user.getUsername())) {
                System.out.println("Username '" + user.getUsername() + "' is already taken");
                return null;
            }
            if (registeredUser.getEmail().equals(user.getEmail())) {
                System.out.println("Email '" + user.getEmail() + "' is already registered");
                return null;
            }
        }

        // If username and email are unique, add the user to the registered users list
        users.add(user);
        return user;
    }

    /**
     * Retrieves a user based on their username.
     *
     * @param username The username to search for.
     * @return The user with the specified username or null if not found.
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Logs in a user with the provided credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The logged-in user.
     * @throws InvalidCredentialsException If no user is found with the provided credentials.
     */
    public User loginUser(String username, String password) throws InvalidCredentialsException {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new InvalidCredentialsException("User not found with the provided credentials");
    }

    /**
     * Verifies user credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if the provided credentials are valid; false otherwise.
     */
    public boolean verifyCredentials(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getPassword().equals(password);
            }
        }
        return false;
    }
}
