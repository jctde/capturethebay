package de.obdachioser.capturethebay.projectlisteners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.events.TeamActionEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by ObdachIoser at 10:53 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class TeamActionListener implements Listener {

    @EventHandler
    public void teamAction(TeamActionEvent event) {

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());

        if(event.getEnumTeamAction() == TeamActionEvent.EnumTeamAction.TEAM_JOIN &&
                CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME) {
        }

        if(event.getEnumTeamAction() == TeamActionEvent.EnumTeamAction.TEAM_LEAVE &&
                CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME) {

            if(playerCache.isIngame()) {

                if(event.getTeam().size() == 0) {

                    Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§cDas Team " + ((DefinedTeam) event.getTeam()).getTeamDisplayName() + " §cwurde vernichtet!");
                    event.getTeam().removeGold(((DefinedTeam) event.getTeam()).getGold());
                    CaptureTheBay.getGameSession().getTeams().removeTeam(((DefinedTeam) event.getTeam()).getName());
                }
            }
        }
    }
}
