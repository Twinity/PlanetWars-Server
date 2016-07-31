package org.twinity.PlanetWars;

/**
 * Created by KaTaNa on 7/31/2016.
 */
public class ServerInfo {
    private Node[] myNodes;
    private Node[] allNodes;
    private Node[] opponentNodes;
    private Node[] freeNodes;
    private int totalTurns;
    private int currentTurn;
    private int remainingTurns;

    public ServerInfo(World inWorld, int inMyId) {
        myNodes = inWorld.getMap().getMyNodes(inMyId);
        allNodes = inWorld.getMap().getAllNodes(inMyId);
        opponentNodes = inWorld.getMap().getOpponentNodes(inMyId);
        freeNodes = inWorld.getMap().getFreeNodes();
        totalTurns = inWorld.getMap().getTotalTurns();
        currentTurn = inWorld.getCurrentTurn();
        remainingTurns = inWorld.getRemainingTurn();
    }
}
