package com.nmarsucco.adventofcode;

import com.nmarsucco.adventofcode.days.Day1;
import com.nmarsucco.adventofcode.days.Day2;
import com.nmarsucco.adventofcode.days.Day3;
import com.nmarsucco.adventofcode.days.Day4;
import com.nmarsucco.adventofcode.days.Day5;
import com.nmarsucco.adventofcode.days.Day6;
import com.nmarsucco.adventofcode.days.Day7;
import com.nmarsucco.adventofcode.days.Day8;
import com.nmarsucco.adventofcode.days.Day9;
import com.nmarsucco.adventofcode.days.Day10;
import com.nmarsucco.adventofcode.days.Day11;
import com.nmarsucco.adventofcode.days.Day12;
import com.nmarsucco.adventofcode.days.Day13;
import com.nmarsucco.adventofcode.days.Day14;

public class Main {

    public static void main(String[] args) {
        Day[] solvedDays = { new Day1(), new Day2(), new Day3(), new Day4(), new Day5(), new Day6(), new Day7(),
                new Day8(), new Day9(), new Day10(), new Day11(), new Day12(), new Day13(), new Day14() };
        for (Day day : solvedDays) {
            System.out.println("Day " + day.getDayNumber() + ":");

            long startTime = System.nanoTime();
            Object part1 = day.solvePart1();
            long part1Time = (System.nanoTime() - startTime) / 1_000_000; // Converto to milliseconds

            startTime = System.nanoTime();
            Object part2 = day.solvePart2();
            long part2Time = (System.nanoTime() - startTime) / 1_000_000;

            System.out.println("Solution of part 1: " + part1.toString() + " (" + part1Time + "ms)");
            System.out.println("Solution of part 2: " + part2.toString() + " (" + part2Time + "ms)");
        }

    }
}
