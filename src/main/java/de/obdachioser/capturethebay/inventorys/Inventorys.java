package de.obdachioser.capturethebay.inventorys;

import com.google.common.collect.Maps;
import de.obdachioser.capturethebay.enums.EnumInventoryType;
import lombok.Getter;

import java.util.HashMap;

/**
 * Created by ObdachIoser at 15:47 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Inventorys {

    @Getter
    private static HashMap<EnumInventoryType, DefinedInventory> inventoryTypeInventoryHashMap = Maps.newHashMap();
}
