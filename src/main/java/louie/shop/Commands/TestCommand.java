package louie.shop.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import louie.bank.BankAPI.BankAPI;
import louie.bank.BankAPI.BankService;

public class TestCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub
        if(label.equalsIgnoreCase("test")) {
            if(sender instanceof Player) {
                if(args.length == 0) {
                    BankAPI bankAPI = new BankService();
                    Player p = (Player) sender;
                    bankAPI.getPlayersBalance(p.getUniqueId()).thenAccept(balance -> {
                        p.sendMessage("Your balance is: " + balance);
                    });
                }
            }
        }
        return false;
    }
    
}
