package org.twinity.PlanetWars;

/**
 * Created by KaTaNa on 7/29/2016.
 */
public class Main {

    public static void main(String[] args) {
        MapReader mapReader = new MapReader(System.getProperty("user.dir" + "map.json"));
        World world = new World(mapReader.read());
        Server server = new Server(world);
    }

}
