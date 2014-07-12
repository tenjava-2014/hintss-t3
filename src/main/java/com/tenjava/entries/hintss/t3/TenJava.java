package com.tenjava.entries.hintss.t3;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class TenJava extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("tornado").setExecutor(new TornadoCommand(this));
        // TODO - this
    }

    public void makeTornado(Location loc) {
        new Tornado(loc, getConfig().getDouble("tornado.radius"), getConfig().getDouble("tornado.speed"), getConfig().getDouble("tornado.strength"), getConfig().getInt("tornado.lifetime")).runTaskTimer(this, 5, 10);
    }

    public void makeTornado(Player p) {
        Random r = new Random();
        makeTornado(p.getLocation());
    }
}
