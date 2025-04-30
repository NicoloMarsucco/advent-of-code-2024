package com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nmarsucco.adventofcode.Day;

public class Day5 extends Day {

    public Day5() {
        this(false);
    }

    public Day5(boolean isTestingMode) {
        super(5, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        int sum = 0;
        Map<Integer, List<Integer>> rules = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            boolean isRulesSection = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    isRulesSection = false;
                } else if (isRulesSection) {
                    addNumbersToRuleMap(rules, line);
                } else {
                    int[] numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                    if (areAllRulesMet(rules, numbers)) {
                        sum += numbers[numbers.length / 2];
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return sum;
    }

    private void addNumbersToRuleMap(Map<Integer, List<Integer>> rules, String line) {
        int[] numbers = Arrays.stream(line.split("\\|")).mapToInt(Integer::parseInt).toArray();
        if (!rules.containsKey(numbers[0])) {
            rules.put(numbers[0], new ArrayList<>());
        }
        rules.get(numbers[0]).add(numbers[1]);
    }

    private boolean areAllRulesMet(Map<Integer, List<Integer>> rules, int[] numbers) {
        Map<Integer, Integer> indices = fillIndexMap(numbers);
        for (int i = 0; i < numbers.length; i++) {
            if (!rules.containsKey(numbers[i])) {
                continue;
            } else {
                List<Integer> currentRules = rules.get(numbers[i]);
                for (int comparedNum : currentRules) {
                    if (!indices.containsKey(comparedNum)) {
                        continue;
                    } else if (indices.get(comparedNum) < i) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Map<Integer, Integer> fillIndexMap(int[] nums) {
        // Number -> index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        return map;
    }

    @Override
    public Object solvePart2() {
        int sum = 0;
        Map<Integer, List<Integer>> rules = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            boolean isRulesSection = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    isRulesSection = false;
                } else if (isRulesSection) {
                    addNumbersToRuleMap(rules, line);
                } else {
                    int[] numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                    if (!areAllRulesMet(rules, numbers)) {
                        fixOrdering(numbers, rules);
                        sum += numbers[numbers.length / 2];
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return sum;
    }

    private void fixOrdering(int[] numbers, Map<Integer, List<Integer>> rules) {
        Map<Integer, Integer> indices = fillIndexMap(numbers);
        int swaps = Integer.MAX_VALUE;

        while (swaps > 0) {
            swaps = 0;
            for (int i = 1; i < numbers.length; i++) {
                int current = numbers[i];
                if (rules.containsKey(current)) {
                    for (int compared : rules.get(current)) {
                        if (indices.getOrDefault(compared, Integer.MAX_VALUE) < i) {
                            swap(numbers, i, indices.get(compared));
                            updateIndices(indices, current, compared);
                            swaps++;
                            break;
                        }
                    }
                }
            }
        }

    }

    private void swap(int[] numbers, int i, int k) {
        int numberI = numbers[i];
        numbers[i] = numbers[k];
        numbers[k] = numberI;
    }

    private void updateIndices(Map<Integer, Integer> indices, int number1, int number2) {
        int indexOfNumber1 = indices.get(number1);
        indices.put(number1, indices.get(number2));
        indices.put(number2, indexOfNumber1);
    }
}
