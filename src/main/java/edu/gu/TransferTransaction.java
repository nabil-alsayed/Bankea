package edu.gu;

// Represents a financial transfer transaction between two accounts
public class TransferTransaction extends Transaction {
    // Unique identifiers for the sender and recipient accounts
    private final String recipientAccountId;

    // Constructor to initialize a transfer transaction with sender, recipient, and amount
    public TransferTransaction(String senderAccountId, String recipientAccountId, double amount) {
        // Call the superclass constructor to set transaction type and amount
        super(TransactionType.TRANSFER, amount, senderAccountId);
        // Initialize sender and recipient account IDs
        this.recipientAccountId = recipientAccountId;
    }

    // Getter for the recipient account ID
    public String getRecipientAccountId() {
        return recipientAccountId;
    }

    @Override
    public String toString() {
        return this.getType() + " Transaction { " + '\n' +
                "   timestamp = " + getTimestamp() + '\n' +
                "   amount = " + getAmount() + '\n' +
                "   sender account ID='" + getAccountId() + '\n' +
                "   recipient account ID='" + recipientAccountId + '\n' +
                "}";
    }
}
