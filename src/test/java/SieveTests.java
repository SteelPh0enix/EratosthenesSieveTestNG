import com.steelph0enix.Calculator;
import com.steelph0enix.EratosthenesSieve;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class SieveTests {
    // Test assumptions: the sieve uses Calculator's
    // method isPrime to check if the number is prime or not.
    // Hence, we have to provide custom implementations of
    // isPrime to check whether the function itself works
    // correctly or not.

    // This, default implementations will be used in some tests that
    // will check if the sieve returns correct values with correct isPrime
    // implementation
    private final EratosthenesSieve sieve = new EratosthenesSieve();

    private final int[] firstPrimeNumbers = new int[]{
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541
    };

    @Test
    void testGenerateTable_returnsCorrectResult_correctParametersGiven() {
        int[] testValues = {1, 2, 3, 4, 5, 10, 20, 50, 100};

        for (int numberCount : testValues) {
            int[][] generatedTable = EratosthenesSieve.generateTable(numberCount);

            assertNotNull(generatedTable);
            assertEquals(generatedTable.length, numberCount);
            for (int i = 0; i < numberCount; i++) {
                assertEquals(generatedTable[i][0], i + 2);
                // the other value doesn't matter as it always should be set
                // to correct one in markPrimesInTable
            }
        }
    }

    @Test
    void testGenerateTable_throwsIllegalArgumentException_incorrectParametersGiven() {
        int[] testValues = {Integer.MIN_VALUE, -5, -1, 0};
        for (int numberCount : testValues) {
            assertThrows(IllegalArgumentException.class, () -> EratosthenesSieve.generateTable(numberCount));
        }
    }

    @Test
    void testMarkPrimesInTable_throwsNPE_nullTableGiven() {
        assertThrows(NullPointerException.class, () -> sieve.markPrimesInTable(null));
    }

    @Test
    void testMarkPrimesInTable_marksPrimesCorrectly_customIsPrimeImplementation() {
        int[] testValues = {1, 2, 5, 10, 20, 50, 100};
        final Calculator testCalc = new Calculator() {
            @Override
            public boolean isPrime(int number) {
                return true;
            }
        };

        final EratosthenesSieve testSieve = new EratosthenesSieve(testCalc);

        for (int numberCount : testValues) {
            // We assume this works correctly, otherwise other tests will fail and notify us about it.
            // generateTable should not use isPrime, so I can use either default `sieve` or `testSieve`
            int[][] table = EratosthenesSieve.generateTable(numberCount);

            int[][] calculatedResult = testSieve.markPrimesInTable(table);
            assertNotNull(calculatedResult);

            for (int[] calculatedResultRow : calculatedResult) {
                assertEquals(calculatedResultRow[1], 1);
            }
        }
    }

    @Test
    void testMarkPrimesInTable_marksNonPrimesCorrectly_customIsPrimeImplementation() {
        int[] testValues = {1, 2, 5, 10, 20, 50, 100};
        final Calculator testCalc = new Calculator() {
            @Override
            public boolean isPrime(int number) {
                return false;
            }
        };

        final EratosthenesSieve testSieve = new EratosthenesSieve(testCalc);

        for (int numberCount : testValues) {
            // We assume this works correctly, otherwise other tests will fail and notify us about it.
            // generateTable should not use isPrime, so I can use either default `sieve` or `testSieve`
            int[][] table = EratosthenesSieve.generateTable(numberCount);

            int[][] calculatedResult = testSieve.markPrimesInTable(table);
            assertNotNull(calculatedResult);

            for (int[] calculatedResultRow : calculatedResult) {
                assertEquals(calculatedResultRow[1], 0);
            }
        }
    }

    @Test
    void testMarkPrimesInTable_returnsCorrectResult_defaultImplAndCorrectParametersGiven() {
        int[] testValues = {1, 2, 5, 10, 20, 50, 100};
        // We assume this works correctly, otherwise other tests will fail and notify us about it.
        for (int numberCount : testValues) {
            int[][] table = EratosthenesSieve.generateTable(numberCount);

            int[][] calculatedResult = sieve.markPrimesInTable(table);
            assertNotNull(calculatedResult);

            // Check if the data in returned array is correct
            for (int[] calculatedResultRow : calculatedResult) {
                if (Arrays.stream(firstPrimeNumbers).anyMatch(x -> x == calculatedResultRow[0])) {
                    assertEquals(calculatedResultRow[1], 1);
                } else {
                    assertEquals(calculatedResultRow[1], 0);
                }
            }
        }
    }

    @Test
    void testSieveTableToString_throwsNPE_argumentIsNull() {
        assertThrows(NullPointerException.class, () -> EratosthenesSieve.sieveTableToString(null));
    }
}
