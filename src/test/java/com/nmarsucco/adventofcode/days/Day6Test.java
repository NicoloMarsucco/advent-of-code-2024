package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day6Test {
    Day6 day6 = new Day6(true);

    @Test
    void testPart1() {
        assertEquals("41", day6.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("6", day6.solvePart2().toString());
    }

}
