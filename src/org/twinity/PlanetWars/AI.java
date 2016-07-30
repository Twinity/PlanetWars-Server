package org.twinity.PlanetWars;

/**
 * Created by KaTaNa on 7/29/2016.
 */
public class AI {

    private World _world;

    public AI(World inWorld) {
        _world = inWorld;
    }

    public World getWorld() {
        return _world;
    }


    public void doTurn() {
        _world.moveArmy(_world.getMap().getMyNodes(_player)[0].getId()
                , _world.getMap().getMyNodes(_player)[0].getAdjacents()[0]
                , _world.getMap().getMyNodes(_player)[0].getArmyCount());
    }
}
