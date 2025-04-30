package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day3Test {
    Day3 day3 = new Day3(true);

    @Test
    void testPart1() {
        assertEquals("161", day3.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("48", day3.solvePart2().toString());
    }
}
