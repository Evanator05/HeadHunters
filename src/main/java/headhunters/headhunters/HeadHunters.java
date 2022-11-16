package headhunters.headhunters;

import headhunters.headhunters.commands.GetBounty;
import headhunters.headhunters.commands.GiveHead;
import headhunters.headhunters.commands.SetBounties;
import headhunters.headhunters.commands.StartHeadHunt;
import headhunters.headhunters.handlers.DeathHandler;
import headhunters.headhunters.handlers.PlayerJoinHandler;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeadHunters extends JavaPlugin {

    private static HeadHunters instance;
    public static HeadHunters GetInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Plugin startup logic
        this.getCommand("startHeadHunt").setExecutor(new StartHeadHunt());
        this.getCommand("giveHead").setExecutor(new GiveHead());
        this.getCommand("setBounties").setExecutor(new SetBounties());
        this.getCommand("getBounty").setExecutor(new GetBounty());

        this.getServer().getPluginManager().registerEvents(new PlayerJoinHandler(), this);
        this.getServer().getPluginManager().registerEvents(new DeathHandler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ItemStack createHead(Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwnerProfile(player.getPlayerProfile());
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        head.setItemMeta(meta);
        return head;
    }
}
