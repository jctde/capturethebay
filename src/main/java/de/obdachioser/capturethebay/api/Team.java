package de.obdachioser.capturethebay.api;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by ObdachIoser at 13:59 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public interface Team {

    List<Player> playerList = Lists.newArrayList();

    void addPlayer(Player player);
    void removePlayer(Player player);
    int size();
    void exec(Consumer<Player> playerConsumer);
    Player get(Integer i);
    List<Player> getList();
    void broadcast(String msg);
}
