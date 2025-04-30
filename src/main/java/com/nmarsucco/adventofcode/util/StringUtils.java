package com.nmarsucco.adventofcode.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static char[][] getInputAsCharMatrix(String inputPath) {
        return transformListOfStringsToCharMatrix(getInputAsListOfStrings(inputPath));
    }

    private static char[][] transformListOfStringsToCharMatrix(List<String> listOfStrings) {
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

    private static List<String> getInputAsListOfStrings(String inputPath) {
        List<String> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return input;
    }

    public static String getInputLine(String inputPath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            line = br.readLine();
        } catch (Exception e) {
            line = "error";
            System.out.println("Error: " + e.getMessage());
        }
        return line;
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

}
