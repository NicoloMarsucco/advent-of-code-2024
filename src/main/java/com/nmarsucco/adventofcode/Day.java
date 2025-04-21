package src.main.java.com.nmarsucco.adventofcode;

public abstract class Day {

    private final int dayNumber;
    private final boolean isTestingMode;

    public Day(int dayNumber, boolean isTestingMode) {
        this.dayNumber = dayNumber;
        this.isTestingMode = isTestingMode;
    }

    public Day(int dayNumber) {
        this(dayNumber, false);
    }

    protected String getInputPath() {
        String fileName = isTestingMode ? "test-input.txt" : "input.txt";
        return "data/" + String.format("day%02d", dayNumber) + "/" + fileName;
    }

    public abstract Object solvePart1();

    public abstract Object solvePart2();

    public int getDayNumber() {
        return this.dayNumber;
    }

}