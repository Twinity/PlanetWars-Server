/**
 * @author    Amir hossein Hajianpour <ahhajianpour1@gmail.com>
 * @version   1.2
 * @since     1.0
 */

package com.twinity.PlanetWarsServer;

public class Node {

    /**
     * Node specifications. Every node has:
     * A unique ID
     * An army count
     * An owner
     * And an array of adjacent nodes connected to the current one
     */
    private int id;
    private int armyCount;
    private int owner;
    private int[] adjacents;

    /**
     * Gets the ID of the current Node
     * @return Returns an int indicating the ID of the current Node.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the army count of the current Node
     * @return Returns an int indicating the army count of the current Node.
     */
    public int getArmyCount() {
        return armyCount;
    }

    /**
     * Gets the ID of the owner of the current Node
     * @return Returns an int indicating the ID of the owner of the current Node.
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Gets the neighbour nodes of the current Node
     * @return Returns an array of int containing the ID of the neighbour nodes of the current Node.
     */
    public int[] getAdjacents() {
        return adjacents;
    }

    /**
     * Sets the army count of the current Node
     * @param inNumber An int which is the number to be set.
     * @throws IllegalArgumentException
     */
    public void setArmyCount(int inNumber) throws IllegalArgumentException {
        if (inNumber > 0) {
            armyCount = inNumber;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets the owner of a Node.
     * <p>
     *     Note that the ID must be between 1 and 999.
     * </p>
     * @param inOwner An int which is the ID of the owner to be set.
     * @throws IllegalArgumentException
     */
    public void setOwner(int inOwner) throws IllegalArgumentException {
        if (inOwner >= 0 && inOwner < 1000) {
            owner = inOwner;
        } else {
            throw new IllegalArgumentException();
        }

    }

}
