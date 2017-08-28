package de.obdachioser.capturethebay.projectlisteners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.BiObject;
import de.obdachioser.capturethebay.bay.Bay;
import de.obdachioser.capturethebay.bay.SimpleBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.events.BayBlockInteractEvent;
import de.obdachioser.capturethebay.events.BayCaptureEvent;
import de.obdachioser.capturethebay.events.BayFoundEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by ObdachIoser at 18:21 on 28.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class BayBlockInteractListener implements Listener {

    @EventHandler
    public void bayBlockInteract(BayBlockInteractEvent event) {

        Bay bay = CaptureTheBay.getGamePlaySession().getBays().getBay(event.getBedrockBlock());
        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());

        if(bay == null) {

            bay = new SimpleBay(event.getPlayer(), new BiObject<Block, Block>(event.getBedrockBlock(), event.getWallBlock()));
            CaptureTheBay.getGamePlaySession().getBays().foundBay(bay);

            Bukkit.getPluginManager().callEvent(new BayFoundEvent(event.getPlayer(), bay.getName(), ((SimpleBay) bay).getBiObject()));

        } else if(bay != null) {

            if(playerCache.getCurrentTeam().getCapturedBays().contains(bay)) {

                event.getPlayer().sendMessage(CaptureTheBay.getPrefix() + "§cDein Team besitzt diese Bucht bereits.");
                return;
            }

            bay.setCapturedTeam(event.getPlayer());
        }
    }
}
