/**
 * @author    Mohammad reza Hajianpour <hajianpour.mr@gmail.com>
 * @version   0.3.1
 * @since     0.1.0
 */

package com.twinity.PlanetWarsServer;

public class ServerConfig {

    /**
     * Server's configuration parameters, including:
     * Port            (default: 4000)
     * Minimum Threads (default: 2)
     * Maximum Threads (default: 8)
     * Timeout         (default: 5000 milliseconds)
     */
    private static int _port = 4000;
    private static int _minThreads = 2;
    private static int _maxThreads = 8;
    private static int _timeout = 5000;
    private static boolean _debugMode = false;

    /**
     * Gets server's port
     * @return Returns an int which is the server's port.
     */
    public static int getPort() {
        return _port;
    }

    /**
     * Gets server's minimum number of threads
     * @return Returns an int which is the minimum number of threads.
     */
    public static int getMinThreads() {
        return _minThreads;
    }

    /**
     * Gets server's maximum number of threads
     * @return Returns an int which is the maximum number of threads.
     */
    public static int getMaxThreads() {
        return _maxThreads;
    }

    /**
     * Gets server's timeout in milliseconds
     * @return Returns an int which is the server's timeout in milliseconds.
     */
    public static int getTimeout() {
        return _timeout;
    }

    /**
     * Gets server's debug mode state
     * @return Returns a boolean showing if server is on debug mode or not
     */
    public static boolean isDebugMode() {
        return _debugMode;
    }

    /**
     * Sets server's port
     * <p>
     *     Note that the port number should be between 1000 and 9999.
     *     Ports below 1000 are system-reserved.
     * </p>
     * @param inPort An int to be set as the server's port.
     */
    public static void setPort(int inPort) {
        if (inPort > 1000 && inPort < 9999)
            _port = inPort;
    }

    /**
     * Sets server's minimum number of
     * <p>
     *     Note that the minimum number should be between 1 and 15.
     * </p>
     * @param inNum An int to be set as the server's minimum number of threads.
     */
    public static void setMinThreads(int inNum) {
        if (inNum > 0 && inNum <= 15)
            _minThreads = inNum;
    }

    /**
     * Sets server's maximum number of threads
     * <p>
     *     Note that the maximum number should be between "Minimum" and 16.
     * </p>
     * @param inNum An int to be set as the server's maximum number of threads.
     */
    public static void setMaxThreads(int inNum) {
        if (inNum > ServerConfig.getMinThreads() && inNum <= 16)
            _maxThreads = inNum;
    }

    /**
     * Sets server's timeout in milliseconds
     * <p>
     *     Note that it must be between 1 and Integer.MAX_VALUE.
     * </p>
     * @param inTimeout An int to be set as the server's timeout in milliseconds.
     */
    public static void setTimeout(int inTimeout) {
        if (inTimeout > 0 && inTimeout < Integer.MAX_VALUE)
            _timeout = inTimeout;
    }

    /**
     * Sets the server's debug mode state
     * @param inToggle A boolean setting the debug mode on/off
     */
    public static void setDebugMode(boolean inToggle) {
        _debugMode = inToggle;
    }

}
