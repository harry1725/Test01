package harry1725.com.test01.commands;

import harry1725.com.test01.test;
import harry1725.com.test01.items.HeheItems;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HeheCommands extends AbstractCommand {
    public HeheCommands(test plugin, String commandLabel) {
        super(plugin, commandLabel);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();

        if (args.length == 1) {
            tabs.add("help");
            tabs.add("info");
            tabs.add("random");
            tabs.add("bandage");
        }

        return tabs;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (label.equalsIgnoreCase("hehe")) {
                if (args.length <= 0) {
                    player.sendMessage(ChatColor.RED + "명령어의 인자가 너무 적거나 없습니다! " + ChatColor.YELLOW + "/hehe help" + ChatColor.RED + " 명령어를 통해 도움말을 확인할 수 있습니다.");
                } else {
                    if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GRAY + "=====================================================");
                        player.sendMessage("");
                        player.sendMessage(ChatColor.AQUA + thehe.getFullName() + ChatColor.WHITE + " 플러그인에 포함된 명령어입니다.");
                        player.sendMessage(ChatColor.GREEN + "/hehe help" + ChatColor.WHITE + " : 이 플러그인의 도움말을 출력합니다.");
                        player.sendMessage(ChatColor.GREEN + "/hehe info" + ChatColor.WHITE + " : 이 플러그인의 정보를 출력합니다.");
                        player.sendMessage(ChatColor.GREEN + "/hehe random" + ChatColor.WHITE + " : 1부터 100 사이의 수 중 무작위 숫자 하나를 출력합니다.");
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GRAY + "=====================================================");
                        player.sendMessage("");
                    } else if (args[0].equalsIgnoreCase("info")) {
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GRAY + "=====================================================");
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GOLD + " - 플러그인 이름" + ChatColor.WHITE + " : " + thehe.getPdfFile().getName());
                        player.sendMessage(ChatColor.GOLD + " - 플러그인 버전" + ChatColor.WHITE + " : " + thehe.getPdfFile().getVersion());
                        player.sendMessage(ChatColor.GOLD + " - 플러그인 만든 놈" + ChatColor.WHITE + " : " + thehe.getPdfFile().getAuthors());
                        player.sendMessage(ChatColor.GOLD + " - 플러그인 메인 클래스" + ChatColor.WHITE + " : " + thehe.getPdfFile().getMain());
                        player.sendMessage(ChatColor.GOLD + " - 설명" + ChatColor.WHITE + " : " + thehe.getPdfFile().getDescription());
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GRAY + "=====================================================");
                        player.sendMessage("");
                    } else if (args[0].equalsIgnoreCase("random")) {
                        int random = (int)(Math.random() * 100 + 1);

                        player.sendMessage(ChatColor.WHITE + "플러그인이 무작위로 생성한 숫자는 " + ChatColor.AQUA + random + ChatColor.WHITE + " 입니다!");
                    } else if (args[0].equalsIgnoreCase("bandage")) {
                        ItemStack bandage = HeheItems.getBandageItemStack();

                        player.getInventory().addItem(bandage);
                        player.sendMessage(ChatColor.RED + "붕대" + ChatColor.WHITE + " 아이템이 지급되었습니다.");
                    }
                }
            }
        }
        
        return false;
    }
}
