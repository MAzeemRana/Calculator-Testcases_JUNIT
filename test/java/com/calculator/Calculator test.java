package com.calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class CalculatorTest {

    static Calculator sharedCalc;
    Calculator calc;

    @BeforeAll
    static void initAll() {
        // expensive setup once per test class
        sharedCalc = new Calculator();
    }
    @BeforeEach
    void init() {
        // fresh instance per test
        calc = new Calculator();
    }
    @Test
    @DisplayName("1 - Addition works")
    void testAddition() {
        assertEquals(7, calc.add(3, 4), "3 + 4 should equal 7");
    }
    @Test
    @DisplayName("2 - Subtraction works")
    void testSubtraction() {
        assertEquals(2, calc.subtract(5, 3));
    }
    @Test
    @DisplayName("3 - Multiplication works")
    void testMultiplication() {
        assertEquals(12, calc.multiply(3, 4));
    }
    @Test
    @DisplayName("4 - Division works")
    void testDivision() {
        assertEquals(5, calc.divide(10, 2));
    }
    @Test
    @DisplayName("5 - Division by zero throws")
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "-1, 1, 0",
            "100, 200, 300"
    })
    void parameterizedAddition(int a, int b, int expected) {
        assertEquals(expected, sharedCalc.add(a, b));
    }

    @AfterEach
    void tearDown() {
        // cleanup if needed
    }

    @AfterAll
    static void tearDownAll() {
        sharedCalc = null;
    }
}
