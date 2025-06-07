package com.nmarsucco.adventofcode;

import com.nmarsucco.adventofcode.days.*;

public class Main {

    public static void main(String[] args) {
        Day[] solvedDays = { new Day1(), new Day2(), new Day3(), new Day4(), new Day5(), new Day6(), new Day7(),
                new Day8(), new Day9(), new Day10(), new Day11(), new Day12(), new Day13(), new Day14(), new Day15(),
                new Day16() };
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
