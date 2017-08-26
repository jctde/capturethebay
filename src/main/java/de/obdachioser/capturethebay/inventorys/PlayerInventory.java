package de.obdachioser.capturethebay.inventorys;

import org.bukkit.inventory.Inventory;

/**
 * Created by ObdachIoser at 14:49 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public interface PlayerInventory {

    void prepare();
    Inventory get();
}
