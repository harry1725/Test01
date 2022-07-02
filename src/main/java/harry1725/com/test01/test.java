package harry1725.com.test01;

import harry1725.com.test01.commands.HeheCommands;
import harry1725.com.test01.items.HeheItems;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.*;
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

    public void openInv(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "게임모드 변경");

        inv.setItem(10, HeheItems.getSurvivalIconItemStack());
        inv.setItem(12, HeheItems.getCreativeIconItemStack());
        inv.setItem(14, HeheItems.getAdventureIconItemStack());
        inv.setItem(16, HeheItems.getSpectatorIconItemStack());

        player.openInventory(inv);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getInventory().addItem(HeheItems.getBandageItemStack());

        event.setJoinMessage(ChatColor.YELLOW + player.getName() + ChatColor.WHITE + "님이 서버에 접속하셨습니다. 어서오세요~");
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

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity().getPlayer();
        Entity killer = event.getEntity().getKiller();

        if (Objects.requireNonNull(event.getDeathMessage()).contains("fell from a high place")) {
            event.setDeathMessage(ChatColor.WHITE + Objects.requireNonNull(victim).getName() + "이(가) 앞을 보지 않고 달리다가 떨어졌습니다.");
        } else if (event.getDeathMessage().contains("went up in flames")) {
            event.setDeathMessage(ChatColor.WHITE + Objects.requireNonNull(victim).getName() + "이(가) 용암에서 살아남지 못했습니다.");
        } else if (killer != null) {
            if (event.getDeathMessage().contains("was slain by")) {
                event.setDeathMessage(ChatColor.WHITE + Objects.requireNonNull(victim).getName() + "이(가) \"멈춰!\"라고 소리질렀지만 " + killer.getName() + "은(는) 더욱 거세게 폭력을 행사했습니다.");
            } else if (event.getDeathMessage().contains("was shot by")) {
                event.setDeathMessage(ChatColor.WHITE + Objects.requireNonNull(victim).getName() + "은(는) 평화롭게 지내고 있었지만 " + killer.getName() + "이(가) 멀리서 저격했습니다.");
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

        event.setQuitMessage(ChatColor.YELLOW + player.getName() + ChatColor.WHITE + "님이 서버에서 나가셨습니다! 안녕히 가세요~");
    }

}
