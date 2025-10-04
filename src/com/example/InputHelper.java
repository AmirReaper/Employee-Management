package com.example;

import java.util.Scanner;

/**
 * Utility class for safely reading user input with validation and exception handling
 */
public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

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

    public static int readInt(String message) {
        return readInt(message, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

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

    /**
     * NEW in v2.1.0 - Read yes/no input with exception handling
     */
    public static String readYesNo(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) return "yes";
            if (input.equals("n") || input.equals("no")) return "no";
            System.out.println("❌ Please enter 'yes' or 'no' (y/n).");
        }
    }
}