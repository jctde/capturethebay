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
import de.obdachioser.capturethebay.events.BayBlockInteractEvent;
import de.obdachioser.capturethebay.inventorys.Inventorys;
import de.obdachioser.capturethebay.inventorys.TeleporterInventory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.awt.*;
import java.util.concurrent.Executors;

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

            PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getPlayer().getUniqueId());

            if(event.getItem() == null || event.getItem().getType() == Material.AIR) return;

            if(event.getItem().getType() == Material.COMPASS) {

                TeleporterInventory teleporterInventory = (TeleporterInventory)
                        Inventorys.getInventoryTypeInventoryHashMap().get(EnumInventoryType.TELEPORTER_INVENTORY);

                event.getPlayer().openInventory(teleporterInventory.get());
                event.getPlayer().playSound(event.getPlayer().getEyeLocation(), Sound.CHEST_OPEN, 1F, 1F);
            }

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

                Block bedrockBlock = null;
                Block wallBlock = null;

                if(event.getClickedBlock().getType() == Material.BEDROCK) {

                    bedrockBlock = event.getClickedBlock();

                    Location location = event.getClickedBlock().getLocation().clone();
                    location.setY(location.getY()+1);

                    if(location.getBlock().getType() != Material.COBBLE_WALL) return;

                    Bukkit.broadcastMessage("Accept: Cobble");

                    wallBlock = location.getBlock();
                }

                if(event.getClickedBlock().getType() == Material.COBBLE_WALL) {

                    wallBlock = event.getClickedBlock();

                    Location location = event.getClickedBlock().getLocation().clone();
                    location.setY(location.getY()-1);

                    if(location.getBlock().getType() != Material.BEDROCK) return;

                    Bukkit.broadcastMessage("Accept: Bedrock");

                    bedrockBlock = location.getBlock();
                }

                if(bedrockBlock != null && wallBlock != null) {

                    BayBlockInteractEvent bayBlockInteractEvent = new BayBlockInteractEvent(false, event.getPlayer(), wallBlock, bedrockBlock);
                    Bukkit.getPluginManager().callEvent(bayBlockInteractEvent);

                    Bukkit.broadcastMessage("Accept: do");

                    if(bayBlockInteractEvent.isCancelled()) {

                        event.setCancelled(true);
                        return;
                    }
                }
            }

            if(event.getItem() == null || event.getItem().getType() == Material.AIR) return;

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