package harry1725.com.maven01;

import harry1725.com.maven01.commands.HeheCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class test extends JavaPlugin {
    PluginDescriptionFile pdfFile = this.getDescription();

    String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

    public PluginDescriptionFile getPdfFile() {
        return pdfFile;
    }

    public String getFullName() {
        return pfName;
    }

    @Override
    public void onEnable() {
        HeheCommand cmd_hehe = new HeheCommand(this, "hehe");

        Objects.requireNonNull(getCommand(cmd_hehe.getLabel())).setExecutor(cmd_hehe);
        Objects.requireNonNull(getCommand(cmd_hehe.getLabel())).setTabCompleter(cmd_hehe);

        test.console(ChatColor.YELLOW + pfName + ChatColor.WHITE + " is now enabled!");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        test.console(ChatColor.YELLOW + pfName + ChatColor.WHITE + " is now disabled!");
        super.onDisable();
    }

    public static void console(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
