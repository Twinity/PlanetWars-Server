package org.twinity.PlanetWars;

/**
 * Created by KaTaNa on 7/29/2016.
 */
public class AI {

    private Player _player;
    private World _world;

    public AI(Player inPlayer) {
        _player = inPlayer;
        _world = inPlayer.getWorld();
    }

    public World getWorld() {
        return _world;
    }

    public Player Me() {
        return _player;
    }

    public void doTurn() {
        Node node = getWorld().getMap().getMyNodes(Me())[0];
        System.out.println(node.getSide());
    }
}
