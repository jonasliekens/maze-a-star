package be.brickbit.maze.domain;

public enum Direction {
    DOWN(1, 0),
    UP(-1, 0),
    RIGHT(0, 1),
    LEFT(0, -1);

    private int yAxisDelta;
    private int xAxisDelta;

    Direction(int yAxisDelta, int xAxisDelta) {
        this.yAxisDelta = yAxisDelta;
        this.xAxisDelta = xAxisDelta;
    }

    public int getyAxisDelta() {
        return yAxisDelta;
    }

    public int getxAxisDelta() {
        return xAxisDelta;
    }
}
