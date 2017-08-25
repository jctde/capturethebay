package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by ObdachIoser at 20:42 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerDamageByPlayerListener implements Listener {

    @EventHandler
    public void playerDamageByPlayer(EntityDamageByEntityEvent event) {

        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {

            PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getEntity().getUniqueId());
            PlayerCache damagerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getDamager().getUniqueId());

            if(damagerCache.getCurrentTeam() == playerCache.getCurrentTeam()) {

                event.setCancelled(true);
                return;
            }

            if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME)
                event.setCancelled(false);
            else
                event.setCancelled(true);
        }

    }

}
