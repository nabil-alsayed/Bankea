package edu.gu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

// Abstract class representing a financial transaction
public abstract class Transaction {
    // Unique identifier for the transaction
    private final String ID;

    // Timestamp indicating when the transaction occurred
    private final String timestamp;

    // Type of the transaction (e.g., deposit, withdrawal, transfer)
    private final TransactionType type;

    // Amount involved in the transaction
    private final double amount;

    // Account of the transaction
    private final String accountId;

    // Constructor to initialize common properties of transactions
    Transaction(TransactionType type, double amount, String accountId) {
        // Generate a unique identifier for the transaction
        this.ID = UUID.randomUUID().toString();

        // Set the type of the transaction
        this.type = type;

        // Set the amount involved in the transaction
        this.amount = amount;

        // Set the timestamp to the current date and time
        this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        // Set the accountId of the transaction
        this.accountId = accountId;
    }

    // Getter method to retrieve the unique identifier of the transaction
    public String getId() {
        return ID;
    }

    // Getter method to retrieve the amount involved in the transaction
    public double getAmount() {
        return amount;
    }

    // Getter method to retrieve the type of the transaction
    public TransactionType getType() {
        return this.type;
    }

    // Getter method to retrieve the timestamp of the transaction
    public String getTimestamp() {
        return timestamp;
    }

    public String getAccountId() {
        return accountId;
    }

    // Override the toString method to provide a string representation of the transaction
    @Override
    public String toString() {
        return type + " Transaction { " + '\n' +
                "   timestamp = " + timestamp + '\n' +
                "   amount = " + amount + '\n' +
                "   account ID='" + accountId + '\n' +
                "}";
    }

    // Override the equals method to compare transactions based on their properties
    @Override
    public boolean equals(Object anotherTransaction) {
        if (this == anotherTransaction) return true;
        if (anotherTransaction == null || getClass() != anotherTransaction.getClass()) return false;
        Transaction that = (Transaction) anotherTransaction;
        return this.ID.equals(that.ID) && Double.compare(that.amount, this.amount) == 0 &&
                Objects.equals(this.timestamp, that.timestamp) && this.type == that.type;
    }

}
