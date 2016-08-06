/**
 * @author    Amir hossein Hajianpour <ahhajianpour1@gmail.com>
 * @author    Mohammad reza Hajianpour <hajianpour.mr@gmail.com>
 * @version   1.2
 * @since     1.0
 */

package com.twinity.PlanetWarsServer;

import org.fusesource.jansi.AnsiConsole;

import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    /**
     * main function which is the starting point of the app
     * @param args TODO: Should contain path to the map file
     */
    public static void main(String[] args) {
        // If debug mode is set
        if (Arrays.asList(args).contains("--debug=true") || Arrays.asList(args).contains("-d")) {
            ServerConfig.setDebugMode(true);
            // Start AnsiConsole
            AnsiConsole.systemInstall();
        }
        // Reads map from MapReader object
        // TODO: A dynamic map name should be passed from args[]
        MapReader mapReader = new MapReader(Paths.get(System.getProperty("user.dir"), "map.json").toString());
        // MapReader.read() returns a Map which is passed into the World object
        World world = new World(mapReader.read());
        /* Server receives the world object to interact with
           it also creates a loop so that the application can live
         */
        Server server = new Server(world);
    }

}
