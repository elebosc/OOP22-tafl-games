package taflgames;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taflgames.common.Player;
import taflgames.controller.SettingsLoader;
import taflgames.controller.SettingsLoaderImpl;
import taflgames.model.BoardImpl;
import taflgames.model.CellsCollectionBuilder;
import taflgames.model.CellsCollectionBuilderImpl;
import taflgames.model.Match;
import taflgames.model.MatchImpl;
import taflgames.model.PiecesCollectionBuilder;
import taflgames.model.PiecesCollectionBuilderImpl;

/**
 * JUnit tests for {@link Match}.
 */
class TestMatch {

    private Match match;

    /**
     * Initializes each test before its execution.
     */
    @BeforeEach
    void init() {
        final SettingsLoader loader = new SettingsLoaderImpl();
        final CellsCollectionBuilder cellsCollBuilder = new CellsCollectionBuilderImpl();
        final PiecesCollectionBuilder piecesCollBuilder = new PiecesCollectionBuilderImpl();
        try {
            loader.loadClassicModeConfig(cellsCollBuilder, piecesCollBuilder);
            this.match = new MatchImpl(
                new BoardImpl(cellsCollBuilder.build(), piecesCollBuilder.build())
            );
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test the player turn queue.
     */
    @Test
    void testQueue() {
        final int turns = 4;
        final List<Player> resultingQueue = new LinkedList<>();
        for (int i = 0; i < turns; i++) {
            resultingQueue.add(match.getActivePlayer());
            match.setNextActivePlayer();
        }
        assertEquals(
            List.of(Player.ATTACKER, Player.DEFENDER, Player.ATTACKER, Player.DEFENDER), 
            resultingQueue
        );
    }

}
