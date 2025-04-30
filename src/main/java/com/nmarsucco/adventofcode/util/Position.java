package com.nmarsucco.adventofcode.util;

import java.util.Objects;

public class Position {

    public final int row;
    public final int col;

    public Position(int row, int column) {
        this.row = row;
        this.col = column;
    }

    public Position add(Position other) {
        return new Position(row + other.row, col + other.col);
    }

    public Position subtract(Position other) {
        return new Position(row - other.row, col - other.col);
    }

    public Position multiply(int number) {
        return new Position(row * number, col * number);
    }

    public Position move(int dRow, int dCol) {
        return new Position(row + dRow, col + dCol);
    }

    public static boolean isOutOfBounds(char[][] array, Position position) {
        return (position.row < 0 || position.row >= array.length || position.col < 0
                || position.col >= array[0].length);
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof Position)) {
            return false;
        }

        Position otherPosition = (Position) other;
        return row == otherPosition.row && col == otherPosition.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

}
