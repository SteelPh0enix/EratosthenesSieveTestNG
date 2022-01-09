package com.steelph0enix;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final EratosthenesSieve sieve = new EratosthenesSieve();

        Scanner in = new Scanner(System.in);
        System.out.print("Enter amount of numbers to check: ");

        final int numbersAmount = in.nextInt();

        int[][] primeTable = null;
        try {
            primeTable = EratosthenesSieve.generateTable(numbersAmount);
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid amount of numbers!");
        }

        try {
            primeTable = sieve.markPrimesInTable(primeTable);
        } catch (NullPointerException exception) {
            System.out.println("Invalid table passed to sieve!");
        }

        System.out.println(EratosthenesSieve.sieveTableToString(primeTable));
    }
}
