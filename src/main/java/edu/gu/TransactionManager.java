package edu.gu;

import edu.gu.exceptions.AccountInactiveException;
import edu.gu.exceptions.InsufficientFundsException;
import edu.gu.exceptions.InvalidTransactionException;

import java.util.ArrayList;

// Class responsible for managing financial transactions
public class TransactionManager implements ITransactionManager {

    // List to store recorded transactions
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    // Record a transaction involving a single account
    public void recordTransaction(Account targetAccount, TransactionType transactionType, double amount)
            throws InsufficientFundsException, AccountInactiveException {
        // Create a transaction based on the specified type
        Transaction transaction = switch (transactionType) {
            case DEPOSIT -> {
                Transaction transactionUnderProcessing = new DepositTransaction(targetAccount.getId(), amount);
                // @TODO AccountManager should handle such parts
                targetAccount.deposit(amount);
                yield transactionUnderProcessing;
            }
            case WITHDRAWAL -> {
                Transaction transactionUnderProcessing = new WithdrawalTransaction(targetAccount.getId(), amount);
                // @TODO AccountManager should handle such parts
                targetAccount.withdraw(amount);
                yield transactionUnderProcessing;
            }
            default -> throw new IllegalArgumentException("Unsupported transaction type: " + transactionType);
        };

        // Add the transaction to the list
        this.transactions.add(transaction);
    }

    // Record a transaction involving two accounts (transfer)
    public void recordTransaction(Account senderAccount, Account recipientAccount, TransactionType transactionType, double amount)
            throws InsufficientFundsException, AccountInactiveException {
        // Create a transfer transaction
        Transaction transaction = new TransferTransaction(senderAccount.getId(), recipientAccount.getId(), amount);

        // @TODO AccountManager should handle such parts
        senderAccount.withdraw(amount);
        recipientAccount.deposit(amount);

        // Add the transaction to the list
        this.transactions.add(transaction);
    }

    // Get details of a specific transaction based on its ID
    public Transaction getTransactionDetails(String TransactionID) throws InvalidTransactionException {
        Transaction requestedTransaction = null;

        // Loop through transactions to find the requested one
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(TransactionID)) {
                requestedTransaction = transaction;
            } else throw new InvalidTransactionException("Transaction ID is invalid");
        }
        return requestedTransaction;
    }

    // Validate if a transaction is present in the list of transactions for a given account
    public boolean validateTransaction(Transaction transaction, Account account) throws InvalidTransactionException {
        boolean isValid = this.getTransactions().contains(transaction);
        if (!isValid) {
            throw new InvalidTransactionException("Transaction object is Invalid");
        } else {
            return true;
        }
    }

    // Get the list of recorded transactions
    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

}
