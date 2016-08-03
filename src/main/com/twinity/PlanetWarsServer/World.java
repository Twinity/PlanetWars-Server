/**
 * Created by KaTaNa on 7/29/2016.
 */

package com.twinity.PlanetWarsServer;

import java.util.ArrayList;
import java.util.Arrays;

public class World {

    private Map _map;
    private float _currentTurn = 1;

    public World(Map inMap) {
        _map = inMap;
    }

    public void moveArmy(ArmyMovement[] inArmyMovement, int inMyId) {
        ArmyMovement[] validArmyMovement = armyMovementValidator(inArmyMovement, inMyId);
        updateMap(validArmyMovement, inMyId);
        _currentTurn += 0.5;
    }

    public Map getMap() {
        return _map;
    }

    public int getCurrentTurn() {
        return (int) _currentTurn;
    }

    public int getRemainingTurns() {
        return getMap().getTotalTurns() - getCurrentTurn();
    }

    private void updateMap(ArmyMovement[] inArmyMovement, int inMyId) {
        for (int i = 0; i < inArmyMovement.length; i++) {
            if (getMap().getNode(inArmyMovement[i].getDestination()).getOwner() == 0) {
                getMap().getNode(inArmyMovement[i].getDestination()).setArmyCount(inArmyMovement[i].getArmyCount());
                getMap().getNode(inArmyMovement[i].getSource()).setArmyCount(getMap().getNode(inArmyMovement[i].getSource()).getArmyCount()
                        - inArmyMovement[i].getArmyCount());
            } else if (getMap().getNode(inArmyMovement[i].getDestination()).getOwner() == inMyId) {
                getMap().getNode(inArmyMovement[i].getDestination()).setArmyCount(inArmyMovement[i].getArmyCount()
                        + getMap().getNode(inArmyMovement[i].getDestination()).getArmyCount());
                getMap().getNode(inArmyMovement[i].getSource()).setArmyCount(getMap().getNode(inArmyMovement[i].getSource()).getArmyCount()
                        - inArmyMovement[i].getArmyCount());
            } else {
                if (inArmyMovement[i].getArmyCount() >= getMap().getNode(inArmyMovement[i].getDestination()).getArmyCount()) {
                    getMap().getNode(inArmyMovement[i].getDestination()).setArmyCount((int) Math.ceil(inArmyMovement[i].getArmyCount()
                            - getMap().getNode(inArmyMovement[i].getDestination()).getArmyCount() * Math.sqrt(getMap().getNode(inArmyMovement[i].getDestination()).getArmyCount()
                            / inArmyMovement[i].getArmyCount())));
                    getMap().getNode(inArmyMovement[i].getDestination()).setOwner(getMap().getNode(inArmyMovement[i].getSource()).getOwner());
                } else {
                    getMap().getNode(inArmyMovement[i].getDestination()).setArmyCount((int) Math.ceil(getMap().getNode(inArmyMovement[i].getDestination()).getArmyCount()
                            - inArmyMovement[i].getArmyCount() * Math.sqrt(inArmyMovement[i].getArmyCount()
                            / getMap().getNode(inArmyMovement[i].getDestination()).getArmyCount())));
                }
            }
        }
    }

    private ArmyMovement[] armyMovementValidator(ArmyMovement[] inArmyMovement, int inMyId) {
        int j;
        ArrayList<ArmyMovement> am = new ArrayList<>(Arrays.asList(inArmyMovement));
        boolean flag = false;
        for (int i = 0; i < am.size(); i++) {
            for (j = 0; j < getMap().getMyNodes(inMyId).length; j++) {
                if (am.get(i).getSource() == getMap().getMyNodes(inMyId)[j].getId()) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                am.remove(i);
            else {
                flag = false;
                for (int k = 0; k < getMap().getMyNodes(inMyId)[j].getAdjacents().length; k++) {
                    if (am.get(i).getDestination() == getMap().getMyNodes(inMyId)[j].getAdjacents()[k]) {
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    am.remove(i);
                else {
                    if (am.get(i).getArmyCount() < 0)
                        am.get(i).setArmyCount(0);
                    else if (am.get(i).getArmyCount() > getMap().getMyNodes(inMyId)[j].getArmyCount())
                        am.get(i).setArmyCount(getMap().getMyNodes(inMyId)[j].getArmyCount());
                }
            }
        }
        return am.toArray(new ArmyMovement[am.size()]);
    }
}
