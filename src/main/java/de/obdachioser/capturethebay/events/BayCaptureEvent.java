package de.obdachioser.capturethebay.events;

import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.bay.Bay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by ObdachIoser at 13:43 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor @Getter
public class BayCaptureEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    private Team team;
    private Player player;
    private Bay bay;

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
