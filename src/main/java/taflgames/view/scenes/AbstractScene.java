package taflgames.view.scenes;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Optional;

/**
 * This abstract class defines the abstract model of a {@link Scene}.
 */
public abstract class AbstractScene implements Scene {

    private final String sceneName;
    private final JPanel scene;
    private static final String FONT_NAME = "Serif";
    private static final String SEP = System.getProperty("file.separator");
    private static final String ROOT = "taflgames" + SEP;

    /**
     * Initializes the scene state.
     * @param sceneName the name of the scene
     * @param bgFileName the name of the file of the background image
     */
    protected AbstractScene(final String sceneName, final Optional<String> bgFileName) {
        this.sceneName = sceneName;
        this.scene = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgFileName.isPresent()) {
                    final Image image = Toolkit.getDefaultToolkit().getImage(ROOT + "images" + SEP + bgFileName.get());
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
    }

    @Override
    public final String getSceneName() {
        return this.sceneName;
    }

    @Override
    public final JPanel getScene() {
        return this.scene;
    }

    public static String getFont() {
        return AbstractScene.FONT_NAME;
    }

}
