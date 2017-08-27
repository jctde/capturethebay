package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by ObdachIoser at 20:42 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerDeathListener implements Listener {

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {

        if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getEntity().getUniqueId()).getEnumPlayerState() == EnumPlayerState.SPECTATOR)
            return;

        PlayerCache entity = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getEntity().getUniqueId());

        event.setDroppedExp(0);
        event.setNewTotalExp(0);
        event.setKeepLevel(false);

        entity.setLives(entity.getLives()-1);
        entity.setDeaths(entity.getDeaths()+1);

        if(entity.getLives() == 0) {

            entity.getCurrentTeam().broadcast(CaptureTheBay.getPrefix() + entity.getGameDisplayName() + " §7ist ausgeschieden.");
            entity.setEnumPlayerState(EnumPlayerState.SPECTATOR);
            entity.getCurrentTeam().removePlayer(event.getEntity());
        }

        if(event.getEntity().getKiller() == null) {

            event.setDeathMessage(CaptureTheBay.getPrefix() + entity.getGameDisplayName() + " §7ist gestorben.");
            return;
        }

        PlayerCache killer = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getEntity().getKiller().getUniqueId());
        killer.setKills(killer.getKills()+1);

        event.setDeathMessage(CaptureTheBay.getPrefix() + entity.getGameDisplayName() + " §7wurde von " + killer.getGameDisplayName() + " §7getötet.");
    }
}
