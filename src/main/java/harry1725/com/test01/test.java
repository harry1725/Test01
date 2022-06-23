package harry1725.com.test01;

import harry1725.com.test01.commands.HeheCommands;
import harry1725.com.test01.items.HeheItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class test extends JavaPlugin implements Listener {
    PluginDescriptionFile pdfFile = this.getDescription();
    PluginManager pManager = Bukkit.getPluginManager();

    String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

    public PluginDescriptionFile getPdfFile() {
        return pdfFile;
    }

    public String getFullName() {
        return pfName;
    }

    public static void console(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    @Override
    public void onEnable() {
        HeheCommands cmd_hehe = new HeheCommands(this, "hehe");

        Objects.requireNonNull(getCommand(cmd_hehe.getLabel())).setExecutor(cmd_hehe);
        Objects.requireNonNull(getCommand(cmd_hehe.getLabel())).setTabCompleter(cmd_hehe);

        pManager.registerEvents(this, this);

        test.console(ChatColor.YELLOW + pfName + ChatColor.WHITE + " is now enabled!");

        super.onEnable();
    }

    @Override
    public void onDisable() {
        test.console(ChatColor.YELLOW + pfName + ChatColor.WHITE + " is now disabled!");
        super.onDisable();
    }

    @EventHandler
    public void onPlayerEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        Action action = event.getAction();
        ItemStack bandage = HeheItems.getBandageItemStack();

        int foodLevel = player.getFoodLevel();
        double health = player.getHealth();

        if(action.equals(Action.RIGHT_CLICK_AIR) && inventory.getItemInMainHand().equals(bandage)) {
            if (foodLevel >= 0 && foodLevel <= 3) {
                player.sendMessage(ChatColor.RED + "붕대를 사용하기 위한 배고픔이 부족합니다. 배고픔을 2칸 이상으로 채운 뒤 다시 사용해 주세요!");
            } else if (foodLevel >= 4) {
                player.setFoodLevel(foodLevel - 4);
                player.setHealth(health + 8.0);
                player.sendMessage(ChatColor.GREEN + "체력을 회복하였습니다.");
            }
        }
    }
}
