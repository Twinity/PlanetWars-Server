package org.twinity.PlanetWars;

/**
 * Created by KaTaNa on 7/29/2016.
 */
public class Player {
    private Node.Side _side;
    private AI _ai;
    private World _world;

    public Player(Node.Side inSide, World inWorld) {
        _side = inSide;
        _world = inWorld;
        _ai = new AI(this);
    }

    // Getters
    public Node.Side getSide() {
        return _side;
    }

    public AI getAI() {
        return _ai;
    }

    public World getWorld() {
        return _world;
    }

}
