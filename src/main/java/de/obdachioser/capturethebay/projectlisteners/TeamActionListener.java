package de.obdachioser.capturethebay.projectlisteners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.api.Teams;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumInventoryType;
import de.obdachioser.capturethebay.events.TeamActionEvent;
import de.obdachioser.capturethebay.inventorys.Inventorys;
import de.obdachioser.capturethebay.inventorys.TeleporterInventory;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME) {

            TeleporterInventory teleporterInventory = (TeleporterInventory)
                    Inventorys.getInventoryTypeInventoryHashMap().get(EnumInventoryType.TELEPORTER_INVENTORY);

            teleporterInventory.callAction(event.getEnumTeamAction(), event.getPlayer());
        }

        if(event.getEnumTeamAction() == TeamActionEvent.EnumTeamAction.TEAM_JOIN &&
                CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME) {
        }

        if(event.getEnumTeamAction() == TeamActionEvent.EnumTeamAction.TEAM_LEAVE &&
                CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME) {

            if(playerCache.isIngame()) {

                if(event.getTeam().size() == 0) {

                    Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§cTeam " + ((DefinedTeam) event.getTeam()).getTeamDisplayName() + " §cwurde vernichtet!");
                    event.getTeam().removeGold(((DefinedTeam) event.getTeam()).getGold());

                    event.getTeam().setAlive(false);
                    CaptureTheBay.getGameSession().getTeams().removeTeam(((DefinedTeam) event.getTeam()).getName());

                    Integer i = CaptureTheBay.getGameSession().getTeams().getAliveTeams();

                    if(i <= 1) {

                        if(i == 0) CaptureTheBay.getGameSession().getCountdownHandler().switchState(GameState.END);
                        if(i == 1) {

                            DefinedTeam definedTeam = (DefinedTeam) CaptureTheBay.getGameSession().getTeams().getLastAliveTeam();
                            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "Team " + definedTeam.getTeamDisplayName() + " §7hat das Spiel gewonnen!");

                            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getEyeLocation(), Sound.WITHER_DEATH, 1F, 1F));
                            CaptureTheBay.getGameSession().getCountdownHandler().switchState(GameState.END);
                        }
                    }
                }
            }
        }
    }
}