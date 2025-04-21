package src.test.java.com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import src.main.java.com.nmarsucco.adventofcode.days.Day1;

public class Day1Test {
    Day1 day = new Day1(true);

    @Test
    void testPart1() {
        assertEquals("11", day.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("31", day.solvePart2().toString());
    }

}
