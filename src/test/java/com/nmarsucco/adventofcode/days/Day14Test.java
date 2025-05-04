package com.nmarsucco.adventofcode.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day14Test {

    Day14 day14 = new Day14(true);

    @Test
    void testPart1() {
        assertEquals("12", day14.solvePart1().toString());
    }

}
