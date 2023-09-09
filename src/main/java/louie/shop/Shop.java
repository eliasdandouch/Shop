package louie.shop;

import louie.shop.Commands.Sell;
import louie.shop.Commands.ShopCommand;
import louie.shop.Commands.TestCommand;
import louie.shop.Config.Config;
import louie.shop.Events.ShopInventoryClickEvent;

import org.bukkit.plugin.java.JavaPlugin;

public final class Shop extends JavaPlugin {

    public static Shop instance;

    public static Shop getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Config.setup();
        Config.save();
        getCommand("sell").setExecutor(new Sell());
        getCommand("shop").setExecutor(new ShopCommand());
        getCommand("test").setExecutor(new TestCommand());
        getServer().getPluginManager().registerEvents(new ShopInventoryClickEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
