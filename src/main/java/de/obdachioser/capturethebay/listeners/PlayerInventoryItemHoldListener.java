package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.awt.peer.CanvasPeer;

/**
 * Created by ObdachIoser at 17:36 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerInventoryItemHoldListener implements Listener {

    @EventHandler
    public void inventoryItemHold(PlayerItemHeldEvent event) {

        if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId()).getEnumPlayerState() == EnumPlayerState.SPECTATOR) {
            event.getPlayer().setFlySpeed(Float.parseFloat("0." + event.getNewSlot()));
        }
    }
}
