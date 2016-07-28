/**
 * Created by KaTaNa on 7/29/2016.
 */
public class Node {
    public static enum Side {
        Neutral, P1, P2;
    }

    private int _id;
    private int _armyCount;
    private Side _side;
    private int[] _adjacents;

    public Node(int inId, int inArmyCount, Side inSide, int[] inAdjacents) throws IllegalArgumentException {
        setId(inId);
        setArmyCount(inArmyCount);
        setSide(inSide);
        setAdjacents(inAdjacents);
    }

    // Getters
    public int getId() {
        return _id;
    }

    public int getArmyCount() {
        return _armyCount;
    }

    public Side getSide() {
        return _side;
    }

    public int[] getAdjacents() {
        return _adjacents;
    }

    // Setters
    public void setId(int inId) {
        if (inId > 100) {
            _id = inId;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setArmyCount(int inNumber) throws IllegalArgumentException {
        if (inNumber > 0) {
            _armyCount = inNumber;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setSide(Side inSide) throws IllegalArgumentException {
        if(inSide != Side.P1 && inSide != Side.P2 && inSide != Side.Neutral) {
            throw new IllegalArgumentException();
        } else {
            _side = inSide;
        }

    }

    public void setAdjacents(int[] inAdjacents) throws IllegalArgumentException {
        _adjacents = new int[inAdjacents.length];
        int idx = 0;
        for (int id : inAdjacents) {
            if (id > 100) {
                _adjacents[idx] = inAdjacents[idx++];
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

}
