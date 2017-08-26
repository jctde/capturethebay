package de.obdachioser.capturethebay.scoreboard.team;

import com.google.common.collect.Maps;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by ObdachIoser at 22:42 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Teams {

    private static HashMap<String, DefinedScoreboardTeam> definedScoreboardTeamHashMap = Maps.newHashMap();

    public static DefinedScoreboardTeam createTeam(String name) {

        DefinedScoreboardTeam definedScoreboardTeam = new SimpleScoreboardTeam(name);

        definedScoreboardTeamHashMap.put(name, definedScoreboardTeam);
        return definedScoreboardTeam;
    }

    public static boolean isRegistredTeam(String name) {
        return definedScoreboardTeamHashMap.containsKey(name);
    }

    public static DefinedScoreboardTeam getTeam(String name) {
        return (definedScoreboardTeamHashMap.containsKey(name) ? definedScoreboardTeamHashMap.get(name) : null);
    }

    public static void sendAllTeams(Player player) {

        for(DefinedScoreboardTeam definedScoreboardTeam : definedScoreboardTeamHashMap.values())
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(
                    ((SimpleScoreboardTeam) definedScoreboardTeam).newPacketPlayOutScoreboardTeam());

    }
}