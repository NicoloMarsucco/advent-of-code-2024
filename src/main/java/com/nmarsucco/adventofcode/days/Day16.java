package com.nmarsucco.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.nmarsucco.adventofcode.Day;
import com.nmarsucco.adventofcode.util.Node;
import com.nmarsucco.adventofcode.util.DijkstraNode;
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
        int rows = grid.length;
        int cols = grid[0].length;

        int[][][] distance = new int[rows][cols][4]; // 4 directions
        for (int[][] row : distance)
            for (int[] cell : row)
                Arrays.fill(cell, Integer.MAX_VALUE);

        PriorityQueue<DijkstraNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));
        Map<DijkstraNode, List<DijkstraNode>> predecessors = new HashMap<>();

        int startRow = grid.length - 2;
        int startColumn = 1;
        distance[startRow][startColumn][Direction.EAST.ordinal()] = 0;

        pq.offer(new DijkstraNode(new Position(startRow, startColumn), Direction.EAST, 0));

        while (!pq.isEmpty()) {
            DijkstraNode curr = pq.poll();
            int r = curr.position.row;
            int c = curr.position.col;
            int d = curr.direction.ordinal();

            if (curr.dist > distance[r][c][d]) {
                continue;
            }

            Direction[] nextPossibleDirections = { curr.direction, curr.direction.rotateClockwise(),
                    curr.direction.rotateCounterClockwise() };

            for (Direction nextDir : nextPossibleDirections) {
                Position newPosition = curr.position.add(nextDir.getDelta());

                // Wall check
                if (grid[newPosition.row][newPosition.col] == '#') {
                    continue;
                }

                int movementCost = (nextDir == curr.direction) ? 1 : 1001;
                int newDist = curr.dist + movementCost;
                int nextDirOrdinal = nextDir.ordinal();
                DijkstraNode nextNode = new DijkstraNode(newPosition, nextDir, newDist);

                if (newDist < distance[newPosition.row][newPosition.col][nextDirOrdinal]) {
                    distance[newPosition.row][newPosition.col][nextDirOrdinal] = newDist;
                    pq.offer(nextNode);
                    predecessors.put(nextNode, new ArrayList<>(Collections.singletonList(curr)));
                } else if (newDist == distance[newPosition.row][newPosition.col][nextDirOrdinal]) {
                    predecessors.computeIfAbsent(nextNode, k -> new ArrayList<>()).add(curr);
                }
            }

        }

        Set<DijkstraNode> visited = new HashSet<>();
        Set<Position> tiles = new HashSet<>();
        int endRow = 1;
        int endColumn = grid[0].length - 2;

        int endDistEast = distance[endRow][endColumn][Direction.EAST.ordinal()];
        int endDistNorth = distance[endRow][endColumn][Direction.NORTH.ordinal()];
        List<Direction> endDirections = new ArrayList<>();

        if (endDistNorth <= endDistEast) {
            endDirections.add(Direction.NORTH);
        }
        if (endDistEast <= endDistNorth) {
            endDirections.add(Direction.EAST);
        }

        for (Direction dir : endDirections) {
            dfs(predecessors, visited, new DijkstraNode(new Position(endRow, endColumn), dir, 0), tiles);
        }

        for (Position pos : tiles) {
            grid[pos.row][pos.col] = 'O';
        }

        return tiles.size() + 1;
    }

    private void dfs(Map<DijkstraNode, List<DijkstraNode>> map, Set<DijkstraNode> visited, DijkstraNode node,
            Set<Position> tiles) {
        if (!visited.add(node) || !map.containsKey(node)) {
            return;
        }
        tiles.add(node.position);

        for (DijkstraNode parent : map.get(node)) {
            dfs(map, visited, parent, tiles);
        }
    }

}
