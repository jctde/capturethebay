package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.sessions.locations.Locations;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by ObdachIoser at 15:11 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {

        CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().add(event.getPlayer().getUniqueId());

        if(CaptureTheBay.getGameSession().getCurrentGameState().toInteger() > 0) {

            event.getPlayer().sendMessage(CaptureTheBay.getPrefix() + "§7");

        } else {

            event.getPlayer().getInventory().setItem(0, ItemStackCreator.a(Material.GOLD_HELMET, "§eWähle dein Team §7§o(Rechtsklick)"));
            event.getPlayer().getInventory().setItem(8, ItemStackCreator.a(Material.SLIME_BALL, "§cZurück zur Lobby §7§o(Rechtsklick)"));
        }

        event.getPlayer().playSound(event.getPlayer().getEyeLocation(), Sound.ITEM_PICKUP, 1F, 1F);

        event.getPlayer().teleport(CaptureTheBay.getGameSession().getCurrentGameState().toInteger()
                > 0 ? Locations.getCurrentGameWorldConfiguration().getMiddleLocation() : Locations.getSpawnLocation());



    }
}
