package de.obdachioser.capturethebay.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by ObdachIoser at 13:43 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor @Getter
public class EnderChestBreaKEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    private Player player;
    private Block block;

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
