package de.obdachioser.capturethebay.events;

import de.obdachioser.capturethebay.api.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by ObdachIoser at 14:37 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor

@Getter
public class TeamActionEvent extends Event {

    public static HandlerList handlerList = new HandlerList();

    private Player player;
    private EnumTeamAction enumTeamAction;
    private Team team;

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public enum EnumTeamAction {

        TEAM_LEAVE(),
        TEAM_JOIN();
    }
}
