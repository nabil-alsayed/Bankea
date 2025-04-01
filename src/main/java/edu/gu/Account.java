package edu.gu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.gu.exceptions.AccountInactiveException;
import edu.gu.exceptions.InsufficientFundsException;

/**
 * Represents a bank account with basic operations such as deposit, withdrawal, and transfer.
 */
public class Account {
    // Unique identifier for the account
    private final String id;

    // Account number associated with the account
    private final String accountNumber;

    // Name of the account
    private final String accountName;

    // Type of the account (e.g., Savings, Checking)
    private final AccountType accountType;

    // Unique identifier of the account owner
    private final String ownerId;

    // Current balance of the account
    private double balance;

    /**
     * Constructs an Account object.
     *
     * @param id            Unique identifier for the account.
     * @param accountNumber Account number associated with the account.
     * @param accountName   Name of the account.
     * @param accountType   Type of the account (e.g., Savings, Checking).
     * @param ownerId       Unique identifier of the account owner.
     * @param balance       Current balance of the account.
     */
    @JsonCreator
    public Account(
            @JsonProperty("id") String id,
            @JsonProperty("accountNumber") String accountNumber,
            @JsonProperty("accountName") String accountName,
            @JsonProperty("accountType") AccountType accountType,
            @JsonProperty("ownerId") String ownerId,
            @JsonProperty("balance") double balance
    ) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.ownerId = ownerId;
        this.balance = balance;
    }


    /**
     * Retrieves the unique identifier of the account.
     *
     * @return The unique identifier of the account.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the account number associated with the account.
     *
     * @return The account number.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Retrieves the type of the account.
     *
     * @return The account type (e.g., Savings, Checking).
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Retrieves the current balance of the account.
     *
     * @return The current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount The amount to be deposited.
     * @return The updated balance after the deposit.
     * @throws AccountInactiveException If the account is inactive.
     */


    /**
     * Retrieves the name of the account.
     *
     * @return The account name.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Retrieves the unique identifier of the account owner.
     *
     * @return The owner's unique identifier.
     */
    public String getOwnerId() {
        return ownerId;
    }

    public double deposit(double amount) throws AccountInactiveException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }

        this.balance += amount;
        return this.balance;
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount The amount to be withdrawn.
     * @return The updated balance after the withdrawal.
     * @throws InsufficientFundsException If there are insufficient funds in the account.
     * @throws AccountInactiveException   If the account is inactive.
     */
    public double withdraw(double amount) throws InsufficientFundsException, AccountInactiveException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        this.balance -= amount;
        return this.balance;
    }

    /**
     * Transfers the specified amount from this account to the recipient account.
     *
     * @param recipient The recipient account.
     * @param amount    The amount to be transferred.
     * @return The updated balance after the transfer.
     * @throws InsufficientFundsException If there are insufficient funds in the account.
     * @throws AccountInactiveException   If either account is inactive.
     */
    public double transfer(Account recipient, double amount)
            throws InsufficientFundsException, AccountInactiveException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than 0");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        recipient.deposit(amount);
        this.withdraw(amount);

        return this.balance;
    }

    /**
     * Generates a string representation of the account.
     *
     * @return A string containing account details.
     */
    public String toString() {
        return ("Account: " + this.accountName + System.lineSeparator()
                + "Account Number: " + this.accountNumber + System.lineSeparator());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return (getAccountNumber().equals(account.getAccountNumber()) && getId().equals(account.getId()));
    }
}
