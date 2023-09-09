package louie.shop.Commands;

import louie.bank.BankAPI.BankAPI;
import louie.bank.BankAPI.BankService;
import louie.shop.Config.Config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import louie.shop.ItemDeserialization.ItemDeserialization;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("shop")) {
            if(sender instanceof Player) {
               if(args.length == 0) {
                   Inventory shopInventory = Bukkit.createInventory(null, 36, "Louie's Shop");
                   BankAPI bankAPI = new BankService();
                   Player p = (Player) sender;
                   for(String shopItem: Config.get().getConfigurationSection("").getKeys(false)) {
                    ItemStack item = ItemDeserialization.base64ToItemStack(shopItem);
                    shopInventory.addItem(item);
                   }
                   p.openInventory(shopInventory);
                   
               }
            }
        }
        return false;
    }
}
