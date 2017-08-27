package de.obdachioser.capturethebay.api;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ObdachIoser at 16:02 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class PlayerStates {

    private static ExecutorService executorService = Executors.newFixedThreadPool(6);

    public static void setSpectator(Player player) {

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId());
        playerCache.setEnumPlayerState(EnumPlayerState.SPECTATOR);

        player.getInventory().clear();
        player.setVelocity(new Vector(0, 1.5, 0));

        if(player.getOpenInventory() != null)
            player.getOpenInventory().close();

        if(playerCache.getCurrentTeam() != null)
            playerCache.getCurrentTeam().removePlayer(player);

        CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().all((id, cache) -> {

            if(cache.getEnumPlayerState() == EnumPlayerState.PLAYER)
                Bukkit.getPlayer(id).hidePlayer(player);
        });

        executorService.execute(() -> {

                try {

                    TimeUnit.MILLISECONDS.sleep(550L);
                    player.setFlying(true);
                    player.setAllowFlight(true);

                } catch (Exception exc) {
                    exc.printStackTrace();
                }
        });
    }

    private static Integer i = 0;

    public static Integer getIngamePlayerSize() {

        i = 0;

        CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().allValues(cache -> {if(cache.isIngame()) i++; });
        return i;
    }
}
