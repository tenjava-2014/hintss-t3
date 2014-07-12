package com.tenjava.entries.hintss.t3;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

/**
 * Created by Henry on 7/12/2014.
 */
public class Tornado extends BukkitRunnable {
    Random r = new Random();

    // this shit doesn't change for the lifetime of the tornado
    // the radius in which the tornado affects blocks
    double radius;
    // the square of the above number
    double radiusSquared;
    // how far the tornado moves each update
    double speed;
    // how hard the tornado throws the blocks it hits
    double strength;

    // this shit changes during the tornado's lifetime
    // where the tornado is right now. the yaw is used for which way the tornado is currently moving
    Location loc;
    // how many more updates the tornado has to live
    int lifeTime;

    /**
     * constructor for making a new tornado. don't use it outside of this class
     * @param loc where the tornado starts out (note that we use the yaw for which way the tornado starts out going)
     * @param radius how wide an area of blocks it should break each update (3 is nice)
     * @param speed how far it moves each update (1 is nice)
     * @param strength how strongly it flings the blocks it picks up (3 is nice)
     * @param lifeTime how many updates the tornado lasts
     */
    public Tornado(Location loc, double radius, double speed, double strength, int lifeTime) {
        this.loc = loc.clone();
        this.radius = radius;
        this.speed = speed;
        this.strength = strength;

        this.lifeTime = lifeTime;

        this.radiusSquared = radius * radius;
    }

    @Override
    public void run() {
        lifeTime--;

        // TODO - randomise angle

        // move the tornado forward by an amount equal to speed
        loc.add(loc.getDirection().normalize().multiply(speed));

        // go through blocks near the highest block at where the tornado is, fling those
        for (int i = (int) -radius - 1; i < radius + 1; i++) {
            for (int j = (int) -radius - 1; j < radius + 1; j++) {
                for (int h = 0; h < 5; h++) {
                    Location potentialLoc = loc.getWorld().getHighestBlockAt(loc.clone().add(i, -h, j)).getLocation();

                    if (potentialLoc.distanceSquared(loc) <= radiusSquared) {
                        Block b = potentialLoc.getBlock();

                        if (b.getType().isSolid()) {
                            FallingBlock fb = loc.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());

                            fb.setVelocity(new Vector(r.nextFloat(), 3, r.nextFloat()));
                        }

                        b.setType(Material.AIR);
                    }
                }
            }
        }

        // TODO - do shit

        // kill tornado if it's supposed to die now
        if (lifeTime == 0) {
            this.cancel();
        }
    }
}
