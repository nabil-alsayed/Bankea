package edu.gu;

import edu.gu.exceptions.AccountInactiveException;
import edu.gu.exceptions.InsufficientFundsException;
import edu.gu.exceptions.InvalidAccountNumberException;

/**
 * Interface for managing bank accounts.
 */
public interface IAccountManager {

    /**
     * Retrieves the balance of the account with the specified identifier.
     *
     * @param accountId The unique identifier of the account.
     * @return The balance of the account.
     */
    double getAccountBalance(String accountId);

    /**
     * Deposits the specified amount into the account with the given identifier.
     *
     * @param accountId The unique identifier of the account.
     * @param amount    The amount to be deposited.
     * @return The updated balance after the deposit.
     * @throws InsufficientFundsException    If there are insufficient funds in the account.
     * @throws AccountInactiveException      If the account is inactive.
     * @throws InvalidAccountNumberException If no account is found with the specified account number.
     */
    double deposit(String accountId, double amount) throws InsufficientFundsException, AccountInactiveException, InvalidAccountNumberException;

    /**
     * Withdraws the specified amount from the account with the given identifier.
     *
     * @param accountId The unique identifier of the account.
     * @param amount    The amount to be withdrawn.
     * @return The updated balance after the withdrawal.
     * @throws InsufficientFundsException    If there are insufficient funds in the account.
     * @throws AccountInactiveException      If the account is inactive.
     * @throws InvalidAccountNumberException If no account is found with the specified account number.
     */
    double withdraw(String accountId, double amount) throws InsufficientFundsException, AccountInactiveException, InvalidAccountNumberException;

    /**
     * Transfers the specified amount from one account to another.
     *
     * @param accountId1 The unique identifier of the source account.
     * @param accountId2 The unique identifier of the destination account.
     * @param amount     The amount to be transferred.
     * @return The updated balance of the source account after the transfer.
     * @throws InvalidAccountNumberException If either the source or destination account is not found.
     * @throws InsufficientFundsException    If there are insufficient funds in the source account.
     * @throws AccountInactiveException      If either the source or destination account is inactive.
     */
    double transfer(String accountId1, String accountId2, double amount)
            throws InvalidAccountNumberException, InsufficientFundsException, AccountInactiveException;
}
