package org.twinity.PlanetWars;

/**
 * Created by KaTaNa on 7/29/2016.
 */
public class Main {

    public static void main(String[] args) {
        MapReader myMap = new MapReader("C:\\Users\\KaTaNa\\IdeaProjects\\PlanetWars\\out\\production\\PlanetWars\\map.json");
        World myWorld = new World(myMap.read());
    }

}
