package src.main.java.com.nmarsucco.adventofcode.common;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Day {

    private final int dayNumber;

    public Day(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    protected Path getInputPath() {
        return Paths.get("data", String.format("day%02d", dayNumber), "input.txt");
    }

    public abstract Object solvePart1();

    public abstract Object solvePart2();

}