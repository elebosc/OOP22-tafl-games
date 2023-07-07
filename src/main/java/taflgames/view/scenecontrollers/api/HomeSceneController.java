package taflgames.view.scenecontrollers.api;

/**
 * This interface extends a generic {@link BasicSceneController}
 * to add specific functions for a {@link taflgames.view.scenes.impl.HomeScene}.
 */
public interface HomeSceneController extends BasicSceneController {

    /**
     * Closes the application.
     */
    void close();

    /**
     * Moves to the High Score scene.
     */
    void goToHighScoreScene();

}
