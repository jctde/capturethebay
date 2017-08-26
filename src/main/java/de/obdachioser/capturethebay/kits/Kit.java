package de.obdachioser.capturethebay.kits;

import de.obdachioser.capturethebay.enums.EnumKitAction;
import org.bukkit.Material;

import java.util.List;

/**
 * Created by ObdachIoser at 15:04 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public interface Kit {

    List<EnumKitAction> actions();
    List<String> description();
    KitContent getContent();
    String name();
    String displayName();
    Integer price();
    Material material();
}
