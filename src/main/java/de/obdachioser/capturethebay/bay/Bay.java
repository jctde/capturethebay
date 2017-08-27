package de.obdachioser.capturethebay.bay;

import de.obdachioser.capturethebay.api.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by ObdachIoser at 13:48 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public interface Bay {

    String getName();
    void setCapturedTeam(Player player);
    Team getCapturedTeam();
    Location getBlockLocation();
    void setGoldPrice(Integer goldPrice);
    Integer getGoldPrice();
}
