package de.obdachioser.capturethebay.api;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.bay.Bay;
import de.obdachioser.capturethebay.cache.api.PlayerCache;
import de.obdachioser.capturethebay.events.TeamActionEvent;
import de.obdachioser.capturethebay.sessions.GameSession;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Created by ObdachIoser at 14:01 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@Getter
public class DefinedTeam implements Team {

    private String name = null;
    private TeamColor teamColor = null;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    private List<Bay> capturedBays = Lists.newArrayList();

    private Integer gold = 5;

    private List<Player> playerList = Lists.newArrayList();

    private boolean alive = true;

    private String teamDisplayName;

    public DefinedTeam(TeamColor teamColor) {

        this.teamColor = teamColor;
        this.name = teamColor.getColorName();
        this.teamDisplayName = teamColor.getColor() + teamColor.getColorName();
    }

    @Override
    public void addPlayer(Player player) {

        playerList.add(player);

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId());
        playerCache.setCurrentTeam(this);

        playerCache.setGameDisplayName(teamDisplayName + " §7| " + teamColor.getColor() + player.getName());
        Bukkit.getPluginManager().callEvent(new TeamActionEvent(player, TeamActionEvent.EnumTeamAction.TEAM_JOIN, this));
    }

    @Override
    public int size() {
        return playerList.size();
    }

    @Override
    public void removePlayer(Player player) {

        playerList.remove(player);

        PlayerCache playerCache = CaptureTheBay.getGameSession().getPlayerCacheCacheHandler().get(player.getUniqueId());
        playerCache.setCurrentTeam(null);

        playerCache.setGameDisplayName("§f" + player.getName());
        Bukkit.getPluginManager().callEvent(new TeamActionEvent(player, TeamActionEvent.EnumTeamAction.TEAM_LEAVE, this));
    }

    @Override
    public Player get(Integer i) {
        return playerList.get(i);
    }

    @Override
    public List<Player> getList() {
        return playerList;
    }

    @Override
    public void broadcast(String msg) {
        playerList.forEach(player -> player.sendMessage(msg));
    }

    public boolean hasGold(Integer d) {
        return d >= gold;
    }

    @Override
    public void addGold(Integer d) {
        gold += d;
    }

    @Override
    public void removeGold(Integer d) {
        gold -= d;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(Boolean b) {
        this.alive = b;
    }

    @Override
    public List<Bay> getCapturedBays() {
        return capturedBays;
    }
}
