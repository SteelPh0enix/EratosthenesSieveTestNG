package com.steelph0enix;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class EratosthenesSieve {
    Calculator calc = null;

    public EratosthenesSieve() {
        calc = new Calculator();
    }

    public EratosthenesSieve(Calculator calculator) {
        calc = calculator;
    }

    public static int[] @NotNull [] generateTable(int numberCount) {
        // Table with pairs of values
        // First value is the number we check
        // Second will either be 1 or 0, depending
        // on whether the first value is prime or not.
        // This method only generates the table and
        // fills the second value with 1.

        if (numberCount < 1) {
            throw new IllegalArgumentException("Cannot generate less than 1 number!");
        }

        int[][] numbersTable = new int[numberCount][2];

        for (int i = 0; i < numberCount; i++) {
            numbersTable[i][0] = i + 2;
            numbersTable[i][1] = 0;
        }

        return numbersTable;
    }

    @Contract("null -> fail")
    public static @NotNull String sieveTableToString(int[][] numbersTable) {
        if (numbersTable == null) {
            throw new NullPointerException("numbersTable cannot be null!");
        }

        StringBuilder builder = new StringBuilder();

        for (int[] valueRow : numbersTable) {
            builder.append(valueRow[0]).append(" -> ").append(valueRow[1]).append('\n');
        }

        return builder.toString();
    }

    public int[][] markPrimesInTable(int[][] numbersTable) {
        if (numbersTable == null) {
            throw new NullPointerException("numbersTable cannot be null!");
        }

        // check if every number in range is prime or not
        for (int i = 0; i < numbersTable.length; i++) {
            numbersTable[i][1] = calc.isPrime(numbersTable[i][0]) ? 1 : 0;
        }

        return numbersTable;
    }
}
