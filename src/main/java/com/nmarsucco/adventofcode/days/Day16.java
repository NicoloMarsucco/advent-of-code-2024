package com.nmarsucco.adventofcode.days;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.nmarsucco.adventofcode.Day;
import com.nmarsucco.adventofcode.util.Node;
import com.nmarsucco.adventofcode.util.Direction;
import com.nmarsucco.adventofcode.util.Position;
import com.nmarsucco.adventofcode.util.StringUtils;

public class Day16 extends Day {

    private static final char targetChar = 'E';

    public Day16(boolean isTestingMode) {
        super(16, isTestingMode);
    }

    public Day16() {
        this(false);
    }

    @Override
    public Object solvePart1() {

        // Uses A* algorithm, Manhattan distance as cost function.
        // We assume that the end is at top right corner.
        char[][] grid = StringUtils.getInputAsCharMatrix(getInputPath());
        return (int) solveMaze(grid).gScore;
    }

    private Node solveMaze(char[][] grid) {
        int numberOfColumns = grid[0].length;

        // Data structures
        // we mark the visisted nodex with X
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Node> visited = new HashSet<>();

        Position initialPosition = new Position(grid.length - 2, 1);

        // Position targetPosition = new Position(1, grid[0].length - 2);
        Node startingNode = new Node(initialPosition, Direction.EAST, 0,
                getHScore(initialPosition, numberOfColumns),
                null);

        pq.add(startingNode);

        // Limit on number of iterations
        int maxIter = 1_000_000;
        int iter = 0;

        while (!pq.isEmpty() && iter < maxIter) {

            iter++;

            Node currentBest = pq.poll();

            if (!visited.add(currentBest)) {
                continue;
            }

            char currChar = grid[currentBest.position.row][currentBest.position.col];

            if (currChar == targetChar) {
                // StringUtils.printMatrix(grid);
                return currentBest;
            }

            // Option 1 : continue in same direction
            // only if valid
            Position candidate = currentBest.position.add(currentBest.direction.getDelta());
            if (grid[candidate.row][candidate.col] != '#') {
                Node candidateNode = new Node(candidate, currentBest.direction, currentBest.gScore + 1,
                        getHScore(candidate, numberOfColumns), currentBest);

                pq.add(candidateNode);

            }

            // Option 2: rotate clockwise
            // only do if not #
            Direction clockWiseDirection = currentBest.direction.rotateClockwise();
            Position clockWisePosition = currentBest.position.add(clockWiseDirection.getDelta());
            if (grid[clockWisePosition.row][clockWisePosition.col] != '#') {
                Node clockwiseCandidate = new Node(clockWisePosition, clockWiseDirection,
                        currentBest.gScore + 1001,
                        getHScore(clockWisePosition, numberOfColumns), currentBest);
                pq.add(clockwiseCandidate);

            }

            // Option 3: rotate counterclockwise
            // only if valid
            Direction counterclockWiseDirection = currentBest.direction.rotateCounterClockwise();
            Position counterclockWisePosition = currentBest.position.add(counterclockWiseDirection.getDelta());
            if (grid[counterclockWisePosition.row][counterclockWisePosition.col] != '#') {
                Node counterclockWiseCandidate = new Node(counterclockWisePosition, counterclockWiseDirection,
                        currentBest.gScore + 1001,
                        getHScore(counterclockWisePosition, numberOfColumns), currentBest);

                pq.add(counterclockWiseCandidate);

            }

            // Mark as visisted
            // grid[currentBest.position.row][currentBest.position.col] = visited;

            grid[currentBest.position.row][currentBest.position.col] = currentBest.direction.toChar();
            // System.out.println("");
            // StringUtils.printMatrix(grid);
        }
        // StringUtils.printMatrix(grid);
        return null;
    }

    private int getHScore(Position position, int gridColumns) {
        return (position.row - 1) + (gridColumns - 2 - position.col);
    }

    @Override
    public Object solvePart2() {
        char[][] grid = StringUtils.getInputAsCharMatrix(getInputPath());
        Node endNode = solveMaze(grid);

        // Priority queue with lowest gscore
        PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> Double.compare(node1.gScore, node2.gScore));

        pq.add(endNode);

        while (true) {

        }

        return 0;
    }

}
