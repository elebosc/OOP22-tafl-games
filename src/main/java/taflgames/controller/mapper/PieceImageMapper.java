package taflgames.controller.mapper;

import taflgames.controller.entitystate.PieceState;
import taflgames.view.imageinfo.impl.PieceImageInfo;

/**
 * An object that maps Pieces types to their corrisponding images.
 */
public interface PieceImageMapper {

    /**
     * Maps a {@link taflgames.controller.entitystate.PieceState} to its
     * corresponding {@link taflgames.view.imageinfo.impl.PieceImageInfo}.
     * @param state the state of the piece that has to be drawn.
     * @return a PieceImageInfo that corresponds to this Piece's state.
     */
    PieceImageInfo mapToImage(PieceState state);

}
