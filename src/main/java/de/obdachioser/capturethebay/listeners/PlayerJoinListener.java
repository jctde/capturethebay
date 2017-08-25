package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.PlayerStates;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.sessions.locations.Locations;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

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

            event.getPlayer().sendMessage(CaptureTheBay.getPrefix() + "§cDu bist nun Spectator.");
            PlayerStates.setSpectator(event.getPlayer());

        } else {

            event.getPlayer().getInventory().clear();

            event.getPlayer().getInventory().setItem(0, ItemStackCreator.a(Material.GOLD_HELMET, "§eWähle dein Team §7§o(Rechtsklick)"));
            event.getPlayer().getInventory().setItem(8, ItemStackCreator.a(Material.SLIME_BALL, "§cZurück zur Lobby §7§o(Rechtsklick)"));
            event.getPlayer().getInventory().setItem(7, ItemStackCreator.a(Material.NETHER_STAR, "§bArchievments §7§o(Rechtsklick)"));
            event.getPlayer().getInventory().setItem(4, ItemStackCreator.a(Material.CHEST, "§aKits §7§o(Rechtsklick)"));

            event.getPlayer().setFlying(false);
            event.getPlayer().setAllowFlight(false);
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
            event.getPlayer().setHealth(20);
            event.getPlayer().setFoodLevel(20);

            event.getPlayer().getInventory().setArmorContents(new ItemStack[] {null, null, null, null});

            event.getPlayer().getActivePotionEffects().clear();
        }

        event.getPlayer().playSound(event.getPlayer().getEyeLocation(), Sound.ITEM_PICKUP, 1F, 1F);

        event.getPlayer().teleport(CaptureTheBay.getGameSession().getCurrentGameState().toInteger()
                > 0 ? Locations.getCurrentGameWorldConfiguration().getMiddleLocation() : Locations.getSpawnLocation());

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.LOBBY || CaptureTheBay.getGameSession().getCurrentGameState() == GameState.END)
            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§f" + event.getPlayer().getName() + " §7hat das Spiel betreten. §7[§f" + Bukkit.getOnlinePlayers().size() + "§7/15]");

    }
}
