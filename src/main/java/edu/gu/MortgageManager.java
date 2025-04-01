package edu.gu;

import edu.gu.exceptions.IncompleteApplicationException;

import java.util.ArrayList;
import java.util.List;

// Class responsible for managing mortgage applications
public class MortgageManager implements IMortgageManager {
    // List to store all mortgage applications
    private final List<MortgageApplication> mortgageApplications;

    // Constructor to initialize the MortgageManager
    public MortgageManager() {
        // Initialize the list of mortgage applications
        this.mortgageApplications = new ArrayList<>();
    }

    // Getter method to retrieve the list of mortgage applications
    public List<MortgageApplication> getMortgageApplications() {
        return mortgageApplications;
    }

    // Method to submit a new mortgage application
    public MortgageApplication submitApplication(User user, double propertyValue, double loanAmount, double interestRate)
            throws IncompleteApplicationException {
        // Create a new mortgage application
        MortgageApplication application = new MortgageApplication(user, propertyValue, loanAmount, interestRate);

        // Check if the application is incomplete
        if (application.isIncomplete()) {
            throw new IncompleteApplicationException("Incomplete application. Please provide all required information");
        }

        // Add the application to the list of mortgage applications
        mortgageApplications.add(application);

        // Return the submitted application
        return application;
    }

    // Method to find a mortgage application by its unique identifier
    public MortgageApplication findApplicationById(String applicationId) {
        // Iterate through the list of mortgage applications
        for (MortgageApplication application : mortgageApplications) {
            // Check if the current application's ID matches the specified ID
            if (application.getId().equals(applicationId)) {
                // Return the matching application
                return application;
            }
        }

        // Return null if no matching application is found
        return null;
    }

    // Method to find mortgage applications with a specific status
    public List<MortgageApplication> findApplicationsByStatus(MortgageApplicationStatus status) {
        // List to store matching applications
        List<MortgageApplication> matchingApplications = new ArrayList<>();

        // Iterate through the list of mortgage applications
        for (MortgageApplication application : mortgageApplications) {
            // Check if the current application has the specified status
            if (application.getApplicationStatus().equals(status)) {
                // Add the matching application to the list
                matchingApplications.add(application);
            }
        }

        // Return the list of matching applications
        return matchingApplications;
    }
}
