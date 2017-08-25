package de.obdachioser.capturethebay.api;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * Created by ObdachIoser at 14:00 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Teams {

    private HashMap<String, DefinedTeam> teamMap = Maps.newHashMap();

    public Team createNewTeam(DefinedTeam definedTeam) {

        teamMap.put(definedTeam.getName(), definedTeam);

        return definedTeam;
    }

    public Team getTeam(String name) {
        return teamMap.get(name);
    }

    public DefinedTeam getDefinedTeam(String name) {
        return teamMap.get(name);
    }

    public void removeTeam(String name) {
        teamMap.remove(name);
    }
}