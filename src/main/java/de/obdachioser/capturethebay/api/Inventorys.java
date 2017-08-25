package de.obdachioser.capturethebay.api;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

/**
 * Created by ObdachIoser at 15:47 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Inventorys {

    @Getter
    private static HashMap<EnumInventoryType, Inventory> inventoryTypeInventoryHashMap = Maps.newHashMap();

}
