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

}
