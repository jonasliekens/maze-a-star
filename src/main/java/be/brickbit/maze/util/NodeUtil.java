package be.brickbit.maze.util;

public class NodeUtil {
    public static int calculateHeuristic(
            int movementCost,
            int startXIndex,
            int startYIndex,
            int endXIndex,
            int endYIndex){

        return movementCost * calculateManhattanDistance(
                startXIndex,
                startYIndex,
                endXIndex,
                endYIndex
        );
    }

    public static int calculateManhattanDistance(
            int startXIndex,
            int startYIndex,
            int endXIndex,
            int endYIndex
    ){
        int xDelta = startXIndex - endXIndex;
        int yDelta = startYIndex - endYIndex;

        return (xDelta + yDelta);
    }

    public static long calculateDistance(
            int startXIndex,
            int startYIndex,
            int endXIndex,
            int endYIndex
    ){
        return Math.round(
                Math.sqrt(
                        Math.pow(endXIndex - startXIndex, 2) + Math.pow(endYIndex - startYIndex, 2)
                )
        );
    }
}
