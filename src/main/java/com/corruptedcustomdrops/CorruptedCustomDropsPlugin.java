package com.corruptedcustomdrops;

import com.corruptedcustomdrops.command.CCDCommand;
import com.corruptedcustomdrops.config.ConfigManager;
import com.corruptedcustomdrops.listener.BlockBreakListener;
import com.corruptedcustomdrops.listener.EntityDeathListener;
import com.corruptedcustomdrops.manager.BlockDropManager;
import com.corruptedcustomdrops.manager.MobDropManager;
import com.corruptedcustomdrops.storage.JsonStorage;
import org.bukkit.plugin.java.JavaPlugin;

public class CorruptedCustomDropsPlugin extends JavaPlugin {

    private static CorruptedCustomDropsPlugin instance;
    private ConfigManager configManager;
    private JsonStorage jsonStorage;
    private MobDropManager mobDropManager;
    private BlockDropManager blockDropManager;

    @Override
    public void onEnable() {
        instance = this;

        // Create data directories
        createDataDirectories();

        // Load global config
        saveDefaultConfig();
        configManager = new ConfigManager(this);

        // Initialize storage system
        jsonStorage = new JsonStorage(this);
        jsonStorage.loadAllConfigurations();

        // Initialize managers
        mobDropManager = new MobDropManager(this, jsonStorage);
        blockDropManager = new BlockDropManager(this, jsonStorage);

        // Register listeners
        registerListeners();

        // Register commands
        registerCommands();

        getLogger().info("§a✓ CorruptedCustomDrops enabled successfully!");
    }

    @Override
    public void onDisable() {
        if (jsonStorage != null) {
            jsonStorage.saveAllConfigurations();
        }
        getLogger().info("§c✗ CorruptedCustomDrops disabled!");
    }

    private void createDataDirectories() {
        getDataFolder().mkdirs();
        new java.io.File(getDataFolder(), "mobs").mkdirs();
        new java.io.File(getDataFolder(), "blocks").mkdirs();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new EntityDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
    }

    private void registerCommands() {
        getCommand("ccd").setExecutor(new CCDCommand(this));
        getCommand("ccd").setTabCompleter(new CCDCommand(this));
    }

    public static CorruptedCustomDropsPlugin getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public JsonStorage getJsonStorage() {
        return jsonStorage;
    }

    public MobDropManager getMobDropManager() {
        return mobDropManager;
    }

    public BlockDropManager getBlockDropManager() {
        return blockDropManager;
    }
}
