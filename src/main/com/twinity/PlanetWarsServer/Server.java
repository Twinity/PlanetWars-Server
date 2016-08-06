/**
 * @author    Mohammad reza Hajianpour <hajianpour.mr@gmail.com>
 * @version   1.2
 * @since     1.1
 */

package com.twinity.PlanetWarsServer;

import com.google.gson.Gson;
import spark.Spark;
import static org.fusesource.jansi.Ansi.*;

public class Server {

    /**
     * Players ID's are stored in _player1Id and _player2Id
     */
    private int _player1Id;
    private int _player2Id;
    /**
     * The World object which is passed into the server
     */
    private World _world;
    /**
     * Number of Connected players and Max possible players
     */
    private static int _connectedPlayers = 0;
    private final int _maxPlayers = 2;
    /**
     * Player turns, which increases every time they make a move.
     * Used to synchronize turns
     */
    private int _player1Turn = 0;
    private int _player2Turn = 0;
    // Is on debug mode?
    private boolean debug;

    /**
     * Server Constructor
     * <p>
     *     It initiates generateId() and Map.initializer(),
     *     setConfigs() and startRouting()
     * </p>
     * <p>
     *     It generates random unique ID's for players, then sets
     *     the ID of Nodes in the Map to the generated ID.
     *     Sets the configs related to the Spark server, and starts
     *     the routing process.
     * </p>
     * @param inWorld Gets a world object from Main class
     */
    public Server(World inWorld) {
        generateIds();
        _world = inWorld;
        _world.getMap().initializer(_player1Id, _player2Id);
        this.setConfigs();
        // Get debug mode (Is set before creating Server object)
        debug = ServerConfig.isDebugMode();
        this.startRouting();
        if (debug) {
            System.out.print(ansi().render("@|green Server started.|@ "));
            System.out.println("Listening on http://localhost:" + ServerConfig.getPort() + "/");
        }
    }

    /**
     * Sets Server-related configs for Spark Server from ServerConfig class
     * <p>
     *     This includes Minimum and Maximum number of threads for server,
     *     server port, and server timeout time.
     * </p>
     */
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

    /**
     * Generates random ID's for players between 1 and 999
     * <p>
     *     Note that it loops the second player's ID until it's not the same
     *     as the first player.
     * </p>
     */
    public void generateIds() {
        _player1Id = (int)Math.round(Math.random() * 999) + 1;
        do {
            _player2Id = (int)Math.round(Math.random() * 999) + 1;
        }
        while(_player2Id == _player1Id);
        if (debug) {
            System.out.println(ansi().render("@|yellow Player 1@|: " + _player1Id));
            System.out.println(ansi().render("@|yellow Player 2@|: " + _player2Id));
        }
    }

    /**
     * Sets GET and SET http endpoints and starts routing
     */
    public void startRouting() {
        /**
         * /serverdata GET endpoint
         * Reads X-Request-ID header from each request to get the player's ID,
         * which means that every request should contain this header with proper ID's
         * to let the server be able to validate them.
         */
        Spark.get("/serverdata", (req, res) -> {
            // Get client ID from X-Request-ID header
            int reqPlayer = Integer.parseInt(req.headers("X-Request-ID"));
            /**
             * A complicated turn-giver for players
             * It let's clients send their request if none of them is ahead of the other.
             * Sends "wait" for the playing that is ahead of the other.
             */
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

            // Creates and populates WorldInfo with real World's data
            WorldInfo worldInfo = new WorldInfo().populate(_world, reqPlayer);
            // Creates a JSON from the populated WorldInfo and sets it as Response Body
            res.body(new Gson().toJson(worldInfo));
            res.header("Content-type", "application/json");

            if (debug){
                System.out.println(ansi().render("@|cyan GET /serverdata@|: "));
                System.out.println(ansi().render("  @|yellow From|@: " + reqPlayer));
                System.out.println(ansi().render("  @|yellow P1 Turn|@: " + _player1Turn));
                System.out.println(ansi().render("  @|yellow P2 Turn|@: " + _player2Turn));
            }
            // Returns a JSON with proper WorldInfo
            return res.body();
        });

        Spark.get("/getid", (req, res) -> {
            // Sets the Response type as text/plain
            res.header("Content-type", "text/plain");

            /**
             * If there's a empty player space available, send a player id.
             * Else, return "null" as the response
             * TODO: Player numbers must not be hard-coded
              */
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

            if (debug) {
                System.out.println(ansi().render("@|cyan GET /getid:|@ "));
                System.out.println(ansi().render("  @|yellow Sent ID:|@ " + res.body()));
                System.out.println(ansi().render("  @|yellow Total Players:|@ " + _maxPlayers));
                System.out.println(ansi().render("  @|yellow Current Players|@: " + _connectedPlayers));
                System.out.print(ansi().render("  @|yellow More Players Allowed?|@ "));
                if (_connectedPlayers >= _maxPlayers)
                    System.out.println(ansi().render("@|red NO|@"));
                else
                    System.out.println(ansi().render("@|green YES|@"));
            }

            return res.body();
        });

        Spark.post("/clientdata", (req, res) -> {
            /**
             * Parses Request Body JSON (which should contain an Array of all movements)
             * Parses X-Request-ID header (which contains player ID)
             * Calls moveArmy() method of World class to initiate the movements
             * Returns "OK" if it went fine.
             * TODO: Should check for errors in moveArmy() and prevent sending "OK" if it went wrong.
             */
            ArmyMovement[] clientArmyMovement = new Gson().fromJson(req.body(), ArmyMovement[].class);
            int playerId = Integer.parseInt(req.headers("X-Request-ID"));
            _world.moveArmy(clientArmyMovement, playerId);
            res.body("OK");

            if (debug) {
                System.out.println(ansi().render("@|cyan POST /clientdata:|@ "));
                System.out.println(ansi().render("  @|yellow From:|@ " + playerId));
            }
            return res.body();
        });
    }

}
