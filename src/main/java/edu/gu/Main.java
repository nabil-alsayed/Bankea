package edu.gu;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gu.exceptions.InvalidCredentialsException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    // The main bank instance for the application
    private final Bank bank;
    // The currently authenticated user
    private User authenticatedUser;

    public Main() {
        Bank bank;
        Locale.setDefault(Locale.ENGLISH);

        try {
            // Read data from the JSON file and create Bank instance
            ObjectMapper mapper = new ObjectMapper();
            JsonData data = mapper.readValue(new File("data.json"), JsonData.class);
            bank = new Bank("Bankea", "Main street", data.accounts, data.users);
        } catch (IOException e) {
            // Handle exceptions when reading data file
            System.out.print("Failed to load demo data!\n");
            bank = new Bank("Bankea", "Main street", new ArrayList<>(), new ArrayList<>());
        }
        this.bank = bank;
    }

    // Main method to start the application
    public static void main(String[] args) {
        Main app = new Main();
        printLogo();
        app.displayMainMenu();
    }

    // Method to read user's choice
    private static int readUserChoice() {
        int choice = -1;
        do {
            try {
                System.out.print("\nEnter your choice: ");
                choice = IOScanner.readInt();
            } catch (Exception e) {
                // Prevent infinite loop by consuming invalid input
                IOScanner.readLine();
                System.out.print("Invalid choice please try again!\n");
            }
        } while (choice == -1);
        return choice;
    }

    // Method to read an account number from the user
    private static String readAccountNumber() {
        System.out.println("------------------------------------");
        System.out.println("Enter account number: ");
        return IOScanner.readLine();
    }

    // Method to display the mortgage application details
    private static void displayMortgageApplication(MortgageApplication application) {
        System.out.printf("Application #%s is %s\n", application.getId(), application.getApplicationStatus());
        System.out.printf(" Property Value %s (in millions)\n", application.getPropertyValue());
        System.out.printf(" Requested Loan amount %s (in millions)\n", application.getLoanAmount());
        System.out.printf(" Proposed Interest Rate %%%s\n", application.getInterestRate());
        System.out.println("------------------------------------");
    }

    private static void printLogo() {
        System.out.println("------------------------------------");
        System.out.println("\n" +
                "########     ###    ##    ## ##    ## ########    ###    \n" +
                "##     ##   ## ##   ###   ## ##   ##  ##         ## ##   \n" +
                "##     ##  ##   ##  ####  ## ##  ##   ##        ##   ##  \n" +
                "########  ##     ## ## ## ## #####    ######   ##     ## \n" +
                "##     ## ######### ##  #### ##  ##   ##       ######### \n" +
                "##     ## ##     ## ##   ### ##   ##  ##       ##     ## \n" +
                "########  ##     ## ##    ## ##    ## ######## ##     ## \n");
        System.out.println("------------------------------------");
    }

    private void login() {
        try {
            System.out.println("------------------------------------");
            System.out.println("Enter username: ");
            String username = IOScanner.readLine();

            System.out.println("Enter password: ");
            String password = IOScanner.readLine();

            // Authenticate user with the bank
            authenticatedUser = bank.loginUser(username, password);
            System.out.println("------------------------------------");
            System.out.println(authenticatedUser.getRole()); // For demo purposes only!
            System.out.printf("Welcome %s!\n", authenticatedUser.getUsername());
            displayUserMenu();
        } catch (InvalidCredentialsException e) {
            // Handle invalid credentials exception
            System.out.println("------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("------------------------------------");
            displayMainMenu();
        }
    }

    // Method to display the user menu
    public void displayUserMenu() {
        // Check if a user is authenticated
        if (authenticatedUser != null) {
            // Display menu based on the user's role
            switch (authenticatedUser.getRole()) {
                case CLIENT -> displayClientMenu();
                case CUSTOMER_SERVICE -> displayCustomerServiceMenu();
                case CREDIT -> displayCreditEmployeeMenu();
                case ADMIN -> displayAdminMenu();
            }
        } else {
            // If no user is authenticated, display the main menu
            displayMainMenu();
        }
    }

    // Method to display the main menu
    private void displayMainMenu() {
        System.out.println("1. Log in");
        System.out.println("2. Exit Program\n");

        // Read user's choice
        int choice = readUserChoice();

        // Process user's choice
        switch (choice) {
            case 1 -> login();
            case 2 -> exitProgram();
            default -> {
                System.out.println("Invalid choice. Please enter a valid option.");
                displayMainMenu();
            }
        }
    }

    // Method to display the client menu
    private void displayClientMenu() {
        System.out.println("------------------------------------");
        System.out.println("0. Logout");
        System.out.println("1. Check Account Balance");
        System.out.println("2. Show Account's transactions");
        System.out.println("9. Exit Program");

        // Read user's choice
        int choice = readUserChoice();

        // Process user's choice
        switch (choice) {
            case 0 -> logout();
            case 1 -> checkAccountBalance();
            case 2 -> showAccountTransactions();
            case 9 -> exitProgram();
            default -> {
                System.out.println("Invalid choice. Please enter a valid option.");
                displayUserMenu();
            }
        }
    }

    // Method to display the customer service menu
    private void displayCustomerServiceMenu() {
        System.out.println("------------------------------------");
        System.out.println("0. Logout");
        System.out.println("1. Check Account Balance");
        System.out.println("2. Withdraw Funds");
        System.out.println("3. Deposit Funds");
        System.out.println("4. Transfer Money");
        System.out.println("5. Submit Mortgage Application");
        System.out.println("6. Register new user");
        System.out.println("7. Create a new client account");
        System.out.println("9. Exit Program");

        // Read user's choice
        int choice = readUserChoice();

        // Process user's choice
        switch (choice) {
            case 0 -> logout();
            case 1 -> checkAccountBalance();
            case 2 -> withdrawFunds();
            case 3 -> depositFunds();
            case 4 -> transferMoney();
            case 5 -> submitMortgageApplication();
            case 6 -> registerNewUser();
            case 7 -> createAccount();
            case 9 -> exitProgram();
            default -> {
                System.out.println("Invalid choice. Please enter a valid option.");
                displayUserMenu();
            }
        }
    }

    // Method to display the credit employee menu
    private void displayCreditEmployeeMenu() {
        System.out.println("------------------------------------");
        System.out.println("0. Logout");
        System.out.println("1. Review Mortgage Applications");
        System.out.println("9. Exit Program");

        // Read user's choice
        int choice = readUserChoice();

        // Process user's choice
        switch (choice) {
            case 0 -> logout();
            case 1 -> reviewMortgageApplications();
            case 9 -> exitProgram();
            default -> {
                System.out.println("Invalid choice. Please enter a valid option.");
                displayUserMenu();
            }
        }
    }

    // Method to display the admin menu
    private void displayAdminMenu() {
        System.out.println("------------------------------------");
        System.out.println("0. Logout");
        System.out.println("1. Register new user");
        System.out.println("9. Exit Program");

        // Read user's choice
        int choice = readUserChoice();

        // Process user's choice
        switch (choice) {
            case 0 -> logout();
            case 1 -> registerNewUser();
            case 9 -> exitProgram();
            default -> {
                System.out.println("Invalid choice. Please enter a valid option.");
                displayUserMenu();
            }
        }
    }

    // Method to register a new user
    private void registerNewUser() {
        System.out.println("------------------------------------");
        System.out.println("Enter username: ");
        String username = IOScanner.readLine();

        System.out.println("Enter password: ");
        String password = IOScanner.readLine();

        System.out.println("Enter email: ");
        String email = IOScanner.readLine();

        int choice = 1; // Client
        if (authenticatedUser.getRole().equals(User.Role.ADMIN)) {
            System.out.println("Select User role: \n1. Client 2. Customer Service\n3. Credit\n4. Admin");
            choice = readUserChoice();
        }

        User.Role role = getUserRole(choice);

        System.out.println("------------------------------------");
        User user = bank.createUser(username, password, email, role);

        System.out.println("------------------------------------");
        if (user != null) {
            System.out.printf("User %s added successfully\n", user.getUsername());
            displayUserMenu();
        } else {
            System.out.println("Failed to add a new user, please try again!\n");
            registerNewUser();
        }
        System.out.println("------------------------------------");
    }

    private void createAccount() {
        String accountName;
        String ownerId;
        AccountType accountType;

        try {
            System.out.println("################################");
            System.out.println("Create account");
            System.out.println("################################");

            // Prompt user for account name
            System.out.println("------------------------------------");
            System.out.println("Enter account name: ");
            accountName = IOScanner.readLine();

            // Prompt user for account's owner ID
            System.out.println("------------------------------------");
            System.out.println("Enter the owner ID: ");
            ownerId = IOScanner.readLine();

            // Prompt user for account's type
            System.out.println("------------------------------------");
            System.out.println("Select the account type: ");
            AccountType[] accountTypesValues = AccountType.values();
            for (int i = 0; i < accountTypesValues.length; i++) {
                System.out.println("------------------------------------");
                System.out.printf("%s. %s\n", i + 1, accountTypesValues[i]);
                System.out.println("------------------------------------");
            }
            int choice = readUserChoice();
            accountType = accountTypesValues[choice - 1];

            // Create account and display success message
            Account newAccount = bank.createAccount(accountName, ownerId, accountType);
            System.out.println("------------------------------------");
            System.out.printf("Account created successfully with #%s", newAccount.getAccountNumber());
            System.out.println("------------------------------------");
            System.out.println("Would you like to use another service?");
            displayUserMenu();
        } catch (Exception e) {
            // Handle exceptions and prompt user for retry or return to the previous menu
            System.out.println("------------------------------------");
            System.out.printf("%s. \n1. Would you like to try again?\n2. Would you like to go back to the previous menu?\n", e.getMessage());
            int choice = readUserChoice();
            switch (choice) {
                case 1 -> createAccount();
                case 2 -> displayUserMenu();
            }
        }
    }


    private User.Role getUserRole(int choice) {
        if (authenticatedUser.getRole().equals(User.Role.ADMIN)) {
            switch (choice) {
                case 1 -> {
                    return User.Role.CLIENT;
                }
                case 2 -> {
                    return User.Role.CUSTOMER_SERVICE;
                }
                case 3 -> {
                    return User.Role.CREDIT;
                }
                case 4 -> {
                    return User.Role.ADMIN;
                }
            }
        } else if (choice == 1) {
            return User.Role.CLIENT;
        }

        System.out.println("------------------------------------");
        System.out.println("Invalid choice. Please enter a valid option.");
        System.out.println("------------------------------------");
        return getUserRole(readUserChoice());
    }


    // Method to check the account balance
    private void checkAccountBalance() {
        // Display account balance for the authenticated user
        System.out.println("################################");
        System.out.println("Check Account Balance");
        System.out.println("################################");
        Account account = getAccount();

        // Check if the authenticated user has access to the account
        if (authenticatedUser.getRole().equals(User.Role.CLIENT) && !account.getOwnerId().equals(authenticatedUser.getId())) {
            System.out.println("------------------------------------");
            System.out.println("Invalid Account. Please try again!");
            System.out.println("------------------------------------");
        } else {
            // Display account balance and prompt for additional actions
            System.out.println("------------------------------------");
            System.out.printf("Current Balance for Account #%s is SEK %s%n", account.getAccountNumber(), account.getBalance());
            System.out.println("------------------------------------");
            System.out.println("Would you like to use another service?");
        }
        displayUserMenu();
    }

    private void showAccountTransactions() {
        // Display account balance for the authenticated user
        System.out.println("################################");
        System.out.println("Show Account Transactions");
        System.out.println("################################");
        Account account = getAccount();

        // Check if the authenticated user has access to the account
        if (authenticatedUser.getRole().equals(User.Role.CLIENT) && !account.getOwnerId().equals(authenticatedUser.getId())) {
            System.out.println("------------------------------------");
            System.out.println("Invalid Account. Please try again!");
            System.out.println("------------------------------------");
        } else {
            // Display account transactions
            List<Transaction> transactions = bank.getAccountTransactions(account);
            if (transactions.isEmpty()) {
                System.out.println("------------------------------------");
                System.out.println("No Transactions!");
                System.out.println("------------------------------------");
            } else {
                transactions.forEach(transaction -> {
                    System.out.println("------------------------------------");
                    System.out.println(transaction.toString());
                    System.out.println("------------------------------------");
                });
            }

            System.out.println("Would you like to use another service?");
        }
        displayUserMenu();
    }

    // Method to get an account based on user input
    private Account getAccount() {
        Account result = null;
        String accountNumber = readAccountNumber();
        try {
            // Retrieve the account based on the provided account number
            result = bank.getAccountByNumber(accountNumber);
        } catch (Exception e) {
            // Handle exceptions and prompt the user for valid input
            System.out.println("------------------------------------");
            System.out.printf("%s Please try again with a valid account number.\n", e.getMessage());
            displayUserMenu();
        }
        return result;
    }

    // Method to withdraw funds from an account
    private void withdrawFunds() {
        try {
            System.out.println("################################");
            System.out.println("Withdraw funds");
            System.out.println("################################");
            Account account = getAccount();

            // Prompt user for withdrawal amount
            System.out.println("------------------------------------");
            System.out.println("Enter amount to withdraw: ");
            double amount = IOScanner.readDouble();

            // Withdraw funds from the specified account and display success message
            bank.withdrawFromAccount(account, amount);
            System.out.println("------------------------------------");
            System.out.printf("Successful Withdrawal for SEK %s\n", amount);
            System.out.printf("Current Balance for Account #%s is SEK %s%n", account.getAccountNumber(), account.getBalance());
            System.out.println("------------------------------------");
            System.out.println("Would you like to use another service?");
            displayUserMenu();
        } catch (Exception e) {
            // Handle exceptions and prompt user for retry or return to the previous menu
            System.out.println("------------------------------------");
            System.out.printf("%s. \n1. Would you like to try again?\n2. Would you like to go back to the previous menu?\n", e.getMessage());
            int choice = readUserChoice();
            switch (choice) {
                case 1 -> withdrawFunds();
                case 2 -> displayUserMenu();
            }
        }
    }

    // Method to deposit funds into an account
    private void depositFunds() {
        try {
            System.out.println("################################");
            System.out.println("Deposit funds");
            System.out.println("################################");
            Account account = getAccount();

            // Prompt user for deposit amount
            System.out.println("------------------------------------");
            System.out.println("Enter amount to deposit: ");
            double amount = IOScanner.readDouble();

            // Deposit funds into the specified account and display success message
            bank.depositToAccount(account, amount);
            System.out.println("------------------------------------");
            System.out.printf("Successful deposit for SEK %s\n", amount);
            System.out.printf("Current Balance for Account #%s is SEK %s%n", account.getAccountNumber(), account.getBalance());
            System.out.println("------------------------------------");
            System.out.println("Would you like to use another service?");
            displayUserMenu();
        } catch (Exception e) {
            // Handle exceptions and prompt user for retry or return to the previous menu
            System.out.println("------------------------------------");
            System.out.printf("%s. \n1. Would you like to try again?\n2. Would you like to go back to the previous menu?\n", e.getMessage());
            int choice = readUserChoice();
            switch (choice) {
                case 1 -> depositFunds();
                case 2 -> displayUserMenu();
            }
        }
    }

    // Method to transfer money between accounts
    private void transferMoney() {
        System.out.println("################################");
        System.out.println("Transfer funds");
        System.out.println("################################");
        try {
            Account senderAccount;
            Account receiverAccount;
            System.out.println("Enter Sender Account ");
            senderAccount = getAccount();
            System.out.println("------------------------------------");
            System.out.println("Enter Receiver Account ");
            receiverAccount = getAccount();
            System.out.println("------------------------------------");
            if (senderAccount.equals(receiverAccount)) {
                throw new RuntimeException("Both Receiver and Sender account are the same, Please review the accounts' numbers.");
            }
            // Prompt user for transfer amount
            System.out.println("Enter amount to transfer: ");
            double amount = IOScanner.readDouble();

            // Transfer funds between specified accounts and display success message
            bank.transfer(senderAccount, receiverAccount, amount);
            System.out.println("------------------------------------");
            System.out.printf("Successful Transfer for SEK %s from account #%s to account #%s\n", amount, senderAccount.getAccountNumber(), receiverAccount.getAccountNumber());
            System.out.printf("Current Balance for Account #%s is SEK %s%n", senderAccount.getAccountNumber(), senderAccount.getBalance());
            System.out.println("------------------------------------");
            System.out.println("Would you like to use another service?");
            displayUserMenu();
        } catch (Exception e) {
            // Handle exceptions and prompt user for retry or return to the previous menu
            System.out.println("------------------------------------");
            System.out.printf("%s. \n1. Would you like to try again?\n2. Would you like to go back to the previous menu?\n", e.getMessage());
            int choice = readUserChoice();
            switch (choice) {
                case 1 -> transferMoney();
                case 2 -> displayUserMenu();
            }
        }
    }

    // Method to submit a mortgage application
    private void submitMortgageApplication() {
        try {
            System.out.println("################################");
            System.out.println("Submit Mortgage Application");
            System.out.println("################################");
            System.out.println("Applicant username: ");
            String username = IOScanner.readLine();
            User user = bank.getUserByUsername(username);
            System.out.println("------------------------------------");

            // Prompt user for mortgage application details
            System.out.println("Enter the Property value (in millions): ");
            double propertyValue = IOScanner.readDouble();
            System.out.println("Enter the loan amount (in millions): ");
            double loanAmount = IOScanner.readDouble();
            System.out.println("Enter the interest rate: ");
            double interestRate = IOScanner.readDouble();

            // Submit the mortgage application and display success message
            MortgageApplication application = bank.submitMortgageApplication(user, propertyValue, loanAmount, interestRate);
            System.out.println("------------------------------------");
            System.out.printf("Application #%s created successfully and waiting approval by our credit department\n", application.getId());
            System.out.println("------------------------------------");
            System.out.println("Would you like to use another service?");
            displayUserMenu();
        } catch (Exception e) {
            // Handle exceptions and prompt user for retry or return to the previous menu
            System.out.println("------------------------------------");
            System.out.printf("%s. \n1. Would you like to try again?\n2. Would you like to go back to the previous menu?\n", e.getMessage());
            int choice = readUserChoice();
            switch (choice) {
                case 1 -> submitMortgageApplication();
                case 2 -> displayUserMenu();
            }
        }
    }

    // Method to review pending mortgage applications
    private void reviewMortgageApplications() {
        try {
            System.out.println("################################");
            System.out.println("Review Mortgage Applications");
            System.out.println("################################");
            List<MortgageApplication> applications = bank.getPendingMortgageApplications();

            // Check if there are pending mortgage applications
            if (applications.isEmpty()) {
                throw new RuntimeException("**No pending Mortgage Applications**");
            }

            // Display details of pending mortgage applications
            for (MortgageApplication application : applications) {
                displayMortgageApplication(application);
            }

            // Prompt user for options to handle applications
            System.out.println("------------------------------------");
            System.out.println("0. Back to menu\n1. Handle Application\n2. View rejected Applications\n");
            int choice = readUserChoice();
            switch (choice) {
                case 0 -> displayUserMenu();
                case 1 -> handleMortgageApplication();
                case 2 -> throw new Exception("Not Implemented!");
                default -> {
                    System.out.println("Invalid choice. Please enter a valid option.");
                    reviewMortgageApplications();
                }
            }
        } catch (Exception e) {
            // Handle exceptions and prompt user for retry or return to the previous menu
            System.out.println("------------------------------------");
            System.out.printf("%s. \n1. Would you like to try again?\n2. Would you like to go back to the previous menu?\n", e.getMessage());
            int choice = readUserChoice();
            switch (choice) {
                case 1 -> reviewMortgageApplications();
                case 2 -> displayUserMenu();
            }
        }
    }

    // Method to handle a specific mortgage application
    private void handleMortgageApplication() {
        System.out.println("------------------------------------");
        MortgageApplication application;
        do {
            // Prompt user to enter the application ID
            System.out.println("Enter the application ID: ");
            String applicationID = IOScanner.readLine();
            application = bank.getMortgageApplicationById(applicationID);
        } while (application == null);

        // Display details of the application
        displayMortgageApplication(application);

        // Prompt user for approval, rejection, or cancellation
        System.out.println("0. Back\n1. Approve Application\n2. Reject Application\n3. Cancel Application");
        int choice = readUserChoice();
        switch (choice) {
            case 1 -> {
                application.approve();
                System.out.printf("Application #%s is now %s\n", application.getId(), application.getApplicationStatus());
            }
            case 2 -> {
                application.reject();
                System.out.printf("Application #%s is now %s\n", application.getId(), application.getApplicationStatus());
            }
            case 3 -> {
                application.cancel();
                System.out.printf("Application #%s is now %s\n", application.getId(), application.getApplicationStatus());
            }
            default -> displayUserMenu();
        }
        displayUserMenu();
    }

    // Method to log out the current user
    private void logout() {
        System.out.println("------------------------------------");
        authenticatedUser = null;
        System.out.println("Logged out!");
        System.out.println("------------------------------------\n");
        displayMainMenu();
    }

    // Method to exit the program
    private void exitProgram() {
        // Close IO scanner and exit the program
        IOScanner.close();
        System.out.println("Thank you for using Bankea.");
        System.exit(0);
    }

}
