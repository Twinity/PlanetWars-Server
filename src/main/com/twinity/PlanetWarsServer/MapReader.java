/**
 * @author    Mohammad reza Hajianpour <hajianpour.mr@gmail.com>
 * @version   0.3.1
 * @since     0.1.0
 */

package com.twinity.PlanetWarsServer;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.fusesource.jansi.Ansi.ansi;

public class MapReader {

    private String _path;
    private Gson _gson;
    private JsonReader _reader;

    /**
     * MapReader Constructor
     * @param inPath A Path to the map file
     */
    public MapReader(String inPath) {
        _path = inPath;
        _gson = new Gson();
    }

    /**
     * Reads Map from the given path in the constructor
     * @return Returns a Map object
     */
    public Map read() {
        try {
            _reader = new JsonReader(new FileReader(_path));
        } catch (FileNotFoundException ex) {
            System.out.println(ansi().render("@|red Could not find or read map from " + _path + ".|@"));
            System.exit(1);
        }
        return _gson.fromJson(_reader, Map.class);
    }

}
