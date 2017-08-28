package de.obdachioser.capturethebay.projectlisteners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.events.EnderChestBreaKEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Random;

/**
 * Created by ObdachIoser at 13:45 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class EnderChestBreakListener implements Listener {

    @EventHandler
    public void enderChestBreak(EnderChestBreaKEvent event) {

        event.getBlock().getLocation().getBlock().setType(Material.AIR);

        Integer f = new Random().nextInt(24);

        if(f == 3 || f == 16 || f == 23) {

            PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());
            playerCache.getCurrentTeam().broadcast(CaptureTheBay.getPrefix() + playerCache.getGameDisplayName() + "§7 hat §6Gold §7gefunden!");
            playerCache.getCurrentTeam().addGold(1);
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

}
