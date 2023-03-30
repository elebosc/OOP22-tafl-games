package taflgames.model.cell.api;
import java.util.*;

import taflgames.common.Player;
import taflgames.common.code.Position;
import taflgames.model.memento.api.CellMemento;
import taflgames.model.pieces.api.Piece;

public interface Cell {
    
    /**
     * Notifies the cell of the movement of a piece.
     * @param source the Position in which the piece moved.
     * @param sender the Piece that was moved.
     * @param events the List of String containing "(name of piece)_MOVE" 
     * that notify the type of move of the piece.
     * @param pieces the Map that associate to each Player it's own map of Piece and Position.
     * @param cells the Map of Position and Cell that that associate
     * to each Position of the Board the type of Cell that is placed there.
     */
    void notify(Position source, Piece sender, List<String> events, Map<Player, Map<Position, Piece>> pieces, Map<Position, Cell> cells);

    /**
     * Verify if a Piece is allowed to move on the cell.
     * @param piece the Piece to verify.
     * @return true if the Piece can move on the cell, false otherwise.
     */
    boolean canAccept(Piece piece);

    /**
     * Set the status of the cell to free or occupied.
     * @param isFree true if the cell is free, false otherwise.
     */
    void setFree(boolean isFree);

    /**
     * Return the type of the cell.
     * @return a String that rapresent the type of the cell.
     */
    String getType();

    /**
     * Check if a cell is free or not.
     * @return true if it's free, false otherwise.
     */
    boolean isFree();

    CellMemento save();

}
