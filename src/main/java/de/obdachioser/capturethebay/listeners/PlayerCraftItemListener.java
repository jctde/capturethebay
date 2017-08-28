package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.Listener;

/**
 * Created by ObdachIoser at 14:12 on 28.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerCraftItemListener implements Listener {

    @EventHandler
    public void craftItem(CraftItemEvent event) {

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getWhoClicked().getUniqueId());

        if(!playerCache.isIngame()) {
            event.setCancelled(true);
        }

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.LOBBY || CaptureTheBay.getGameSession().getCurrentGameState() == GameState.END) {
            event.setCancelled(true);
        }
    }
}
