package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.countdown.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by ObdachIoser at 14:20 on 28.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerPickupItemListener implements Listener {

    @EventHandler
    public void playerPickupItem(PlayerPickupItemEvent event) {

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.LOBBY || CaptureTheBay.getGameSession().getCurrentGameState() == GameState.END) {

            event.setCancelled(true);
            return;
        }

        if(!CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId()).isIngame()) {

            event.setCancelled(true);
            return;
        }
    }
}