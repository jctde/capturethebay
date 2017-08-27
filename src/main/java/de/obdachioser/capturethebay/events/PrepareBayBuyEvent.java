package de.obdachioser.capturethebay.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by ObdachIoser at 13:46 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor @Getter
public class PrepareBayBuyEvent extends Event implements Cancellable {

    private static HandlerList handlerList = new HandlerList();
    private boolean cancelled = false;



    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
