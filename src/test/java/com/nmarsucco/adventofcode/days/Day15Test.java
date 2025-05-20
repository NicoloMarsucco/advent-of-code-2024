package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day15Test {

    Day15 day15 = new Day15(true);

    @Test
    void testPart1() {
        assertEquals("10092", day15.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("9021", day15.solvePart2().toString());
    }

}
