package headhunters.headhunters.commands;

import headhunters.headhunters.HeadHunters;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StartHeadHunt implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            ItemStack head = HeadHunters.createHead(player);
            player.getInventory().addItem(head);
        }
        return true;
    }

}
