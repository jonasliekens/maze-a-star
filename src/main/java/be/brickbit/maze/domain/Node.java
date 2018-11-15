package be.brickbit.maze.domain;

import be.brickbit.maze.util.NodeUtil;

public class Node implements Comparable<Node>{
    private int xIndex;
    private int yIndex;
    private int cost;
    private int distanceFromStart;
    private int heuristicValue;
    private Node parent;

    public Node(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.cost = 0;
        this.distanceFromStart = 0;
        this.heuristicValue = 0;
    }

    public int getxIndex() {
        return xIndex;
    }

    public int getyIndex() {
        return yIndex;
    }

    public int getCost() {
        return cost;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public Node getParent() {
        return parent;
    }

    public Node setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    public void calculateCost(int movementCost, int startXIndex, int startYIndex, int goalXIndex, int goalYIndex){
        this.heuristicValue = NodeUtil.calculateHeuristic(movementCost, xIndex, yIndex, goalXIndex, goalYIndex);
        this.distanceFromStart = NodeUtil.calculateManhattanDistance(startXIndex, startYIndex, xIndex, yIndex);
        this.cost = heuristicValue + distanceFromStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return xIndex == node.xIndex &&
                yIndex == node.yIndex;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.getCost());
    }
}
