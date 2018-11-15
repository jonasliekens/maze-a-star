package be.brickbit.maze.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import be.brickbit.maze.domain.Direction;
import be.brickbit.maze.domain.FieldType;
import be.brickbit.maze.domain.Maze;
import be.brickbit.maze.domain.Node;

public class AStarSolver {
    private List<Node> openNodes = new ArrayList<>();
    private List<Node> closedNodes = new ArrayList<>();
    private final List<Direction> possibleDirections = Arrays.asList(
            Direction.UP,
            Direction.DOWN,
            Direction.LEFT,
            Direction.RIGHT
    );

    public void solve(Maze maze){
        this.openNodes.add(maze.getStartNode());

        // Loop until we find the end
        while (openNodes.size() > 0) {
            Node currentNode = openNodes.get(0);

            //Find node with lowest cost to continue path
            for(Node node : openNodes){
                if (node.getCost() < currentNode.getCost()) {
                    currentNode = node;
                }
            }

            openNodes.remove(currentNode);
            closedNodes.add(currentNode);
            Collections.sort(openNodes);

            //Check if we found the goal
            if(currentNode.equals(maze.getEndNode())){
                markPath(maze, currentNode);
                return;
            }

            //Find valid neighbours
            for (Direction direction : possibleDirections){
                int newXIndex = currentNode.getxIndex() + direction.getxAxisDelta();
                int newYIndex = currentNode.getyIndex() + direction.getyAxisDelta();

                try {
                    if(maze.getField(newXIndex, newYIndex) != FieldType.WALL){
                        Node validNeighbour = new Node(newXIndex, newYIndex);
                        validNeighbour.setParent(currentNode);

                        if(!openNodes.contains(validNeighbour) && !closedNodes.contains(validNeighbour)) {
                            validNeighbour.calculateCost(
                                    1,
                                    maze.getStartNode().getxIndex(),
                                    maze.getStartNode().getyIndex(),
                                    maze.getEndNode().getxIndex(),
                                    maze.getEndNode().getyIndex()
                            );
                            openNodes.add(validNeighbour);
                        }
                    }
                } catch (IndexOutOfBoundsException ex){
                    // The new position was invalid, so we can just skip
                }
            }
        }

        for(Node node : closedNodes){
            maze.setVisited(node.getxIndex(), node.getyIndex());
        }
    }

    private void markPath(Maze maze, Node currentNode) {
        maze.setMarker(currentNode.getxIndex(), currentNode.getyIndex());

        if(currentNode.getParent() != null) {
            markPath(maze, currentNode.getParent());
        }
    }
}
