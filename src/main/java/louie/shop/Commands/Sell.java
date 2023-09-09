package louie.shop.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static louie.shop.Config.Setter.setNewItem;

public class Sell implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("sell")) {
          if (sender instanceof Player) {
              if (args.length == 0) {
                 Player p = (Player) sender;
                  ItemStack itemInPlayersHand = p.getInventory().getItemInMainHand();
                  setNewItem(itemInPlayersHand, p);
                  p.getInventory().setItemInMainHand(null);
              }
          }
        }
        return false;
    }
}
