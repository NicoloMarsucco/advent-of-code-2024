package src.test.java.com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import src.main.java.com.nmarsucco.adventofcode.days.Day4;

public class Day4Test {
    Day4 day4 = new Day4(true);

    @Test
    void testPart1() {
        assertEquals("18", day4.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("9", day4.solvePart2().toString());
    }
}
