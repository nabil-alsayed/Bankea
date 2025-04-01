package edu.gu;

import java.util.Scanner;

/**
 * Utility class for handling user input.
 */
public class IOScanner {
    // Scanner instance to read input from the console
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads an integer from the console.
     *
     * @return The integer entered by the user.
     */
    public static int readInt() {
        // Read an integer and consume the newline character
        int userInput = scanner.nextInt();
        IOScanner.readLine();
        return userInput;
    }

    /**
     * Reads a double from the console.
     *
     * @return The double entered by the user.
     */
    public static double readDouble() {
        return scanner.nextDouble();
    }

    /**
     * Reads a line of text from the console.
     *
     * @return The line of text entered by the user.
     */
    public static String readLine() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner to release system resources.
     */
    public static void close() {
        scanner.close();
    }
}
