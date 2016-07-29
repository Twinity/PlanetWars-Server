/**
 * Created by KaTaNa on 7/29/2016.
 */

package org.twinity.PlanetWars;

import java.io.FileNotFoundException;

public class World {

    private Map _map;
    private Player _p1;
    private Player _p2;

    public World() throws FileNotFoundException {
        MapReader reader = new MapReader("C:\\Users\\KaTaNa\\IdeaProjects\\PlanetWars\\out\\production\\PlanetWars\\map.json");
        try{
            _map = reader.read();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        _p1 = new Player(Node.Side.P1, this);
        _p2 = new Player(Node.Side.P2, this);
    }

    public Map getMap() {
        return _map;
    }
}
