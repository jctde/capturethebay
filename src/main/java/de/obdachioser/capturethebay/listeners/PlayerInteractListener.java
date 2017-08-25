package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.countdown.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.awt.*;

/**
 * Created by ObdachIoser at 17:54 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerInteractListener implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            if(event.getItem() == null || event.getItem().getType() == Material.AIR) return;

            if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.LOBBY) {


                if(event.getItem().getType().equals(Material.GOLD_HELMET)) {

                    Bukkit.broadcastMessage("TEAMS");
                    event.setCancelled(true);
                }

                if(event.getItem().getType().equals(Material.CHEST)) {

                    Bukkit.broadcastMessage("KITS");
                    event.setCancelled(true);
                }

                if(event.getItem().getType().equals(Material.NETHER_STAR)) {

                    Bukkit.broadcastMessage("ARCHIVMENTS");
                    event.setCancelled(true);
                }

                if(event.getItem().getType().equals(Material.SLIME_BALL)) {

                    Bukkit.broadcastMessage("LEAVE");
                    event.setCancelled(true);
                }

                event.getPlayer().updateInventory();
            }
        }
    }
}