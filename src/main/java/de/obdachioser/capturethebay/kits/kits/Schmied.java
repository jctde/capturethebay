package de.obdachioser.capturethebay.kits.kits;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.enums.EnumKitAction;
import de.obdachioser.capturethebay.kits.Kit;
import de.obdachioser.capturethebay.kits.KitContent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by ObdachIoser at 23:08 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Schmied implements Kit {

    private KitContent kitContent;
    private String name;
    private String displayName;

    public Schmied() {

        this.name = getClass().getSimpleName();
        this.displayName = "§6§lSCHMIED";
        this.kitContent = new KitContent(
                new ItemStack[] {},
                new ItemStack[] {}
        );

    }

    @Override
    public Material material() {
        return Material.IRON_INGOT;
    }

    @Override
    public KitContent getContent() {
        return this.kitContent;
    }

    @Override
    public List<EnumKitAction> actions() {
        return Lists.newArrayList();
    }

    @Override
    public List<String> description() {
        return Lists.newArrayList("no description :)");
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public Integer price() {
        return 10000;
    }
}
