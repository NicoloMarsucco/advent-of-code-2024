package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day12Test {
    Day12 day12 = new Day12(true);

    @Test
    void testPart1() {
        assertEquals("1930", day12.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("1206", day12.solvePart2().toString());
    }

}
