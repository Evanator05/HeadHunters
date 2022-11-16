package headhunters.headhunters.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;

import static headhunters.headhunters.HeadHunters.GetInstance;

public class SetBounties implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("This command requires operator privileges");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("Must enter a mode");
            return true;
        }

        switch (args[0]) {
            case ("set"):
                setBounties();
                break;
            case ("unset"): // remove all players bounties (requires players are online)
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.getPersistentDataContainer().set(new NamespacedKey(GetInstance(), "target"), PersistentDataType.STRING, "null");
                    player.getPersistentDataContainer().set(new NamespacedKey(GetInstance(), "hunter"), PersistentDataType.STRING, "null");
                }
                break;
            default:
                sender.sendMessage("Please enter a valid argument");
                break;
        }
        return true;
    }

    private void setBounties() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getPersistentDataContainer().get(new NamespacedKey(GetInstance(), "target"), PersistentDataType.STRING) == "null") {
                for (Player player2 : Bukkit.getOnlinePlayers()) {
                    if (player2.getPersistentDataContainer().get(new NamespacedKey(GetInstance(), "hunter"), PersistentDataType.STRING) == "null") {
                        player.getPersistentDataContainer().set(new NamespacedKey(GetInstance(), "target"), PersistentDataType.STRING, player2.getUniqueId().toString());
                        player2.getPersistentDataContainer().set(new NamespacedKey(GetInstance(), "hunter"), PersistentDataType.STRING, player.getUniqueId().toString());
                    }
                }
            }
        }
    } // end of setBounties
}