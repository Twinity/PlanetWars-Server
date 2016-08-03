/**
 * Created by KaTaNa on 7/29/2016.
 */

package com.twinity.PlanetWarsServer;

public class Main {

    public static void main(String[] args) {
        // Reads map from MapReader object
        // TODO: A dynamic map name should be passed from args[]
        MapReader mapReader = new MapReader(System.getProperty("user.dir") + "\\map.json");
        // MapReader.read() returns a Map which is passed into the World object
        World world = new World(mapReader.read());
        /* Server receives the world object to interact with
           it also creates a loop so that the application can live
         */
        Server server = new Server(world);
    }

}
