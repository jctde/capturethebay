package de.obdachioser.capturethebay.sessions;

import de.obdachioser.capturethebay.api.Teams;
import de.obdachioser.capturethebay.cache.CacheConsumer;
import de.obdachioser.capturethebay.cache.CacheHandler;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.CountdownHandler;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.countdown.SimpleCountdownInitializer;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sun.misc.Cache;

import java.util.UUID;

/**
 * Created by ObdachIoser at 13:25 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@Getter @Setter
public class GameSession {

    private CacheHandler<UUID, PlayerCache> playerCacheCacheHandler = null;

    private Teams teams = new Teams();
    private CountdownHandler countdownHandler = new CountdownHandler(GameState.LOBBY, new SimpleCountdownInitializer());

    private GameState currentGameState = GameState.LOBBY;

    public GameSession() {

        playerCacheCacheHandler = new CacheHandler((operator) -> {

            Player player = Bukkit.getPlayer((UUID) operator);
            return new PlayerCache(0, 0, 0, 0, 5,null,
                    "§f" + player.getName(), (currentGameState.toInteger() > 0 ? EnumPlayerState.SPECTATOR : EnumPlayerState.PLAYER));
        });
    }

    public void startSession() {
        countdownHandler.init();
    }

    public void stopSession() {
        countdownHandler.shutdown();
    }
}
