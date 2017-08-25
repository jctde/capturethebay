package de.obdachioser.capturethebay.cache.api;

import de.obdachioser.capturethebay.api.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ObdachIoser at 14:22 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor

@Getter @Setter
public class PlayerCache {

    private Integer tickets, kills, receivedChests = 0;
    private Team currentTeam = null;
    private String gameDisplayName = "";

}
