package edu.gu;

import edu.gu.exceptions.InvalidCredentialsException;

/**
 * Interface for managing user authentication and authorization.
 */
public interface IAuthenticationManager {

    /**
     * Creates a new user with the specified details.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @param email    The email address for the new user.
     * @param role     The role of the new user.
     * @return The newly created user.
     */
    User createUser(String username, String password, String email, User.Role role);

    /**
     * Logs in a user with the provided credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The logged-in user.
     * @throws InvalidCredentialsException If no user is found with the provided credentials.
     */
    User loginUser(String username, String password) throws InvalidCredentialsException;

    /**
     * Verifies user credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if the provided credentials are valid; false otherwise.
     */
    boolean verifyCredentials(String username, String password);
}
