package edu.gu;

import edu.gu.exceptions.AccountInactiveException;
import edu.gu.exceptions.InsufficientFundsException;
import edu.gu.exceptions.InvalidAccountNumberException;

import java.util.List;
import java.util.UUID;

/**
 * Manages a collection of bank accounts and provides operations to create, retrieve, and manage accounts.
 */
public class AccountManager implements IAccountManager {
    // List to store the accounts managed by this AccountManager
    private final List<Account> accounts;

    /**
     * Constructs an AccountManager with a list of existing accounts.
     *
     * @param accounts The list of accounts to be managed.
     */
    AccountManager(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Creates a new account with the specified details and adds it to the managed accounts list.
     *
     * @param accountName The name of the account.
     * @param ownerId     The unique identifier of the account owner.
     * @param accountType The type of the account (e.g., Savings, Checking).
     * @return The newly created account.
     */
    public Account createAccount(String accountName, String ownerId, AccountType accountType) {
        Account newAccount = new Account(
                UUID.randomUUID().toString(),
                ((long) (System.nanoTime() * (Math.random() * 1000)) + "").substring(0, 9), // Generate a unique 9 digits Account Number
                accountName,
                accountType,
                ownerId,
                0
        );
        accounts.add(newAccount);
        return newAccount;
    }

    /**
     * Retrieves an account based on its unique identifier.
     *
     * @param accountId The unique identifier of the account to retrieve.
     * @return The account with the specified identifier or null if not found.
     */
    public Account getAccount(String accountId) {
        for (Account account : this.accounts) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }
        return null; // Account not found
    }

    /**
     * Retrieves an account based on its account number.
     *
     * @param accountNumber The account number to search for.
     * @return The account with the specified account number.
     * @throws InvalidAccountNumberException If no account is found with the specified account number.
     */
    public Account getAccountByNumber(String accountNumber) throws InvalidAccountNumberException {
        for (Account account : this.accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new InvalidAccountNumberException(String.format("No Account with #%s was found.", accountNumber));
    }

    /**
     * Retrieves the balance of an account based on its unique identifier.
     *
     * @param accountId The unique identifier of the account.
     * @return The balance of the account or -1 if the account is not found.
     */
    public double getAccountBalance(String accountId) {
        Account account = getAccount(accountId);
        return (account != null) ? account.getBalance() : -1; // Return -1 if account not found
    }

    /**
     * Deposits the specified amount into the account with the given identifier.
     *
     * @param accountId The unique identifier of the account.
     * @param amount    The amount to be deposited.
     * @return The updated balance after the deposit.
     * @throws AccountInactiveException      If the account is inactive.
     * @throws InvalidAccountNumberException If no account is found with the specified account number.
     */
    public double deposit(String accountId, double amount) throws AccountInactiveException, InvalidAccountNumberException {
        Account account = getAccount(accountId);
        if (account == null) {
            throw new InvalidAccountNumberException("Invalid account number: " + accountId);
        }
        return account.deposit(amount);
    }

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
    public double withdraw(String accountId, double amount)
            throws InsufficientFundsException, AccountInactiveException, InvalidAccountNumberException {
        Account account = getAccount(accountId);
        if (account == null) {
            throw new InvalidAccountNumberException("Invalid account number: " + accountId);
        }
        return account.withdraw(amount);
    }

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
    public double transfer(String accountId1, String accountId2, double amount)
            throws InvalidAccountNumberException, InsufficientFundsException, AccountInactiveException {
        Account sourceAccount = getAccount(accountId1);
        Account destinationAccount = getAccount(accountId2);

        if (sourceAccount == null || destinationAccount == null) {
            throw new InvalidAccountNumberException("Invalid account number");
        }

        return sourceAccount.transfer(destinationAccount, amount);
    }
}
