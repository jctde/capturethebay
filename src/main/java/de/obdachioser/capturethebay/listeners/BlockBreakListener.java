package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by ObdachIoser at 20:42 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class BlockBreakListener implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME)
            event.setCancelled(false);
        else
            event.setCancelled(true);

        if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId()).getEnumPlayerState() == EnumPlayerState.SPECTATOR)
            event.setCancelled(true);

    }
}