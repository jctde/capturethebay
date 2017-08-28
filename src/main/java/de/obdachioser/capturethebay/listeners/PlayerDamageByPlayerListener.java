package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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

    @EventHandler (priority = EventPriority.HIGH)
    public void playerDamageByPlayer(EntityDamageByEntityEvent event) {

        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {

            PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(((Player) event.getEntity()).getUniqueId());
            PlayerCache damagerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(((Player) event.getDamager()).getUniqueId());

            Bukkit.broadcastMessage("p: " + playerCache.getCurrentTeam());
            Bukkit.broadcastMessage("d: " + damagerCache.getCurrentTeam());

            if(!damagerCache.isIngame()) {

                Bukkit.broadcastMessage("is spect");
                event.setCancelled(true);
            }

            if(playerCache.getCurrentTeam() != null && damagerCache.getCurrentTeam() != null) {

                if(((DefinedTeam) playerCache.getCurrentTeam()).getTeamDisplayName().equals(((DefinedTeam) damagerCache.getCurrentTeam()).getTeamDisplayName())) {

                    Bukkit.broadcastMessage("is equals team");
                    event.setCancelled(true);
                }
            }

            if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME && !event.isCancelled())
                event.setCancelled(false);
            else
                event.setCancelled(true);
        }
    }

}
