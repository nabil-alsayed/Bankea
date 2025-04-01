package edu.gu;

import edu.gu.exceptions.AccountInactiveException;
import edu.gu.exceptions.InsufficientFundsException;
import edu.gu.exceptions.InvalidTransactionException;

/**
 * Interface for managing banking transactions.
 */
public interface ITransactionManager {

    /**
     * Records a transaction between two accounts.
     *
     * @param senderAccount    The account initiating the transaction.
     * @param recipientAccount The account receiving the transaction.
     * @param transactionType  The type of transaction (e.g., deposit, withdrawal, transfer).
     * @param amount           The amount involved in the transaction.
     * @throws InsufficientFundsException If there are insufficient funds in the sender account.
     * @throws AccountInactiveException   If either the sender or recipient account is inactive.
     */
    void recordTransaction(Account senderAccount, Account recipientAccount, TransactionType transactionType, double amount)
            throws InsufficientFundsException, AccountInactiveException;

    /**
     * Retrieves details of a specific transaction based on its unique identifier.
     *
     * @param transactionID The unique identifier of the transaction.
     * @return The details of the specified transaction.
     * @throws InvalidTransactionException If no transaction is found with the specified identifier.
     */
    Transaction getTransactionDetails(String transactionID) throws InvalidTransactionException;

    /**
     * Validates whether a transaction is valid for the specified account.
     *
     * @param transaction The transaction to be validated.
     * @param account     The account for which the transaction is being validated.
     * @return True if the transaction is valid for the account; false otherwise.
     * @throws InvalidTransactionException If the transaction is invalid.
     */
    boolean validateTransaction(Transaction transaction, Account account) throws InvalidTransactionException;
}
