package src.main.java.com.nmarsucco.adventofcode.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.main.java.com.nmarsucco.adventofcode.Day;
import src.main.java.com.nmarsucco.adventofcode.util.Position;
import src.main.java.com.nmarsucco.adventofcode.util.StringUtils;

public class Day8 extends Day {

    public Day8() {
        this(false);
    }

    public Day8(boolean isTestingMode) {
        super(8, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        char[][] matrix = StringUtils.getInputAsCharMatrix(getInputPath());
        Map<Character, List<Position>> mapOfAntennas = fillMapOfAntennas(matrix);
        return calculateAntiNodesPositions(matrix, mapOfAntennas, true);
    }

    Map<Character, List<Position>> fillMapOfAntennas(char[][] matrix) {
        Map<Character, List<Position>> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '.') {
                    map.putIfAbsent(matrix[i][j], new ArrayList<>());
                    map.get(matrix[i][j]).add(new Position(i, j));
                }
            }
        }
        return map;
    }

    private int calculateAntiNodesPositions(char[][] matrix, Map<Character, List<Position>> map, boolean isPartOne) {
        Set<Position> antiNodesPositions = new HashSet<>();
        Iterator<Map.Entry<Character, List<Position>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Character, List<Position>> entry = it.next();
            getNodesForCurrentList(entry.getValue(), antiNodesPositions, matrix, isPartOne);
        }
        return antiNodesPositions.size();
    }

    private void getNodesForCurrentList(List<Position> positions, Set<Position> set, char[][] matrix,
            boolean isPart1) {
        for (Position positionOuterLoop : positions) {
            for (Position positionInnerLoop : positions) {
                if (positionInnerLoop.equals(positionOuterLoop)) {
                    continue;
                }
                Position diff = positionOuterLoop.subtract(positionInnerLoop);
                if (isPart1) {
                    Position antiNode = positionOuterLoop.add(diff);
                    if (!Position.isOutOfBounds(matrix, antiNode)) {
                        set.add(antiNode);
                    }
                } else {
                    int multiplicationFactor = 0;
                    Position antiNode = positionOuterLoop.add(diff.multiply(multiplicationFactor));
                    while (!Position.isOutOfBounds(matrix, antiNode)) {
                        set.add(antiNode);
                        antiNode = positionOuterLoop.add(diff.multiply(++multiplicationFactor));
                    }
                }

            }
        }
    }

    @Override
    public Object solvePart2() {
        char[][] matrix = StringUtils.getInputAsCharMatrix(getInputPath());
        Map<Character, List<Position>> mapOfAntennas = fillMapOfAntennas(matrix);
        return calculateAntiNodesPositions(matrix, mapOfAntennas, false);
    }

}
