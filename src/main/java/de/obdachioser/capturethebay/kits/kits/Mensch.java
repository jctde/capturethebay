package de.obdachioser.capturethebay.kits.kits;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.enums.EnumKitAction;
import de.obdachioser.capturethebay.kits.Kit;
import de.obdachioser.capturethebay.kits.KitContent;
import de.obdachioser.capturethebay.kits.Kits;
import org.bukkit.Material;

import java.util.List;

/**
 * Created by ObdachIoser at 15:54 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Mensch implements Kit {

    private String name = "Mensch";
    private String displayName = "§b§lMENSCH";

    public Mensch() {

    }

    @Override
    public Integer price() {
        return 50000;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<String> description() {
        return Lists.newArrayList("",
                "§7Als §eMensch §7bist du",
                "§7unsichtbar und kannst",
                "§7dich unbemerkt durchschleichen",
                "");
    }

    @Override
    public List<EnumKitAction> actions() {
        return null;
    }

    @Override
    public KitContent getContent() {
        return null;
    }

    @Override
    public Material material() {
        return Material.APPLE;
    }
}
