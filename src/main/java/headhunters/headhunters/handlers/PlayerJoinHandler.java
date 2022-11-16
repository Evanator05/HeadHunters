package headhunters.headhunters.handlers;

import headhunters.headhunters.HeadHunters;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerJoinHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.setWhitelisted(true);
        PersistentDataContainer data = p.getPersistentDataContainer();
        if (!data.has(new NamespacedKey(HeadHunters.GetInstance(), "target"), PersistentDataType.STRING)) {
            data.set(new NamespacedKey(HeadHunters.GetInstance(), "target"), PersistentDataType.STRING, "null");
        }
        if (!data.has(new NamespacedKey(HeadHunters.GetInstance(), "hunter"), PersistentDataType.STRING)) {
            data.set(new NamespacedKey(HeadHunters.GetInstance(), "hunter"), PersistentDataType.STRING, "null");
        }

    }
}
