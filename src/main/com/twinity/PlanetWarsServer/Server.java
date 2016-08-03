/**
 * Created by Borderliner on 8/1/2016.
 */

package com.twinity.PlanetWarsServer;

import com.google.gson.Gson;
import spark.Spark;

public class Server {

    private int _player1Id;
    private int _player2Id;
    private World _world;
    private static int _connectedPlayers = 0;
    private final int _maxPlayers = 2;
    private int _player1Turn = 0;
    private int _player2Turn = 0;

    public Server(World inWorld) {
        generateIds();
        _world = inWorld;
        _world.getMap().initializer(_player1Id, _player2Id);
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

    public void generateIds() {
        _player1Id = (int)Math.round(Math.random() * 999) + 1;
        do {
            _player2Id = (int)Math.round(Math.random() * 999) + 1;
        }
        while(_player2Id == _player1Id);
    }

    public void startRouting() {
        Spark.get("/serverdata", (req, res) -> {
            int reqPlayer = Integer.parseInt(req.headers("X-Request-ID"));
            if (_player1Turn == _player2Turn) {
                if (reqPlayer == _player1Id)
                    _player1Turn++;
                else if (reqPlayer == _player2Id)
                    _player2Turn++;

            } else if (_player2Turn == _player1Turn + 1) {
                if (reqPlayer == _player2Id) {
                    return "wait";
                }
                _player1Turn++;
            } else if (_player1Turn == _player2Turn + 1) {
                if (reqPlayer == _player1Id) {
                    return "wait";
                }
                _player2Turn++;
            }
            WorldInfo worldInfo = new WorldInfo(_world, reqPlayer);
            res.body(new Gson().toJson(worldInfo));
            res.header("Content-type", "application/json");
            return res.body();
        });

        Spark.get("/getid", (req, res) -> {
            res.header("Content-type", "text/plain");

            if (_connectedPlayers < _maxPlayers) {
                if (_connectedPlayers == 0) {
                    _connectedPlayers++;
                    res.body(String.valueOf(_player1Id));
                } else if (_connectedPlayers == 1) {
                    _connectedPlayers++;
                    res.body(String.valueOf(_player2Id));
                }
            } else {
                res.body("null");
            }

            return res.body();
        });

        Spark.post("/clientdata", (req, res) -> {
            ArmyMovement[] clientArmyMovement = new Gson().fromJson(req.body(), ArmyMovement[].class);
            int playerId = Integer.parseInt(req.headers("X-Request-ID"));
            _world.moveArmy(clientArmyMovement, playerId);
            res.body("OK");
            return res.body();
        });
    }

}