package com.nmarsucco.adventofcode.util;

import java.util.Arrays;

// Quadrants count
//      4   |   1
// --------------------
//     3    |   2

public class RobotsGrid {

    public final int rows;
    public final int columns;
    private final int[] robotsInEachQuadrant;
    private boolean[][] part2Grid;

    public RobotsGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.robotsInEachQuadrant = new int[4];
        resetPart2Grid();
    }

    public void addRobot(Position position) {
        if (position.row == (rows / 2) || position.col == (columns / 2)) {
            return;
        }
        robotsInEachQuadrant[determineQuadrant(position)]++;
    }

    public int getSafetyFactor() {
        return Arrays.stream(robotsInEachQuadrant).reduce(1, (a, b) -> a * b);
    }

    private int determineQuadrant(Position position) {
        boolean isTop = position.row < (rows / 2);
        boolean isLeft = position.col < (columns / 2);
        if (isTop && !isLeft) {
            return 0;
        } else if (!isTop && !isLeft) {
            return 1;
        } else if (!isTop && isLeft) {
            return 2;
        }
        return 3;
    }

    public void resetPart2Grid() {
        part2Grid = new boolean[rows][columns];
    }

    public void addRobotPart2(Position position) {
        part2Grid[position.row][position.col] = true;
    }

    public int countAdjacentRobots() {
        int res = 0;
        for (int i = 0; i < part2Grid.length; i++) {
            for (int j = 0; j < part2Grid[0].length; j++) {
                res += addAdjacents(i, j, part2Grid);
            }
        }
        return res;
    }

    private int addAdjacents(int i, int j, boolean[][] grid) {
        int val = 0;
        if (i > 0 && grid[i - 1][j]) {
            val++;
        }
        if (i < grid.length - 1 && grid[i + 1][j]) {
            val++;
        }
        if (j > 0 && grid[i][j - 1]) {
            val++;
        }
        if (j < grid[0].length - 1 && grid[i][j + 1]) {
            val++;
        }
        return val;
    }

}
