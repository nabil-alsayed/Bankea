# Bankea - Banking system

Bankea is a terminal-based banking application built in Java that allows users with different roles (Client, Customer Service, Credit Department, and Admin) to interact with a simulated banking system. The system supports basic banking operations such as account management, user authentication, deposits, withdrawals, transfers, and mortgage application handling.

```
Project Structure:
.
├── data.json                  # Demo data file (users and accounts)
├── edu/gu/                   # Main Java source directory
│   ├── Main.java             # Main class with application logic
│   ├── Bank.java             # Bank class handling core operations
│   ├── User.java             # User class with roles and credentials
│   ├── Account.java          # Account details and operations
│   ├── Transaction.java      # Transaction logging
│   ├── MortgageApplication.java # Mortgage application handling
│   ├── AccountType.java      # Enum for account types
│   ├── JsonData.java         # Data wrapper for loading demo data
│   ├── IOScanner.java        # Utility class for input handling
│   └── exceptions/
│       └── InvalidCredentialsException.java # Custom exception
```

## Features

- Role-based access control: `CLIENT`, `CUSTOMER_SERVICE`, `CREDIT`, `ADMIN`
- Login/logout flow with console prompts
- View account balances and transaction history
- Deposit and withdraw money
- Transfer funds between accounts
- Apply for and review mortgage applications (CREDIT role)
- Create new users and accounts (ADMIN & CUSTOMER_SERVICE roles)
- Data preloaded from `data.json` if present

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven or your preferred build system

### How to Run

1. Clone or download the repository.
2. Ensure a valid `data.json` file is in the project root (optional; otherwise, the app starts with empty data).
3. Compile and run the `Main` class.

```bash
javac -cp .:path/to/jackson/jackson-databind.jar edu/gu/Main.java
java -cp .:path/to/jackson/jackson-databind.jar edu.gu.Main
```

### Example `data.json`

```json
{
  "users": [
    {
      "username": "admin",
      "password": "adminpass",
      "email": "admin@bankea.com",
      "role": "ADMIN"
    }
  ],
  "accounts": []
}
```

## User Roles & Permissions

| Role             | Permissions                                                                 |
|------------------|------------------------------------------------------------------------------|
| CLIENT           | View own accounts and transactions                                           |
| CUSTOMER_SERVICE | Manage accounts, perform deposits/withdrawals/transfers, submit mortgages   |
| CREDIT           | Review and handle mortgage applications                                      |
| ADMIN            | Create users, manage system                                                  |

## Error Handling

- Invalid login attempts trigger custom `InvalidCredentialsException`
- Input mismatches are gracefully handled with retry prompts
- Exceptions while reading data files are logged with fallback to empty dataset

## Logo

When the application starts, it prints a welcome logo in ASCII art:

```
########     ###    ##    ## ##    ## ########    ###    
##     ##   ## ##   ###   ## ##   ##  ##         ## ##   
##     ##  ##   ##  ####  ## ##  ##   ##        ##   ##  
########  ##     ## ## ## ## #####    ######   ##     ## 
##     ## ######### ##  #### ##  ##   ##       ######### 
##     ## ##     ## ##   ### ##   ##  ##       ##     ## 
########  ##     ## ##    ## ##    ## ######## ##     ## 
```

## License

This project is for educational/demo purposes. No license specified.
