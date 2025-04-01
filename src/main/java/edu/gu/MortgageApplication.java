package edu.gu;

import java.util.UUID;

// Class representing a mortgage application
public class MortgageApplication {
    // Unique identifier for the mortgage application
    private final String id;

    // User who submitted the mortgage application
    private final User applicant;

    // Property value associated with the mortgage application
    private final double propertyValue;

    // Loan amount requested in the mortgage application
    private final double loanAmount;

    // Interest rate specified in the mortgage application
    private final double interestRate;

    // Status of the mortgage application (e.g., PENDING, APPROVED, REJECTED, CANCELLED)
    private MortgageApplicationStatus applicationStatus;

    // Constructor to initialize a mortgage application
    public MortgageApplication(User applicant, double propertyValue, double loanAmount, double interestRate) {
        // Generate a unique identifier for the mortgage application
        this.id = UUID.randomUUID().toString();

        // Set the applicant and other details
        this.applicant = applicant;
        this.propertyValue = propertyValue;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;

        // Set the initial application status to PENDING
        this.applicationStatus = MortgageApplicationStatus.PENDING;
    }

    // Getter method for retrieving the unique identifier of the mortgage application
    public String getId() {
        return id;
    }

    // Getter method for retrieving the applicant of the mortgage application
    public User getApplicant() {
        return applicant;
    }

    // Getter method for retrieving the property value of the mortgage application
    public Double getPropertyValue() {
        return propertyValue;
    }

    // Getter method for retrieving the loan amount of the mortgage application
    public Double getLoanAmount() {
        return loanAmount;
    }

    // Getter method for retrieving the interest rate of the mortgage application
    public Double getInterestRate() {
        return interestRate;
    }

    // Getter method for retrieving the current application status
    public MortgageApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    // Setter method for updating the application status
    public void setApplicationStatus(MortgageApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    // Method to check if the mortgage application is incomplete
    public boolean isIncomplete() {
        // Check if any of the required fields is null or has an invalid value
        return applicant == null || propertyValue <= 0 || loanAmount <= 0 || interestRate <= 0;
    }

    // Method to approve the mortgage application
    public void approve() {
        // Perform approval logic (can be extended as needed)
        this.applicationStatus = MortgageApplicationStatus.APPROVED;
    }

    // Method to reject the mortgage application
    public void reject() {
        // Perform rejection logic (can be extended as needed)
        this.applicationStatus = MortgageApplicationStatus.REJECTED;
    }

    // Method to cancel the mortgage application
    public void cancel() {
        // Perform cancellation logic (can be extended as needed)
        this.applicationStatus = MortgageApplicationStatus.CANCELLED;
    }
}
