/**
 * Created by Borderliner on 8/1/2016.
 */

package org.twinity.PlanetWars;

import spark.Spark;

public class Server {

    private int _player1Id;
    private int _player2Id;

    public Server() {
        this.setConfigs();
        this.startRouting();
        _player1Id = -1;
        _player2Id = -2;
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

        })
    }

}
