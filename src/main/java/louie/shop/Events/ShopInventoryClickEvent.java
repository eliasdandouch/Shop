package louie.shop.Events;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import louie.bank.BankAPI.BankAPI;
import louie.bank.BankAPI.BankService;
import louie.shop.Config.Config;
import louie.shop.ItemSerialization.ItemSerialization;

public class ShopInventoryClickEvent implements Listener {

    @EventHandler
    public void onShopInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        p.sendMessage("event ran");

        if(event.getInventory().getViewers().get(0).getOpenInventory().getTitle() == "Louie's Shop") {
            event.setCancelled(true);
            ItemStack itemClicked = event.getCurrentItem();
            String itemClickedBase64 = ItemSerialization.itemStackToBase64(itemClicked);
            Bukkit.getLogger().info(itemClickedBase64);
            Integer itemClickedPrice = Config.get().getInt(itemClickedBase64 + ".Price", 0);
            BankAPI bankAPI = new BankService();
            bankAPI.getPlayersBalance(p.getUniqueId()).thenAccept(balance -> {
                p.sendMessage("Your balance is: " + balance + " and the price of the item is: " + itemClickedPrice);
                if(balance > itemClickedPrice || balance == itemClickedPrice) {
                    Inventory playerInventory = p.getInventory();
                    playerInventory.addItem(itemClicked);
                    Config.get().set(itemClickedBase64, null);
                    Config.save();
                } else {
                    p.sendMessage("Insufficient Funds!");
                }
            });
        }
    }
}
