/**
 * Created by KaTaNa on 7/29/2016.
 */

package com.twinity.PlanetWarsServer;

public class Node {

    private int id;
    private int armyCount;
    private int owner;
    private int[] adjacents;

    // Getters
    public int getId() {
        return id;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public int getOwner() {
        return owner;
    }

    public int[] getAdjacents() {
        return adjacents;
    }

    // Setters
    public void setArmyCount(int inNumber) throws IllegalArgumentException {
        if (inNumber > 0) {
            armyCount = inNumber;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setOwner(int inOwner) throws IllegalArgumentException {
        if (inOwner >= 0 && inOwner <= 1000) {
            owner = inOwner;
        } else {
            throw new IllegalArgumentException();
        }

    }

}
