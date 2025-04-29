package src.main.java.com.nmarsucco.adventofcode;

import src.main.java.com.nmarsucco.adventofcode.days.Day1;
import src.main.java.com.nmarsucco.adventofcode.days.Day2;
import src.main.java.com.nmarsucco.adventofcode.days.Day3;
import src.main.java.com.nmarsucco.adventofcode.days.Day4;
import src.main.java.com.nmarsucco.adventofcode.days.Day5;
import src.main.java.com.nmarsucco.adventofcode.days.Day6;
import src.main.java.com.nmarsucco.adventofcode.days.Day7;
import src.main.java.com.nmarsucco.adventofcode.days.Day8;
import src.main.java.com.nmarsucco.adventofcode.days.Day9;
import src.main.java.com.nmarsucco.adventofcode.days.Day10;

public class Main {

    public static void main(String[] args) {
        Day[] solvedDays = { new Day1(), new Day2(), new Day3(), new Day4(), new Day5(), new Day6(), new Day7(),
                new Day8(), new Day9(), new Day10() };
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
