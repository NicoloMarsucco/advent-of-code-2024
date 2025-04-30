package com.nmarsucco.adventofcode.days;

import com.nmarsucco.adventofcode.Day;
import com.nmarsucco.adventofcode.util.StringUtils;

public class Day12 extends Day {

    private static final int[] subGridI = { 1, 0, 1, 2, 1 };
    private static final int[] subGridJ = { 0, 1, 2, 1, 0 };
    private static final int[] determiningI = { 0, 0, 2, 2 };
    private static final int[] determiningJ = { 0, 2, 2, 0 };

    public Day12() {
        this(false);
    }

    public Day12(boolean isTestingMode) {
        super(12, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        return solve(true);
    }

    private int solve(boolean isPartOne) {
        char[][] grid = StringUtils.getInputAsCharMatrix(getInputPath());
        int totalPrice = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (Character.isUpperCase(grid[i][j])) {
                    char curr = grid[i][j];
                    int[] parameters = new int[2]; // Area, perimeter
                    dfsSearch(grid, i, j, curr, parameters, isPartOne);
                    totalPrice += parameters[0] * parameters[1];
                }
            }
        }
        return totalPrice;
    }

    private void dfsSearch(char[][] grid, int i, int j, char curr, int[] par, boolean isPartOne) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != curr) {
            return;
        }
        addArea(par);
        if (isPartOne) {
            addPerimeter(grid, i, j, par, curr);
        } else {
            countCorners(grid, i, j, par, curr);
        }
        grid[i][j] = Character.toLowerCase(curr);

        dfsSearch(grid, i - 1, j, curr, par, isPartOne);
        dfsSearch(grid, i + 1, j, curr, par, isPartOne);
        dfsSearch(grid, i, j - 1, curr, par, isPartOne);
        dfsSearch(grid, i, j + 1, curr, par, isPartOne);
    }

    private void addArea(int[] par) {
        par[0]++;
    }

    private void addPerimeter(char[][] grid, int i, int j, int[] par, char curr) {

        // Top
        if (i == 0 || Character.toUpperCase(grid[i - 1][j]) != curr) {
            par[1]++;
        }

        // Bottom
        if (i == grid.length - 1 || Character.toUpperCase(grid[i + 1][j]) != curr) {
            par[1]++;
        }

        // Left
        if (j == 0 || Character.toUpperCase(grid[i][j - 1]) != curr) {
            par[1]++;
        }

        // Right
        if (j == grid[0].length - 1 || Character.toUpperCase(grid[i][j + 1]) != curr) {
            par[1]++;
        }
    }

    private void countCorners(char[][] grid, int i, int j, int[] par, char curr) {
        boolean[][] subGrid = fillSubGrid(grid, i, j, curr);

        for (int k = 1; k < subGridI.length; k++) {
            int prevRow = subGridI[k - 1];
            int currRow = subGridI[k];
            int prevCol = subGridJ[k - 1];
            int currCol = subGridJ[k];

            boolean prevBool = subGrid[prevRow][prevCol];
            boolean currBool = subGrid[currRow][currCol];
            boolean determiningSquare = subGrid[determiningI[k - 1]][determiningJ[k - 1]];

            if ((!prevBool && !currBool) || (prevBool && currBool && !determiningSquare)) {
                par[1]++;
            }
        }
    }

    private boolean[][] fillSubGrid(char[][] grid, int i, int j, char curr) {
        boolean[][] subGrid = new boolean[3][3];

        // Top row
        if (i > 0) {
            if (j > 0) {
                subGrid[0][0] = (Character.toUpperCase(grid[i - 1][j - 1]) == curr);
            }
            subGrid[0][1] = Character.toUpperCase(grid[i - 1][j]) == curr;
            if (j < grid[0].length - 1) {
                subGrid[0][2] = Character.toUpperCase(grid[i - 1][j + 1]) == curr;
            }
        }

        // Middle row
        if (j > 0) {
            subGrid[1][0] = (Character.toUpperCase(grid[i][j - 1]) == curr);
        }
        if (j < grid[0].length - 1) {
            subGrid[1][2] = (Character.toUpperCase(grid[i][j + 1]) == curr);
        }

        // Bottom
        if (i < grid.length - 1) {
            if (j > 0) {
                subGrid[2][0] = (Character.toUpperCase(grid[i + 1][j - 1]) == curr);
            }
            subGrid[2][1] = Character.toUpperCase(grid[i + 1][j]) == curr;
            if (j < grid[0].length - 1) {
                subGrid[2][2] = Character.toUpperCase(grid[i + 1][j + 1]) == curr;
            }
        }
        return subGrid;
    }

    @Override
    public Object solvePart2() {
        return solve(false);
    }

}
