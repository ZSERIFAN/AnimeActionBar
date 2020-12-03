package by.thm;

import by.thm.listener.PlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class Util {

    private static File dir = new File("plugins", "AnimeActionBar");

    public static void confSetup() {
        if (!dir.exists()) {
            Util.log(Util.LogType.INFO, "Directory plugins/thmSkills was not found. Creating new file...");
            dir.mkdir();
        }
        if (!ActionBar.config.exists()) return;
        try {
            ActionBar.cfg.load(ActionBar.config);
            Util.log(Util.LogType.INFO, "File config.yml was successfully loaded from disk.");
        } catch (IOException e) {
            //
        } catch (InvalidConfigurationException e) {
            Util.log(Util.LogType.ERROR, "Couldn't load default config for plugin thmSkills.jar (INVALID_CONFIGURATION)");
        }
    }

    public static void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), ActionBar.getInstance());
    }

    public enum LogType {
        ERROR,
        INFO,
        WARNING
    }

    public static void log(LogType logType, String message) {
        switch (logType) {
            case ERROR:
                System.out.println("[AnimeActionBar] (ERROR | FATAL) - " + message);
                break;
            case INFO:
                System.out.println("[AnimeActionBar] (INFO) - " + message);
                break;
            case WARNING:
                System.out.println("[AnimeActionBar] (WARNING) - " + message);
                break;
        }
    }
}
