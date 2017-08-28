package de.obdachioser.capturethebay.countdown;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.kits.Kit;
import de.obdachioser.capturethebay.kits.Kits;
import de.obdachioser.capturethebay.sessions.locations.Locations;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

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

                player.playSound(player.getEyeLocation(), Sound.ITEM_PICKUP, 1F, 1F);

                if(cache.getKitState().getCurrentKit() == null) cache.getKitState().setCurrentKit(Kits.getDefaultKit().getKit());
                player.sendMessage(CaptureTheBay.getPrefix() + "§7Du hast das Kit " + cache.getKitState().getCurrentKit().displayName() + " §7ausgewählt.");

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

            List<String> noAliveTeams = Lists.newArrayList();

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "Alle Spieler werden teleportiert...");

            for(Team team : CaptureTheBay.getGameSession().getTeams().all0()) {

                Bukkit.broadcastMessage("TargTeam: " + ((DefinedTeam) team).getTeamDisplayName());

                if(team.size() == 0) noAliveTeams.add(((DefinedTeam) team).getTeamDisplayName());
            }

            if(noAliveTeams.size() >= 2) {

                Team team = CaptureTheBay.getGameSession().getTeams().getEmptiestTeam();
                noAliveTeams.remove(((DefinedTeam) team).getTeamDisplayName());

                Integer d = (Bukkit.getOnlinePlayers().size()/2);

                for(Player player : Bukkit.getOnlinePlayers()) {

                    if(d != 0) d--; else break;

                    PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId());

                    if(playerCache.getCurrentTeam() != null) playerCache.getCurrentTeam().removePlayer(player);

                    playerCache.setCurrentTeam(team);
                    playerCache.getCurrentTeam().addPlayer(player);

                    player.sendMessage(CaptureTheBay.getPrefix() + "§7Du wurdest zum §eAusgleichen §7in ein anderes §eTeam §7hinzugefügt.");
                }
            }

            for(String s : noAliveTeams) Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§cTeam " + s + " §cspielt diese Runde nicht mit.");

            CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().all((id, cache) -> {

                Player player = Bukkit.getPlayer(id);

                if(player.getOpenInventory() != null) player.closeInventory();

                player.getInventory().clear();

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

    private Integer d = 120;

    private void handleIngameState(Integer time) {

        if(i.contains(time)) {

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "Das Spiel endet in §e" + (time > 60 ? (time/60)+1 : time) + " "
                    + (time < 61 ? (time == 1 ? "Sekunde" : "Sekunden") : "Minuten") + "§7!");

            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getEyeLocation(), Sound.NOTE_PLING, 1F, 1F));
        }

        if(d == 0) {

            Bukkit.broadcastMessage("FALLING BLOCK!");
            Bukkit.getScheduler().scheduleSyncDelayedTask(CaptureTheBay.getInstance(), () -> d());
        }

        d--;
    }

    private void d() {

        for(Integer i = 0; i != 6; i++) {

            Location location = new Location(Bukkit.getWorld("world"), 0, 120, 0);

            FallingBlock fallingBlock = Bukkit.getWorld("world").spawnFallingBlock(location, Material.ENDER_CHEST, (byte) 0);
            fallingBlock.setVelocity(new Vector(Double.parseDouble("0." + new Random().nextInt(10)), 0.45, Double.parseDouble("0." + new Random().nextInt(10))));
        }

        d = 120;

    }

    private void handleEndState(Integer time) {

        if(i.contains(time)) {

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§cDer Server startet in " + time + " " + (time == 1 ? "Sekunde" : "Sekunden") + " §cneu!");
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getEyeLocation(), Sound.NOTE_PLING, 1F, 1F));
        }

        if(time == 0) {

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + "§cDer Server startet jetzt neu!");

            Bukkit.shutdown();
        }
    }
}