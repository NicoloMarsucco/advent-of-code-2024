package com.nmarsucco.adventofcode.util;

public class DijkstraNode extends DirectionalNode {

    public final int dist;

    public DijkstraNode(Position position, Direction direction, int distance) {
        super(position, direction);
        this.dist = distance;
    }
}
