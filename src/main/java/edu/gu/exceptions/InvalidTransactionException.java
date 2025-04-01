package edu.gu.exceptions;

public class InvalidTransactionException extends Exception {

    public InvalidTransactionException(String msg) {
        super(msg);
    }

    public InvalidTransactionException() {
        super();
    }
}
