package taflgames.view.scenecontrollers.api;

import java.io.InputStream;

/**
 * This interface extends a generic {@link BasicSceneController}
 * to add specific functions for a {@link taflgames.view.scenes.impl.RulesScene}.
 */
public interface RulesSceneController extends BasicSceneController {

    /**
     * @return the input stream from which the rules document is read
     */
    InputStream getRulesFileStream();

}
