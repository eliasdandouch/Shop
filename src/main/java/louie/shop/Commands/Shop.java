package louie.shop.Commands;

import louie.bank.BankAPI.BankAPI;
import louie.bank.BankAPI.BankService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.concurrent.CompletableFuture;

public class Shop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("shop")) {
            if(sender instanceof Player) {
               if(args.length == 0) {
                   Inventory shopInventory = Bukkit.createInventory(null, 36, "Louie's Shop");
                   BankAPI bankAPI = new BankService();
                   Player p = (Player) sender;
                   bankAPI.getPlayersBalance(p.getUniqueId()).thenAccept(balance -> {
                      p.sendMessage("Your balance is: " + ChatColor.GREEN + balance);
                   });
                   
               }
            }
        }
        return false;
    }
}
