package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
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

    }

}
