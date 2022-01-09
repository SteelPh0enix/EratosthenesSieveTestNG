package com.steelph0enix;

public class Calculator {
    public int floorRootValue(int number) {
        return (int) Math.sqrt(number);
    }

    public boolean isDivisible(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Cannot divide by 0!");
        }

        double result = dividend / (double) divisor;
        return result == (int) result;
    }

    public boolean isPrime(int number) {
        // To support negative numbers
        final int absNumber = Math.abs(number);

        // To exclude -1, 0 and 1
        if (absNumber < 2) {
            return false;
        }

        // The actual check
        final int checkLimit = floorRootValue(absNumber);
        for (int divisor = 2; divisor <= checkLimit; divisor++) {
            if (isDivisible(absNumber, divisor)) {
                return false;
            }
        }

        return true;
    }
}
