/**
 * Created by KaTaNa on 7/29/2016.
 */

package com.twinity.PlanetWarsServer;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MapReader {

    private String _path;
    private Gson _gson;
    private JsonReader _reader;

    public MapReader(String inPath) {
        _path = inPath;
        _gson = new Gson();
    }

    public Map read() {
        try {
            _reader = new JsonReader(new FileReader(_path));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return _gson.fromJson(_reader, Map.class);
    }

}
