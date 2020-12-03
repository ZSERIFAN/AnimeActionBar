package by.thm.listener;

import by.thm.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class PlayerMoveListener implements Listener {

    private int maxSaturation = 20;

    private int curSaturation = maxSaturation;

    private int decayTask;
    private int regenTask;

    @EventHandler
    public void onToggleSprintEvent(PlayerToggleSprintEvent e) {
        Player player = e.getPlayer();
        if (e.isSprinting()) {
            cancelRegen();
            decayStamina(player);
        } else {
            cancelDecay();
            regenStamina(player);
        }
    }

    @EventHandler
    public void onFoodConsume(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    private void decayStamina(final Player player) {
        this.decayTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ActionBar.getInstance(), new Runnable() {
            @Override
            public void run() {
                curSaturation -= 1;
                if (curSaturation <= 6) {
                    curSaturation = 1;
                    player.setFoodLevel(curSaturation);
                } else
                    player.setFoodLevel(curSaturation);
            }
        }, 20L, 20L);
    }

    private void cancelDecay() {
        Bukkit.getServer().getScheduler().cancelTask(this.decayTask);
    }

    private void regenStamina(final Player player) {
        this.regenTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ActionBar.getInstance(), new Runnable() {
            @Override
            public void run() {
                curSaturation += 1;
                if (curSaturation >= 20) {
                    curSaturation = 20;
                    player.setFoodLevel(curSaturation);
                } else
                    player.setFoodLevel(curSaturation);
            }
        }, 20L, 20L);
    }

    private void cancelRegen() {
        Bukkit.getServer().getScheduler().cancelTask(this.regenTask);
    }
}
