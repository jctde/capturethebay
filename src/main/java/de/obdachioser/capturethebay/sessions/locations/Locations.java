package de.obdachioser.capturethebay.sessions.locations;

import de.obdachioser.capturethebay.CaptureTheBay;
import de.obdachioser.capturethebay.api.Team;
import de.obdachioser.capturethebay.config.CurrentGameWorldConfiguration;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;

/**
 * Created by ObdachIoser at 15:14 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Locations {

    @Getter
    private static Location spawnLocation = Bukkit.getWorld("world").getSpawnLocation();

    @Getter
    private static CurrentGameWorldConfiguration currentGameWorldConfiguration = new CurrentGameWorldConfiguration()
            .apply((List<Team>) CaptureTheBay.getGameSession().getTeams().all0());
}
