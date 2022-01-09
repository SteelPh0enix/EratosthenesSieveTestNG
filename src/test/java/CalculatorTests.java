import com.steelph0enix.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

@Test()
public class CalculatorTests {
    private final Calculator calc = new Calculator();

    @DataProvider(name = "divisiblePairs")
    public static Object[][] divisiblePairs() {
        return new Object[][]{{1, 1}, {2, 1}, {2, 2}, {3, 1}, {4, 1}, {4, 2}, {5, 1}, {6, 3}, {10, 2}, {10, 5}, {100, 10}, {70, 7}, {36, 6}};
    }

    @DataProvider(name = "nonDivisiblePairs")
    public static Object[][] nonDivisiblePairs() {
        return new Object[][]{{1, 20}, {22, 15}, {3, 2}, {5, 3}, {123, 456}, {199, 10}, {100, 33}};
    }

    @Test
    void testFloorRootValue_returnsCorrectResult_correctParametersGiven() {
        int[] testValues = {0, 1, 2, 3, 4, 5, 10, 20, 50, 100, 1000, Integer.MAX_VALUE};

        for (int number : testValues) {
            final int calculatorResult = calc.floorRootValue(number);
            final int expectedResult = (int) Math.sqrt(number);

            assertEquals(calculatorResult, expectedResult);
        }
    }

    @Test(dataProvider = "divisiblePairs")
    void testIsDivisible_returnsCorrectResult_correctParametersGiven(int dividend, int divisor) {
        final boolean calculatorResult = calc.isDivisible(dividend, divisor);
        final boolean expectedResult = true;

        assertEquals(calculatorResult, expectedResult);
    }


    @Test(dataProvider = "nonDivisiblePairs")
    void testIsDivisible_returnsCorrectResult_incorrectParametersGiven(int dividend, int divisor) {
        final boolean calculatorResult = calc.isDivisible(dividend, divisor);
        final boolean expectedResult = false;

        assertEquals(calculatorResult, expectedResult);
    }

    @Test
    void testIsDivisible_throwsException_divisionByZero() {
        int[] testValues = {0, 1, 2, 3, 4, 5, 10, 20, 50, 100, 1000, Integer.MAX_VALUE};
        for (int dividend : testValues) {
            assertThrows(IllegalArgumentException.class, () -> calc.isDivisible(dividend, 0));
        }
    }

    @Test
    void testIsPrime_returnsCorrectResult_correctParametersGiven() {
        int[] testValues = {-2, -3, -5, -7, -11, -13, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

        for (int number : testValues) {
            final boolean calculatorResult = calc.isPrime(number);
            final boolean expectedResult = true;

            assertEquals(calculatorResult, expectedResult);
        }
    }

    @Test
    void testIsPrime_returnsCorrectResult_incorrectParametersGiven() {
        int[] testValues = {-10, -8, -6, -4, -1, 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22};

        for (int number : testValues) {
            final boolean calculatorResult = calc.isPrime(number);
            final boolean expectedResult = false;

            assertEquals(calculatorResult, expectedResult);
        }
    }
}
