package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day7Test {
    Day7 day7 = new Day7(true);

    @Test
    void testPart1() {
        assertEquals("3749", day7.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("11387", day7.solvePart2().toString());
    }
}
