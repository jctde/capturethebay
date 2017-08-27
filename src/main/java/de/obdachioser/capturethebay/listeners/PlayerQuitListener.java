package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumInventoryType;
import de.obdachioser.capturethebay.inventorys.Inventorys;
import de.obdachioser.capturethebay.inventorys.TeamInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by ObdachIoser at 15:11 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {

        event.setQuitMessage(null);

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());

        if(playerCache.getCurrentTeam() != null) {

            if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME)
                playerCache.getCurrentTeam().exec(player -> player.sendMessage
                        (CaptureTheBay.getPrefix() + playerCache.getGameDisplayName() + " §7hat das Team verlassen."));

            ((TeamInventory) Inventorys.getInventoryTypeInventoryHashMap().get(EnumInventoryType.TEAMS_INVENTORY))
                    .removePlayerFromLore(event.getPlayer(), (DefinedTeam) playerCache.getCurrentTeam());

            playerCache.getCurrentTeam().removePlayer(event.getPlayer());
        }

        CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().remove(event.getPlayer().getUniqueId());

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.LOBBY || CaptureTheBay.getGameSession().getCurrentGameState() == GameState.END)
            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§f" + event.getPlayer().getName() + " §7hat das Spiel verlassen. §7[§f"
                    + (Bukkit.getOnlinePlayers().size()-1) + "§7/"+CaptureTheBay.getGameSession().getMaxplayers()+"]");
    }
}