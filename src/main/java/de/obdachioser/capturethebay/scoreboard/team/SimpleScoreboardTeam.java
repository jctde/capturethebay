package de.obdachioser.capturethebay.scoreboard.team;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.scoreboard.Scoreboards;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.ScoreboardTeam;
import net.minecraft.server.v1_8_R3.ScoreboardTeamBase;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import java.util.List;

/**
 * Created by ObdachIoser at 22:43 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */

public class SimpleScoreboardTeam implements DefinedScoreboardTeam {

    private ScoreboardTeam scoreboardTeam;
    private PacketPlayOutScoreboardTeam packetPlayOutScoreboardTeam;

    private String name;
    private String prefix = "§f";
    private String suffix = "§f";

    public SimpleScoreboardTeam(String name) {

        this.name = name;

        this.scoreboardTeam = new ScoreboardTeam(Scoreboards.getScoreboard(), name);
        this.scoreboardTeam.setPrefix(prefix);
        this.scoreboardTeam.setSuffix(suffix);
        this.scoreboardTeam.b(ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS);
    }

    public PacketPlayOutScoreboardTeam newPacketPlayOutScoreboardTeam() {
        return packetPlayOutScoreboardTeam = new PacketPlayOutScoreboardTeam(scoreboardTeam, 0);
    }

    public void broadcast(Packet packet) {
        Bukkit.getOnlinePlayers().forEach(player -> ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet));
    }

    @Override
    public void add(String player) {

        this.scoreboardTeam.getPlayerNameSet().add(player);

        this.broadcast(this.newPacketPlayOutScoreboardTeam());
    }

    @Override
    public void remove(String player) {

        this.scoreboardTeam.getPlayerNameSet().remove(player);

        this.broadcast(this.newPacketPlayOutScoreboardTeam());
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.scoreboardTeam.setPrefix(prefix);
    }

    @Override
    public void setSuffix(String suffix) {
        this.suffix = suffix;
        this.scoreboardTeam.setSuffix(suffix);
    }

    @Override
    public List<String> getPlayers() {
        return Lists.newArrayList(scoreboardTeam.getPlayerNameSet());
    }
}
