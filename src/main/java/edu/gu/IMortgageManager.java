package edu.gu;

import edu.gu.exceptions.IncompleteApplicationException;

import java.util.List;

/**
 * Interface for managing mortgage applications.
 */
public interface IMortgageManager {

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
    MortgageApplication submitApplication(User user, double propertyValue, double loanAmount, double interestRate)
            throws IncompleteApplicationException;

    /**
     * Retrieves a list of all mortgage applications.
     *
     * @return The list of mortgage applications.
     */
    List<MortgageApplication> getMortgageApplications();
}
