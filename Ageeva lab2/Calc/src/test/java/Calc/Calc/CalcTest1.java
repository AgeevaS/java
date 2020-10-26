package Calc.Calc;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest1 {

    @org.junit.jupiter.api.Test
    void calculate() {
        Calc calc = new Calc();
        int actual = calc.calculate(9+8*3);
        int expected = 33;
        assertEquals (expected, actual);
    }
}