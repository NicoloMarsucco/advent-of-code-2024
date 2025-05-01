package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day13Test {
    Day13 day13 = new Day13(true);

    @Test
    void testPart1() {
        assertEquals("480", day13.solvePart1().toString());
    }
}
