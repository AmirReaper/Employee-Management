package com.example;

import java.util.Scanner;

/**
 * Utility class for safely reading user input with validation.
 */
public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads an integer with optional min/max validation.
     *
     * @param message Prompt message to display
     * @param min Minimum valid value
     * @param max Maximum valid value
     * @return Valid integer input
     */
    public static int readInt(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < min || value > max) {
                    System.out.println("❌ Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Reads an integer without range check.
     *
     * @param message Prompt message
     * @return Valid integer input
     */
    public static int readInt(String message) {
        return readInt(message, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Reads a string with validation (cannot be empty).
     *
     * @param message Prompt message
     * @return Valid string input
     */
    public static String readString(String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("❌ Input cannot be empty. Please try again.");
            System.out.print(message);
            input = scanner.nextLine().trim();
        }
        return input;
    }
}
