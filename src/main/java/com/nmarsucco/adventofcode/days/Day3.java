package src.main.java.com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.main.java.com.nmarsucco.adventofcode.Day;

public class Day3 extends Day {

    public Day3() {
        this(false);
    }

    public Day3(boolean isTestingMode) {
        super(3, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        int sum = 0;
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String[] parts = matcher.group().split(",");
                    int number1 = Integer.valueOf(parts[0].substring(4));
                    int number2 = Integer.valueOf(parts[1].substring(0, parts[1].length() - 1));
                    sum += number1 * number2;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sum;
    }

    @Override
    public Object solvePart2() {
        int sum = 0;
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        try (BufferedReader br = new BufferedReader(new FileReader(getInputPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                boolean isEnabled = true;
                while (matcher.find()) {
                    if (matcher.group().equals("do()")) {
                        isEnabled = true;
                    } else if (matcher.group().equals("don't()")) {
                        isEnabled = false;
                    } else if (isEnabled) {
                        String[] parts = matcher.group().split(",");
                        int number1 = Integer.valueOf(parts[0].substring(4));
                        int number2 = Integer.valueOf(parts[1].substring(0, parts[1].length() - 1));
                        sum += number1 * number2;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return sum;
    }

}
