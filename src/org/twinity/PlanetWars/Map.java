package org.twinity.PlanetWars;

import java.util.ArrayList;

/**
 * Created by KaTaNa on 7/29/2016.
 */
public class Map {

    private int nodesCount;
    private int totalTurns;
    private Node[] allNodes;

    public void initializer(int inPlayerId1, int inPlayerId2){
        for (Node node : this.getAllNodes())
            if (node.getOwner() == inMyId)
                myNodes.add(node);
    }

    // Getters
    public int getNodesCount() {
        return nodesCount;
    }

    public int getTotalTurns() {
        return totalTurns;
    }

    private Node[] getAllNodes() {
        return allNodes;
    }

    public Node[] getAllNodes(int inMyId) {
        ArrayList<Node> allNodes = new ArrayList<>();
        for (Node node : this.getAllNodes())
            if (node.getOwner() != inMyId && node.getOwner() != 0) {
                node.setArmyCount(getStrengthLevel(node.getArmyCount()));
                allNodes.add(node);
            } else
                allNodes.add(node);

        return allNodes.toArray(new Node[allNodes.size()]);
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
            if (node.getOwner() != inMyId && node.getOwner() != 0) {
                node.setArmyCount(getStrengthLevel(node.getArmyCount()));
                opponentNodes.add(node);
            }

        return opponentNodes.toArray(new Node[opponentNodes.size()]);
    }

    public Node[] getFreeNodes() {
        ArrayList<Node> freeNodes = new ArrayList<>();
        for (Node node : this.getAllNodes())
            if (node.getOwner() == 0)
                freeNodes.add(node);

        return freeNodes.toArray(new Node[freeNodes.size()]);
    }

    private int getStrengthLevel(int inArmyCount) {
        int armyCountSum = 0;
        for (int i = 0; i < getAllNodes().length; i++) {
            armyCountSum += getAllNodes()[i].getArmyCount();
        }
        if (inArmyCount <= armyCountSum / 4)
            return 1;
        else if (inArmyCount > armyCountSum / 4 && inArmyCount <= armyCountSum / 2)
            return 2;
        else if (inArmyCount > armyCountSum / 2 && inArmyCount <= 3 * armyCountSum / 4)
            return 3;
        else
            return 4;
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

    public int getOpponentId(int inMyId){
        for (Node node : this.getAllNodes())
            if (node.getOwner() != inMyId && node.getOwner() != 0)
                return node.getOwner();
        return -1;
    }

}
