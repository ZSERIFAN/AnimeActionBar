package by.thm.runnables;

import by.thm.ActionBar;
import by.thm.Direction;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class ActionBarRunnable implements Runnable {

    private BukkitTask task;

    private int k = 0;

    public ActionBarRunnable() {
        task = Bukkit.getScheduler().runTaskTimer(ActionBar.getInstance(), this, 0L, ActionBar.cfg.getLong("runnable-ticks"));
    }

    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.isSprinting()) {
                String init = "|||Sprint|||";
                String stamina = "";
                String ending = "";
                int remainingFood = 20 - p.getFoodLevel();
                if (remainingFood >= init.length()) {
                    if (this.k >= 0 && this.k < 10) {
                        stamina = stamina + "&4[&c|||Sprint|||";
                        this.k++;
                    }
                    else if (this.k >= 10) {
                        stamina = stamina + "&4[&8|||Sprint|||";
                        this.k++;
                        if (this.k >= 20) this.k = 0;
                    }
                    ending = "&4]";
                } else if (remainingFood < init.length() && remainingFood >= 6) {
                    int i;
                    stamina = stamina + "&6[";
                    for (i = 0; i < init.length() - remainingFood; i++)
                        stamina = stamina + "&e" + init.substring(i, i+1);
                    for (; i < init.length(); i++)
                        stamina = stamina + "&8" + init.substring(i, i+1);
                    ending = "&6]";
                } else if (remainingFood < 6) {
                    int i;
                    stamina = stamina + "&2[";
                    for (i = 0; i < init.length() - remainingFood; i++)
                        stamina = stamina + "&a" + init.substring(i, i+1);
                    for (; i < init.length(); i++)
                        stamina = stamina + "&8" + init.substring(i, i+1);
                    ending = "&2]";
                }
                stamina = stamina + ending;
                stamina = ChatColor.translateAlternateColorCodes('&', stamina);
                String message = ChatColor.translateAlternateColorCodes('&', ActionBar.cfg.getString("actionbar-sprint")).replace("%amount%", String.valueOf((int) p.getHealth())).replace("%total%", String.valueOf((int) p.getMaxHealth())).replace("%stamina%", stamina);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
            }
            else {
                String message = ChatColor.translateAlternateColorCodes('&', ActionBar.cfg.getString("actionbar-direction")).replace("%amount%", String.valueOf((int) p.getHealth())).replace("%total%", String.valueOf((int) p.getMaxHealth())).replace("%locationX%", String.valueOf((int) p.getLocation().getX())).replace("%direction%", Direction.toText(Direction.yawToFace(p.getLocation().getYaw(), true))).replace("%locationZ%", String.valueOf((int) p.getLocation().getZ()));
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
            }
        }
    }
}
