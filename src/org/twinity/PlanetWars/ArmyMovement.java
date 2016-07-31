package org.twinity.PlanetWars;

/**
 * Created by KaTaNa on 7/30/2016.
 */

public class ArmyMovement {

    private int source;
    private int destination;
    private int armyCount;

    public ArmyMovement(int inSource, int inDestination, int inArmyCount) {
        this.source = inSource;
        this.destination = inDestination;
        this.armyCount = inArmyCount;
    }

    public ArmyMovement() {
        this.source = 0;
        this.destination = 0;
        this.armyCount = 0;
    }

    public int getSource() {
        return this.source;
    }

    public int getDestination() {
        return this.destination;
    }

    public int getArmyCount() {
        return this.armyCount;
    }

    public void setArmyCount(int inArmyCount) {
        this.armyCount = inArmyCount > 0 ? inArmyCount : 0;
    }

}
