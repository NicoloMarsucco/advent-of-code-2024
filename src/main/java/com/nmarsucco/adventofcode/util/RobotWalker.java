package com.nmarsucco.adventofcode.util;

public class RobotWalker {

    private final Position initialPosition;
    private final int velocityX;
    private final int velocityY;
    private final RobotsGrid grid;

    public RobotWalker(Position initialPosition, int velocityX, int velocityY, RobotsGrid grid) {
        this.initialPosition = initialPosition;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.grid = grid;
    }

    public Position forecastPosition(int t) {
        int finalRow = (((initialPosition.row + t * velocityY) % grid.rows) + grid.rows) % grid.rows;
        int finalColumn = (((initialPosition.col + t * velocityX) % grid.columns) + grid.columns) % grid.columns;
        return new Position(finalRow, finalColumn);
    }

}
