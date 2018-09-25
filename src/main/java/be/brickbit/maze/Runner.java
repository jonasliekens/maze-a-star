package be.brickbit.maze;

public class Runner {
    public static void main(String[] args) {
        Maze m = new Maze(39, 23);
        m.generate();
        m.markSpot(2,1);
        m.print();
    }
}
