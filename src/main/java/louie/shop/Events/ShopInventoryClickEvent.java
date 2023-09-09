package louie.shop.Events;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import louie.bank.BankAPI.BankAPI;
import louie.bank.BankAPI.BankService;
import louie.shop.Config.Config;
import louie.shop.ItemSerialization.ItemSerialization;

public class ShopInventoryClickEvent implements Listener {

    @EventHandler
    public void onShopInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if(event.getInventory().getViewers().get(0).getOpenInventory().getTitle() == "Louie's Shop") {
            event.setCancelled(true);
            ItemStack itemClicked = event.getCurrentItem();
            String itemClickedBase64 = ItemSerialization.itemStackToBase64(itemClicked);
            Integer itemClickedPrice = Config.get().getInt(itemClickedBase64 + ".Price", 0);
            BankAPI bankAPI = new BankService();
            bankAPI.getPlayersBalance(p.getUniqueId()).thenAccept(balance -> {
                if(balance > itemClickedPrice || balance == itemClickedPrice) {
                    bankAPI.removeMoney(p.getUniqueId(), itemClickedPrice);
                    Inventory playerInventory = p.getInventory();
                    ItemFactory itemFactory = Bukkit.getItemFactory();
                    ItemMeta meta = itemFactory.getItemMeta(itemClicked.getType());
                    itemClicked.setItemMeta(meta);
                    playerInventory.addItem(itemClicked);
                    Config.get().set(itemClickedBase64, null);
                    Config.save();
                } else {
                    p.sendMessage("Insufficient Funds!");
                }
            });
            p.closeInventory();
        }
    }
}
