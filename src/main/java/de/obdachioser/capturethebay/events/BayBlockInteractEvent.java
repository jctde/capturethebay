package de.obdachioser.capturethebay.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by ObdachIoser at 16:34 on 28.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor

@Getter
public class BayBlockInteractEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    @Setter
    private boolean cancelled = false;

    private Player player;
    private Block clickedBlock;

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
