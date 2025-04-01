package edu.gu;

import edu.gu.exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a bank with various functionalities such as managing accounts, authentication, transactions, and mortgages.
 */
class Bank {
    private static AccountManager accountManager;
    private static AuthenticationManager authenticationManager;
    private static TransactionManager transactionManager;
    private static MortgageManager mortgageManager;
    private final String name;
    private final String location;

    /**
     * Constructs a Bank object with the specified name, location, accounts, and users.
     *
     * @param name     The name of the bank.
     * @param location The location of the bank.
     * @param accounts The list of existing accounts.
     * @param users    The list of registered users.
     */
    public Bank(String name, String location, List<Account> accounts, List<User> users) {
        this.name = name;
        this.location = location;
        accountManager = new AccountManager(accounts);
        authenticationManager = new AuthenticationManager(users);
        transactionManager = new TransactionManager();
        mortgageManager = new MortgageManager();
    }

    /**
     * Retrieves the name of the bank.
     *
     * @return The name of the bank.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the location of the bank.
     *
     * @return The location of the bank.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Retrieves an account based on its account number.
     *
     * @param accountNumber The account number to search for.
     * @return The account with the specified account number.
     * @throws InvalidAccountNumberException If no account is found with the specified account number.
     */
    public Account getAccountByNumber(String accountNumber) throws InvalidAccountNumberException {
        return accountManager.getAccountByNumber(accountNumber);
    }

    /**
     * Retrieves a user based on their username.
     *
     * @param username The username to search for.
     * @return The user with the specified username.
     */
    public User getUserByUsername(String username) {
        return authenticationManager.getUserByUsername(username);
    }

    /**
     * Logs in a user with the provided credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The logged-in user.
     * @throws InvalidCredentialsException If no user is found with the provided credentials.
     */
    public User loginUser(String username, String password) throws InvalidCredentialsException {
        return authenticationManager.loginUser(username, password);
    }

    /**
     * Creates a new user with the specified details.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @param email    The email address for the new user.
     * @param role     The role of the new user.
     * @return The newly created user.
     */
    public User createUser(String username, String password, String email, User.Role role) {
        return authenticationManager.createUser(username, password, email, role);
    }

    /**
     * Records a withdrawal transaction for the specified account.
     *
     * @param account The account from which the withdrawal is made.
     * @param amount  The amount of the withdrawal.
     * @throws InsufficientFundsException If there are insufficient funds in the account.
     * @throws AccountInactiveException   If the account is inactive.
     */
    public void withdrawFromAccount(Account account, double amount) throws InsufficientFundsException, AccountInactiveException {
        transactionManager.recordTransaction(account, TransactionType.WITHDRAWAL, amount);
    }

    /**
     * Records a deposit transaction for the specified account.
     *
     * @param account The account to which the deposit is made.
     * @param amount  The amount of the deposit.
     * @throws AccountInactiveException   If the account is inactive.
     * @throws InsufficientFundsException If there are insufficient funds in the account.
     */
    public void depositToAccount(Account account, double amount) throws AccountInactiveException, InsufficientFundsException {
        transactionManager.recordTransaction(account, TransactionType.DEPOSIT, amount);
    }

    /**
     * Records a transfer transaction from one account to another.
     *
     * @param senderAccount   The account from which the transfer is made.
     * @param receiverAccount The account to which the transfer is made.
     * @param amount          The amount of the transfer.
     * @throws InsufficientFundsException If there are insufficient funds in the sender account.
     * @throws AccountInactiveException   If either the sender or receiver account is inactive.
     */
    public void transfer(Account senderAccount, Account receiverAccount, double amount) throws InsufficientFundsException, AccountInactiveException {
        transactionManager.recordTransaction(senderAccount, receiverAccount, TransactionType.TRANSFER, amount);
    }

    /**
     * Submits a mortgage application for a user.
     *
     * @param user          The user applying for the mortgage.
     * @param propertyValue The value of the property for which the mortgage is applied.
     * @param loanAmount    The requested loan amount.
     * @param interestRate  The interest rate for the mortgage.
     * @return The submitted mortgage application.
     * @throws IncompleteApplicationException If the mortgage application is incomplete.
     */
    public MortgageApplication submitMortgageApplication(User user, double propertyValue, double loanAmount, double interestRate) throws IncompleteApplicationException {
        return mortgageManager.submitApplication(user, propertyValue, loanAmount, interestRate);
    }

    /**
     * Retrieves a list of pending mortgage applications.
     *
     * @return The list of pending mortgage applications.
     */
    public List<MortgageApplication> getPendingMortgageApplications() {
        return mortgageManager.findApplicationsByStatus(MortgageApplicationStatus.PENDING);
    }

    /**
     * Retrieves a mortgage application based on its unique identifier.
     *
     * @param id The unique identifier of the mortgage application.
     * @return The mortgage application with the specified identifier.
     */
    public MortgageApplication getMortgageApplicationById(String id) {
        return mortgageManager.findApplicationById(id);
    }

    /**
     * Retrieves a list of transactions based on its account unique identifier.
     *
     * @param account The account to which the deposit is made.
     * @return The list of transactions belonging to this account.
     */
    public List<Transaction> getAccountTransactions(Account account) {
        return transactionManager.getTransactions().stream().filter(transaction -> transaction.getAccountId().equals(account.getId()) || ((TransferTransaction) transaction).getRecipientAccountId().equals(account.getId())).collect(Collectors.toList());
    }

    /**
     * * Create a new client account.
     *
     * @param accountName The account name.
     * @param ownerId The account's owner ID.
     * @param accountType The account type.
     * @return The newly created account.
     */
    public Account createAccount(
            String accountName,
            String ownerId,
            AccountType accountType) {
        return accountManager.createAccount(accountName, ownerId, accountType);
    }


}
