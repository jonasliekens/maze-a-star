package be.brickbit.maze.domain;

import java.util.Random;

import static be.brickbit.maze.domain.FieldType.END;
import static be.brickbit.maze.domain.FieldType.SPACE;
import static be.brickbit.maze.domain.FieldType.START;
import static be.brickbit.maze.domain.FieldType.WALL;

/**
 * Builder class to generate a @link be.brickbit.maze.domain.Maze instance
 */
public final class MazeBuilder {
    private int width;
    private int height;
    private static final Direction[] DIRECTIONS = {
            Direction.DOWN,
            Direction.UP,
            Direction.RIGHT,
            Direction.LEFT
    };

    private MazeBuilder() {
        this.width = 11;
        this.height = 11;
    }

    public static MazeBuilder aMaze() {
        return new MazeBuilder();
    }

    /**
     * Assigns a custom height to the maze generator, default height is 11
     * @param height the height of the maze
     * @return @link be.brickbit.maze.domain.MazeBuilder instance
     */
    public MazeBuilder withHeight(int height) {
        if(height >= 3) {
            this.height = height;
            return this;
        }else {
            throw new IllegalArgumentException("The maze needs a height of at least 3 elements");
        }
    }

    /**
     * Assigns a custom width to the maze generator, default width is 11
     * @param width the height of the maze
     * @return @link be.brickbit.maze.domain.MazeBuilder instance
     */
    public MazeBuilder withWidth(int width) {
        if(width >= 3) {
            this.width = width;
            return this;
        } else {
            throw new IllegalArgumentException("The maze needs a width of at least 3 elements");
        }
    }

    /**
     * Builds a Maze object containing a maze matrix
     * @return a Maze instance
     */
    public Maze build() {
        return new Maze(generate());
    }

    /**
     * Generates a solvable maze
     * @return maze matrix
     */
    private FieldType[][] generate() {
        FieldType[][] maze = new FieldType[height][width];

        // Fill matrix with walls
        for (int yAxisIndex = 0; yAxisIndex < height; yAxisIndex++) {
            for (int xAxisIndex = 0; xAxisIndex < width; xAxisIndex++) {
                maze[yAxisIndex][xAxisIndex] = WALL;
            }
        }

        //Mark START
        maze[1][0] = START;
        //Mark END
        maze[height - 2][width - 1] = END;

        //Carve maze
        maze[1][1] = SPACE;
        carve(maze, 1, 1);

        return maze;
    }

    /**
     * recursively carves out a solvable maze from a given matrix
     * @param maze the maze matrix
     * @param yAxisStartIndex the y axis index the algorithm should start at
     * @param xAxisStartIndex the x axis index the algorithm should start at
     */
    private void carve(FieldType[][] maze, int yAxisStartIndex, int xAxisStartIndex) {
        int randomDirectionIndex = new Random().nextInt(4);
        int count = 0;

        while (count < 4) {
            final int newYAxisPosition = yAxisStartIndex + DIRECTIONS[randomDirectionIndex].getyAxisDelta();
            final int newXAxisPosition = xAxisStartIndex + DIRECTIONS[randomDirectionIndex].getxAxisDelta();

            final int nextStepYAxisPosition = newYAxisPosition + DIRECTIONS[randomDirectionIndex].getyAxisDelta();
            final int nextStepXAxisPosition = newXAxisPosition + DIRECTIONS[randomDirectionIndex].getxAxisDelta();

            if (isValidCarvePosition(maze, newYAxisPosition, newXAxisPosition) && isValidCarvePosition(maze, nextStepYAxisPosition, nextStepXAxisPosition)) {
                carveSpace(maze, newYAxisPosition, newXAxisPosition);
                carveSpace(maze, nextStepYAxisPosition, nextStepXAxisPosition);
                carve(maze, nextStepYAxisPosition, nextStepXAxisPosition);
            } else {
                //Change direction
                randomDirectionIndex = (randomDirectionIndex + 1) % 4;
                count += 1;
            }
        }
    }

    private void carveSpace(FieldType[][] maze, int yAxisPosition, int xAxisPosition) {
        if(maze[yAxisPosition][xAxisPosition] != END){
            maze[yAxisPosition][xAxisPosition] = SPACE;
        }
    }

    /**
     * checks if the given coordinates are not out of bounds and will leave the maze with a
     * surrounding wall
     * @param maze the maze matrix
     * @param yAxisPosition y axis index
     * @param xAxisPosition x axis index
     * @return true if the coordinates are valid, false otherwise
     */
    private boolean isValidCarvePosition(FieldType[][] maze, int yAxisPosition, int xAxisPosition) {
        return yAxisPosition < height &&
                yAxisPosition > 0 &&
                xAxisPosition < width &&
                xAxisPosition > 0 &&
                (maze[yAxisPosition][xAxisPosition] == WALL || maze[yAxisPosition][xAxisPosition] == END);
    }

    private enum Direction {
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
}
