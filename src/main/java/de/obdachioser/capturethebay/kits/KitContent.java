package de.obdachioser.capturethebay.kits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ObdachIoser at 15:04 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@AllArgsConstructor
@Getter
public class KitContent {

    private ItemStack[] itemContents;
    private ItemStack[] armorContents;

}
