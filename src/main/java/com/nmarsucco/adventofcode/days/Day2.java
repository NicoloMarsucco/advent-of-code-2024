package src.main.java.com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import src.main.java.com.nmarsucco.adventofcode.Day;

public class Day2 extends Day {

    public static final int MIN_DIFF = 1;
    public static final int MAX_DIFF = 3;

    public Day2() {
        this(false);
    }

    public Day2(boolean isTestingMode) {
        super(2, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        int validReports = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            String line = br.readLine();
            while (line != null) {
                int[] nums = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                if (isValidReport(nums)) {
                    validReports++;
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return validReports;
    }

    private boolean isValidReport(int[] nums) {
        int sign = (int) Math.signum(nums[1] - nums[0]);
        if (sign == 0) {
            return false;
        }
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((int) Math.signum(diff) != sign) {
                return false;
            }
            if (Math.abs(diff) > MAX_DIFF || Math.abs(diff) < MIN_DIFF) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object solvePart2() {
        int validReports = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            String line = br.readLine();
            while (line != null) {
                int[] nums = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                if (isValidReport(nums) || couldBeAValidReport(nums)) {
                    validReports++;
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return validReports;
    }

    private boolean couldBeAValidReport(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (isValidReport(formNewArrayWithoutItem(nums, i))) {
                return true;
            }
        }
        return false;
    }

    private int[] formNewArrayWithoutItem(int[] nums, int indexOfItemToRemove) {
        int[] newNums = new int[nums.length - 1];
        int pointerOriginal;
        int pointerNew = 0;
        for (pointerOriginal = 0; pointerOriginal < nums.length; pointerOriginal++) {
            if (pointerOriginal != indexOfItemToRemove) {
                newNums[pointerNew++] = nums[pointerOriginal];
                ;
            }
        }
        return newNums;
    }

}
