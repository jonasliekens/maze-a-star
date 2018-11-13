package be.brickbit.maze.domain;

import java.util.Arrays;

import static be.brickbit.maze.domain.FieldType.*;

public class Maze {
    private FieldType[][] maze;

    protected Maze(FieldType[][] maze){
        this.maze = maze;
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
        if(this.maze[yAxis][xAxis] == SPACE){
            this.maze[yAxis][xAxis] = MARKER;
        }else{
            throw new IllegalArgumentException("You can't walk through walls!");
        }
    }

    public FieldType getField(int xAxis, int yAxis){
        return this.maze[yAxis][xAxis];
    }
}
