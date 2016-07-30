/**
 * Created by KaTaNa on 7/29/2016.
 */

package org.twinity.PlanetWars;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class World {

    private Map _map;
    private Player _player1;
    private Player _player2;
    private int _turnNumber = 1;
    private ArrayList<ArmyMovement> _armyMovement;

    public World() throws FileNotFoundException {
        _armyMovement = new ArrayList<>();
        readMap();

        _player1 = new Player(Node.Side.P1, this);
        _player2 = new Player(Node.Side.P2, this);

        for (int i = 1; i <= _map.getTotalTurns(); i++) {
            _player1.getAI().doTurn();
            armyMovementValidator(_player1);
            // TODO: 7/30/2016  map should be updated
            _armyMovement.clear();
            _player2.getAI().doTurn();
            armyMovementValidator(_player1);
            // TODO: 7/30/2016  map should be updated
            _turnNumber++;
            _armyMovement.clear();
        }
    }

    private void readMap() {
        MapReader reader = new MapReader("C:\\Users\\KaTaNa\\IdeaProjects\\PlanetWars\\out\\production\\PlanetWars\\map.json");
        try {
            _map = reader.read();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void moveArmy(int inSource, int inDestination, int inArmyCount) {
        _armyMovement.add(new ArmyMovement(inSource, inDestination, inArmyCount));
    }

    public Map getMap() {
        return _map;
    }

    private void armyMovementValidator(Player inPlayer) {
        int k = 0, j, m = 0;
        for (int i = 0; i < _armyMovement.size(); i++) {
            for (j = 0; j < inPlayer.getWorld().getMap().getMyNodes(inPlayer).length; j++) {
                if (_armyMovement.get(i)._source == inPlayer.getWorld().getMap().getMyNodes(inPlayer)[j].getId()) {
                    k = 1;
                    break;
                }
            }
            if (k == 0)
                _armyMovement.remove(i);
            else {
                for (int l = 0; l < inPlayer.getWorld().getMap().getMyNodes(inPlayer)[j].getAdjacents().length; l++) {
                    if (_armyMovement.get(i)._destination == inPlayer.getWorld().getMap().getMyNodes(inPlayer)[j].getAdjacents()[l]) {
                        m = 1;
                        break;
                    }
                }
                if (m == 0)
                    _armyMovement.remove(i);
                else{
                    if (_armyMovement.get(i)._armyCount < 0)
                        _armyMovement.get(i)._armyCount = 0;
                    else if (_armyMovement.get(i)._armyCount > inPlayer.getWorld().getMap().getMyNodes(inPlayer)[j].getArmyCount())
                        _armyMovement.get(i)._armyCount = inPlayer.getWorld().getMap().getMyNodes(inPlayer)[j].getArmyCount();
                }
            }

        }
    }
}
