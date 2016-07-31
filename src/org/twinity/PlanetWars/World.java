package org.twinity.PlanetWars;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by KaTaNa on 7/29/2016.
 */
public class World {

    private Map _map;
    private int _currentTurn = 1;
    private int _player1;
    private int _player2;
    private ArrayList<ArmyMovement> _armyMovementPlayer1;
    private ArrayList<ArmyMovement> _armyMovementPlayer2;

    public World() {
        readMap();

        _armyMovementPlayer1 = new ArrayList<>();
        _armyMovementPlayer2 = new ArrayList<>();
        for (int i = 1; i <= _map.getTotalTurns(); i++) {
            // TODO: send all the information to clients
            // TODO: receive ArmyMovements of player1
            // TODO: receive ArmyMovements of player2
            armyMovementValidator(_armyMovementPlayer1, 1);
            armyMovementValidator(_armyMovementPlayer2, 2);
            // TODO: 7/30/2016  map should be updated
            _armyMovementPlayer1.clear();
            _armyMovementPlayer2.clear();
            _currentTurn++;
        }
    }

    public Map getMap() {
        return _map;
    }

    private void readMap() {
        try {
            MapReader reader = new MapReader("C:\\Users\\KaTaNa\\IdeaProjects\\PlanetWars\\out\\production\\PlanetWars\\map.json");
            _map = reader.read();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getCurrentTurn() {
        return _currentTurn;
    }

    public int getRemainingTurn() {
        return getMap().getTotalTurns() - getCurrentTurn();
    }

    private void updateMap(ArrayList<ArmyMovement> inArmyMovement, int inMyId) {
        for (int i = 0; i < inArmyMovement.size(); i++) {
            if (getMap().getNode(inArmyMovement.get(i).getDestination()).getOwner() == 0) {
                getMap().getNode(inArmyMovement.get(i).getDestination()).setArmyCount(inArmyMovement.get(i).getArmyCount());
                getMap().getNode(inArmyMovement.get(i).getSource()).setArmyCount(getMap().getNode(inArmyMovement.get(i).getSource()).getArmyCount()
                        - inArmyMovement.get(i).getArmyCount());
            } else if (getMap().getNode(inArmyMovement.get(i).getDestination()).getOwner() == inMyId) {
                getMap().getNode(inArmyMovement.get(i).getDestination()).setArmyCount(inArmyMovement.get(i).getArmyCount()
                        + getMap().getNode(inArmyMovement.get(i).getDestination()).getArmyCount());
                getMap().getNode(inArmyMovement.get(i).getSource()).setArmyCount(getMap().getNode(inArmyMovement.get(i).getSource()).getArmyCount()
                        - inArmyMovement.get(i).getArmyCount());
            } else {
                if (inArmyMovement.get(i).getArmyCount() >= getMap().getNode(inArmyMovement.get(i).getDestination()).getArmyCount()) {
                    getMap().getNode(inArmyMovement.get(i).getDestination()).setArmyCount((int) Math.ceil(inArmyMovement.get(i).getArmyCount()
                            - getMap().getNode(inArmyMovement.get(i).getDestination()).getArmyCount() * Math.sqrt(getMap().getNode(inArmyMovement.get(i).getDestination()).getArmyCount()
                            / inArmyMovement.get(i).getArmyCount())));
                    getMap().getNode(inArmyMovement.get(i).getDestination()).setOwner(getMap().getNode(inArmyMovement.get(i).getSource()).getOwner());
                } else {
                    getMap().getNode(inArmyMovement.get(i).getDestination()).setArmyCount((int) Math.ceil(getMap().getNode(inArmyMovement.get(i).getDestination()).getArmyCount()
                            - inArmyMovement.get(i).getArmyCount() * Math.sqrt(inArmyMovement.get(i).getArmyCount()
                            / getMap().getNode(inArmyMovement.get(i).getDestination()).getArmyCount())));
                }
            }
        }
    }

    public void moveArmy(ArrayList<ArmyMovement> inArmyMovement, int inSource, int inDestination, int inArmyCount) {
        inArmyMovement.add(new ArmyMovement(inSource, inDestination, inArmyCount));
    }

    private void armyMovementValidator(ArrayList<ArmyMovement> inArmyMovement, int inMyId) {
        int j;
        boolean flag = false;
        for (int i = 0; i < inArmyMovement.size(); i++) {
            for (j = 0; j < getMap().getMyNodes(inMyId).length; j++) {
                if (inArmyMovement.get(i).getSource() == getMap().getMyNodes(inMyId)[j].getId()) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                inArmyMovement.remove(i);
            else {
                flag = false;
                for (int k = 0; k < getMap().getMyNodes(inMyId)[j].getAdjacents().length; k++) {
                    if (inArmyMovement.get(i).getDestination() == getMap().getMyNodes(inMyId)[j].getAdjacents()[k]) {
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    inArmyMovement.remove(i);
                else {
                    if (inArmyMovement.get(i).getArmyCount() < 0)
                        inArmyMovement.get(i).setArmyCount(0);
                    else if (inArmyMovement.get(i).getArmyCount() > getMap().getMyNodes(inMyId)[j].getArmyCount())
                        inArmyMovement.get(i).setArmyCount(getMap().getMyNodes(inMyId)[j].getArmyCount());
                }
            }
        }
    }
}
