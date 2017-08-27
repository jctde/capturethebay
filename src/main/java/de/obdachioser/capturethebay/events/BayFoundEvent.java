package de.obdachioser.capturethebay.events;

import de.obdachioser.capturethebay.bay.Bay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by ObdachIoser at 14:05 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor @Getter
public class BayFoundEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    private Player player;
    private String bayName;
    private Location location;

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
