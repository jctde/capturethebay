package de.obdachioser.capturethebay.bay;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
    private HashMap<Bay, Block> locationHashMap = Maps.newHashMap();

    public Bay foundBay(Bay bay) {

        bayHashMap.put(bay.getName(), bay);

        Bukkit.broadcastMessage("BL: " + bay.getBlockLocation());
        Location location = new Location(bay.getBlockLocation().getWorld(), (int) bay.getBlockLocation().getX(),
                (int) bay.getBlockLocation().getY(), (int) bay.getBlockLocation().getZ());

        locationHashMap.put(bay, ((SimpleBay) bay).getBlock());
        Location location1 = location.clone();

        if(location1.getBlock().getType() == Material.BEDROCK) location1.setY(location1.getY()+1);
        else if(location1.getBlock().getType() == Material.COBBLE_WALL) location1.setY(location1.getY()-1);

        locationHashMap.put(bay, ((SimpleBay) bay).getBlock());
        return bay;
    }

    public boolean existBay(Block block) {
        return locationHashMap.containsValue(block);
    }

    public Bay captureBay(Player player, Bay bay) {

        bay.setCapturedTeam(player);
        return bay;
    }

    public Bay getBay(String name) {
        return (bayHashMap.containsKey(name) ? bayHashMap.get(name) : null);
    }

    public Bay getBay(Block block) {

        if(!locationHashMap.containsValue(block)) return null;

        for(Map.Entry<Bay, Block> entry : locationHashMap.entrySet())
            if(entry.getKey() == block) return entry.getKey();

        return null;
    }

    public boolean existBay(String name) {
        return bayHashMap.containsKey(name);
    }
}