package com.md_5.testplugin;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {

    public static final Logger logger = Bukkit.getServer().getLogger();

    public void onEnable() {
        getServer().getPluginManager().registerEvent(Type.PLAYER_COMMAND_PREPROCESS, new PlayerListener() {

            @Override
            public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
                System.out.println("Event executed at lowest priority, Cancelling.");
                event.setCancelled(true);
            }
        }, Priority.Lowest, this);
        getServer().getPluginManager().registerEvent(Type.PLAYER_COMMAND_PREPROCESS, new PlayerListener() {

            @Override
            public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
                System.out.println("Event executed at high priority.");
                System.out.println("It's cancelled state is " + event.isCancelled());
            }
        }, Priority.High, this);
        getServer().getPluginManager().registerEvent(Type.PLAYER_COMMAND_PREPROCESS, new PlayerListener() {

            @Override
            public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
                System.out.println("Event executed at monitor priority, this should not be called.");
                System.out.println("It's cancelled state is " + event.isCancelled());
            }
        }, Priority.Monitor, this, true);
        logger.info(String.format("WELog v%1$s by md_5 enabled", this.getDescription().getVersion()));
    }

    public void onDisable() {
        logger.info(String.format("WELog v%1$s by md_5 disabled", this.getDescription().getVersion()));
    }
}
