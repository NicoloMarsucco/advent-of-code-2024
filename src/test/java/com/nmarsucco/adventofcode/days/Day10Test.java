package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day10Test {
    Day10 day10 = new Day10(true);

    @Test
    void testPart1() {
        assertEquals("36", day10.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("81", day10.solvePart2().toString());
    }
}
