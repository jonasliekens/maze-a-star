package be.brickbit.maze.domain;

import java.util.Arrays;

import static be.brickbit.maze.domain.FieldType.*;

public class Maze {
    private FieldType[][] maze;
    private final Node startNode;
    private final Node endNode;

    protected Maze(FieldType[][] maze, Node startNode, Node endNode){
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public void print() {
        Arrays.stream(this.maze).forEach(row -> {
            Arrays.stream(row).forEach(field -> {
                System.out.print(field.toString());
            });
            System.out.println();
        });
    }

    public void setMarker(int xAxis, int yAxis){
        if(this.maze[yAxis][xAxis] != WALL){
            this.maze[yAxis][xAxis] = MARKER;
        }else{
            throw new IllegalArgumentException("You can't walk through walls!");
        }
    }

    public void setVisited(int xAxis, int yAxis){
        this.maze[yAxis][xAxis] = VISITED;
    }

    public FieldType getField(int xAxis, int yAxis){
        return this.maze[yAxis][xAxis];
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }
}
