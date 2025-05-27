package com.nmarsucco.adventofcode.util;

import java.util.Objects;

public class Node implements Comparable<Node> {

    public final Position position;
    public final Direction direction;
    public final double gScore;
    public final double hScore;
    public final double fScore;
    public final Node parent;

    public Node(Position position, Direction direction, double gScore, double hScore, Node parent) {
        this.position = position;
        this.direction = direction;
        this.gScore = gScore;
        this.hScore = hScore;
        this.fScore = gScore + hScore;
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.fScore, other.fScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Node node = (Node) other;
        return position.equals(node.position) && direction.equals(node.direction);
    }

}
