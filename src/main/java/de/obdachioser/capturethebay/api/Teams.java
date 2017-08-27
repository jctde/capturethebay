package de.obdachioser.capturethebay.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ObdachIoser at 14:00 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Teams {

    @Getter
    private HashMap<String, DefinedTeam> teamMap = Maps.newHashMap();

    public Team createNewTeam(DefinedTeam definedTeam) {

        teamMap.put(definedTeam.getName(), definedTeam);

        return definedTeam;
    }

    public Collection<DefinedTeam> all() {
        return teamMap.values();
    }

    public List<Team> all0() {
        return Lists.newArrayList(teamMap.values());
    }

    public Team getTeam(String name) {
        return teamMap.get(name);
    }

    public Team getEmptiestTeam() {

        Team team = null;

        Integer i = 1;

        while(team == null) {

            for(Team t : teamMap.values()) if(t.size() <= i) return t;
            i++;
        }

        Bukkit.broadcastMessage("Team is null");
        return null;
    }

    public Integer getAliveTeams() {

        Integer i = 0;

        for(Team team : teamMap.values()) if(team.isAlive()) i++;
        return i;
    }

    public Team getLastAliveTeam() {
        for(Team team : teamMap.values()) if(team.isAlive()) return team;
        return null;
    }

    public DefinedTeam getDefinedTeam(String name) {
        return teamMap.get(name);
    }

    public void removeTeam(String name) {
        teamMap.remove(name);
    }
}