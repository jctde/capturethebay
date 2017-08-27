package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
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

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME) {

            if(event.getBlock().getType() == Material.ENDER_CHEST) {

                event.setCancelled(true);
                event.getBlock().getLocation().getBlock().setType(Material.AIR);

                if(new Random().nextInt(8) == 3) {

                    PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());

                    playerCache.getCurrentTeam().broadcast(CaptureTheBay.getPrefix() + playerCache.getGameDisplayName() + "§7 hat §6Gold §7gefunden!");
                    return;
                }

                Integer d = new Random().nextInt(4);
                while(d == 0) d = new Random().nextInt(4);

                Bukkit.broadcastMessage("d.: " + d);

                for(Integer i = d; i != 0; i--) {

                    event.getBlock().getWorld().dropItem(event.getBlock().getLocation(),
                            CaptureTheBay.getGamePlaySession().getItemStackDropList().get(new Random().nextInt
                                    (CaptureTheBay.getGamePlaySession().getItemStackDropList().size())));
                }
            }

        } else
            event.setCancelled(true);

        if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId()).getEnumPlayerState() == EnumPlayerState.SPECTATOR)
            event.setCancelled(true);
    }
}