package de.obdachioser.capturethebay.events;

import de.obdachioser.capturethebay.countdown.GameState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by ObdachIoser at 16:26 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor
@Getter

public class GameStateChangeEvent extends Event {

    public static HandlerList handlerList = new HandlerList();

    private GameState oldState;
    private GameState newState;

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
