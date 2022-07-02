package harry1725.com.test01.commands;

import harry1725.com.test01.test;
import harry1725.com.test01.items.HeheItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
                        if (args[1].equalsIgnoreCase("config")) {
                            player.sendMessage(ChatColor.AQUA + "/hehe config" + ChatColor.WHITE + " 사용 방법 :");
                            player.sendMessage(ChatColor.GREEN + "/hehe config <String>" + ChatColor.WHITE + " : config.yml 파일에 있는 String 값을 읽어 출력합니다.");
                        } else if (args[1].equalsIgnoreCase("tp")) {
                            player.sendMessage(ChatColor.AQUA + "/hehe tp" + ChatColor.WHITE + " 사용 방법 :");
                            player.sendMessage(ChatColor.GREEN + "/hehe tp save" + ChatColor.WHITE + "플레이어의 현재 좌표를 저장합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe tp load" + ChatColor.WHITE + "플레이어가 저장한 좌표로 순간이동합니다.");
                        } else {
                            player.sendMessage("");
                            player.sendMessage(ChatColor.GRAY + "=====================================================");
                            player.sendMessage("");
                            player.sendMessage(ChatColor.AQUA + thehe.getFullName() + ChatColor.WHITE + " 플러그인에 포함된 명령어입니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe help" + ChatColor.WHITE + " : 이 플러그인의 도움말을 출력합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe info" + ChatColor.WHITE + " : 이 플러그인의 정보를 출력합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe random" + ChatColor.WHITE + " : 1부터 100 사이의 수 중 무작위 숫자 하나를 출력합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe bandage" + ChatColor.WHITE + " : 사용 시 배고픔을 2칸 소모하여 캐릭터의 체력을 4칸 회복하는 붕대 아이템을 지급합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe config <String>" + ChatColor.WHITE + " : config.yml 파일에 있는 String 값을 읽어 출력합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe tp <save/load>" + ChatColor.WHITE + " : 플레이어의 현재 좌표를 저장하거나 저장한 좌표로 순간이동합니다.");
                            player.sendMessage("");
                            player.sendMessage(ChatColor.GRAY + "=====================================================");
                            player.sendMessage("");
                        }
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
                    } else if (args[0].equalsIgnoreCase("config")) {
                        if (args.length <= 1) {
                            player.sendMessage(ChatColor.RED + "입력하신 인자값이 너무 적거나 없습니다. 아래의 목록에서 config에 등록된 이름을 찾을 수 있습니다.");
                            player.sendMessage(ChatColor.RED + "config.yml에 있는 내용: " + ChatColor.YELLOW + "first, second");
                        } else {
                            if (args[1].equalsIgnoreCase("first")) {
                                player.sendMessage(ChatColor.AQUA + "config.yml의 first 메세지입니다.");
                                player.sendMessage(ChatColor.WHITE + "first : " + ChatColor.GRAY + Bukkit.spigot().getConfig().getString("first"));
                            } else if (args[1].equalsIgnoreCase("second")) {
                                player.sendMessage(ChatColor.AQUA + "config.yml의 second 메세지입니다.");
                                player.sendMessage(ChatColor.WHITE + "second : " + ChatColor.GRAY + Bukkit.spigot().getConfig().getString("second"));
                            } else {
                                player.sendMessage(ChatColor.RED + "/hehe config에 등록되어 있지 않은 명령어입니다. " + ChatColor.YELLOW + "/hehe help config" + ChatColor.RED + "를 통해 명령어 사용 방법을 확인하세요.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("tp")) {
                        if (args.length <= 1) {
                            player.sendMessage(ChatColor.RED + "입력하신 인자값이 너무 적거나 없습니다. " + ChatColor.YELLOW + "/hehe help tp" + ChatColor.YELLOW + "를 통해 명령어 사용 방법을 확인하세요.");
                        } else {
                            if (args[1].equalsIgnoreCase("save")) {
                                Location location = player.getLocation();

                                thehe.getConfig().set(player.getName() + ".position.x", location.getBlockX());
                                thehe.getConfig().set(player.getName() + ".position.y", location.getBlockY());
                                thehe.getConfig().set(player.getName() + ".position.y", location.getBlockZ());
                                thehe.getConfig().set(player.getName() + ".position.pitch", location.getPitch());
                                thehe.getConfig().set(player.getName() + ".position.yaw", location.getYaw());
                                thehe.saveConfig();

                                player.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "의 현재 좌표가 저장되었습니다.");
                            } else if (args[1].equalsIgnoreCase("load")) {
                                if (!thehe.getConfig().isSet(player.getName() + ".position.x") ||
                                    !thehe.getConfig().isSet(player.getName() + ".position.y") ||
                                    !thehe.getConfig().isSet(player.getName() + ".position.z") ||
                                    !thehe.getConfig().isSet(player.getName() + ".position.pitch") ||
                                    !thehe.getConfig().isSet(player.getName() + ".position.yaw")) {
                                    player.sendMessage(ChatColor.RED + "저장된 좌표가 없습니다! " + ChatColor.YELLOW + "/hehe tp save" + ChatColor.RED + " 명령어를 통해 좌표를 먼저 지정해 주세요.");
                                } else {
                                    double x = thehe.getConfig().getDouble(player.getName() + ".position.x");
                                    double y = thehe.getConfig().getDouble(player.getName() + ".position.y");
                                    double z = thehe.getConfig().getDouble(player.getName() + ".position.z");
                                    float pitch = (float) thehe.getConfig().get(player.getName() + ".position.pitch");
                                    float yaw = (float) thehe.getConfig().get(player.getName() + ".position.yaw");

                                    player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "/hehe tp에 등록되어 있지 않은 명령어입니다. " + ChatColor.YELLOW + "/hehe help tp" + ChatColor.RED + "를 통해 명령어 사용 방법을 확인하세요.");
                            }
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "hehe에 등록되어 있지 않은 명령어입니다. " + ChatColor.YELLOW + "/hehe help" + ChatColor.RED + "를 통해 명령어 목록을 확인하세요.");
                    }
                }
            }
        }
        
        return false;
    }
}
