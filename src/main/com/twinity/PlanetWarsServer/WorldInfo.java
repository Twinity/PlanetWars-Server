/**
 * @author    Amir hossein Hajianpour <ahhajianpour1@gmail.com>
 * @version   0.3.1
 * @since     0.1.0
 */

package com.twinity.PlanetWarsServer;

public class WorldInfo {
    // Contains My Nodes, All Nodes, Opponent Nodes and Free Nodes
    private Node[] myNodes;
    private Node[] allNodes;
    private Node[] opponentNodes;
    private Node[] freeNodes;
    // Contains Total Turns, Current Turn and Remaining Turns
    private int totalTurns;
    private int currentTurn;
    private int remainingTurns;
    // All Players ID's will be stored here
    private int myId;
    private int opponentId;
    private int neutralId;

    public WorldInfo WorldInfo() {
        return this;
    }

    /**
     * Populates WorldInfo with the data collected from World.Map
     * @param inWorld Receives an instance of World
     * @param inMyId Receives requested player's ID to customize data accordingly
     * @return Returns an instance of WorldInfo to be sent to the client
     */
    public WorldInfo populate(World inWorld, int inMyId) {
        myNodes = inWorld.getMap().getMyNodes(inMyId);
        allNodes = inWorld.getMap().getFilteredAllNodes(inMyId);
        opponentNodes = inWorld.getMap().getOpponentNodes(inMyId);
        freeNodes = inWorld.getMap().getFreeNodes();
        totalTurns = inWorld.getMap().getTotalTurns();
        currentTurn = inWorld.getCurrentTurn();
        remainingTurns = inWorld.getRemainingTurns();
        myId = inMyId;
        opponentId = inWorld.getMap().getOpponentId(inMyId);
        // Neutral Nodes always have the ID of zero.
        neutralId = 0;
        return this;
    }
}
