package com.tenjava.entries.hintss.t3;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class TenJava extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("tornado").setExecutor(new TornadoCommand(this));

        // below this point I stopped giving a shit
        new BukkitRunnable() {
            @Override
            public void run() {
                makeTornado(getServer().getOnlinePlayers()[new Random().nextInt(getServer().getOnlinePlayers().length)]);
            }
        }.runTaskTimer(this, getConfig().getLong("tornado.randomdelay"), getConfig().getLong("tornado.randomdelay"));
    }

    public void makeTornado(Location loc) {
        new Tornado(loc, getConfig().getDouble("tornado.radius"), getConfig().getDouble("tornado.speed"), getConfig().getDouble("tornado.strength"), getConfig().getInt("tornado.lifetime")).runTaskTimer(this, 5, 10);
    }

    public void makeTornado(Player p) {
        Random r = new Random();
        makeTornado(p.getLocation().add(r.nextDouble() * 30 - 15, 0, r.nextDouble() * 30 - 15));
    }
}
