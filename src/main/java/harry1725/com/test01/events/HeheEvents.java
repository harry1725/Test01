package harry1725.com.test01.events;

import harry1725.com.test01.items.HeheItems;
import harry1725.com.test01.test;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.*;

import java.util.Objects;
import java.util.logging.Logger;

public class HeheEvents implements Listener {
    public final Logger logger = Logger.getLogger("Minecraft");
    public static test test;

    public void scboard(Player player) {
        ScoreboardManager sm = Bukkit.getScoreboardManager();

        Scoreboard board = Objects.requireNonNull(sm).getNewScoreboard();
        Objective obj = board.registerNewObjective("Testing", "dummy", "");
        Score score = obj.getScore("Score");

        Team team = board.registerNewTeam("TestTeam");

        team.addEntry(player.getName());
        team.setPrefix(ChatColor.GREEN + "");
        team.setCanSeeFriendlyInvisibles(true);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.AQUA + "스코어보드 테스트!");

        score.setScore(20);

        player.setScoreboard(board);
    }

    public HeheEvents(test plugin) {
        test = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getInventory().addItem(HeheItems.getBandageItemStack());

        scboard(player);

        event.setJoinMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + "님이 서버에 접속하셨습니다. 어서오세요~");
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
            } else if (health >= 16.0) {
                player.sendMessage(ChatColor.RED + "붕대를 사용하기에는 아직 건강합니다. 체력을 2칸 이상 잃은 뒤 다시 사용해 주세요!");
            } else {
                player.setFoodLevel(foodLevel - 4);
                player.setHealth(health + 4.0);
                player.sendMessage(ChatColor.GREEN + "체력을 회복하였습니다.");
            }
        }
    }

    @EventHandler
    public void onEntityDamaged(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        double damage = event.getDamage();

        if (damager instanceof Player) {
            String victim = entity.getName();
            String attacker = damager.getName();

            damager.sendMessage(ChatColor.YELLOW + victim + ChatColor.WHITE + " (이)가 " + ChatColor.YELLOW + attacker + ChatColor.WHITE + " 로부터" + ChatColor.RED + damage + ChatColor.WHITE + " 만큼의 데미지를 입었습니다!");
        } else if (damager instanceof Arrow) {
            Arrow arrow = (Arrow) damager;
            LivingEntity shooter = (LivingEntity) arrow.getShooter();

            String victim = entity.getName();
            String attacker = Objects.requireNonNull(shooter).getName();

            shooter.sendMessage(ChatColor.YELLOW + attacker + ChatColor.WHITE + " (이)가 " + ChatColor.YELLOW + victim + ChatColor.WHITE + " 에게 화살로 " + ChatColor.RED + damage + ChatColor.WHITE + " 만큼의 데미지를 입혔습니다!");
        } else if (damager instanceof SpectralArrow) {
            SpectralArrow arrow = (SpectralArrow) damager;
            LivingEntity shooter = (LivingEntity) arrow.getShooter();

            String victim = entity.getName();
            String attacker = Objects.requireNonNull(shooter).getName();

            shooter.sendMessage(ChatColor.YELLOW + attacker + ChatColor.WHITE + " (이)가 " + ChatColor.YELLOW + victim + ChatColor.WHITE + " 에게 " + ChatColor.YELLOW + "분광 화살" + ChatColor.WHITE + "로 " + ChatColor.RED + damage + ChatColor.WHITE + " 만큼의 데미지를 입혔습니다!");
        } else if (damager instanceof Snowball) {
            Snowball arrow = (Snowball) damager;
            LivingEntity shooter = (LivingEntity) arrow.getShooter();

            String victim = entity.getName();
            String attacker = Objects.requireNonNull(shooter).getName();

            shooter.sendMessage(ChatColor.YELLOW + attacker + ChatColor.WHITE + " (이)가 " + ChatColor.YELLOW + victim + ChatColor.WHITE + " 에게 " + ChatColor.AQUA + "눈덩이" + ChatColor.WHITE + "로 " + ChatColor.RED + damage + ChatColor.WHITE + " 만큼의 데미지를 입혔습니다!");
        } else {
            Player player = (Player) entity;

            String victim = player.getName();
            String attacker = damager.getName();

            damager.sendMessage(ChatColor.YELLOW + attacker + ChatColor.WHITE + " (이)가 " + ChatColor.YELLOW + victim + ChatColor.WHITE + " 에게" + ChatColor.RED + damage + ChatColor.WHITE + " 만큼의 데미지를 입혔습니다!");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();

        if (Objects.requireNonNull(event.getDeathMessage()).contains("fell from a high place")) {
            event.setDeathMessage(ChatColor.WHITE + Objects.requireNonNull(victim).getDisplayName() + " 이(가) 앞을 보지 않고 달리다가 떨어졌습니다.");
        } else if (event.getDeathMessage().contains("went up in flames")) {
            event.setDeathMessage(ChatColor.WHITE + Objects.requireNonNull(victim).getDisplayName() + " 이(가) 너무 추운 나머지 불길 속으로 뛰어들었습니다.");
        } else if (killer != null) {
            if (event.getDeathMessage().contains("was slain by")) {
                event.setDeathMessage(ChatColor.WHITE + Objects.requireNonNull(victim).getDisplayName() + " 이(가) \"멈춰!\"라고 외쳤지만 " + killer.getDisplayName() + " 은(는) 더욱 거세게 폭력을 행사했습니다.");
            } else if (event.getDeathMessage().contains("was shot by")) {
                event.setDeathMessage(ChatColor.WHITE + Objects.requireNonNull(victim).getDisplayName() + " 은(는) 평화롭게 지내고 있었지만 " + killer.getDisplayName() + " 이(가) 멀리서 저격했습니다.");
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase("게임모드 변경")) {
            switch (Objects.requireNonNull(event.getCurrentItem()).getType()) {
                case CRAFTING_TABLE:
                    player.setGameMode(GameMode.SURVIVAL);
                    player.closeInventory();
                    player.sendMessage(ChatColor.WHITE + "게임모드를 " + ChatColor.GREEN + "생존 모드" + ChatColor.WHITE + "로 변경하였습니다.");
                    break;

                case DIAMOND_BLOCK:
                    player.setGameMode(GameMode.CREATIVE);
                    player.closeInventory();
                    player.sendMessage(ChatColor.WHITE + "게임모드를 " + ChatColor.AQUA + "창작 모드" + ChatColor.WHITE + "로 변경하였습니다.");
                    break;

                case LEATHER_HELMET:
                    player.setGameMode(GameMode.ADVENTURE);
                    player.closeInventory();
                    player.sendMessage(ChatColor.WHITE + "게임모드를 " + ChatColor.YELLOW + "모험 모드" + ChatColor.WHITE + "로 변경하였습니다.");
                    break;

                case GLASS:
                    player.setGameMode(GameMode.SPECTATOR);
                    player.closeInventory();
                    player.sendMessage(ChatColor.WHITE + "게임모드를 " + ChatColor.GRAY + "관전자 모드" + ChatColor.WHITE + "로 변경하였습니다.");
                    break;
                default:
                    Bukkit.broadcastMessage(ChatColor.RED + "디버그: switch-case에서 default:가 실행되었습니다.");
                    break;
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        player.getInventory().removeItem(HeheItems.getBandageItemStack());

        event.setQuitMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + "님이 서버에서 나가셨습니다! 안녕히 가세요~");
    }
}
