package com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.util.Arrays;

import com.nmarsucco.adventofcode.Day;

public class Day13 extends Day {

    public Day13() {
        this(false);
    }

    public Day13(boolean isTestingMode) {
        super(13, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        return solvePuzzle(true);
    }

    private long solvePuzzle(boolean isPartOne) {
        long[] tokens = new long[1];
        try (BufferedReader br = getNewBufferedReader()) {
            String line;
            long[][] matrixA = new long[2][2];
            long[] matrixB = new long[2];
            long[] x = new long[2];
            while ((line = br.readLine()) != null) {
                processLine(line, matrixA, matrixB, x, tokens, isPartOne);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return tokens[0];
    }

    private void processLine(String line, long[][] matrixA, long[] matrixB, long[] x, long[] tokens,
            boolean isPartOne) {
        if (line.equals("")) {
            return;
        }
        if (line.contains("Prize")) {
            fillMatrixB(line, matrixB, isPartOne);
            if (solve(matrixA, matrixB, x) && ((x[0] <= 100) || !isPartOne) && ((x[1] <= 100) || !isPartOne)) {
                tokens[0] += 3 * x[0] + x[1];
            }
        } else {
            fillMatrixA(line, matrixA);
        }
    }

    private void fillMatrixA(String line, long[][] matrixA) {
        int column = line.contains("A") ? 0 : 1;
        long[] values = Arrays.stream(line.split("Button .: X\\+|, Y\\+")).filter((part) -> part.length() > 0)
                .mapToLong(Long::parseLong).toArray();
        matrixA[0][column] = values[0];
        matrixA[1][column] = values[1];
    }

    private void fillMatrixB(String line, long[] matrixB, boolean isPartOne) {
        long delta = isPartOne ? 0 : 10000000000000L;

        long[] numbers = Arrays.stream(line.split("Prize: X=|, Y=")).filter((part) -> part.length() > 0)
                .mapToLong(Long::parseLong).toArray();
        matrixB[0] = numbers[0] + delta;
        matrixB[1] = numbers[1] + delta;

    }

    private static long calculateDeterminant(long[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    private boolean solve(long[][] matrixA, long[] matrixB, long[] x) {
        // Solve using Cramer's rule
        long determinant = calculateDeterminant(matrixA);

        if (determinant == 0) {
            return false;
        }

        // Solve for x1
        long[][] dX = deepCopy(matrixA);
        dX[0][0] = matrixB[0];
        dX[1][0] = matrixB[1];
        x[0] = calculateDeterminant(dX) / determinant;

        // Solve for x2
        long[][] dY = deepCopy(matrixA);
        dY[0][1] = matrixB[0];
        dY[1][1] = matrixB[1];
        x[1] = calculateDeterminant(dY) / determinant;

        // Check if valid
        return isValidSolution(x, matrixA, matrixB);
    }

    private long[][] deepCopy(long[][] array) {
        long[][] res = new long[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                res[i][j] = array[i][j];
            }
        }
        return res;
    }

    private static boolean isValidSolution(long[] x, long[][] matrixA, long[] matrixB) {
        boolean line1 = (matrixA[0][0] * x[0] + matrixA[0][1] * x[1] == matrixB[0]);
        boolean line2 = (matrixA[1][0] * x[0] + matrixA[1][1] * x[1] == matrixB[1]);
        return line1 && line2;
    }

    @Override
    public Object solvePart2() {
        return solvePuzzle(false);
    }

}
