package headhunters.headhunters.handlers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.Map;

public class DeathHandler implements Listener {
    private Map inventories = new HashMap();

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        ItemStack[] inventoryContents = player.getInventory().getContents();
        Player killer = event.getEntity().getKiller();
        if (killer != null) { // if the player gets killed by another player
            for (int i = 0; i < inventoryContents.length; i++) { // go through each item in the players inventory
                ItemStack item = inventoryContents[i];
                if (item != null){ // if the item exists
                    if (item.getType() == Material.PLAYER_HEAD) { // if the item is a player head add it to the killers inventory
                        killer.getInventory().addItem(item);
                    }
                }
                inventoryContents[i] = null;
            }
            inventories.put(player.getName(), inventoryContents);
        } else { // if player dies to natural causes
            for (int i = 0; i < inventoryContents.length; i++) { // go through each item in the players inventory
                ItemStack item = inventoryContents[i];
                if (item != null) { // if the item exists
                    if (item.getType() != Material.PLAYER_HEAD) { // if the item is not a player head remove it from the inventory
                        inventoryContents[i] = null;
                    }
                }
            }
            inventories.put(player.getName(), inventoryContents);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        ItemStack[] items = (ItemStack[]) inventories.get(player.getName());
        player.getInventory().setContents(items);
    }
}
