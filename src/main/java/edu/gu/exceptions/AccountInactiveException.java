package edu.gu.exceptions;

public class AccountInactiveException extends Exception {
    public AccountInactiveException(String message) {
        super(message);
    }
}