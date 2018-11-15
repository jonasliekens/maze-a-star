package be.brickbit.maze;

import be.brickbit.maze.domain.Maze;
import be.brickbit.maze.domain.MazeBuilder;
import be.brickbit.maze.solver.AStarSolver;

public class Runner {
    public static void main(String[] args) {
        AStarSolver solver = new AStarSolver();
        Maze maze = MazeBuilder.aMaze()
                .withWidth(23)
                .withHeight(23)
                .build();
        System.out.println("Maze: ");
        maze.print();
        solver.solve(maze);
        System.out.println("Solution: ");
        maze.print();
    }
}
