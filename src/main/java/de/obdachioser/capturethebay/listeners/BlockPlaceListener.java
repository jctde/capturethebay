package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.countdown.GameState;
import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by ObdachIoser at 20:43 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class BlockPlaceListener implements Listener {

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME)
            event.setCancelled(false);
        else
            event.setCancelled(true);

    }

}
