package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day5Test {

    Day5 day5 = new Day5(true);

    @Test
    void testPart1() {
        assertEquals("143", day5.solvePart1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("123", day5.solvePart2().toString());
    }

}
