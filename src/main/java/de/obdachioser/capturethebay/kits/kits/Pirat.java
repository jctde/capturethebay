package de.obdachioser.capturethebay.kits.kits;

import com.google.common.collect.Lists;
import de.obdachioser.capturethebay.enums.EnumKitAction;
import de.obdachioser.capturethebay.kits.DefaultKit;
import de.obdachioser.capturethebay.kits.Kit;
import de.obdachioser.capturethebay.kits.KitContent;
import de.obdachioser.capturethebay.kits.Kits;
import de.obdachioser.capturethebay.utils.ItemStackCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by ObdachIoser at 15:09 on 26.08.2017.
 *
 * @TODO
 * @Finished: false
 */
public class Pirat extends DefaultKit implements Kit {

    private KitContent kitContent;

    private String name = "Pirat";
    private String displayName = "§e§lPIRAT";

    public Pirat() {

        this.kitContent = new KitContent(
                new ItemStack[] {ItemStackCreator.a(Material.STONE_SWORD, "§7Piraten Schwert")},
                new ItemStack[] {null, null, ItemStackCreator.a(Material.LEATHER_CHESTPLATE, "§ePiratenschutz")}
        );
    }

    @Override
    public KitContent getContent() {
        return kitContent;
    }

    @Override
    public List<EnumKitAction> actions() {
        return Lists.newArrayList();
    }

    @Override
    public List<String> description() {
        return Lists.newArrayList(
                    " ",
                              "§7Als §cPirat §7legst du dich",
                              "§7mit der §fCrew §7gegen andere",
                              "§cPiraten §7an!",
                              " ",
                              "§7Jeder fängt klein an §8- §7Werde eine Lebende §bLegende§7!",
                               " ");
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
        return 0;
    }

    public Kit getKit() {
        return this;
    }
}
