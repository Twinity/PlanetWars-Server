/**
 * @author    Amir hossein Hajianpour <ahhajianpour1@gmail.com>
 * @version   1.2
 * @since     1.0
 */

package com.twinity.PlanetWarsServer;

public class ArmyMovement {

    // Index of source and destination of each move
    private int source;
    private int destination;
    // Number of soldiers to send to the destination node
    private int armyCount;

    /**
     * ArmyMovement Constructor
     * @param inSource Source node's index
     * @param inDestination Destination node's index
     * @param inArmyCount Number of soldiers to send to the destination node
     */
    public ArmyMovement(int inSource, int inDestination, int inArmyCount) {
        this.source = inSource;
        this.destination = inDestination;
        this.armyCount = inArmyCount;
    }

    /**
     * Gets source node's index
     * @return Returns an int containing source node's index
     */
    public int getSource() {
        return this.source;
    }

    /**
     * Gets destination node's index
     * @return Returns an int containing destination node's index
     */
    public int getDestination() {
        return this.destination;
    }

    /**
     * Gets node's army count
     * @return
     */
    public int getArmyCount() {
        return this.armyCount;
    }

    /**
     * Sets army count of the node
     * @param inArmyCount Number of soldiers to set as army count
     */
    public void setArmyCount(int inArmyCount) {
        this.armyCount = inArmyCount > 0 ? inArmyCount : 0;
    }

}
