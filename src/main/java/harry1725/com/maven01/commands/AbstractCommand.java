package harry1725.com.maven01.commands;

import harry1725.com.maven01.test;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public abstract class AbstractCommand implements TabExecutor {
    protected test thehe;
    private final String label;

    public AbstractCommand(test plugin, String commandLabel) {
        this.thehe = plugin;
        this.label = commandLabel;
    }

    public String getLabel() {
        return label;
    }

    public abstract List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args);
    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
