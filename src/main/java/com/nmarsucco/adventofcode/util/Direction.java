package com.nmarsucco.adventofcode.util;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Direction rotateClockwise() {
        return values()[(this.ordinal() + 1) % 4];
    }

    public Direction rotateCounterClockwise() {
        return values()[(this.ordinal() + 3) % 4]; // same as -1 mod 4
    }

    public Position getDelta() {
        switch (this) {
            case Direction.NORTH:
                return new Position(-1, 0);
            case Direction.EAST:
                return new Position(0, 1);
            case Direction.SOUTH:
                return new Position(1, 0);
            default:
                return new Position(0, -1);
        }
    }

    public char toChar() {
        switch (this) {
            case Direction.NORTH:
                return '^';
            case Direction.EAST:
                return '>';
            case Direction.SOUTH:
                return 'v';
            default:
                return '<';
        }
    }

}