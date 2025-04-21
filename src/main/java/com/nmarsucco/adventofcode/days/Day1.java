package src.main.java.com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import src.main.java.com.nmarsucco.adventofcode.Day;

public class Day1 extends Day {

    public Day1() {
        this(false);
    }

    public Day1(boolean isTestingMode) {
        super(1, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        List<Integer> column1 = new ArrayList<>();
        List<Integer> column2 = new ArrayList<>();

        fillColumnLists(column1, column2);

        Collections.sort(column1);
        Collections.sort(column2);

        return calculateSumOfDistances(column1, column2);
    }

    private void fillColumnLists(List<Integer> column1, List<Integer> column2) {
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split("   ");
                column1.add(Integer.valueOf(columns[0]));
                column2.add(Integer.valueOf(columns[1]));
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private int calculateSumOfDistances(List<Integer> column1, List<Integer> column2) {
        int sum = 0;
        for (int i = 0; i < column1.size(); i++) {
            sum += Math.abs(column1.get(i) - column2.get(i));
        }
        return sum;
    }

    @Override
    public Object solvePart2() {

        // Maps to keep track of how often the elements appear in the second column
        Map<Integer, Integer> countsColumn1 = new HashMap<>();
        Map<Integer, Integer> countsColumn2 = new HashMap<>();

        fillCountMaps(countsColumn1, countsColumn2);

        return calculateSimilarityScore(countsColumn1, countsColumn2);
    }

    private void fillCountMaps(Map<Integer, Integer> column1, Map<Integer, Integer> column2) {
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split("   ");
                int valueColumn1 = Integer.valueOf(columns[0]);
                int valueColumn2 = Integer.valueOf(columns[1]);
                column1.put(valueColumn1, column1.getOrDefault(valueColumn1, 0) + 1);
                column2.put(valueColumn2, column2.getOrDefault(valueColumn2, 0) + 1);
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private int calculateSimilarityScore(Map<Integer, Integer> column1, Map<Integer, Integer> column2) {
        int similarityScore = 0;
        for (Map.Entry<Integer, Integer> entry : column1.entrySet()) {
            similarityScore += entry.getKey() * entry.getValue() * column2.getOrDefault(entry.getKey(), 0);
        }
        return similarityScore;
    }

}
