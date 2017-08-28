package de.obdachioser.capturethebay.scoreboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by ObdachIoser at 14:31 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class ScoreboardHandler {


    @Getter
    private String scoreboardName;

    @Getter
    private List<String> index;

    private Scoreboard scoreboard;
    private ScoreboardObjective scoreboardObjective;

    private List<ScoreboardScore> scoreboardScores = Lists.newArrayList();

    @Getter
    @Setter
    private HashMap<String, String> replacements = Maps.newHashMap();

    public ScoreboardHandler(String name, String... index) {

        this.scoreboardName = name;
        this.index = Lists.newArrayList(index);

        initializeScoreboard();
    }

    private void initializeScoreboard() {

        this.scoreboard = new Scoreboard();
        this.scoreboardObjective = scoreboard.registerObjective("scoreboard", IScoreboardCriteria.b);

        scoreboardObjective.setDisplayName(scoreboardName);
    }

    public void sendScoreboard(Player player) {

        PacketPlayOutScoreboardObjective packetPlayOutScoreboardObjectiveRemovePacket
                = new PacketPlayOutScoreboardObjective(scoreboardObjective, 1);

        PacketPlayOutScoreboardObjective packetPlayOutScoreboardObjectiveCreatePacket
                = new PacketPlayOutScoreboardObjective(scoreboardObjective, 0);

        PacketPlayOutScoreboardDisplayObjective packetPlayOutScoreboardDisplayObjective
                = new PacketPlayOutScoreboardDisplayObjective(1, scoreboardObjective);


        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutScoreboardObjectiveRemovePacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutScoreboardObjectiveCreatePacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutScoreboardDisplayObjective);

        sendScoreboardScores(player);
    }

    private Integer i;

    public void sendScoreboardScores(Player player) {

        List<String> ind = replaceReplacements();

        Integer i = ind.size();

        for (String s : ind) {

            ScoreboardScore scoreboardScore = new ScoreboardScore(scoreboard, scoreboardObjective, s);
            scoreboardScore.setScore(i);

            PacketPlayOutScoreboardScore packetPlayOutScoreboardScore = new PacketPlayOutScoreboardScore(scoreboardScore);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutScoreboardScore);

            i--;
        }
    }

    public void sendTestScoreboard(Player player) {

        ScoreboardTeam scoreboardTeam = new ScoreboardTeam(scoreboard, "");
        scoreboardTeam.setPrefix("§fDu bist §f§l");
        scoreboardTeam.setSuffix("§f!");
        scoreboardTeam.b(ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS);

        scoreboardTeam.getPlayerNameSet().add(player.getName());

        PacketPlayOutScoreboardTeam packetPlayOutScoreboardTeam = new PacketPlayOutScoreboardTeam(scoreboardTeam, 0);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutScoreboardTeam);
    }

    public void updateScore(Player player, Integer i, String newScore, String old) {

        ScoreboardScore scoreboardScore = new ScoreboardScore(scoreboard, scoreboardObjective, newScore);
        scoreboardScore.setScore(i);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutScoreboardScore(scoreboardScore));
    }

    private List<String> replaceReplacements() {

        if (replacements.isEmpty()) return index;

        List<String> returnType = Lists.newArrayList();

        for (String string : index) {
            for (Map.Entry<String, String> entry : replacements.entrySet()) {

                if (string.contains(entry.getKey()))
                    string = string.replace(entry.getKey(), entry.getValue());
            }

            returnType.add(string);
        }

        return returnType;
    }
}