package de.obdachioser.capturethebay.inventorys;

import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by ObdachIoser at 14:48 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class KitsInventory implements PlayerInventory {

    private Inventory inventory;
    private Player player;

    public KitsInventory(Player player) {
        this.player = player;
        this.inventory = Bukkit.createInventory(null, 9*5, "§bDeine Kits");
    }

    @Override
    public void prepare() {

        for(Integer i = 0; i != 9; i++) inventory.setItem(i, ItemStackCreator.c());
        for(Integer i = 36; i != 45; i++) inventory.setItem(i, ItemStackCreator.c());

        inventory.setItem(4, ItemStackCreator.a(Material.CHEST, "§aWähle dir dein Kit aus"));
    }

    @Override
    public Inventory get() {
        return inventory;
    }
}
