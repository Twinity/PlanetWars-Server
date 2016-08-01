/**
 * Created by Borderliner on 8/1/2016.
 */

package org.twinity.PlanetWars;

import com.google.gson.Gson;
import spark.Spark;

public class Server {

    private int _player1Id;
    private int _player2Id;
    private World world;

    public Server() {
        _player1Id = -1;
        _player2Id = -2;
        world = new World();
        this.setConfigs();
        this.startRouting();
    }

    public void setConfigs() {
        ServerConfig.setMinThreads(2);
        ServerConfig.setMaxThreads(8);
        ServerConfig.setPort(4000);
        ServerConfig.setTimeout(5000);

        Spark.port(ServerConfig.getPort());
        Spark.threadPool(
            ServerConfig.getMaxThreads(),
            ServerConfig.getMinThreads(),
            ServerConfig.getTimeout()
        );
    }

    public void startRouting() {
        Spark.get("/serverdata", (req, res) -> {
            WorldInfo worldInfo = new WorldInfo(world, Integer.parseInt(req.headers("X-Request-ID")));
            res.body(new Gson().toJson(worldInfo));
            res.header("Content-type", "application/json");
            return res.body();
        });

        Spark.get("/getid", (req, res) -> {
            res.header("Content-type", "text/plain");

            if (_player1Id == -1) {
                _player1Id = (int)Math.round(Math.random() * 999) + 1;
                res.body(String.valueOf(_player1Id));

            } else if (_player2Id == -2) {
                do {
                    _player2Id = (int)Math.round(Math.random() * 999) + 1;
                }
                while(_player2Id == _player1Id);

                res.body(String.valueOf(_player2Id));
            }

            return res.body();
        });

        Spark.post("/clientdata", (req, res) -> {
            ArmyMovement[] clientArmyMovement = new Gson().fromJson(req.body(), ArmyMovement[].class);
            int playerId = Integer.parseInt(req.headers("X-Request-ID"));
            
        });
    }

}
