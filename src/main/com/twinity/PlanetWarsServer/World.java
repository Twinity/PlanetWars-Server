/**
 * @author    Amir hossein Hajianpour <ahhajianpour1@gmail.com>
 * @version   1.2
 * @since     1.0
 */

package com.twinity.PlanetWarsServer;

import java.util.ArrayList;
import java.util.Arrays;

public class World {

    // An instance of Map class to be used in World
    private Map _map;
    // FIXME: What is _currentTurn when we have implemented it on the server?
    private float _currentTurn = 1;

    /**
     * World Constructor
     * @param inMap Receives and saves a Map inside itself.
     */
    public World(Map inMap) {
        _map = inMap;
    }

    /**
     * Applies the input ArmyMovement array and updates the map accordingly.
     * @param inArmyMovement An ArmyMovement array to update the map with.
     * @param inMyId An int which is the owner of the ArmyMovement array
     */
    public void moveArmy(ArmyMovement[] inArmyMovement, int inMyId) {
        ArmyMovement[] validArmyMovement = armyMovementValidator(inArmyMovement, inMyId);
        updateMap(validArmyMovement, inMyId);
        // FIXME: Is _currentTurn in sync with player turns on the Server?
        _currentTurn += 0.5;
    }

    /**
     * Gets the map object residing in the World class
     * @return Returns a map object
     */
    public Map getMap() {
        return _map;
    }

    /**
     * Gets the current turn
     * @return Returns an int indicating the current turn
     */
    public int getCurrentTurn() {
        return (int) _currentTurn;
    }

    /**
     * Gets the remaining turns of the game
     * @return Returns an int indicating the remaining turns of the game.
     */
    public int getRemainingTurns() {
        return getMap().getTotalTurns() - getCurrentTurn();
    }

    /**
     * Updates the map with a valid ArmyMovement
     * @param inArmyMovement An ArmyMovement array to update the map with.
     * @param inMyId An int which is the current player's ID.
     */
    // TODO: Should separate game's rules from updating the map.
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

    /**
     * Validates ArmyMovement array and checks if the movements are sensible.
     * @param inArmyMovement An ArmyMovement array to be validated.
     * @param inMyId An int which is the current player's ID.
     * @return Returns an array of ArmyMovement with valid movements.
     */
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
