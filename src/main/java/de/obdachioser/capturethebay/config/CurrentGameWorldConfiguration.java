package de.obdachioser.capturethebay.config;

import com.google.common.collect.Maps;
import de.obdachioser.capturethebay.api.DefinedTeam;
import de.obdachioser.capturethebay.api.Team;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ObdachIoser at 15:17 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@Getter @Setter
public class CurrentGameWorldConfiguration extends LocaleGameConfiguration {

    private HashMap<Team, Location> teamLocations = Maps.newHashMap();
    private Location middleLocation;

    private String gameWorldName;

    public CurrentGameWorldConfiguration() {

        this.gameWorldName = "world";
        this.middleLocation = Bukkit.getWorld(gameWorldName).getSpawnLocation();
    }

    public void apply(List<DefinedTeam> teamList) {

        for(DefinedTeam team : teamList)
            teamLocations.put(team, locationConverter(getFileConfiguration().getString(team.getName() + ".location")));
    }

    private Location locationConverter(String value) {

        String[] split = value.split(":");
        return new Location(Bukkit.getWorld(split[0]), a(split[1]), a(split[2]), a(split[3]), b(split[4]), b(split[5]));
    }

    private double a(String d) {
        return Double.parseDouble(d);
    }

    private float b(String d) {
        return Float.parseFloat(d);
    }

}
