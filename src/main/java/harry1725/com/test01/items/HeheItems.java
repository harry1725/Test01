package harry1725.com.test01.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Objects;

public class HeheItems {
    public static ItemStack getBandageItemStack() {
        ItemStack bandageItemStack = new ItemStack(Material.PAPER);
        ItemMeta bandageItemMeta = bandageItemStack.getItemMeta();

        Objects.requireNonNull(bandageItemMeta).setDisplayName(ChatColor.RED + "붕대");
        bandageItemMeta.setLore(Collections.singletonList(ChatColor.GREEN + "사용 시 배고픔을 2칸 소모하여 캐릭터의 체력을 4칸 회복합니다."));
        bandageItemStack.setItemMeta(bandageItemMeta);

        return bandageItemStack;
    }

    public static ItemStack getSurvivalIconItemStack() {
        ItemStack survivalIconItemStack = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta survivalIconItemMeta = survivalIconItemStack.getItemMeta();

        Objects.requireNonNull(survivalIconItemMeta).setDisplayName(ChatColor.GREEN + "생존 모드");
        survivalIconItemMeta.setLore(Collections.singletonList(ChatColor.WHITE + "플레이어의 게임 모드를 " + ChatColor.GREEN + "생존 모드" + ChatColor.WHITE + " 로 변경합니다."));
        survivalIconItemStack.setItemMeta(survivalIconItemMeta);

        return survivalIconItemStack;
    }
    public static ItemStack getCreativeIconItemStack() {
        ItemStack creativeIconItemStack = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta creativeIconItemMeta = creativeIconItemStack.getItemMeta();

        Objects.requireNonNull(creativeIconItemMeta).setDisplayName(ChatColor.AQUA + "창작 모드");
        creativeIconItemMeta.setLore(Collections.singletonList(ChatColor.WHITE + "플레이어의 게임 모드를 " + ChatColor.AQUA + "창작 모드" + ChatColor.WHITE + " 로 변경합니다."));
        creativeIconItemStack.setItemMeta(creativeIconItemMeta);

        return creativeIconItemStack;
    }

    public static ItemStack getAdventureIconItemStack() {
        ItemStack adventureIconItemStack = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta adventureIconItemMeta = adventureIconItemStack.getItemMeta();

        Objects.requireNonNull(adventureIconItemMeta).setDisplayName(ChatColor.YELLOW + "모험 모드");
        adventureIconItemMeta.setLore(Collections.singletonList(ChatColor.WHITE + "플레이어의 게임 모드를 " + ChatColor.YELLOW + "모험 모드" + ChatColor.WHITE + " 로 변경합니다."));
        adventureIconItemStack.setItemMeta(adventureIconItemMeta);

        return adventureIconItemStack;
    }

    public static ItemStack getSpectatorIconItemStack() {
        ItemStack spectatorIconItemStack = new ItemStack(Material.GLASS);
        ItemMeta spectatorIconItemMeta = spectatorIconItemStack.getItemMeta();

        Objects.requireNonNull(spectatorIconItemMeta).setDisplayName(ChatColor.GRAY + "관전자 모드");
        spectatorIconItemMeta.setLore(Collections.singletonList(ChatColor.WHITE + "플레이어의 게임 모드를 " + ChatColor.GRAY + "관전자 모드" + ChatColor.WHITE + " 로 변경합니다."));
        spectatorIconItemStack.setItemMeta(spectatorIconItemMeta);

        return spectatorIconItemStack;
    }

}
