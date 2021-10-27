/*
 * Main.java
 *
 * TCSS 305 – Fall 2021
 * Instructor - Kavanc Dincer
 * Ian McLean
 * Assignment 1
 */
import java.util.*;
/**
 * This program provides
 * the user with a calculator
 * for binary, and hexadecimal
 * conversion functions, as well
 * as arithmetic.
 * @author Ian McLean
 * @version 1.0
 */
public class Main {
    /**
     * Main method, the starting
     * point for this program.
     *
     * @param args - command line arguments ignored
     *               in this program.
     */
    public static void main(String[] args) {
        boolean cont = true;
        while(cont) {
            display();
            int input = receiveInput();
            cont = testChoice(input);
        }
    }

    /**
     * Tests if the user wants
     * to quit the funciton.
     *
     * @param theValue - The String telling us to quit or not.
     *
     * @return The truth value of wanting to quit.
     */
    public static boolean toQuit(String theValue) {
        String s = "y";
        return s.equals(theValue);
    }

    /**
     * Converts a decimal int
     * a binary String.
     *
     * @param decimal - The decimal which will be converted.
     *
     * @return The binary String.
     */
    public static String decimalToBinary(int decimal) {
        boolean negative = false;
        if (decimal == 0) {
            return "0";
        }
        String result;
        if (decimal < 0) {
            decimal *= -1;
            negative = true;
        }
        int x = 1;
            while (x + x <= decimal) {
                x += x;
            }
            int cur = x / 2;
            result = "1";
            while (cur >= 1) {
                if (x + cur > decimal) {
                    result += "0";
                } else {
                    result += "1";
                    x += cur;
                }
                cur = cur / 2;
            }
            if (negative) {
                return "-" + result + "\n";
            } else {
                return result + "\n";
            }
    }

    /**
     * Converts a binary string into a decimal (int) value.
     *
     * @param s - The binary String which will be converted
     *            into a decimal.
     *
     * @return The integer we converted to.
     */
    public static int binaryToDecimal(String s) {
        int end;
        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
            end = 1;
        } else {
            end = 0;
        }
            int result = 0;
            int index = 1;
            for (int i = s.length() - 1; i >= end; i--) {
                if (s.charAt(i) == '1' || s.charAt(i) == '0') {
                    if (s.charAt(i) == '1') {
                        result += index;
                    }
                    index *= 2;
                }
            }
            if (negative) {
                return -result;
            } else {
                return result;
            }
    }

    /**
     * Does basic arithmetic in binary
     * numbers
     *
     * @param firstNumber - Binary number on left side of the equation.
     * @param op - The operation intended on performing.
     * @param secondNumber - Binary Number on the right side of the equation.
     */
    public static void arithmeticBinary(String firstNumber, String op, String secondNumber) {
        int i = binaryToDecimal(firstNumber);
        int j = binaryToDecimal(secondNumber);
        int k = switch (op) {
            case "+" -> j + i;
            case "-" -> j - i;
            case "*" -> j * i;
            default -> j / i;
        };
        System.out.println(decimalToBinary(k));
    }

    /**
     * Converts decimal into hexadecimal.
     *
     * @param n - The int we want to convert to hexadecimal.
     *
     * @return The hexadecimal String we converted to.
     */
    public static String decimalToHex(int n) {
        int remainder;
        String s="";
        char[] arr = {'0','1','2','3','4','5','6','7'
                     ,'8','9','A','B','C','D','E','F'};
        while (n > 0) {
            remainder= n % 16;
            s = arr[remainder] + s;
            n = n / 16;
        }
        return s;
    }

    /**
     * Converts hexadecimal to decimal.
     *
     * @param s - the String that will be converted to decimal.
     *
     * @return The hexadecimal in decimal form.
     */
    public static int hexToDec(String s) {
        String str2 = "0123456789ABCDEF";
        int length = s.length() - 1;
        int base = 1;
        int result = 0;
        for(int i = length; i >= 0; i--) {
            int index = str2.indexOf(s.charAt(i));
            result += (base * index);
            base *= 16;
        }
        return result;
    }

    /**
     * Does basic arithmetic in hexadecimal
     * numbers.
     *
     * @param firstNumber - The hexadecimal on the left side of the equation.
     * @param op - The operation we intend on performing.
     * @param secondNumber - The hexadecimal on the right side of the
     *                       equation.
     *
     * @return The solution expressed in hexadecimal.
     */
    public static String arithmeticHex(String firstNumber, String op, String secondNumber) {
        int i = hexToDec(firstNumber);
        int j = hexToDec(secondNumber);
        int k = switch (op) {
            case "+" -> j + i;
            case "-" -> j - i;
            case "*" -> j * i;
            default -> j / i;
        };
        return decimalToHex(k);
    }

    /**
     * Displays the user an interface for
     * calculator options.
     */
    public static void display() {
        System.out.println("""
                What would you like to calculate?
               
                1) Binary to Decimal
                2) Decimal to Binary
                3) Arithmetic in Binary
                4) Decimal to HexaDecimal
                5) HexaDecimal to Decimal
                6) Arithmetic HexaDecimal
                7) Quit""");
    }

    /**
     * Receives user's numerical input.
     *
     * @return the numerical input the user chose.
     */
    public static int receiveInput() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("Invalid Input, try again");
            scan.next();
        }
        return scan.nextInt();

    }

    /**
     * Decides the direction and flow the program take
     * based off of the operation the user would like
     * to perform.
     *
     * @param n - The numerical choice the user made.
     *
     * @return if the user chose to continue the program.
     */
    public static boolean testChoice(int n) {
            Scanner scan = new Scanner(System.in);
            boolean quit = false;
            switch (n) {
                case 1 -> {
                    while (!quit) {
                        System.out.println("Enter a binary value");
                        String send = scan.next();
                        System.out.println(binaryToDecimal(send));
                        System.out.println("Press y to quit or anything else to continue");
                        send = scan.next();
                        quit = toQuit(send);
                    }
                }
                case 2 -> {
                    while (!quit) {
                        System.out.println("Enter a decimal value");
                        int value = receiveInput();
                        System.out.println(decimalToBinary(value));
                        System.out.println("Press y to quit or anything else to continue");
                        String send = scan.next();
                        quit = toQuit(send);
                    }
                }
                case 3 -> {
                    while (!quit) {
                        System.out.println("Enter first binary number");
                        String first = scan.next();
                        System.out.println("Enter operation (+ -, *, /)");
                        String operator = scan.next();
                        System.out.println("Enter second binary number");
                        String second = scan.next();
                        arithmeticBinary(first, operator, second);
                        System.out.println("Press y to quit or anything else to continue");
                        String send = scan.next();
                        quit = toQuit(send);
                    }
                }
                case 4 -> {
                    while (!quit) {
                        System.out.println("Enter a decimal value");
                        int value = receiveInput();
                        System.out.println(decimalToHex(value));
                        System.out.println("Press y to quit or anything else to continue");
                        String send = scan.next();
                        quit = toQuit(send);
                    }
                }
                case 5 -> {
                    while (!quit) {
                        System.out.println("Enter a hexadecimal value");
                        String value = scan.next();
                        System.out.println(hexToDec(value));
                        System.out.println("Press y to quit or anything else to continue");
                        String send = scan.next();
                        quit = toQuit(send);
                    }
                }
                case 6 -> {
                    System.out.println("Enter first hexadecimal");
                    String first = scan.next();
                    System.out.println("Enter operation (+ -, *, /)");
                    String operator = scan.next();
                    System.out.println("Enter second hexadecimal");
                    String second = scan.next();
                    arithmeticHex(first, operator, second);
                    System.out.println("Press y to quit or anything else to continue");
                    String send = scan.next();
                    quit = toQuit(send);
                }
                case 7 -> {
                    return false;
                }
                default -> System.out.println("Invalid input, try again");
            }
            return true;
    }
}
