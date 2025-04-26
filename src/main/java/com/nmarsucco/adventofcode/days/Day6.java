package src.main.java.com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import src.main.java.com.nmarsucco.adventofcode.Day;

public class Day6 extends Day {

    public Day6() {
        this(false);
    }

    public Day6(boolean isTestingMode) {
        super(6, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        int[] position = new int[2];
        int[] direction = { -1, 0 };
        int distinctVisitedLocations = 0;
        char visitedAnnotation = 'X';

        char[][] grid = transformListOfStringsToCharMatrix(getInputAsListOfStrings(position));

        while (!amIOffTheGrid(grid, position)) {

            if (grid[position[0]][position[1]] != visitedAnnotation) {
                grid[position[0]][position[1]] = visitedAnnotation;
                distinctVisitedLocations++;
            }
            fixDirection(grid, position, direction);
            moveToNextSquare(grid, position, direction);
        }
        return distinctVisitedLocations;
    }

    @Override
    public Object solvePart2() {
        int[] position = new int[2];
        int[] direction = { -1, 0 };
        Set<String> set = new HashSet<>();
        Set<String> subSet = new HashSet<>();
        Set<String> obstructionPositions = new HashSet<>();

        char[][] grid = transformListOfStringsToCharMatrix(getInputAsListOfStrings(position));

        while (!amIOffTheGrid(grid, position)) {

            fixDirection(grid, position, direction);

            if (set.add(canonicalize(position, direction)) && couldIAddObstacleInFrontOfMe(grid, position, direction)) {
                int[] subDirection = direction.clone();
                int[] subPosition = position.clone();
                subSet.clear();
                grid[position[0] + direction[0]][position[1] + direction[1]] = '#';
                rotateBy90Degrees(subDirection);
                while (valueOfTheGrid(grid, sumArrays(subDirection, subPosition)) == '#') {
                    rotateBy90Degrees(subDirection);
                }

                while (!amIOffTheGrid(grid, subPosition)) {
                    fixDirection(grid, subPosition, subDirection);
                    if (!subSet.add(canonicalize(subPosition, subDirection))
                            || set.contains(canonicalize(subPosition, subDirection))) {
                        obstructionPositions.add(Arrays.toString(sumArrays(position, direction)));
                        break;
                    }
                    moveToNextSquare(grid, subPosition, subDirection);
                }
                grid[position[0] + direction[0]][position[1] + direction[1]] = '.';

            }
            grid[position[0]][position[1]] = 'X';
            moveToNextSquare(grid, position, direction);
        }

        return obstructionPositions.size();
    }

    private List<String> getInputAsListOfStrings(int[] position) {
        List<String> inputsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputsList.add(line);
                if (line.contains("^")) {
                    position[1] = line.indexOf('^');
                    position[0] = inputsList.size() - 1;
                }
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

    private boolean amIOffTheGrid(char[][] grid, int[] position) {
        return (position[0] < 0 || position[0] >= grid.length || position[1] < 0 || position[1] >= grid[0].length);
    }

    private void fixDirection(char[][] grid, int[] position, int[] direction) {
        while (!amIOffTheGrid(grid, sumArrays(direction, position))
                && valueOfTheGrid(grid, sumArrays(direction, position)) == '#') {
            rotateBy90Degrees(direction);
        }
    }

    private void moveToNextSquare(char[][] grid, int[] position, int[] direction) {
        position[0] += direction[0];
        position[1] += direction[1];
    }

    private char valueOfTheGrid(char[][] grid, int[] position) {
        return grid[position[0]][position[1]];
    }

    private int[] sumArrays(int[] direction, int[] position) {
        int[] sum = new int[2];
        sum[0] = direction[0] + position[0];
        sum[1] = direction[1] + position[1];
        return sum;
    }

    private void rotateBy90Degrees(int[] direction) {
        int copyFirst = direction[0];
        direction[0] = direction[1];
        direction[1] = -copyFirst;
    }

    private String canonicalize(int[] position, int[] direction) {
        return Arrays.toString(position) + Arrays.toString(direction);
    }

    private boolean couldIAddObstacleInFrontOfMe(char[][] grid, int[] position, int[] direction) {
        return (!amIOffTheGrid(grid, sumArrays(direction, position))
                && valueOfTheGrid(grid, sumArrays(direction, position)) != 'X');
    }

}
