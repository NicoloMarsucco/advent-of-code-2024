package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day8Test {
    Day8 day8 = new Day8(true);

    @Test
    void testPart1() {
        assertEquals("14", day8.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("34", day8.solvePart2().toString());
    }

}
