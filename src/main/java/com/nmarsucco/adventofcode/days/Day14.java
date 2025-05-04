package com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nmarsucco.adventofcode.Day;
import com.nmarsucco.adventofcode.util.Position;
import com.nmarsucco.adventofcode.util.RobotWalker;
import com.nmarsucco.adventofcode.util.RobotsGrid;

public class Day14 extends Day {

    public Day14() {
        this(false);
    }

    public Day14(boolean isTestingMode) {
        super(14, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        RobotsGrid grid = new RobotsGrid(isTestingMode() ? 7 : 103, isTestingMode() ? 11 : 101);
        int deltaT = 100;
        Pattern pattern = Pattern.compile("-?\\d+");
        try (BufferedReader br = getNewBufferedReader()) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                List<Integer> numbers = findNumbersInString(line, matcher);
                Position position = new Position(numbers.get(1), numbers.get(0));
                RobotWalker robot = new RobotWalker(position, numbers.get(2), numbers.get(3), grid);
                Position finalPosition = robot.forecastPosition(deltaT);
                grid.addRobot(finalPosition);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return grid.getSafetyFactor();
    }

    private static List<Integer> findNumbersInString(String line, Matcher matcher) {
        List<Integer> res = new ArrayList<>(4);
        while (matcher.find()) {
            res.add(Integer.parseInt(matcher.group()));
        }
        return res;
    }

    @Override
    public Object solvePart2() {
        RobotsGrid grid = new RobotsGrid(103, 101);
        Pattern pattern = Pattern.compile("-?\\d+");
        List<RobotWalker> robots = new ArrayList<>(500);
        try (BufferedReader br = getNewBufferedReader()) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                List<Integer> numbers = findNumbersInString(line, matcher);
                Position position = new Position(numbers.get(1), numbers.get(0));
                robots.add(new RobotWalker(position, numbers.get(2), numbers.get(3), grid));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        int maxIter = 10000;
        int adjacentCount = Integer.MIN_VALUE;
        int step = -1;
        for (int i = 1; i < maxIter; i++) {
            grid.resetPart2Grid();
            for (RobotWalker robot : robots) {
                grid.addRobotPart2(robot.forecastPosition(i));
            }
            int adjacent = grid.countAdjacentRobots();
            if (adjacent > adjacentCount) {
                adjacentCount = adjacent;
                step = i;
            }

        }

        return step;
    }
}
