package headhunters.headhunters.commands;

import headhunters.headhunters.HeadHunters;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveHead implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("This command requires operator privileges");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("Must enter a name");
            return true;
        }

        Player player = Bukkit.getServer().getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage("Please enter a valid player name");
            return true;
        }

        giveHead(player);
        sender.sendMessage("Gave " + player.getName() + " their head.");
        return true;
    }

    public void giveHead(Player player) {
        ItemStack head = HeadHunters.createHead(player);
        player.getInventory().addItem(head);
    }

}
