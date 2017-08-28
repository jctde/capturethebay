package de.obdachioser.capturethebay.listeners;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.PlayerStates;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ObdachIoser at 20:42 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerDeathListener implements Listener {

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {

        if(CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getEntity().getUniqueId()).getEnumPlayerState() == EnumPlayerState.SPECTATOR)
            return;

        PlayerCache entity = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getEntity().getUniqueId());

        event.setDroppedExp(0);
        event.setNewTotalExp(0);
        event.setKeepLevel(false);

        entity.setLives(entity.getLives()-1);
        entity.setDeaths(entity.getDeaths()+1);

        Executors.newCachedThreadPool().execute(() -> {

            try {

                TimeUnit.MILLISECONDS.sleep(45L);

                ((CraftPlayer) event.getEntity()).getHandle().playerConnection
                        .a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));

            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });

        if(event.getEntity().getKiller() == null) {

            event.setDeathMessage(CaptureTheBay.getPrefix() + entity.getGameDisplayName() + " §7ist gestorben.");

            if(entity.getLives() == 0) {

                Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + entity.getGameDisplayName() + " §7ist ausgeschieden.");
                entity.getCurrentTeam().removePlayer(event.getEntity());
                entity.setEnumPlayerState(EnumPlayerState.SPECTATOR);

                PlayerStates.setSpectator(event.getEntity());
            }

            return;
        }

        PlayerCache killer = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(event.getEntity().getKiller().getUniqueId());
        killer.setKills(killer.getKills()+1);

        event.setDeathMessage(CaptureTheBay.getPrefix() + entity.getGameDisplayName() + " §7wurde von " + killer.getGameDisplayName() + " §7getötet.");

        if(entity.getLives() == 0) {

            Bukkit.broadcastMessage(CaptureTheBay.getPrefix() + entity.getGameDisplayName() + " §7ist ausgeschieden.");
            entity.getCurrentTeam().removePlayer(event.getEntity());
            entity.setEnumPlayerState(EnumPlayerState.SPECTATOR);

            PlayerStates.setSpectator(event.getEntity());
        }
    }
}
