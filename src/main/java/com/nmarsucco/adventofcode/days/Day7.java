package com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.util.Arrays;

import com.nmarsucco.adventofcode.Day;

public class Day7 extends Day {

    public Day7() {
        this(false);
    }

    public Day7(boolean isTestingMode) {
        super(7, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        long sum = 0;
        try (BufferedReader br = getNewBufferedReader()) {
            String line;
            while ((line = br.readLine()) != null) {
                long[] values = Arrays.stream(line.split(" |: ")).mapToLong(Long::parseLong).toArray();
                if (dfsSearchPart1(values, 2, values[1])) {
                    sum += values[0];
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sum;
    }

    private static boolean dfsSearchPart1(long[] array, int i, long cumSum) {
        if (i == array.length - 1) {
            return (cumSum + array[i] == array[0]) || (cumSum * array[i] == array[0]);
        }
        if (cumSum > array[0]) {
            return false;
        }
        return dfsSearchPart1(array, i + 1, cumSum + array[i]) || dfsSearchPart1(array, i + 1, cumSum * array[i]);
    }

    @Override
    public Object solvePart2() {
        long sum = 0;
        try (BufferedReader br = getNewBufferedReader()) {
            String line;
            while ((line = br.readLine()) != null) {
                long[] values = Arrays.stream(line.split(" |: ")).mapToLong(Long::parseLong).toArray();
                if (dfsSearchPart2(values, 2, values[1])) {
                    sum += values[0];
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sum;
    }

    private static boolean dfsSearchPart2(long[] array, int i, long cumSum) {
        if (cumSum > array[0]) {
            return false;
        }
        long thirdOperation = Long.parseLong(String.valueOf(cumSum) + String.valueOf(array[i]));
        if (i == array.length - 1) {
            return (cumSum + array[i] == array[0]) || (cumSum * array[i] == array[0]) || (thirdOperation == array[0]);
        }
        return dfsSearchPart2(array, i + 1, cumSum + array[i]) || dfsSearchPart2(array, i + 1, cumSum * array[i])
                || dfsSearchPart2(array, i + 1, thirdOperation);
    }
}
