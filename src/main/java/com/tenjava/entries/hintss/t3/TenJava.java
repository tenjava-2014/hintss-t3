package com.tenjava.entries.hintss.t3;

import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("tornado").setExecutor(new TornadoCommand(this));
        // TODO - this
    }
}
