package src.main.java.com.nmarsucco.adventofcode.days;

import src.main.java.com.nmarsucco.adventofcode.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import src.main.java.com.nmarsucco.adventofcode.Day;

public class Day11 extends Day {

    Map<Long, Long> v = new HashMap<>();

    public Day11() {
        this(false);
    }

    public Day11(boolean isTestingMode) {
        super(11, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        return solveForNBlinks(25);
    }

    private long solveForNBlinks(int blinks) {
        v.clear();
        fillMapWithInput(StringUtils.getInputLine(getInputPath()));
        blink(blinks);
        return countStones();
    }

    private void blink(int blinks) {
        for (int rep = 0; rep < blinks; rep++) {

            Map<Long, Long> newV = new HashMap<>();

            for (Map.Entry<Long, Long> entry : v.entrySet()) {
                long x = entry.getKey();
                long count = entry.getValue();

                if (x == 0) {
                    // Rule 1: If stone number is 0, replace it with 1
                    newV.put(1L, newV.getOrDefault(1L, 0L) + count);
                } else if (String.valueOf(x).length() % 2 == 0) {
                    // Rule 2: If the number has even digits, split it into two halves
                    String s = String.valueOf(x);
                    long left = Long.parseLong(s.substring(0, s.length() / 2));
                    long right = Long.parseLong(s.substring(s.length() / 2));
                    newV.put(left, newV.getOrDefault(left, 0L) + count);
                    newV.put(right, newV.getOrDefault(right, 0L) + count);
                } else {
                    // Rule 3: Odd-digit number → multiply by 2024
                    long product = x * 2024;
                    newV.put(product, newV.getOrDefault(product, 0L) + count);
                }
            }

            v = newV;
        }
    }

    private long countStones() {
        long total = 0;
        for (long count : v.values()) {
            total += count;
        }
        return total;
    }

    private void fillMapWithInput(String line) {
        Arrays.stream(line.split(" "))
                .mapToLong(Long::parseLong)
                .forEach(x -> v.put(x, v.getOrDefault(x, 0L) + 1));
        ;
    }

    @Override
    public Object solvePart2() {
        return solveForNBlinks(75);
    }

}
