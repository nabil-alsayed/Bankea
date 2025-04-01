package edu.gu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Represents a container class for deserializing JSON data using Jackson.
 * The class is annotated with `JsonIgnoreProperties` to ignore any unknown properties during deserialization.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class JsonData {
    // List of users extracted from JSON data
    public ArrayList<User> users;

    // List of accounts extracted from JSON data
    public ArrayList<Account> accounts;
}
