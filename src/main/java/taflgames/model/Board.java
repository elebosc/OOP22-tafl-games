package taflgames.model;

import taflgames.common.code.Position;
import taflgames.common.api.Vector;

import taflgames.common.Player;

/**
 * This interface defines a board.
 */
public interface Board {

    boolean isStartingPointValid(Position start, Player player);

    boolean isDestinationValid(Position start, Position dest, Player player);

    void makeMove(Position oldPos, Position newPos);

    Position getFurthestReachablePos(Vector direction);

    void moveByVector(Vector direction);

}
