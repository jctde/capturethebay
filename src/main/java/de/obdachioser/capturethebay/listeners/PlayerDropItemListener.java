package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by ObdachIoser at 20:39 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void playerDropItem(PlayerDropItemEvent event) {

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME)
            event.setCancelled(false);
        else {

            event.setCancelled(true);
            return;
        }

        event.getItemDrop().setPickupDelay(45);

        if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId()).getEnumPlayerState() == EnumPlayerState.SPECTATOR)
            event.setCancelled(true);
    }
}