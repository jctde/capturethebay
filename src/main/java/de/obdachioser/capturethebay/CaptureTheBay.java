package de.obdachioser.capturethebay;

import de.obdachioser.capturethebay.config.CurrentGameWorldConfiguration;
import de.obdachioser.capturethebay.config.LocaleGameConfiguration;
import de.obdachioser.capturethebay.listeners.*;
import de.obdachioser.capturethebay.sessions.GameSession;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by ObdachIoser at 13:15 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class CaptureTheBay extends JavaPlugin {

    @Getter
    private static CaptureTheBay instance;

    @Getter
    private static GameSession gameSession;

    @Getter
    private static LocaleGameConfiguration localeGameConfiguration;

    @Getter
    private static CurrentGameWorldConfiguration currentGameWorldConfiguration;

    @Getter
    private static String prefix = "§f[§aCaptureTheBay§f] §7";

    public CaptureTheBay() {
        instance = this;
    }

    @Override
    public void onEnable() {

        gameSession = new GameSession();

//        localeGameConfiguration = new CurrentGameWorldConfiguration();
//        localeGameConfiguration.apply(new File("plugins/CaptureTheBay", "config.yml"));

//        currentGameWorldConfiguration = (CurrentGameWorldConfiguration) localeGameConfiguration;
        gameSession.startSession();

        System.out.println("[CaptureTheBay] Game was started");

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        gameSession.stopSession();
    }

    private void registerListeners() {

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerDamageByPlayerListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
    }

    private void registerCommands() {

    }
}
