/**
 * Created by KaTaNa on 7/29/2016.
 */
package org.twinity.PlanetWars;

import com.google.gson.annotations.SerializedName;

public class Node {

    public static enum Side {
        @SerializedName("0")
        Neutral,

        @SerializedName("1")
        P1,

        @SerializedName("2")
        P2
    }

    private int id;
    private int armyCount;
    private Side side;
    private int[] adjacents;

    public Node(int inId, int inArmyCount, Side inSide, int[] inAdjacents) throws IllegalArgumentException {
        setId(inId);
        setArmyCount(inArmyCount);
        setSide(inSide);
        setAdjacents(inAdjacents);
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public Side getSide() {
        return side;
    }

    public int[] getAdjacents() {
        return adjacents;
    }

    // Setters
    public void setId(int inId) {
        if (inId > 100) {
            id = inId;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setArmyCount(int inNumber) throws IllegalArgumentException {
        if (inNumber > 0) {
            armyCount = inNumber;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setSide(Side inSide) throws IllegalArgumentException {
        if(inSide != Side.P1 && inSide != Side.P2 && inSide != Side.Neutral) {
            throw new IllegalArgumentException();
        } else {
            side = inSide;
        }

    }

    public void setAdjacents(int[] inAdjacents) throws IllegalArgumentException {
        adjacents = new int[inAdjacents.length];
        int idx = 0;
        for (int id : inAdjacents) {
            if (id > 100) {
                adjacents[idx] = inAdjacents[idx++];
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

}
