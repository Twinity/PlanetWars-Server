/**
 * @author Amir hossein Hajianpour <ahhajianpour1@gmail.com>
 * @author Mohammad reza Hajianpour <hajianpour.mr@gmail.com>
 * @version 1.2
 * @since 1.0
 */

package com.twinity.PlanetWarsServer;

import java.util.ArrayList;

public class Map {

    // Total number of all nodes
    private int nodesCount;
    // Total number of turns players are allowed to play
    private int totalTurns;
    // Array of all nodes in the map
    private Node[] allNodes;

    /**
     * Replaces generic id's of map (1 and 2) with randomly generated ID's from server
     *
     * @param inPlayerId1 An int of randomlt generated ID for player 1
     * @param inPlayerId2 An int of randomlt generated ID for player 1
     */
    public void initializer(int inPlayerId1, int inPlayerId2) {
        for (Node node : this.getAllNodes())
            if (node.getOwner() == 1)
                node.setOwner(inPlayerId1);
            else if (node.getOwner() == 2)
                node.setOwner(inPlayerId2);
    }

    /**
     * Gets total number of nodes in the map
     *
     * @return Returns an int containing total number of nodes in the map
     */
    public int getNodesCount() {
        return nodesCount;
    }

    /**
     * Gets total number of turns the players are allowed to play
     *
     * @return Returns an int containing total number of turns
     */
    public int getTotalTurns() {
        return totalTurns;
    }

    /**
     * Gets all nodes in the map
     *
     * @return Returns an array of Node class, containing all nodes in the map
     */
    private Node[] getAllNodes() {
        return allNodes;
    }

    /**
     * Gets filtered version of all nodes of the map.
     * <p>
     * Note that it gets the player id to hide opponent's army count.
     * </p>
     *
     * @param inMyId an int which is the ID of the player to customize the map
     * @return Returns an array of nodes containing all nodes in the map
     */
    public Node[] getFilteredAllNodes(int inMyId) {
        ArrayList<Node> allNodes = new ArrayList<>();
        for (Node node : this.getAllNodes())
            // If the node is the enemy's, set it as approximate strength, instead of the exact army count
            if (node.getOwner() != inMyId && node.getOwner() != 0) {
                node.setArmyCount(getStrengthLevel(node.getArmyCount()));
                allNodes.add(node);
            } else
                allNodes.add(node);

        return allNodes.toArray(new Node[allNodes.size()]);
    }

    /**
     * Gets given player's nodes
     *
     * @param inMyId An int which is the ID of the player
     * @return Returns an array of Node class which contains the given player's nodes.
     */
    public Node[] getMyNodes(int inMyId) {
        ArrayList<Node> myNodes = new ArrayList<>();
        for (Node node : this.getAllNodes())
            if (node.getOwner() == inMyId)
                myNodes.add(node);

        return myNodes.toArray(new Node[myNodes.size()]);
    }

    /**
     * Gets given player's opponent nodes
     * <p>
     * Note that it gets the player id to hide opponent's army count.
     * </p>
     *
     * @param inMyId An int which is the ID of the current player
     * @return Returns an array of Node class which contains the given player's opponent nodes.
     */
    public Node[] getOpponentNodes(int inMyId) {
        ArrayList<Node> opponentNodes = new ArrayList<>();
        for (Node node : this.getAllNodes())
            if (node.getOwner() != inMyId && node.getOwner() != 0) {
                node.setArmyCount(getStrengthLevel(node.getArmyCount()));
                opponentNodes.add(node);
            }

        return opponentNodes.toArray(new Node[opponentNodes.size()]);
    }

    /**
     * Gets all free nodes on the map
     *
     * @return Returns an array of Node class which contains the free nodes on the map.
     */
    public Node[] getFreeNodes() {
        ArrayList<Node> freeNodes = new ArrayList<>();
        for (Node node : this.getAllNodes())
            if (node.getOwner() == 0)
                freeNodes.add(node);

        return freeNodes.toArray(new Node[freeNodes.size()]);
    }

    /**
     * Converts army count to an approximate strength level.
     * <p>
     * This function is mainly used to hide current player's opponent's exact army count.
     * </p>
     *
     * @param inArmyCount An int of army counts to be converted
     * @return Returns an int between 1 and 4, 1 being very weak, and 4 being very powerful.
     */
    //FIXME: Bad idea to set strength as a proportion of total army count of all nodes.
    private int getStrengthLevel(int inArmyCount) {
        int armyCountSum = 0, minArmyCount = 1000, maxArmyCount = 0;
        for (int i = 0; i < getAllNodes().length; i++) {
            if (getAllNodes()[i].getOwner() != 0) {
                armyCountSum += getAllNodes()[i].getArmyCount();
                if (minArmyCount > getAllNodes()[i].getArmyCount())
                    minArmyCount = getAllNodes()[i].getArmyCount();
                if (maxArmyCount < getAllNodes()[i].getArmyCount())
                    maxArmyCount = getAllNodes()[i].getArmyCount();
            }
        }

        float armyCountAverage = armyCountSum / (getAllNodes().length - getFreeNodes().length);
        float firstQuarter = (minArmyCount + armyCountAverage) / 2;
        float thirdQuarter = (maxArmyCount + armyCountAverage) / 2;

        if (inArmyCount <= firstQuarter)
            return 1;
        else if (inArmyCount > firstQuarter && inArmyCount <= armyCountAverage)
            return 2;
        else if (inArmyCount > armyCountAverage && inArmyCount <= thirdQuarter)
            return 3;
        else
            return 4;
    }

    /**
     * Checks to see if the given index belongs to the given player
     *
     * @param inNodeId An int indicating the index of a node to check.
     * @param inMyId   An int for the player's ID.
     * @return Returns a boolean, true if the node belongs to the user, false otherwise.
     */
    public boolean isMyNode(int inNodeId, int inMyId) {
        for (Node node : this.getMyNodes(inMyId))
            if (node.getId() == inNodeId)
                return true;
        return false;
    }

    /**
     * Gets a node from the given index.
     *
     * @param inNodeId An int indicating the index of the node.
     * @return Returns an instance of Node which has the given ID.
     */
    public Node getNode(int inNodeId) {
        for (Node node : this.getAllNodes())
            if (node.getId() == inNodeId)
                return node;
        return null;
    }

    // FIXME: Why looping all the nodes to get opponent ID?

    /**
     * Gets the opponent's ID.
     *
     * @param inMyId An int indicating the current player.
     * @return Returns an int which is the opponent's ID.
     */
    public int getOpponentId(int inMyId) {
        for (Node node : this.getAllNodes())
            if (node.getOwner() != inMyId && node.getOwner() != 0)
                return node.getOwner();
        return -1;
    }

}
