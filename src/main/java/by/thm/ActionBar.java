package by.thm;

import by.thm.runnables.ActionBarRunnable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class ActionBar extends JavaPlugin {

    private static ActionBar instance;
    public static ActionBar getInstance() {
        return instance;
    }

    public static File config = new File("plugins/AnimeActionBar/config.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(config);

    public void onEnable() {
        getLogger().log(Level.INFO, "Attempting to enable ActionBar modules.");
        instance = this;
        Util.confSetup();
        Util.registerEvents();
        saveDefaultConfig();
        new ActionBarRunnable();
        getLogger().log(Level.INFO, "Plugin has been successfully enabled.");
    }

    public void onDisable() {
        getLogger().log(Level.INFO, "Disabling plugin.");
    }
}
