package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day11Test {

    Day11 day11 = new Day11(true);

    @Test
    void testPart1() {
        assertEquals("55312", day11.solvePart1().toString());
    }

}
