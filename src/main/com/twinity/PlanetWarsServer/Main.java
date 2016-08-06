/**
 * @author    Amir hossein Hajianpour <ahhajianpour1@gmail.com>
 * @author    Mohammad reza Hajianpour <hajianpour.mr@gmail.com>
 * @version   0.3.1
 * @since     0.1.0
 */

package com.twinity.PlanetWarsServer;

import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;
import java.util.Arrays;

import static org.fusesource.jansi.Ansi.ansi;

public class Main {

    /**
     * main function which is the starting point of the app
     * @param args Command-line arguments ([-d|--debug=true] [-m|--map] "~/path/to/map.json")
     */
    public static void main(String[] args) {
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));
        // If debug mode is set
        if (arguments.contains("--debug=true") || arguments.contains("-d")) {
            ServerConfig.setDebugMode(true);
            // Start AnsiConsole
            AnsiConsole.systemInstall();
        }
        // The argument after -m or --map is the map
        int idx = arguments.indexOf("-m");
        if (idx == -1) {
            idx = arguments.indexOf("--map");
            if (idx == -1) {
                System.out.println(ansi().render("@|red Please specify a map with [-m|--map] \"~/path/to/map.json\"|@"));
                System.exit(1);
            }
        }
        String map = arguments.get(idx + 1);

        // Reads map from MapReader object
        MapReader mapReader = new MapReader(map);
        // MapReader.read() returns a Map which is passed into the World object
        World world = new World(mapReader.read());
        /* Server receives the world object to interact with
           it also creates a loop so that the application can live
         */
        Server server = new Server(world);
    }

}
