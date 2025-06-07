package com.nmarsucco.adventofcode.util;

import java.util.Objects;

public class DirectionalNode {
    public final Position position;
    public final Direction direction;

    public DirectionalNode(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
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

        DirectionalNode node = (DirectionalNode) other;
        return position.equals(node.position) && direction.equals(node.direction);
    }

}
