package src.test.java.com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import src.main.java.com.nmarsucco.adventofcode.days.Day9;

public class Day9Test {
    Day9 day9 = new Day9(true);

    @Test
    void testPart1() {
        assertEquals("1928", day9.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("2858", day9.solvePart2().toString());
    }

}
