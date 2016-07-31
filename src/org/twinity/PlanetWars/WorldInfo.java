package org.twinity.PlanetWars;

/**
 * Created by KaTaNa on 7/31/2016.
 */
public class WorldInfo {
    private Node[] myNodes;
    private Node[] allNodes;
    private Node[] opponentNodes;
    private Node[] freeNodes;
    private int totalTurns;
    private int currentTurn;
    private int myId;
    private int opponentId;
    private int neutralId;

    public WorldInfo(World inWorld, int inMyId) {
        myNodes = inWorld.getMap().getMyNodes(inMyId);
        allNodes = inWorld.getMap().getAllNodes(inMyId);
        opponentNodes = inWorld.getMap().getOpponentNodes(inMyId);
        freeNodes = inWorld.getMap().getFreeNodes();
        totalTurns = inWorld.getMap().getTotalTurns();
        currentTurn = inWorld.getCurrentTurn();
        myId = inMyId;
        opponentId = myId == 1 ? 2 : 1;
        neutralId = 0;
    }
}
