package headhunters.headhunters.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

import static headhunters.headhunters.HeadHunters.GetInstance;

public class GetBounty implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("This command requires operator privileges");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be a player to use this command");
            return true;
        }

        Player player = (Player) sender;

        String struuid = player.getPersistentDataContainer().get(new NamespacedKey(GetInstance(), "target"), PersistentDataType.STRING);
        UUID uuid = UUID.fromString(struuid);
        String playerName = Bukkit.getPlayer(uuid).getName();
        sender.sendMessage("Your target is: " + playerName);
        return true;
    }

}
