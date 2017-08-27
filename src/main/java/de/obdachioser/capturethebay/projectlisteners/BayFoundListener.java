package de.obdachioser.capturethebay.projectlisteners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.events.BayFoundEvent;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ObdachIoser at 14:31 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class BayFoundListener implements Listener {

    @EventHandler
    public void bayFound(BayFoundEvent event) {

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());
        DefinedTeam definedTeam = (DefinedTeam) playerCache.getCurrentTeam();

        Location location = event.getLocation().clone();
        location.setY(location.getY()+4);

        ItemStack itemStack = ItemStackCreator.d(Material.WOOL, definedTeam.getTeamColor());

        location.getBlock().setType(itemStack.getType());
        location.getBlock().setData(itemStack.getData().getData());

        Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "Die Bucht §e" + event.getBayName() + " §7wurde vom Team " + definedTeam.getTeamDisplayName() + " §7gefunden!");
    }
}