package de.obdachioser.capturethebay.bay;

import com.google.common.collect.Maps;
import de.obdachioser.capturethebay.api.BiObject;
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
    private HashMap<Bay, BiObject<Block, Block>> locationHashMap = Maps.newHashMap();

    public Bay foundBay(Bay bay) {

        SimpleBay simpleBay = (SimpleBay) bay;
        locationHashMap.put(bay, simpleBay.getBiObject());
        return bay;
    }

    public boolean existBay(Block block) {

        for(Map.Entry<Bay, BiObject<Block, Block>> entry : locationHashMap.entrySet()) {
            if(entry.getValue().getValue1() == block || entry.getValue().getValue0() == block) return true;
        }

        return false;
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

        for(Map.Entry<Bay, BiObject<Block, Block>> entry : locationHashMap.entrySet())
            if(entry.getValue().getValue0() == block || entry.getValue().getValue1() == block) return entry.getKey();

        return null;
    }

    public boolean existBay(String name) {
        return bayHashMap.containsKey(name);
    }
}