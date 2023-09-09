package louie.shop.Commands;

import louie.bank.BankAPI.BankAPI;
import louie.bank.BankAPI.BankService;
import louie.shop.Config.Config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import louie.shop.ItemDeserialization.ItemDeserialization;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("shop")) {
            if(sender instanceof Player) {
               if(args.length == 0) {
                   Inventory shopInventory = Bukkit.createInventory(null, 36, "Louie's Shop");
                   Player p = (Player) sender;

                   // Loops through each item in the Config/Shop
                   for(String shopItem: Config.get().getConfigurationSection("").getKeys(false)) {
                        // Converts the base64 from the config into an item
                        ItemStack item = ItemDeserialization.base64ToItemStack(shopItem);

                        // // Gets the item meta to change the name and add a lore (items description)
                        // ItemMeta itemMeta = item.getItemMeta();

                        // // Sets the display name of the item
                        // itemMeta.setDisplayName(ChatColor.BOLD +""+ ChatColor.BLUE + item.getType().toString().replace("_", " "));

                        // // Creates a new ArrayList that is used for the itemLore
                        // List<String> itemMetaLore = new ArrayList<>();

                        // Bukkit.getLogger().info(shopItem);
                        // // Fetches the itemPrice and who sold the item from the config
                        // Integer itemPrice = Config.get().getInt(shopItem + ".Price", 0);
                        // String itemSoldBy = Config.get().getString(shopItem + ".Player", null);

                        // // Adds the values we just fetched to the item lore
                        // itemMetaLore.add(ChatColor.GREEN + "Left Click: Buy: " + ChatColor.RED + itemPrice);
                        // itemMetaLore.add(ChatColor.GREEN + "Player: " + ChatColor.RED + itemSoldBy);

                        // // Then we set the item lore to the item meta, then set the item meta to the item
                        // itemMeta.setLore(itemMetaLore);
                        // item.setItemMeta(itemMeta);

                        // Lastly, we add the item to the shopInventory
                        shopInventory.addItem(item);
                    }
                   p.openInventory(shopInventory);
                   
               }
            }
        }
        return false;
    }
}
