package com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nmarsucco.adventofcode.Day;

public class Day4 extends Day {

    private int countPart1 = 0;
    private final String WORD_TO_FIND = "XMAS";
    private final int[] deltaI = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private final int[] deltaJ = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public Day4() {
        this(false);
    }

    public Day4(boolean isTestingMode) {
        super(4, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        char[][] matrix = transformListOfStringsToCharMatrix(getInputAsListOfStrings());
        countOccurrencesPart1(matrix);
        return countPart1;
    }

    private List<String> getInputAsListOfStrings() {
        List<String> inputsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputsList.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return inputsList;
    }

    private char[][] transformListOfStringsToCharMatrix(List<String> listOfStrings) {
        int numberOfRows = listOfStrings.size();
        int numberOfColumns = listOfStrings.get(0).length();
        char[][] matrix = new char[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                matrix[i][j] = listOfStrings.get(i).charAt(j);
            }
        }
        return matrix;
    }

    private void countOccurrencesPart1(char[][] grid) {
        char firstLetter = WORD_TO_FIND.charAt(0);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == firstLetter) {
                    for (int k = 0; k < deltaI.length; k++) {
                        recursiveDfs(grid, i + deltaI[k], j + deltaJ[k], 1, k);
                    }
                }

            }
        }
    }

    private void recursiveDfs(char[][] grid, int i, int j, int letterIndex, int directionIndex) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length
                || grid[i][j] != WORD_TO_FIND.charAt(letterIndex)) {
            return;
        }
        if (letterIndex == WORD_TO_FIND.length() - 1) {
            countPart1++;
            return;
        }
        recursiveDfs(grid, i + deltaI[directionIndex], j + deltaJ[directionIndex], letterIndex + 1, directionIndex);
    }

    @Override
    public Object solvePart2() {
        char[][] matrix = transformListOfStringsToCharMatrix(getInputAsListOfStrings());
        return countOccurrencesPart2(matrix);
    }

    private int countOccurrencesPart2(char[][] grid) {
        int count = 0;
        char triggerChar = 'A';
        Set<String> validDiagonals = new HashSet<>();
        validDiagonals.add("MS");
        validDiagonals.add("SM");
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (grid[i][j] == triggerChar) {
                    String diagonal1 = "" + grid[i - 1][j - 1] + grid[i + 1][j + 1];
                    String diagonal2 = "" + grid[i - 1][j + 1] + grid[i + 1][j - 1];
                    if (validDiagonals.contains(diagonal1) && validDiagonals.contains(diagonal2)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
