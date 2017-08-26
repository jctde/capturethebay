package de.obdachioser.capturethebay.sessions;

import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.api.TeamColor;
import de.obdachioser.capturethebay.api.Teams;
import de.obdachioser.capturethebay.cache.CacheConsumer;
import de.obdachioser.capturethebay.cache.CacheHandler;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.countdown.CountdownHandler;
import de.obdachioser.capturethebay.countdown.GameState;
import de.obdachioser.capturethebay.countdown.SimpleCountdownInitializer;
import de.obdachioser.capturethebay.enums.EnumInventoryType;
import de.obdachioser.capturethebay.enums.EnumPlayerState;
import de.obdachioser.capturethebay.inventorys.Inventorys;
import de.obdachioser.capturethebay.inventorys.TeamInventory;
import de.obdachioser.capturethebay.scoreboard.ScoreboardHandler;
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

    private ScoreboardHandler scoreboardHandler = new ScoreboardHandler(
            "§f§lAIOHUB.NET",
            "§1",
            " §fMap",
            " §b%map%",
            "§2",
            " §7Teams",
            "  §cVerboten",
            "§3");

    private Integer maxplayers = 15;
    private Integer minplayers = 2;

    public GameSession() {

        playerCacheCacheHandler = new CacheHandler((operator) -> {

            Player player = Bukkit.getPlayer((UUID) operator);
            return new PlayerCache(0, 0, 0, 0, 5,null,
                    "§f" + player.getName(), (currentGameState.toInteger() > 0 ? EnumPlayerState.SPECTATOR : EnumPlayerState.PLAYER));
        });

        Inventorys.getInventoryTypeInventoryHashMap().put(EnumInventoryType.TEAMS_INVENTORY, new TeamInventory());
        Inventorys.prepareAll();

        teams.createNewTeam(new DefinedTeam(TeamColor.RED));
        teams.createNewTeam(new DefinedTeam(TeamColor.BLUE));
        teams.createNewTeam(new DefinedTeam(TeamColor.GREEN));
    }

    public void startSession() {
        countdownHandler.init();
    }

    public void stopSession() {
        countdownHandler.shutdown();
    }
}
