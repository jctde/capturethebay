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

    RED("§4", "Rot", (short) 14),
    GREEN("§2", "Grün", (short) 13),
    BLUE("§1", "Blau", (short) 11),
    YELLOW("§3", "Grün", (short) 4);

    @Getter
    private String color, colorName;

    @Getter
    private Short subId;

    TeamColor(String c, String cname, Short subid) {
        this.color = c;
        this.colorName = cname;
        this.subId = subid;
    }
}
