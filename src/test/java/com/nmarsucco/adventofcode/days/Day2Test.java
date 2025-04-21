package src.test.java.com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import src.main.java.com.nmarsucco.adventofcode.days.Day2;

public class Day2Test {
    Day2 day2 = new Day2(true);

    @Test
    void testPart1() {
        assertEquals("2", day2.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("4", day2.solvePart2().toString());
    }

}
