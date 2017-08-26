package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by ObdachIoser at 20:55 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class FoodLevelChangeListener implements Listener {

    @EventHandler
    public void foodLevelChange(FoodLevelChangeEvent event) {

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME)
            event.setCancelled(false);
        else
            event.setCancelled(true);

        if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getEntity().getUniqueId()).getEnumPlayerState() == EnumPlayerState.SPECTATOR)
            event.setCancelled(true);

    }

}
