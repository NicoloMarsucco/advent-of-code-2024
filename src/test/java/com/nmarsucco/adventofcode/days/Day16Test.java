package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day16Test {

    Day16 day16 = new Day16(true);

    @Test
    void testPart1() {
        assertEquals("11048", day16.solvePart1().toString());
    }
}
