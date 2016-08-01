package org.twinity.PlanetWars;

/**
 * a NODE is a vertex in a graph
 */
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
