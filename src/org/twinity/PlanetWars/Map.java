/**
 * Created by KaTaNa on 7/29/2016.
 */
package org.twinity.PlanetWars;

import java.util.ArrayList;

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

    public Node[] getNodes() {
        return nodes;
    }

    public Node[] getMyNodes(Player inPlayer) {
        ArrayList<Node> myNodes = new ArrayList<>();
        for(Node node : this.getNodes()) {
            if (node.getSide() == inPlayer.getSide()) {
                myNodes.add(node);
            }
        }

        return myNodes.toArray(new Node[myNodes.size()]);
    }

    public Node[] getOpponentNodes(Player inPlayer) {
        ArrayList<Node> enemyNodes = new ArrayList<>();
        for(Node node : this.getNodes()) {
            if (node.getSide() != inPlayer.getSide() && node.getSide() != Node.Side.Neutral) {
                enemyNodes.add(node);
            }
        }

        return enemyNodes.toArray(new Node[enemyNodes.size()]);
    }

    public Node[] getFreeNodes(Player inPlayer) {
        ArrayList<Node> neutralNodes = new ArrayList<>();
        for(Node node : this.getNodes()) {
            if (node.getSide() == Node.Side.Neutral) {
                neutralNodes.add(node);
            }
        }

        return neutralNodes.toArray(new Node[neutralNodes.size()]);
    }

}
