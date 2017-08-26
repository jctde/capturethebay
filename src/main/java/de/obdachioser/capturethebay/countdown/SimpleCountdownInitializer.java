package de.obdachioser.capturethebay.countdown;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.kits.Kit;
import de.obdachioser.capturethebay.kits.Kits;
import de.obdachioser.capturethebay.sessions.locations.Locations;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * Created by ObdachIoser at 14:25 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class SimpleCountdownInitializer implements CountdownInitializer {

    private List<Integer> i = Lists.newArrayList(60, 45, 30, 15, 10, 5, 4, 3, 2, 1, (60*1), (60*2), (60*3), (60*4), (60*9), (60*14), (60*29));

    @Override
    public void handle(Integer time) {

        if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.LOBBY)
            handleLobbyState(time);
        else if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.INGAME)
            handleIngameState(time);
        else if(CaptureTheBay.getGameSession().getCurrentGameState() == GameState.END) {
            handleEndState(time);
        }
    }

    private void handleLobbyState(Integer time) {

        if(i.contains(time) && Bukkit.getOnlinePlayers().size() >= CaptureTheBay.getGameSession().getMinplayers()) {

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "Das Spiel beginnt in §e" + time + " " + (time == 1 ? "Sekunde" : "Sekunden") + "§7!");

            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getEyeLocation(), Sound.NOTE_PLING, 1F, 1F));

        } else if(time == 45 && Bukkit.getOnlinePlayers().size() < CaptureTheBay.getGameSession().getMinplayers()) {

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§cWarten auf weitere Spieler...");
            CaptureTheBay.getGameSession().getCountdownHandler().reset();

            Bukkit.getOnlinePlayers().forEach(player -> player.setLevel(0));
            return;

        } else if(time < 45 && Bukkit.getOnlinePlayers().size() < CaptureTheBay.getGameSession().getMinplayers()) {

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§cWarten auf weitere Spieler...");
            CaptureTheBay.getGameSession().getCountdownHandler().reset();

            Bukkit.getOnlinePlayers().forEach(player -> player.setLevel(0));
            return;
        }

        if(time > 45 && Bukkit.getOnlinePlayers().size() < CaptureTheBay.getGameSession().getMinplayers()) return;

        if(time == 5) {

            CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().all((id, cache) -> {

                Player player = Bukkit.getPlayer(id);

                if(player.getOpenInventory() != null) player.closeInventory();

                player.getInventory().clear();
                player.setVelocity(new Vector(0, 15, 0));

                if(cache.getKitState().getCurrentKit() == null) {

                    cache.getKitState().setCurrentKit(Kits.getDefaultKit().getKit());
                    player.sendMessage(CaptureTheBay.getPrefix() + "§7Du hast das Kit " + cache.getKitState().getCurrentKit().displayName() + " §7ausgewählt.");
                }

                if(cache.getCurrentTeam() == null) {

                    CaptureTheBay.getGameSession().getTeams().getEmptiestTeam().addPlayer(player);
                    player.sendMessage(CaptureTheBay.getPrefix() + "§7Du bist nun im Team " + ((DefinedTeam) cache.getCurrentTeam()).getTeamDisplayName() + "§7!");

                    DefinedTeam definedTeam = ((DefinedTeam) cache.getCurrentTeam());

                    player.getInventory().setArmorContents(new ItemStack[]{
                            ItemStackCreator.d(Material.LEATHER_BOOTS, definedTeam.getTeamColor(), "§7Team " + definedTeam.getTeamDisplayName()),
                            ItemStackCreator.d(Material.LEATHER_LEGGINGS, definedTeam.getTeamColor(), "§7Team " + definedTeam.getTeamDisplayName()),
                            ItemStackCreator.d(Material.LEATHER_CHESTPLATE, definedTeam.getTeamColor(), "§7Team " + definedTeam.getTeamDisplayName()),
                            player.getInventory().getHelmet()
                    });
                }
            });
        }

        if(time == 0) {

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "Alle Spieler werden teleportiert...");

            CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().all((id, cache) -> {

                Player player = Bukkit.getPlayer(id);

                player.teleport(Locations.getCurrentGameWorldConfiguration().getTeamLocations().get(cache.getCurrentTeam()));
                player.playSound(player.getEyeLocation(), Sound.ENDERMAN_TELEPORT, 1F, 1F);

                player.setLevel(0);

                Kit kit = cache.getKitState().getCurrentKit();

                player.getInventory().setArmorContents(kit.getContent().getArmorContents());
                player.getInventory().addItem(kit.getContent().getItemContents());
            });
            return;
        }

        Bukkit.getOnlinePlayers().forEach(player -> player.setLevel(time));
    }

    private void handleIngameState(Integer time) {

        Bukkit.broadcastMessage("t.: " + time);

        if(i.contains(time)) {

            Bukkit.broadcastMessage("Das Spiel endet in " + (time > 60 ? (time/60)+1 : time) + " §e"
                    + (time < 61 ? (time == 1 ? "Sekunde" : "Sekunden") : "Minuten"));

            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getEyeLocation(), Sound.NOTE_PLING, 1F, 1F));
        }
    }

    private void handleEndState(Integer time) {

        if(i.contains(time)) {

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§cDer Server startet in " + time + " " + (time == 1 ? "Sekunde" : "Sekunden") + " §cneu!");
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getEyeLocation(), Sound.NOTE_PLING, 1F, 1F));
        }
    }
}