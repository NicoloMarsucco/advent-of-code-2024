package com.nmarsucco.adventofcode.days;

import com.nmarsucco.adventofcode.util.Position;
import com.nmarsucco.adventofcode.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

import com.nmarsucco.adventofcode.Day;

public class Day10 extends Day {

    public Day10() {
        this(false);
    }

    public Day10(boolean isTestingMode) {
        super(10, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        char[][] grid = StringUtils.getInputAsCharMatrix(getInputPath());
        int sumOfScores = 0;
        int[] score = new int[1];
        Set<Position> peaksReached = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    dfsSearchPart1(grid, i, j, score, '/', peaksReached);
                    sumOfScores += score[0];
                    score[0] = 0;
                    peaksReached.clear();
                }
            }
        }
        return sumOfScores;
    }

    private void dfsSearchPart1(char[][] grid, int i, int j, int[] sumOfScores, char previous,
            Set<Position> peaksReached) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        char curr = grid[i][j];
        if ((curr - previous != 1)) {
            return;
        }
        if (curr == '9') {
            if (peaksReached.add(new Position(i, j))) {
                sumOfScores[0]++;
            }
            return;
        }
        dfsSearchPart1(grid, i - 1, j, sumOfScores, curr, peaksReached);
        dfsSearchPart1(grid, i + 1, j, sumOfScores, curr, peaksReached);
        dfsSearchPart1(grid, i, j - 1, sumOfScores, curr, peaksReached);
        dfsSearchPart1(grid, i, j + 1, sumOfScores, curr, peaksReached);
    }

    @Override
    public Object solvePart2() {
        char[][] grid = StringUtils.getInputAsCharMatrix(getInputPath());
        int sumOfRatings = 0;
        int[] rating = new int[1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    dfsSearchPart2(grid, i, j, rating, '/');
                    sumOfRatings += rating[0];
                    rating[0] = 0;
                }
            }
        }
        return sumOfRatings;
    }

    private void dfsSearchPart2(char[][] grid, int i, int j, int[] rating, char previous) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        char curr = grid[i][j];
        if ((curr - previous != 1)) {
            return;
        }
        if (curr == '9') {
            rating[0]++;
            return;
        }
        dfsSearchPart2(grid, i - 1, j, rating, curr);
        dfsSearchPart2(grid, i + 1, j, rating, curr);
        dfsSearchPart2(grid, i, j - 1, rating, curr);
        dfsSearchPart2(grid, i, j + 1, rating, curr);
    }

}
