package taflgames;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import java.util.*;
import taflgames.common.code.Position;
import taflgames.model.board.api.Board;
import taflgames.model.board.code.BoardImpl;
import taflgames.model.pieces.api.Piece;
import taflgames.model.pieces.code.BasicPiece;
import taflgames.common.Player;
import taflgames.model.cell.api.Cell;
import taflgames.model.cell.code.ClassicCell;

public class TestBoard {

    private static Board board;
    private static Map<Player, Map<Position, Piece>> pieces = new HashMap<>();
    private static Map<Position, Cell> cells = new HashMap<>();
    

    @BeforeAll
	static void init() {
        Player p1 = Player.ATTACKER;
        Player p2 = Player.DEFENDER;
        Map<Position, Piece> piecesPlayer1 = new HashMap<>();
        piecesPlayer1.put(new Position(0, 0), new BasicPiece(new Position(0, 0), p1));
        pieces.put(p1, piecesPlayer1);
        pieces.put(p2, null);
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                cells.put(new Position(i,j), new ClassicCell());
            }
        }
		board = new BoardImpl(pieces, cells, 5);
	}
    
    @Test
    void testIsStartingPointValid(){
        
        
    }
}
