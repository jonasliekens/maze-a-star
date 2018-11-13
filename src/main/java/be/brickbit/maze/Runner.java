package be.brickbit.maze;

import be.brickbit.maze.domain.Maze;
import be.brickbit.maze.domain.MazeBuilder;

public class Runner {
    public static void main(String[] args) {
        Maze maze = MazeBuilder.aMaze()
                .withWidth(22)
                .withHeight(23)
                .build();
        maze.print();
    }
}
