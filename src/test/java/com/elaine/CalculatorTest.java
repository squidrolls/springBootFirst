package com.elaine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    void canAddNumbers(){
        Calculator underTest = new Calculator();
        int result = underTest.add(10, 20);
        assertEquals(30, result);
    }

    @Test
    void canHandleWhenInputIsZero(){
        Calculator underTest = new Calculator();
        int result = underTest.add(0);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("foo")
    void canAddNumbersFromAGivenArray(){
        Calculator underTest = new Calculator();
        var numbers = new int[]{1, 5, 3};
        int result = underTest.add(numbers);
        assertEquals(9, result);
    }
}
