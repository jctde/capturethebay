package de.obdachioser.capturethebay.inventorys;

import org.bukkit.inventory.Inventory;

/**
 * Created by ObdachIoser at 17:58 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public interface DefinedInventory {

    void prepare();
    Inventory get();
    void update();
}
