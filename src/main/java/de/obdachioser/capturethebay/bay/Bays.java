package de.obdachioser.capturethebay.bay;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ObdachIoser at 13:53 on 27.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Bays {

    private HashMap<String, Bay> bayHashMap = Maps.newHashMap();
    private HashMap<Bay, Location> locationHashMap = Maps.newHashMap();

    public Bay foundBay(Bay bay) {

        bayHashMap.put(bay.getName(), bay);

        Bukkit.broadcastMessage("BL: " + bay.getBlockLocation());
        Location location = new Location(bay.getBlockLocation().getWorld(), (int) bay.getBlockLocation().getX(),
                (int) bay.getBlockLocation().getY(), (int) bay.getBlockLocation().getZ());

        locationHashMap.put(bay, location);
        Location location1 = location.clone();

        if(location1.getBlock().getType() == Material.BEDROCK) location1.setY(location1.getY()+1);
        else if(location1.getBlock().getType() == Material.COBBLE_WALL) location1.setY(location1.getY()-1);

        locationHashMap.put(bay, location1);
        return bay;
    }

    public boolean existBay(Location location) {

        Location location1 = new Location(location.getWorld(), (int) location.getX(), (int) location.getY(), (int) location.getZ());
        return locationHashMap.containsValue(location1);
    }

    public Bay captureBay(Player player, Bay bay) {
        bay.setCapturedTeam(player);
        return bay;
    }

    public Bay getBay(String name) {
        return (bayHashMap.containsKey(name) ? bayHashMap.get(name) : null);
    }

    public Bay getBay(Location location) {

        if(!locationHashMap.containsValue(location)) return null;

        for(Map.Entry<Bay, Location> entry : locationHashMap.entrySet())
            if(entry.getKey().getBlockLocation() == location) return entry.getKey();

        return null;
    }

    public boolean existBay(String name) {
        return bayHashMap.containsKey(name);
    }
}