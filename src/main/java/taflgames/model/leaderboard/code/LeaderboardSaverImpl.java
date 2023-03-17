package taflgames.model.leaderboard.code;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import taflgames.model.leaderboard.api.Leaderboard;
import taflgames.model.leaderboard.api.LeaderboardSaver;

/**
 * This class allows to save {@link taflgames.model.leaderboard.api.Leaderboard} type objects
 * to .yaml files.
 */
public class LeaderboardSaverImpl implements LeaderboardSaver {

    private static final String SEP = System.getProperty("file.separator");
    private static final String PATH = System.getProperty("user.dir") + SEP + "tafl-games" + SEP + "src"
    + SEP + "main" + SEP + "resources" + SEP + "taflgames" + SEP + "leaderboardSave" + SEP;
    private static final String LEADERBOARD_SAVE_FILE_NAME = "leaderboard.yaml" + SEP;
    
    /**
     * Saves a {@link taflgames.model.leaderboard.api.Leaderboard} to a YAML file
     * @param leaderboard the leaderboard to be saved
     */
    @Override
    public void saveLeaderboard(Leaderboard leaderboard) {
        /* There's no reason for keeping old leaderboards,
         * so the FileWriter has 'false' as append parameter:
         * this way, old leaderboards will be overwritten.
        */
        try (FileWriter writer = new FileWriter(PATH + LEADERBOARD_SAVE_FILE_NAME, false)) {
            final Yaml yaml = new Yaml();
            yaml.dump(leaderboard.getLeaderboard(), writer);
        } catch (IOException e) {
            System.out.println("Error while trying to access the save file for the leaderboard.");
            e.printStackTrace();
        }
    }

    /**
     * Reads a {@link taflgames.model.leaderboard.api.Leaderboard} from a YAML file
     * @return the retrieved leaderboard
     */
    @Override
    public Leaderboard retrieveFromSave() {
        try (InputStream inputStream = new FileInputStream(PATH + LEADERBOARD_SAVE_FILE_NAME)) {
            Yaml yaml = new Yaml(new Constructor(LeaderBoardImpl.class, new LoaderOptions()));
            LeaderBoardImpl leaderboard = yaml.load(inputStream);
            return leaderboard;
        } catch (IOException e) {
            System.out.println("Error while trying to read from the save file for the leaderboard.");
            e.printStackTrace();
        }
        return null;
    }
    
}
