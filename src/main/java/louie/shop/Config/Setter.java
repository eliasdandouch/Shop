package louie.shop.Config;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static louie.shop.ItemSerialization.ItemSerialization.itemStackToBase64;

public class Setter {

    public static void setNewItem(ItemStack item, Player p, Integer itemPrice) throws IllegalStateException {
        String base64Item = itemStackToBase64(item);
        Config.get().set(base64Item + "." + "Player", p.getDisplayName());
        Config.get().set(base64Item + "." + "Price", itemPrice);
        Config.save();
    }
}
