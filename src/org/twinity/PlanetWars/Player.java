package org.twinity.PlanetWars;

/**
 * Created by KaTaNa on 7/29/2016.
 */
public class Player {
    private Node.Side _side;
    private AI _ai;
    private World _world;

    public Player(Node.Side inSide, World inWorld) throws IllegalArgumentException {
        try {
            _side = inSide;
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        _world = inWorld;
        _ai = new AI(this);
    }

    // Getters
    public Node.Side getSide() {
        return _side;
    }

    public World getWorld() {
        return _world;
    }

}
