package louie.shop.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static louie.shop.Config.Setter.setNewItem;

import java.util.ArrayList;
import java.util.List;

public class Sell implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("sell")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    Player p = (Player) sender;
                    Integer itemInPlayersHandPrice = Integer.parseInt(args[0]);
                    ItemStack itemInPlayersHand = p.getInventory().getItemInMainHand();
                    Bukkit.getLogger().info("Sell item: " + itemInPlayersHand);

                    // Gets the item meta to change the name and add a lore (items description)
                    ItemMeta itemMeta = itemInPlayersHand.getItemMeta();

                    // Sets the display name of the item
                    itemMeta.setDisplayName(
                            ChatColor.BOLD + "" + ChatColor.BLUE + itemInPlayersHand.getType().toString().replace("_", " "));

                    // Creates a new ArrayList that is used for the itemLore
                    List<String> itemMetaLore = new ArrayList<>();


                    // Adds the values we just fetched to the item lore
                    itemMetaLore.add(ChatColor.GREEN + "Left Click: Buy: " + ChatColor.RED + itemInPlayersHandPrice);
                    itemMetaLore.add(ChatColor.GREEN + "Player: " + ChatColor.RED + p.getDisplayName());

                    // Then we set the item lore to the item meta, then set the item meta to the
                    // item
                    itemMeta.setLore(itemMetaLore);
                    itemInPlayersHand.setItemMeta(itemMeta);

                    setNewItem(itemInPlayersHand, p, itemInPlayersHandPrice);
                    p.getInventory().setItemInMainHand(null);
                }
            }
        }
        return false;
    }
}
