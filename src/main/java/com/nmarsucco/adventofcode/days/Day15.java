package com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nmarsucco.adventofcode.Day;
import com.nmarsucco.adventofcode.util.StringUtils;

public class Day15 extends Day {

    public Day15() {
        this(false);
    }

    public Day15(boolean isTestingMode) {
        super(15, isTestingMode);
    }

    @Override
    public Object solvePart1() {

        // Preparing the input
        int[] initialPosition = new int[2];
        List<String> gridList = new ArrayList<>();
        String commands = completeFirstScan(gridList, initialPosition);
        char[][] grid = StringUtils.transformListOfStringsToCharMatrix(gridList);

        // Solving the puzzle
        moveTheRobot(grid, initialPosition, commands);

        return calculateGpsSum(grid, true);
    }

    private String completeFirstScan(List<String> list, int[] initialPosition) {
        try (BufferedReader br = getNewBufferedReader()) {
            String line;
            boolean isGridPart = true;
            StringBuilder commands = new StringBuilder();
            while ((line = br.readLine()) != null) {

                // If it the blank line, we transition from grid to commands
                if (line.isBlank()) {
                    isGridPart = false;
                    continue;
                }

                if (isGridPart) {
                    if (line.contains("@")) {
                        initialPosition[0] = list.size();
                        initialPosition[1] = line.indexOf('@');
                        line = line.replace('@', '.');
                    }
                    list.add(line);
                } else {
                    commands.append(line);
                }
            }
            return commands.toString();
        } catch (Exception e) {
            return "";
        }
    }

    private void moveTheRobot(char[][] grid, int[] position, String commands) {
        // Array to keppe track of the direction of the robot
        int[] delta = new int[2];

        for (char command : commands.toCharArray()) {
            convertCommandToDirection(command, delta);
            tryToMoveTheRobot(position, delta, grid);
            // StringUtils.printMatrix(grid);
        }

    }

    private void convertCommandToDirection(char command, int[] delta) {
        switch (command) {
            case '^':
                delta[0] = -1;
                delta[1] = 0;
                break;
            case 'v':
                delta[0] = 1;
                delta[1] = 0;
                break;
            case '<':
                delta[0] = 0;
                delta[1] = -1;
                break;
            default:
                delta[0] = 0;
                delta[1] = 1;
        }
    }

    private void tryToMoveTheRobot(int[] position, int[] delta, char[][] grid) {
        int steps = 1;
        while (true) {
            char next = grid[position[0] + steps * delta[0]][position[1] + steps * delta[1]];
            if (next == '#') {
                return;
            }
            if (next == '.') {
                break;
            }
            steps++;
        }

        // There are Os in front of us
        if (steps > 1) {
            grid[position[0] + delta[0]][position[1] + delta[1]] = '.';
            grid[position[0] + steps * delta[0]][position[1] + steps * delta[1]] = 'O';
        }

        position[0] += delta[0];
        position[1] += delta[1];
    }

    int calculateGpsSum(char[][] grid, boolean isPartOne) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'O' || grid[i][j] == '[') {
                    sum += 100 * i + j;
                    if (!isPartOne) {
                        j++;
                    }
                }

            }
        }
        return sum;
    }

    @Override
    public Object solvePart2() {
        // Preparing the input
        int[] initialPosition = new int[2];
        List<String> gridList = new ArrayList<>();
        String commands = completeFirstScan(gridList, initialPosition);
        char[][] gridPartOne = StringUtils.transformListOfStringsToCharMatrix(gridList);
        initialPosition[1] = initialPosition[1] * 2;
        char[][] grid = convertToTypeTwoGrid(gridPartOne);

        // Solving the puzzle
        moveTheRobotPartTwo(grid, initialPosition, commands);

        return calculateGpsSum(grid, false);
    }

    private char[][] convertToTypeTwoGrid(char[][] grid) {
        char[][] newGrid = new char[grid.length][2 * grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                char curr = grid[i][j];
                if (curr == 'O') {
                    newGrid[i][2 * j] = '[';
                    newGrid[i][2 * j + 1] = ']';
                } else {
                    newGrid[i][2 * j] = curr;
                    newGrid[i][2 * j + 1] = curr;
                }
            }
        }
        return newGrid;
    }

    private void moveTheRobotPartTwo(char[][] grid, int[] position, String commands) {
        // Array to keep track of the direction of the robot
        int[] delta = new int[2];

        for (char command : commands.toCharArray()) {
            // System.out.println("--------------------------");
            convertCommandToDirection(command, delta);
            // System.out.println(command);
            int[] candidate = { position[0] + delta[0], position[1] + delta[1] };
            if (canMove(grid, candidate, delta)) {
                int[] newPos = { position[0] + delta[0], position[1] + delta[1] };
                moveStuff(grid, newPos, delta);
                position[0] = position[0] + delta[0];
                position[1] = position[1] + delta[1];
            }
            // System.out.println(Arrays.toString(position));
            // StringUtils.printMatrix(grid);
        }

    }

    private void moveStuff(char[][] grid, int[] pos, int[] delta) {

        if (delta[0] == 0) {
            int steps = 0;
            while (grid[pos[0]][pos[1] + delta[1] * steps] != '.') {
                steps++;
            }
            while (steps >= 0) {
                grid[pos[0]][pos[1] + delta[1] * steps] = grid[pos[0]][pos[1] + delta[1] * (steps - 1)];
                steps--;
            }
            return;
        }

        char c = grid[pos[0]][pos[1]];

        if (c == '.') {
            grid[pos[0]][pos[1]] = grid[pos[0] - delta[0]][pos[1]];
            return;
        }
        int otherPartColum = c == '[' ? pos[1] + 1 : pos[1] - 1;

        int[] other = { pos[0] + delta[0], otherPartColum };
        int[] above = { pos[0] + delta[0], pos[1] };

        moveStuff(grid, other, delta);
        moveStuff(grid, above, delta);

        grid[pos[0]][pos[1]] = grid[pos[0] - delta[0]][pos[1]];
        grid[pos[0]][otherPartColum] = '.';
    }

    private boolean canMove(char[][] grid, int[] position, int[] delta) {
        if (delta[0] == 0) {
            if (delta[1] < 0) {
                int j = position[1];
                int i = position[0];
                while (j >= 0) {
                    if (grid[i][j] == ']') {
                        j -= 2;
                    } else if (grid[i][j] == '.') {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                int j = position[1];
                int i = position[0];
                while (j < grid[0].length) {
                    if (grid[i][j] == '[') {
                        j += 2;
                    } else if (grid[i][j] == '.') {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            // Recursive
            char c = grid[position[0]][position[1]];
            if (c == '#') {
                return false;
            }
            if (c == '.') {
                return true;
            }
            int[] topOther = { position[0] + delta[0], c == ']' ? position[1] - 1 : position[1] + 1 };
            int[] top = { position[0] + delta[0], position[1] };
            return canMove(grid, topOther, delta) && canMove(grid, top, delta);
        }
        return false;
    }

}
