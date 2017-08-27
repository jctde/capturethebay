package de.obdachioser.capturethebay.projectlisteners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.events.GameStateChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import java.util.Random;

/**
 * Created by ObdachIoser at 01:23 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class GameStateChangeListener implements Listener {

    @EventHandler
    public void gameState(GameStateChangeEvent event) {

        Bukkit.broadcastMessage("Called change: Old: " + event.getOldState().name() + " : New: " + event.getNewState().name());

        if(event.getNewState() == GameState.INGAME)
            Bukkit.getScheduler().scheduleSyncDelayedTask(CaptureTheBay.getInstance(), () -> d());
    }

    private void d() {

        Random random = new Random();

        for(Integer i = 0; i != 32; i++) {

            Location location = new Location(Bukkit.getWorld("world"), 0, 120, 0);


            FallingBlock fallingBlock = Bukkit.getWorld("world").spawnFallingBlock(location, Material.ENDER_CHEST, (byte) 0);
            fallingBlock.setVelocity(new Vector(
                    Double.parseDouble(random.nextInt(3) + "." + new Random().nextInt(5)),
                    1,
                    Double.parseDouble(random.nextInt(3) + "." + new Random().nextInt(5))));
        }
    }
}