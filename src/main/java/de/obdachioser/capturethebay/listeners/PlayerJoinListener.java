package de.obdachioser.capturethebay.listeners;

import com.google.common.collect.Maps;
import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.PlayerStates;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.enums.EnumInventoryType;
import de.obdachioser.capturethebay.enums.EnumPlayerInventoryType;
import de.obdachioser.capturethebay.events.TeamActionEvent;
import de.obdachioser.capturethebay.inventorys.Inventorys;
import de.obdachioser.capturethebay.inventorys.TeamInventory;
import de.obdachioser.capturethebay.inventorys.TeleporterInventory;
import de.obdachioser.capturethebay.scoreboard.team.SimpleScoreboardTeam;
import de.obdachioser.capturethebay.scoreboard.team.Teams;
import de.obdachioser.capturethebay.sessions.locations.Locations;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by ObdachIoser at 15:11 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {

        event.setJoinMessage(null);

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().add(event.getPlayer().getUniqueId());

        playerCache.getPlayerInventoryMap().get(EnumPlayerInventoryType.PLAYER_KITS).prepare();

        if(CaptureTheBay.getGameSession().getCurrentGameState().toInteger() > 0) {

            event.getPlayer().sendMessage(CaptureTheBay.getPrefix() + "§cDu bist nun Spectator.");
            PlayerStates.setSpectator(event.getPlayer());

            for(Player player : Bukkit.getOnlinePlayers()) {

                if(playerCache.isIngame()) {

                    player.hidePlayer(event.getPlayer());
                    event.getPlayer().showPlayer(event.getPlayer());
                }
            }

        } else {

            event.getPlayer().getInventory().clear();

            event.getPlayer().getInventory().setItem(0, ItemStackCreator.a(Material.GOLD_HELMET, "§eWähle dein Team §7§o(Rechtsklick)"));
            event.getPlayer().getInventory().setItem(8, ItemStackCreator.a(Material.SLIME_BALL, "§cZurück zur Lobby §7§o(Rechtsklick)"));
            event.getPlayer().getInventory().setItem(7, ItemStackCreator.a(Material.NETHER_STAR, "§bArchievments §7§o(Rechtsklick)"));
            event.getPlayer().getInventory().setItem(4, ItemStackCreator.a(Material.CHEST, "§aKits §7§o(Rechtsklick)"));

            TeleporterInventory teleporterInventory = (TeleporterInventory) Inventorys.getInventoryTypeInventoryHashMap().get(EnumInventoryType.TELEPORTER_INVENTORY);
            teleporterInventory.callAction(TeamActionEvent.EnumTeamAction.TEAM_JOIN, event.getPlayer());

            event.getPlayer().setFlying(false);
            event.getPlayer().setAllowFlight(false);
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
            event.getPlayer().setHealth(20);
            event.getPlayer().setFoodLevel(20);

            event.getPlayer().setFlySpeed(0.2F);
            event.getPlayer().setWalkSpeed(0.2F);

            HashMap<String, String> replacements = Maps.newHashMap();
            replacements.put("%map%", Locations.getCurrentGameWorldConfiguration().getMiddleLocation().getWorld().getName());

            CaptureTheBay.getGameSession().getScoreboardHandler().setReplacements(replacements);
            CaptureTheBay.getGameSession().getScoreboardHandler().sendScoreboard(event.getPlayer());

            event.getPlayer().getInventory().setArmorContents(new ItemStack[] {
                    null, null, null, ItemStackCreator.a(Material.CHAINMAIL_HELMET, "§bSchutzhelm")});

            event.getPlayer().getActivePotionEffects().clear();

            for(Player player : Bukkit.getOnlinePlayers()) {
                player.showPlayer(event.getPlayer());
                event.getPlayer().showPlayer(event.getPlayer());
            }
        }

        CaptureTheBay.getGameSession().getScoreboardHandler().sendTestScoreboard(event.getPlayer());

        event.getPlayer().playSound(event.getPlayer().getEyeLocation(), Sound.ITEM_PICKUP, 1F, 1F);

        event.getPlayer().teleport(CaptureTheBay.getGameSession().getCurrentGameState().toInteger()
                > 0 ? Locations.getCurrentGameWorldConfiguration().getMiddleLocation() : Locations.getSpawnLocation());

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.LOBBY)
            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§f" + event.getPlayer().getName() + " §7hat das Spiel betreten. §7[§f" + Bukkit.getOnlinePlayers().size()
                    + "§7/"+CaptureTheBay.getGameSession().getMaxplayers()+"]");
    }
}