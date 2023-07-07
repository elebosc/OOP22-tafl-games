package taflgames.view.scenecontrollers.api;

import java.util.Optional;

import taflgames.common.Player;

/**
 * This interface extends a generic {@link BasicSceneController}
 * to add specific functions for a {@link taflgames.view.scenes.impl.GameOverScene}.
 */
public interface GameOverController extends BasicSceneController {
    /**
     * Sets the scene to a {@link taflgames.view.scenes.impl.UserRegistrationScene}.
     */
    void goToRegistrationScene();

    /**
     * Returns the winner player or an empty Optional if last match resulted in a Draw.
     * @return the winner {@link taflgames.common.Player} if there is a winner,
     * an empty Optional otherwise.
     */
    Optional<Player> getWinner();
}
