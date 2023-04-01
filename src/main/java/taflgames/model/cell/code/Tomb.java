package taflgames.model.cell.code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import taflgames.common.code.Position;
import taflgames.model.cell.api.Cell;
import taflgames.model.cell.api.CellComponent;
import taflgames.model.memento.api.CellMemento;
import taflgames.model.pieces.api.Piece;
import taflgames.common.Player;


public final class Tomb extends AbstractCell implements CellComponent {

    private Map<Player, Queue<Piece>> deadPieces = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void notify(
        final Position source,
        final Piece sender,
        final List<String> events,
        final Map<Player, Map<Position, Piece>> pieces,
        final Map<Position, Cell> cells
    ) {
        // Per ora considero event come una stringa
        if (events.contains("QUEEN_MOVE")) {
            // viene resuscitata una pedina del giocatore mangiata sulla casella corrente (se esiste)
            resumePiece(sender.getPlayer(), pieces, cells);
        }
        if (events.contains("DEAD_PIECE")) {
            addDeadPieces(sender.getPlayer(), sender);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canAccept(final Piece piece) {
        return super.isFree();
    }

    private void resumePiece(
        final Player player,
        final Map<Player, Map<Position, Piece>> pieces,
        final Map<Position, Cell> cells
    ) {
        // Se sulla tomba ci sono pedine mangiate del giocatore corrente
        if (this.deadPieces.get(player) != null && !deadPieces.get(player).isEmpty()) {
            final Piece pieceToResume = deadPieces.get(player).poll();    // prende la prima pedina in coda
            pieceToResume.reanimate();	// ora è viva
            cells.get(pieceToResume.getCurrentPosition()).setFree(false);
            pieces.get(player).put(pieceToResume.getCurrentPosition(), pieceToResume);
        }
    }

    public void addDeadPieces(final Player player, final Piece piece) {
        if (!deadPieces.containsKey(player)) {
            final Queue<Piece> list = new LinkedList<>();
            list.add(piece);
            deadPieces.put(player, list);
        } else {
            deadPieces.get(player).add(piece);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "Tomb";
    }

    @Override
    public CellMemento save() {
        return this.new TombMementoImpl();
    }

    public void restore(final TombMementoImpl tm) {
        this.deadPieces = tm.getInnerDeadPieces();
        super.restore(tm);
    }

    public final class TombMementoImpl implements CellMemento {
        private final Map<Player, Queue<Piece>> innerDeadPieces;
        private final boolean isFree;

        public TombMementoImpl() {
            /* This way of copying maps should create a deep copy. */
            this.innerDeadPieces = Tomb.this.deadPieces.entrySet().stream()
                .map(entry -> {
                    /* This longer lambda creates a deep copy of the Queues, to
                     * ensure that modifications of the state of the match do not
                     * affect this snapshot of the queued dead pieces.
                     */
                    final Queue<Piece> queue = new LinkedList<>();
                    entry.getValue().stream().forEachOrdered(piece -> queue.add(piece));
                    return Map.entry(entry.getKey(), queue);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            this.isFree = Tomb.this.isFree();
        }

        @Override
        public void restore() {
            Tomb.this.restore(this);
        }

        @Override
        public boolean getCellStatus() {
            return this.isFree;
        }

        public Map<Player, Queue<Piece>> getInnerDeadPieces() {
            return this.innerDeadPieces;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyComponent(
        final Position source,
        final Piece sender,
        final List<String> events,
        final Map<Player, Map<Position, Piece>> pieces,
        final Map<Position, Cell> cells
    ) {
        this.notify(source, sender, events, pieces, cells);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        if (this.deadPieces.isEmpty()) {
            return false;
        }
        /* If there's at least one of the
         * queues that is not empty, this Tomb is still
         * active.
         */
        return this.deadPieces.values().stream()
                .filter(queue -> !queue.isEmpty())
                .findAny()
                .isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getComponentType() {
        return this.getType();
    }
}
