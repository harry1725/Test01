package harry1725.com.test01.commands;

import harry1725.com.test01.files.EnumTest;
import harry1725.com.test01.files.Permissions;
import harry1725.com.test01.test;
import harry1725.com.test01.items.HeheItems;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class HeheCommands extends AbstractCommand {
    public final Logger logger = Logger.getLogger("Minecraft");

    public HeheCommands(test plugin, String commandLabel) {
        super(plugin, commandLabel);
    }

    public void openInv(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "게임모드 변경");

        inv.setItem(10, HeheItems.getSurvivalIconItemStack());
        inv.setItem(12, HeheItems.getCreativeIconItemStack());
        inv.setItem(14, HeheItems.getAdventureIconItemStack());
        inv.setItem(16, HeheItems.getSpectatorIconItemStack());

        player.openInventory(inv);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabs = new ArrayList<>();

        if (args.length == 1) {
            tabs.add("help");
            tabs.add("info");
            tabs.add("random");
            tabs.add("bandage");
            tabs.add("config");
            tabs.add("tp");
            tabs.add("perm");
            tabs.add("inv");
            tabs.add("timer");
            tabs.add("cancel");
            tabs.add("time");
            tabs.add("entity");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("tp")) {
                tabs.add("save");
                tabs.add("load");
            } else if (args[0].equalsIgnoreCase("timer")) {
                tabs.add("bar");
            } else if (args[0].equalsIgnoreCase("time")) {
                tabs.add("6");
                tabs.add("12");
                tabs.add("18");
                tabs.add("24");
            } else if (args[0].equalsIgnoreCase("potion")) {
                tabs.add("nv");
                tabs.add("st");
            } else if (args[0].equalsIgnoreCase("entity")) {
                tabs.add("wolf");
                tabs.add("ocelot");
                tabs.add("villager");
                tabs.add("armor_stand");
            }
        }

        return tabs;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getName();
            Location location = player.getLocation();
            World world = player.getWorld();
            String worldName = player.getWorld().getName();

            if (label.equalsIgnoreCase("hehe") || label.equalsIgnoreCase("hh")) {
                if (args.length <= 0) {
                    player.sendMessage(ChatColor.RED + "명령어의 인자가 너무 적거나 없습니다! " + ChatColor.YELLOW + "/hehe help" + ChatColor.RED + " 명령어를 통해 도움말을 확인할 수 있습니다.");
                } else {
                    final int[] time = {0};

                    if (args[0].equalsIgnoreCase("help")) {
                        if (args.length >= 2) {
                            if (args[1].equalsIgnoreCase("config")) {
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GRAY + "=====================================================");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "초록색 명령어" + ChatColor.YELLOW + "는 권한이 필요없고, ");
                                player.sendMessage(ChatColor.GOLD + "주황색 명령어" + ChatColor.YELLOW + "는 권한이 필요합니다.");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.AQUA + "/hehe config" + ChatColor.WHITE + " 사용 방법 :");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "/hehe config <String>" + ChatColor.WHITE + " : config.yml 파일에 있는 String 값을 읽어 출력합니다.");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GRAY + "=====================================================");
                                player.sendMessage("");
                            } else if (args[1].equalsIgnoreCase("tp")) {
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GRAY + "=====================================================");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "초록색 명령어" + ChatColor.YELLOW + "는 권한이 필요없고, ");
                                player.sendMessage(ChatColor.GOLD + "주황색 명령어" + ChatColor.YELLOW + "는 권한이 필요합니다.");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.AQUA + "/hehe tp" + ChatColor.WHITE + " 사용 방법 :");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GOLD + "/hehe tp save" + ChatColor.WHITE + "플레이어의 현재 좌표를 저장합니다.");
                                player.sendMessage(ChatColor.GOLD + "/hehe tp load" + ChatColor.WHITE + "플레이어가 저장한 좌표로 순간이동합니다.");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GRAY + "=====================================================");
                                player.sendMessage("");
                            } else if (args[1].equalsIgnoreCase("time")) {
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GRAY + "=====================================================");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "초록색 명령어" + ChatColor.YELLOW + "는 권한이 필요없고, ");
                                player.sendMessage(ChatColor.GOLD + "주황색 명령어" + ChatColor.YELLOW + "는 권한이 필요합니다.");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.AQUA + "/hehe time" + ChatColor.WHITE + " 사용 방법 :");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GOLD + "/hehe time 6" + ChatColor.WHITE + "월드의 시간을 오전 6시로 변경합니다.");
                                player.sendMessage(ChatColor.GOLD + "/hehe time 12" + ChatColor.WHITE + "월드의 시간을 오후 12시로 변경합니다.");
                                player.sendMessage(ChatColor.GOLD + "/hehe time 18" + ChatColor.WHITE + "월드의 시간을 오후 6시로 변경합니다.");
                                player.sendMessage(ChatColor.GOLD + "/hehe time 24" + ChatColor.WHITE + "월드의 시간을 오전 12시로 변경합니다.");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GRAY + "=====================================================");
                                player.sendMessage("");
                            } else if (args[1].equalsIgnoreCase("potion")) {
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GRAY + "=====================================================");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "초록색 명령어" + ChatColor.YELLOW + "는 권한이 필요없고, ");
                                player.sendMessage(ChatColor.GOLD + "주황색 명령어" + ChatColor.YELLOW + "는 권한이 필요합니다.");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.AQUA + "/hehe potion" + ChatColor.WHITE + " 사용 방법 :");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "/hehe potion <포션 효과>" + ChatColor.WHITE + "포션 효과를 부여합니다.");
                                player.sendMessage(ChatColor.AQUA + "포션 효과" + ChatColor.WHITE + " :");
                                player.sendMessage(ChatColor.BLUE + "nv (야간 투시)" + ChatColor.RED + ", " + ChatColor.YELLOW + "st (포화)" + ChatColor.RED + "");
                            } else if (args[1].equalsIgnoreCase("nickname")) {
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GRAY + "=====================================================");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "초록색 명령어" + ChatColor.YELLOW + "는 권한이 필요없고, ");
                                player.sendMessage(ChatColor.GOLD + "주황색 명령어" + ChatColor.YELLOW + "는 권한이 필요합니다.");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.AQUA + "/hehe nickname" + ChatColor.WHITE + " 사용 방법 :");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "/hehe nickname <String>" + ChatColor.WHITE + "플레이어의 닉네임을 <String>으로 설정합니다.");
                                player.sendMessage(ChatColor.GREEN + "/hehe nickname reset" + ChatColor.WHITE + "플레이어의 닉네임을 초기화합니다.");
                            } else if (args[1].equalsIgnoreCase("entity")) {
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GRAY + "=====================================================");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "초록색 명령어" + ChatColor.YELLOW + "는 권한이 필요없고, ");
                                player.sendMessage(ChatColor.GOLD + "주황색 명령어" + ChatColor.YELLOW + "는 권한이 필요합니다.");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.AQUA + "/hehe entity" + ChatColor.WHITE + " 사용 방법 :");
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "/hehe entity <String>" + ChatColor.WHITE + "플레이어의 위치에 입력된 엔티티를 소환합니다.");
                                player.sendMessage("");
                            } else {
                                player.sendMessage(ChatColor.RED + "/hehe help에 등록되지 않은 명령어입니다. " + ChatColor.YELLOW + "/hehe help time" + ChatColor.WHITE + "를 통해 명령어 사용 방법을 확인하세요.");
                            }
                        } else {
                            player.sendMessage("");
                            player.sendMessage(ChatColor.GRAY + "=====================================================");
                            player.sendMessage("");
                            player.sendMessage(ChatColor.AQUA + thehe.getFullName() + ChatColor.WHITE + " 플러그인에 포함된 명령어입니다.");
                            player.sendMessage(ChatColor.GREEN + "초록색 명령어" + ChatColor.YELLOW + "는 권한이 필요없고, ");
                            player.sendMessage(ChatColor.GOLD + "주황색 명령어" + ChatColor.YELLOW + "는 권한이 필요합니다.");
                            player.sendMessage("");
                            player.sendMessage(ChatColor.GRAY + "-----------------------------------------------------");
                            player.sendMessage("");
                            player.sendMessage(ChatColor.GREEN + "/hehe help" + ChatColor.WHITE + " : 이 플러그인의 도움말을 출력합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe info" + ChatColor.WHITE + " : 이 플러그인의 정보를 출력합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe random" + ChatColor.WHITE + " : 1부터 100 사이의 수 중 무작위 숫자 하나를 출력합니다.");
                            player.sendMessage(ChatColor.GOLD + "/hehe bandage" + ChatColor.WHITE + " : 사용 시 배고픔을 2칸 소모하여 캐릭터의 체력을 4칸 회복하는 붕대 아이템을 지급합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe config <String>" + ChatColor.WHITE + " : config.yml 파일에 있는 String 값을 읽어 출력합니다.");
                            player.sendMessage(ChatColor.GOLD + "/hehe tp [save/load]" + ChatColor.WHITE + " : 플레이어의 현재 좌표를 저장하거나 저장한 좌표로 순간이동합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe perm" + ChatColor.WHITE + " : 플레이어가 권한을 가지고 있는지 확인합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe inv" + ChatColor.WHITE + " : 게임모드를 변경할 수 있는 인벤토리 창을 띄웁니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe timer [bar]" + ChatColor.WHITE + " : 10초 타이머를 시작합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe cancel" + ChatColor.WHITE + " : 플러그인을 통해 실행되는 모든 것을 취소합니다.");
                            player.sendMessage(ChatColor.GOLD + "/hehe time <6/12/18/24>" + ChatColor.WHITE + " 월드의 시간을 오전 6시/오후 12시/오후 6시/오전 12시로 변경합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe potion <포션 효과>" + ChatColor.WHITE + "포션 효과를 부여합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe nickname <reset/String>" + ChatColor.WHITE + "플레이어 닉네임을 설정하거나 초기화합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe enum <String>" + ChatColor.WHITE + "열거형 테스트입니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe entity <String>" + ChatColor.WHITE + "플레이어의 위치에 입력된 엔티티를 소환합니다.");
                            player.sendMessage(ChatColor.GREEN + "/hehe detect" + ChatColor.WHITE + "플레이어를 기준으로 모든 방향에 대해 20블록 내에 있는 모든 엔티티를 감지하여 출력합니다.");
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
                        if (!player.isOp()) {
                            player.sendMessage(ChatColor.RED + "이 명령어를 사용할 권한이 없습니다! 관리자에게 문의해 보세요.");
                        } else {
                            player.getInventory().addItem(HeheItems.getBandageItemStack());
                            player.sendMessage(ChatColor.RED + "붕대" + ChatColor.WHITE + " 아이템이 지급되었습니다.");
                        }
                    } else if (args[0].equalsIgnoreCase("config")) {
                        if (args.length <= 1) {
                            player.sendMessage(ChatColor.RED + "입력하신 인자값이 너무 적거나 없습니다. 아래의 목록에서 config에 등록된 이름을 찾을 수 있습니다.");
                            player.sendMessage(ChatColor.RED + "config.yml에 있는 내용: " + ChatColor.YELLOW + "first, second, " + playerName);
                        } else {
                            if (args[1].equalsIgnoreCase("first")) {
                                player.sendMessage(ChatColor.AQUA + "config.yml의 first 메세지입니다.");
                                player.sendMessage(ChatColor.WHITE + "first : " + ChatColor.GRAY + thehe.getConfig().getString("first"));
                            } else if (args[1].equalsIgnoreCase("second")) {
                                player.sendMessage(ChatColor.AQUA + "config.yml의 second 메세지입니다.");
                                player.sendMessage(ChatColor.WHITE + "second : " + ChatColor.GRAY + thehe.getConfig().getString("second"));
                            } else if (args[1].equals(playerName)) {
                                player.sendMessage(ChatColor.AQUA + "config.yml의 " + playerName + " 메세지입니다.");
                                player.sendMessage(ChatColor.WHITE + "second : " + ChatColor.GRAY + thehe.getConfig().getString(playerName));
                            } else {
                                player.sendMessage(ChatColor.RED + "/hehe config에 등록되어 있지 않은 명령어입니다. " + ChatColor.YELLOW + "/hehe help config" + ChatColor.RED + "를 통해 명령어 사용 방법을 확인하세요.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("tp")) {
                        if (!player.isOp()) {
                            player.sendMessage(ChatColor.RED + "이 명령어를 사용할 권한이 없습니다! 관리자에게 문의해 보세요.");
                        } else {
                            if (args.length <= 1) {
                                player.sendMessage(ChatColor.RED + "입력하신 인자값이 너무 적거나 없습니다. " + ChatColor.YELLOW + "/hehe help tp" + ChatColor.YELLOW + "를 통해 명령어 사용 방법을 확인하세요.");
                            } else {
                                if (args[1].equalsIgnoreCase("save")) {
                                    thehe.getConfig().set(playerName + ".position.x", location.getBlockX());
                                    thehe.getConfig().set(playerName + ".position.y", location.getBlockY());
                                    thehe.getConfig().set(playerName + ".position.y", location.getBlockZ());
                                    thehe.getConfig().set(playerName + ".position.pitch", location.getPitch());
                                    thehe.getConfig().set(playerName + ".position.yaw", location.getYaw());
                                    thehe.saveConfig();

                                    player.sendMessage(ChatColor.YELLOW + playerName + ChatColor.GREEN + "의 현재 좌표가 저장되었습니다.");
                                } else if (args[1].equalsIgnoreCase("load")) {
                                    if (!thehe.getConfig().isSet(playerName + ".position.x")) {
                                        player.sendMessage(ChatColor.RED + "저장된 좌표가 없습니다! " + ChatColor.YELLOW + "/hehe tp save" + ChatColor.RED + " 명령어를 통해 좌표를 먼저 지정해 주세요.");
                                    } else {
                                        double x = thehe.getConfig().getDouble(playerName + ".position.x");
                                        double y = thehe.getConfig().getDouble(playerName + ".position.y");
                                        double z = thehe.getConfig().getDouble(playerName + ".position.z");

                                        player.teleport(new Location(world, x, y, z));
                                        player.getLocation().setPitch((float) thehe.getConfig().getDouble(playerName + ".position.pitch"));
                                        player.getLocation().setYaw((float) thehe.getConfig().getDouble(playerName + ".position.yaw"));
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "/hehe tp에 등록되어 있지 않은 명령어입니다. " + ChatColor.YELLOW + "/hehe help tp" + ChatColor.RED + "를 통해 명령어 사용 방법을 확인하세요.");
                                }
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("perm")) {
                        if (player.hasPermission(new Permissions().Test)) {
                            player.sendMessage(ChatColor.WHITE + "현재 " + ChatColor.GREEN + "Test.bypass" + ChatColor.WHITE + " 권한을 가지고 있습니다.");
                        } else {
                            player.sendMessage(ChatColor.WHITE + "현재 " + ChatColor.RED + "Test.bypass" + ChatColor.WHITE + " 권한을 가지고 있지 않습니다.");
                        }
                    } else if (args[0].equalsIgnoreCase("inv")) {
                        openInv(player);
                        player.sendMessage(ChatColor.GREEN + "게임모드를 변경할 수 있는 인벤토리 창을 띄웠습니다. ESC 키를 이용해 닫을 수 있습니다.");
                    } else if (args[0].equalsIgnoreCase("timer")) {
                        if (args.length <= 1) {
                            time[0] = 10;

                            player.sendMessage("");
                            player.sendMessage(ChatColor.GREEN + "카운트다운을 시작합니다.");

                            thehe.getServer().getScheduler().scheduleSyncRepeatingTask(thehe, () -> {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (time[0] != 0) {
                                        p.sendMessage(ChatColor.GREEN + "카운트 다운 : " + ChatColor.YELLOW + time[0] + ChatColor.AQUA + " 초");
                                        p.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10F, 1F);
                                        time[0]--;
                                    } else {
                                        p.sendMessage(ChatColor.GREEN + "카운트 다운 : " + ChatColor.RED + "종료!");
                                        p.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 10F, 1F);
                                        time[0]--;
                                        Bukkit.getScheduler().cancelTasks(thehe);
                                    }
                                }
                            }, 0L, 20L);
                        } else {
                            if (args[1].equalsIgnoreCase("bar")) {
                                player.sendMessage("");
                                player.sendMessage(ChatColor.GREEN + "카운트다운을 시작합니다.");

                                thehe.getServer().getScheduler().scheduleSyncRepeatingTask(thehe, () -> {
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (time[0] != -1) {
                                            p.sendMessage(ChatColor.GREEN + "카운트 다운 : " + ChatColor.YELLOW + time[0] + ChatColor.AQUA + " 초");
                                            p.setLevel(time[0]);
                                            p.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10F, 1F);
                                            time[0]--;
                                        } else {
                                            p.setLevel(0);
                                            p.sendMessage(ChatColor.GREEN + "카운트 다운 : " + ChatColor.RED + "종료!");
                                            p.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 10F, 1F);
                                            time[0]--;
                                            Bukkit.getScheduler().cancelTasks(thehe);
                                        }
                                    }
                                }, 0L, 20L);
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("cancel")) {
                        player.sendMessage(ChatColor.RED + "카운트 다운이 취소되었습니다.");
                        Bukkit.getScheduler().cancelTasks(thehe);
                    } else if (args[0].equalsIgnoreCase("time")) {
                        if (!player.isOp()) {
                            player.sendMessage(ChatColor.RED + "이 명령어를 사용할 권한이 없습니다! 관리자에게 문의해 보세요.");
                        } else {
                            if (args.length <= 1) {
                                player.sendMessage(ChatColor.RED + "입력하신 인자값이 너무 적거나 없습니다. " + ChatColor.YELLOW + "/hehe help time" + ChatColor.YELLOW + "를 통해 명령어 사용 방법을 확인하세요.");
                            } else if (args[1].equalsIgnoreCase("6")) {
                                Objects.requireNonNull(Bukkit.getWorld(worldName)).setTime(0);
                                player.sendMessage(ChatColor.GREEN + "세계의 시간을 " + ChatColor.YELLOW + "AM 6:00" + ChatColor.WHITE + "로 변경하였습니다.");
                            } else if (args[1].equalsIgnoreCase("12")) {
                                Objects.requireNonNull(Bukkit.getWorld(worldName)).setTime(6000);
                                player.sendMessage(ChatColor.GREEN + "세계의 시간을 " + ChatColor.YELLOW + "PM 12:00" + ChatColor.WHITE + "로 변경하였습니다.");
                            } else if (args[1].equalsIgnoreCase("18")) {
                                Objects.requireNonNull(Bukkit.getWorld(worldName)).setTime(12000);
                                player.sendMessage(ChatColor.GREEN + "세계의 시간을 " + ChatColor.YELLOW + "PM 6:00" + ChatColor.WHITE + "로 변경하였습니다.");
                            } else if (args[1].equalsIgnoreCase("24")) {
                                Objects.requireNonNull(Bukkit.getWorld(worldName)).setTime(18000);
                                player.sendMessage(ChatColor.GREEN + "세계의 시간을 " + ChatColor.YELLOW + "AM 12:00" + ChatColor.WHITE + "로 변경하였습니다.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("potion")) {
                        if (args.length <= 1) {
                            player.sendMessage(ChatColor.RED + "명령어의 인자가 너무 적거나 없습니다! " + ChatColor.YELLOW + "/hehe help potion" + ChatColor.RED + " 명령어를 통해 도움말을 확인할 수 있습니다.");
                        } else {
                            if (args[1].equalsIgnoreCase("nv")) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0, false, false));
                                player.sendMessage(ChatColor.GREEN + "포션 효과 " + ChatColor.BLUE + "야간 투시" + ChatColor.GREEN + "가 적용되었습니다.");
                            } else if (args[1].equalsIgnoreCase("st")) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000000, 100, false, false));
                                player.sendMessage(ChatColor.GREEN + "포션 효과 " + ChatColor.YELLOW + "포화" + ChatColor.GREEN + "가 적용되었습니다.");
                            } else {
                                player.sendMessage(ChatColor.RED + "알 수 없는 포션 효과입니다. 아래의 목록에 있는 포션 효과를 입력해 주세요.");
                                player.sendMessage(ChatColor.BLUE + "nv (야간 투시)" + ChatColor.RED + ", " + ChatColor.YELLOW + "st (포화)" + ChatColor.RED + "");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("nickname")) {
                        if (args.length <= 1) {
                            player.sendMessage(ChatColor.RED + "명령어의 인자가 너무 적거나 없습니다! " + ChatColor.YELLOW + "/hehe help nickname" + ChatColor.RED + " 명령어를 통해 도움말을 확인할 수 있습니다.");
                        } else {
                            if (args[1].equals("reset")) {
                                player.setDisplayName(playerName);
                                player.setPlayerListName(playerName);

                                player.sendMessage(ChatColor.GREEN + "플레이어의 닉네임을 초기화하였습니다.");
                            } else if (args[1].equals(playerName) || args[1].equals(player.getDisplayName())) {
                                player.sendMessage(ChatColor.RED + "변경하려는 이름이 현재 설정된 이름과 동일합니다. 다시 입력해 주세요.");
                                player.sendMessage(ChatColor.RED + "닉네임을 초기화하려면 " + ChatColor.YELLOW + "/hehe nickname reset" + ChatColor.RED + "을 입력해 주세요.");
                            } else {
                                player.sendMessage(ChatColor.AQUA + "플레이어의 닉네임이 " + ChatColor.WHITE + player.getDisplayName() + ChatColor.AQUA + " 에서 " + ChatColor.WHITE + args[1] + ChatColor.AQUA + " 으로 변경합니다.");

                                player.setDisplayName(args[1]);
                                player.setPlayerListName(args[1]);
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("enum")) {
                        if (args.length <= 1) {
                            player.sendMessage(ChatColor.RED + "열거 값이 입력되지 않았거나 알 수 없는 열거형 값입니다. 아래의 표를 참고해 주세요.");
                            player.sendMessage(ChatColor.YELLOW + "apple, chocolate, ramyeon, rice, corn, sugar_cube, salad");
                        } else {
                            for (EnumTest e : EnumTest.getFoods()) {
                                if (args[1].equalsIgnoreCase(e.foodName)) {
                                    String result = e.healthy ? "건강한 음식입니다." : "건강하지 않은 음식입니다.";
                                    String fdName = "";

                                    switch (e.foodName) {
                                        case "apple":
                                            fdName = "사과";
                                            break;
                                        case "chocolate":
                                            fdName = "초콜렛";
                                            break;
                                        case "ramyeon":
                                            fdName = "라면";
                                            break;
                                        case "rice":
                                            fdName = "쌀";
                                            break;
                                        case "corn":
                                            fdName = "옥수수";
                                            break;
                                        case "sugar_cube":
                                            fdName = "각설탕";
                                            break;
                                        case "salad":
                                            fdName = "샐러드";
                                            break;
                                        default:
                                            break;
                                    }

                                    player.sendMessage(ChatColor.WHITE + fdName + " 은(는) " + result);
                                    break;
                                }
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("entity")) {
                        if (args.length <= 1) {
                            player.sendMessage(ChatColor.RED + "명령어의 인자가 너무 적거나 없습니다! 소환 가능한 엔티티는 아래의 표를 참고해 주세요!");
                            player.sendMessage(ChatColor.YELLOW + "늑대 (Wolf), 오셀롯 (Ocelot), 주민 (Villager), 갑옷 거치대 (armor_stand)");
                        } else if (args[1].equalsIgnoreCase("wolf")) {
                            world.spawnEntity(location, EntityType.WOLF);
                            player.sendMessage(ChatColor.GREEN + "플레이어가 서 있는 위치에 엔티티 ", ChatColor.YELLOW + "\"늑대\"" + ChatColor.GREEN + "를 소환하였습니다.");
                        } else if (args[1].equalsIgnoreCase("ocelot")) {
                            world.spawnEntity(location, EntityType.OCELOT);
                            player.sendMessage(ChatColor.GREEN + "플레이어가 서 있는 위치에 엔티티 ", ChatColor.YELLOW + "\"오셀롯\"" + ChatColor.GREEN + "을 소환하였습니다.");
                        } else if (args[1].equalsIgnoreCase("villager")) {
                            world.spawnEntity(location, EntityType.VILLAGER);
                            player.sendMessage(ChatColor.GREEN + "플레이어가 서 있는 위치에 엔티티 ", ChatColor.YELLOW + "\"주민\"" + ChatColor.GREEN + "을 소환하였습니다.");
                        } else if (args[1].equalsIgnoreCase("armor_stand")) {
                            world.spawnEntity(location, EntityType.ARMOR_STAND);
                            player.sendMessage(ChatColor.GREEN + "플레이어가 서 있는 위치에 엔티티 ", ChatColor.YELLOW + "\"갑옷 거치대\"" + ChatColor.GREEN + "를 소환하였습니다.");
                        } else {
                            player.sendMessage(ChatColor.RED + "알 수 없는 엔티티입니다. 아래의 목록에 있는 엔티티를 입력해 주세요.");
                            player.sendMessage(ChatColor.YELLOW + "늑대 (Wolf), 오셀롯 (Ocelot), 주민 (Villager), 갑옷 거치대 (armor_stand)");
                        }
                    } else if (args[0].equalsIgnoreCase("detect")) {
                        player.sendMessage(ChatColor.AQUA + "현재 플레이어 기준 모든 방향에 대해 20블록 내에 있는 엔티티 목록입니다.");
                        for (Entity entity : player.getNearbyEntities(20.0, 20.0, 20.0)) {
                            player.sendMessage((entity instanceof Player ? entity.getName() : entity.getType().name()));
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "hehe에 등록되어 있지 않은 명령어입니다. " + ChatColor.YELLOW + "/hehe help" + ChatColor.RED + "를 통해 명령어 목록을 확인하세요.");
                    }
                }
            }
        } else {
            if (command.getName().equalsIgnoreCase("cs")) {
                if (args.length <= 0) {
                    sender.sendMessage(ChatColor.YELLOW + "인자 값이 너무 적거나 없습니다. 다른 커맨드를 입력해 주세요!");
                } else {
                    if (args[0].equalsIgnoreCase("test")) {
                        sender.sendMessage(ChatColor.GREEN + "콘솔 커맨드가 정상적으로 작동합니다! (멀티 커맨드 1)");
                    } else if (args[0].equalsIgnoreCase("exam")) {
                        if (args.length <= 1) {
                            sender.sendMessage(ChatColor.YELLOW + "인자 값이 너무 적거나 없습니다. 다른 커맨드를 입력해 주세요!");
                        } else {
                            if (args[1].equalsIgnoreCase("t1")) {
                                sender.sendMessage(ChatColor.GREEN + "콘솔 커맨드가 정상적으로 작동합니다! (멀티 커맨드 2)");
                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
}
