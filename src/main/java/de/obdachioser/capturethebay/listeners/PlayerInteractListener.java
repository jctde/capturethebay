package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.bay.Bay;
import de.obdachioser.capturethebay.bay.SimpleBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumInventoryType;
import de.obdachioser.capturethebay.enums.EnumPlayerInventoryType;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import de.obdachioser.capturethebay.inventorys.Inventorys;
import de.obdachioser.capturethebay.inventorys.TeleporterInventory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.awt.*;

/**
 * Created by ObdachIoser at 17:54 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerInteractListener implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.LOBBY ||
                CaptureTheBay.getGameSession().getCurrentGameState() == GameState.END) {

            event.setCancelled(true);
        }

        if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId()).getEnumPlayerState() == EnumPlayerState.SPECTATOR) {

            event.setCancelled(true);
            return;
        }

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            if(event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.ENDER_CHEST) {

                event.setCancelled(true);
                return;
            }

            if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME) {

                if(event.getClickedBlock() == null || event.getClickedBlock().getType() == Material.AIR) return;

                if(event.getClickedBlock().getType() == Material.BEDROCK) {

                    if(CaptureTheBay.getGamePlaySession().getBays().existBay(event.getClickedBlock().getLocation())) {

                        Bukkit.broadcastMessage("ALREADY FOUND!");
                        return;
                    }

                    Bukkit.broadcastMessage("NOT FOUND!");
                    CaptureTheBay.getGamePlaySession().getBays().foundBay(new SimpleBay(event.getPlayer(), event.getClickedBlock().getLocation()));
                }

                if(event.getClickedBlock().getType() == Material.COBBLE_WALL) {

                    if(event.getClickedBlock().getType() == Material.BEDROCK) {

                        if(CaptureTheBay.getGamePlaySession().getBays().existBay(event.getClickedBlock().getLocation())) {

                            Bukkit.broadcastMessage("ALREADY FOUND!");
                            return;
                        }

                        Bukkit.broadcastMessage("NOT FOUND!");
                        CaptureTheBay.getGamePlaySession().getBays().foundBay(new SimpleBay(event.getPlayer(), event.getClickedBlock().getLocation()));
                    }
                }
            }

            if(event.getItem() == null || event.getItem().getType() == Material.AIR) return;

            if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME) {

                PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());

                if(event.getItem().getType() == Material.COMPASS) {

                    if(playerCache.getEnumPlayerState() == EnumPlayerState.PLAYER) return;

                    TeleporterInventory teleporterInventory = (TeleporterInventory)
                            Inventorys.getInventoryTypeInventoryHashMap().get(EnumInventoryType.TELEPORTER_INVENTORY);

                    event.getPlayer().openInventory(teleporterInventory.get());
                    event.getPlayer().playSound(event.getPlayer().getEyeLocation(), Sound.CHEST_OPEN, 1F, 1F);
                    return;
                }
            }

            if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.LOBBY) {

                PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());

                if(event.getItem().getType().equals(Material.GOLD_HELMET)) {

                    event.getPlayer().playSound(event.getPlayer().getEyeLocation(), Sound.CHEST_OPEN, 1F, 1F);

                    event.getPlayer().openInventory(Inventorys.getInventoryTypeInventoryHashMap().get(EnumInventoryType.TEAMS_INVENTORY).get());
                    event.setCancelled(true);
                }

                if(event.getItem().getType().equals(Material.CHEST)) {

                    event.getPlayer().openInventory(playerCache.getPlayerInventoryMap().get(EnumPlayerInventoryType.PLAYER_KITS).get());
                    event.getPlayer().playSound(event.getPlayer().getEyeLocation(), Sound.CHEST_OPEN, 1F, 1F);
                    event.setCancelled(true);
                }

                if(event.getItem().getType().equals(Material.NETHER_STAR)) {

                    Bukkit.broadcastMessage("ARCHIVMENTS");
                    event.setCancelled(true);
                }

                if(event.getItem().getType().equals(Material.SLIME_BALL)) {

                    Bukkit.broadcastMessage("LEAVE");
                    event.setCancelled(true);
                }

                event.getPlayer().updateInventory();
            }
        }
    }
}