package louie.shop;

import louie.shop.Commands.Sell;
import louie.shop.Config.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shop extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Config.setup();
        Config.save();
        getCommand("sell").setExecutor(new Sell());
        getCommand("shop").setExecutor(new louie.shop.Commands.Shop());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
