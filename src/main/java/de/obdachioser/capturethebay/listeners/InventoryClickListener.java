package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.enums.EnumInventoryType;
import de.obdachioser.capturethebay.inventorys.Inventorys;
import de.obdachioser.capturethebay.inventorys.TeamInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by ObdachIoser at 01:55 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {

        if (event.getCurrentItem() == null) return;
        if (event.getAction() == null) return;
        if (event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getInventory() == null) return;

        Player player = ((Player) event.getWhoClicked());
        event.setCancelled(true);

        if (event.getInventory().getTitle().contains("Wähle dir dein Team")) {

            if (event.getCurrentItem().getType() == Material.STAINED_GLASS_PANE ||
                    event.getCurrentItem().getType() == Material.BOOK) {

                event.setCancelled(true);
                return;
            }

            TeamInventory teamInventory = (TeamInventory) Inventorys.getInventoryTypeInventoryHashMap().get(EnumInventoryType.TEAMS_INVENTORY);

            String replace = "";

            PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId());

            if(playerCache.getCurrentTeam() == null)
                replace = " §ckein Team";
            else if(playerCache.getCurrentTeam() != null) {
                replace = " " + ((DefinedTeam) playerCache.getCurrentTeam()).getTeamDisplayName();
            }

            if (teamInventory.isInEqualsTeam(player, event.getCurrentItem())) {

                player.sendMessage(CaptureTheBay.getPrefix() + "§cDu bist bereits in dem Team.");
                player.playSound(player.getEyeLocation(), Sound.CHEST_CLOSE, 1F, 1F);
                player.closeInventory();
                event.setCancelled(true);
                return;
            }

            Boolean b = teamInventory.joinTeam(player, event.getCurrentItem());

            if (!b) {

                player.sendMessage(CaptureTheBay.getPrefix() + "§cDas Team ist bereits voll.");
                player.playSound(player.getEyeLocation(), Sound.CHEST_CLOSE, 1F, 1F);
                player.closeInventory();
                event.setCancelled(true);
                return;
            }

            player.sendMessage(CaptureTheBay.getPrefix() + "§7Du bist dem Team " + event.getCurrentItem().getItemMeta().getDisplayName() + " §7beigetreten!");
            CaptureTheBay.getGameSession().getScoreboardHandler().updateScore(player, 2, " " + event.getCurrentItem().getItemMeta().getDisplayName(), replace);
            player.playSound(player.getEyeLocation(), Sound.CHEST_CLOSE, 1F, 1F);
            player.closeInventory();
        }
    }
}