package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import de.obdachioser.capturethebay.events.EnderChestBreaKEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

/**
 * Created by ObdachIoser at 20:42 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class BlockBreakListener implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

        event.getPlayer().updateInventory();

        if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId()).getEnumPlayerState() == EnumPlayerState.SPECTATOR) {

            event.setCancelled(true);
            return;
        }

        for(Team team : CaptureTheBay.getGameSession().getTeams().all0()) {

            if(CaptureTheBay.getCurrentGameWorldConfiguration().getTeamLocations().get(team) == event.getBlock().getLocation()) {
                event.getPlayer().sendMessage(CaptureTheBay.getPrefix()
                        + "§cDu darfst den Spawnpoint von Team " + ((DefinedTeam) team).getTeamDisplayName() +" §cnicht abbauen1");
            }
        }

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME) {

            if(event.getBlock().getType() == Material.ENDER_CHEST) {

                Bukkit.getPluginManager().callEvent(new EnderChestBreaKEvent(event.getPlayer(), event.getBlock()));
                event.setCancelled(true);
            }

        } else
            event.setCancelled(true);
    }
}