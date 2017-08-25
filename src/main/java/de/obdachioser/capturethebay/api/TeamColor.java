package de.obdachioser.capturethebay.api;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ObdachIoser at 14:03 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public enum TeamColor {

    RED("§4", "Rot"),
    GREEN("§3", "Grün"),
    BLUE("§1", "Blau"),
    YELLOW("§3", "Grün");

    @Getter
    private String color, colorName;

    TeamColor(String c, String cname) {
        this.color = c;
        this.colorName = cname;
    }
}
