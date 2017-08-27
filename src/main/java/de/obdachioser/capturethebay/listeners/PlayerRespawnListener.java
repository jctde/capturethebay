package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.PlayerStates;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import de.obdachioser.capturethebay.sessions.locations.Locations;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by ObdachIoser at 15:58 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void playerRespawn(PlayerRespawnEvent event) {

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());

        if(playerCache.getEnumPlayerState() == EnumPlayerState.SPECTATOR) {

            PlayerStates.setSpectator(event.getPlayer());
            event.getPlayer().sendMessage(CaptureTheBay.getPrefix() + "§cDu bist nun Spectator.");
            event.setRespawnLocation(Locations.getCurrentGameWorldConfiguration().getMiddleLocation());
            return;
        }

        event.setRespawnLocation(Locations.getCurrentGameWorldConfiguration().getTeamLocations().get(playerCache.getCurrentTeam()));
    }

}
