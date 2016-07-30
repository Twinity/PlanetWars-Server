package org.twinity.PlanetWars;

import java.util.ArrayList;

/**
 * Created by KaTaNa on 7/29/2016.
 */
public class Map {

    private int nodesCount;
    private int turns;
    private Node[] nodes;

    // Getters
    public int getNodesCount() {
        return nodesCount;
    }

    public int getTotalTurns() {
        return turns;
    }

    public Node[] getAllNodes() {
        return nodes;
    }

    // Other Methods
    public Node[] getMyNodes(int inMyId) {
        ArrayList<Node> myNodes = new ArrayList<>();
        for (Node node : this.getAllNodes())
            if (node.getOwner() == inMyId)
                myNodes.add(node);

        return myNodes.toArray(new Node[myNodes.size()]);
    }

    public Node[] getOpponentNodes(int inMyId) {
        ArrayList<Node> opponentNodes = new ArrayList<>();
        for (Node node : this.getAllNodes())
            if (node.getOwner() != inMyId && node.getOwner() != 0)
                opponentNodes.add(node);

        return opponentNodes.toArray(new Node[opponentNodes.size()]);
    }

    public Node[] getFreeNodes() {
        ArrayList<Node> freeNodes = new ArrayList<>();
        for (Node node : this.getAllNodes())
            if (node.getOwner() == 0)
                freeNodes.add(node);

        return freeNodes.toArray(new Node[freeNodes.size()]);
    }

    public boolean isMyNode(int inNodeId, int inMyId) {
        for (Node node : this.getMyNodes(inMyId))
            if (node.getId() == inNodeId)
                return true;
        return false;
    }

    public Node getNode(int inNodeId) {
        for (Node node : this.getAllNodes())
            if (node.getId() == inNodeId)
                return node;
        return null;
    }

}
