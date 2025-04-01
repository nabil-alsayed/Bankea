package edu.gu;

/**
 * Represents a deposit transaction in the banking system.
 */
public class DepositTransaction extends Transaction {

    // The unique identifier of the account involved in the deposit
    private final String accountId;

    /**
     * Constructs a DepositTransaction object.
     *
     * @param accountId The unique identifier of the account involved in the deposit.
     * @param amount    The amount of the deposit.
     */
    public DepositTransaction(String accountId, double amount) {
        // Call the constructor of the superclass (Transaction)
        super(TransactionType.DEPOSIT, amount, accountId);
        this.accountId = accountId;
    }

    /**
     * Retrieves the account ID associated with the deposit transaction.
     *
     * @return The account ID.
     */
    public String getAccountId() {
        return this.accountId;
    }


}
