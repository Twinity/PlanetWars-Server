/**
 * Created by Borderliner on 8/1/2016.
 */

package org.twinity.PlanetWars;

import spark.Spark;

public class Server {

    public Server() {
        this.setConfigs();
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

}
