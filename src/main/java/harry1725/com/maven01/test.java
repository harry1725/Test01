package harry1725.com.maven01;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class test extends JavaPlugin {
    PluginDescriptionFile pdfFile = this.getDescription();

    String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

    @Override
    public void onEnable() {
        test.console(ChatColor.YELLOW + pfName + ChatColor.WHITE + " 이(가) 활성화되었습니다!");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        test.console(ChatColor.YELLOW + pfName + ChatColor.WHITE + " 이(가) 비활성화되었습니다!");
        super.onDisable();
    }

    public static void console(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
