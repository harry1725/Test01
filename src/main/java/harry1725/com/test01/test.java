package harry1725.com.test01;

import harry1725.com.test01.commands.HeheCommands;
import harry1725.com.test01.events.HeheEvents;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class test extends JavaPlugin implements Listener {
    public final Logger logger = Logger.getLogger("Minecraft");
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

    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    @Override
    public void onEnable() {
        HeheCommands cmd_hehe = new HeheCommands(this, "hehe");
        HeheCommands cmd_test = new HeheCommands(this, "test");

        Objects.requireNonNull(getCommand(cmd_hehe.getLabel())).setExecutor(cmd_hehe);
        Objects.requireNonNull(getCommand(cmd_hehe.getLabel())).setTabCompleter(cmd_hehe);
        console(ChatColor.WHITE + "Commands " + ChatColor.YELLOW + "/hehe" + ChatColor.WHITE + " and " + ChatColor.YELLOW + "/hh" + ChatColor.WHITE + " have been added!");

        Objects.requireNonNull(getCommand(cmd_test.getLabel())).setExecutor(cmd_test);
        Objects.requireNonNull(getCommand(cmd_test.getLabel())).setTabCompleter(cmd_test);
        console(ChatColor.WHITE + "Command " + ChatColor.YELLOW + "/cs" + ChatColor.WHITE + " has been added!");

        pManager.registerEvents(new HeheEvents(this), this);
        console(ChatColor.WHITE + "HeheEvents" + ChatColor.WHITE + " have been added!");

        NamespacedKey key = new NamespacedKey(this, NamespacedKey.MINECRAFT);
        ShapedRecipe saddle = new ShapedRecipe(key, new ItemStack(Material.SADDLE, 1))
                .shape("!!!", "!@!", "@ @").
                setIngredient('!', Material.LEATHER).setIngredient('@', Material.IRON_INGOT);
        ShapelessRecipe chest = new ShapelessRecipe(key, new ItemStack(Material.OAK_PLANKS, 8))
                .addIngredient(Material.CHEST);
        FurnaceRecipe diamond = new FurnaceRecipe(key, new ItemStack(Material.DIAMOND, 1), Material.COAL, 8, 10);

        Bukkit.addRecipe(saddle);
        Bukkit.addRecipe(chest);
        Bukkit.addRecipe(diamond);

        getConfig().options().copyDefaults(true);
        saveConfig();

        test.console(ChatColor.YELLOW + pfName + ChatColor.WHITE + " is now enabled!");

        super.onEnable();
    }

    @Override
    public void onDisable() {
        saveConfig();

        test.console(ChatColor.YELLOW + pfName + ChatColor.WHITE + " is now disabled!");
        super.onDisable();
    }
}
