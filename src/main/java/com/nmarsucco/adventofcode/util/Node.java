package com.nmarsucco.adventofcode.util;

public class Node extends DirectionalNode implements Comparable<Node> {

    public final double gScore;
    public final double hScore;
    public final double fScore;
    public final Node parent;

    public Node(Position position, Direction direction, double gScore, double hScore, Node parent) {
        super(position, direction);
        this.gScore = gScore;
        this.hScore = hScore;
        this.fScore = gScore + hScore;
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.fScore, other.fScore);
    }

}
