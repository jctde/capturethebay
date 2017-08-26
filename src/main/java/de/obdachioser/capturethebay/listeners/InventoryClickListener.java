package de.obdachioser.capturethebay.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by ObdachIoser at 01:55 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }

}
