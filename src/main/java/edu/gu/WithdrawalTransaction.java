package edu.gu;

// Represents a withdrawal transaction in the banking system
public class WithdrawalTransaction extends Transaction {
    // ID of the account associated with the withdrawal
    private final String accountId;

    // Constructor for creating a withdrawal transaction
    public WithdrawalTransaction(String accountId, double amount) {
        // Calls the constructor of the superclass (Transaction) with the withdrawal transaction type and amount
        super(TransactionType.WITHDRAWAL, amount, accountId);
        // Initializes the account ID
        this.accountId = accountId;
    }

    // Getter for the account ID associated with the withdrawal
    public String getAccountId() {
        return this.accountId;
    }
}
