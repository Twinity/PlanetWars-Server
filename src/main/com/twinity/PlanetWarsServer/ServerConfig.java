/**
 * Created by Borderliner on 8/1/2016.
 */

package com.twinity.PlanetWarsServer;

public class ServerConfig {

    private static int _port = 4000;
    private static int _minThreads = 2;
    private static int _maxThreads = 8;
    private static int _timeout = 5000;

    public static int getPort() {
        return _port;
    }

    public static int getMinThreads() {
        return _minThreads;
    }

    public static int getMaxThreads() {
        return _maxThreads;
    }

    public static int getTimeout() {
        return _timeout;
    }

    public static void setPort(int inPort) {
        if (inPort > 1000 && inPort < 9999)
            _port = inPort;
    }

    public static void setMinThreads(int inNum) {
        if (inNum > 0 && inNum < 16)
            _minThreads = inNum;
    }

    public static void setMaxThreads(int inNum) {
        if (inNum > 0 && inNum <16)
            _maxThreads = inNum;
    }

    public static void setTimeout(int inTimeout) {
        if (inTimeout > 0 && inTimeout < Integer.MAX_VALUE)
            _timeout = inTimeout;
    }

}
