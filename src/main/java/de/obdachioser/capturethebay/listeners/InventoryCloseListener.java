package de.obdachioser.capturethebay.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Created by ObdachIoser at 14:33 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class InventoryCloseListener implements Listener {

    @EventHandler
    public void playerCloseInventory(InventoryCloseEvent event) {

        if(event.getInventory().getTitle().contains("Wähle dir dein Team")) {

            ((Player) event.getPlayer()).playSound(event.getPlayer().getEyeLocation(), Sound.CHEST_CLOSE, 1F, 1F);
        }

        if(event.getInventory().getTitle().contains("Spieler die im Spiel sind")) {

            ((Player) event.getPlayer()).playSound(event.getPlayer().getEyeLocation(), Sound.CHEST_CLOSE, 1F, 1F);
        }

        if(event.getInventory().getTitle().contains("Deine Kits")) {

            ((Player) event.getPlayer()).playSound(event.getPlayer().getEyeLocation(), Sound.CHEST_CLOSE, 1F, 1F);
        }
    }
}
