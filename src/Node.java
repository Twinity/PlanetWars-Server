/**
 * Created by KaTaNa on 7/29/2016.
 */
public class Node {
    private enum Side {
        P1, P2, Neutral;
    }

    private int _id;
    private int _armyCount;
    private Side _side;

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

    // Setters
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

}
